package summer;

import summer.exception.BeanInitializationException;
import summer.exception.BeansException;
import summer.exception.NoSuchBeanDefinitionException;
import summer.exception.NoUniqueBeanDefinitionException;

import java.lang.reflect.Constructor;
import java.util.*;

public class BeanContainer {

    // --- fields ---

    private final List<BeanDefinition> beanDefinitions = new ArrayList<>();

    private final Map<String, Object> beansByNameMap = new HashMap<>();

    // --- bean container business logic ---

    public void defineBean(String name, Class<?> beanClass) {
        beanDefinitions.add(new BeanDefinition(name, beanClass));
    }

    public void refresh() {
        beansByNameMap.clear();
        // we uee the dependency-map for two things:
        // 1) each map key tells us, which bean we still need to create
        // 2) each map value is a list of bean names, which do not exist yet. Hence, we can only
        // start with creating beans that have an empty list. With each new bean we thin out these
        // lists to make other beans creatable since their dependencies now exist
        Map<String, Set<String>> workToDoMap = createBeanDependencyMap(beanDefinitions);
        while (!workToDoMap.isEmpty()) {
            // what bean to create/realize next? must have an empty set of missing dependencies
            var beanName = workToDoMap.entrySet().stream()
                    .filter(e -> e.getValue().isEmpty())
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse(null);
            // got one?
            if (beanName == null) {
                throw new BeansException("Circular dependency detected, unfinished beans: " + workToDoMap.keySet());
            } else {
                createBean(beanName);
                // remove the newly created bean from our to-do list
                workToDoMap.remove(beanName);
                // remove the new bean from each others bean's missing list
                workToDoMap.values().forEach(set -> set.remove(beanName));
            }
        }
    }

    private void createBean(String name) {
        try {
            BeanDefinition def = getBeanDefinition(name);
            var constr = findConstructor(def.getType());
            var constrParamTypes = constr.getParameterTypes();
            var constrParamBeanNames = resolveBeanNames(constrParamTypes, beanDefinitions);
            var beans = getBeans(constrParamBeanNames);
            Object bean = constr.newInstance(beans);
            registerBean(bean, def.getName());
        } catch (Exception e) {
            throw new BeanInitializationException(name, e);
        }
    }

    private void registerBean(Object bean, String name) {
        beansByNameMap.put(name, bean);
    }

    private BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitions.stream().filter(def -> def.getName().equals(beanName))
                .findAny().orElseThrow(() -> new NoSuchBeanDefinitionException("beanName"));
    }

    public <T> T getBean(Class<T> requiredType) {
        var beanName = resolveBeanName(requiredType, this.beanDefinitions);
        return getBean(beanName, requiredType);
    }

    public <T> T getBean(String name, Class<T> requiredType) {
        return (T) getBean(name);
    }

    public Object getBean(String name) {
        return this.beansByNameMap.get(name);
    }

    public Object[] getBeans(String[] names) {
        return Arrays.stream(names)
                .map(this::getBean)
                .toArray();
    }

    // --- internal helper methods ---

    private Constructor<?> findConstructor(Class<?> beanClass) {
        var constructors = beanClass.getDeclaredConstructors();
        if (constructors.length != 1) {
            throw new IllegalStateException("Class " + beanClass.getName() + " does not provide a single constructor");
        }
        return constructors[0];
    }

    private Class<?>[] findConstructorParameterTypes(Class<?> type) {
        Constructor<?> constructor = findConstructor(type);
        return constructor.getParameterTypes();
    }

    Map<String, Set<String>> createBeanDependencyMap(Collection<BeanDefinition> defs) {
        var map = new HashMap<String, Set<String>>();
        for (var def : defs) {
            Class<?>[] constructorParamTypes = findConstructorParameterTypes(def.getType());
            String[] constructorParamBeanNames = resolveBeanNames(constructorParamTypes, defs);
            map.put(def.getName(), new HashSet<>(Arrays.asList(constructorParamBeanNames)));
        }
        return map;
    }

    String[] resolveBeanNames(Class<?>[] types, Collection<BeanDefinition> defs) {
        var names = new String[types.length];
        for (int n = 0; n < types.length; n++) {
            names[n] = resolveBeanName(types[n], defs);
        }
        return names;
    }


    String resolveBeanName(Class<?> type, Collection<BeanDefinition> defs) {
        // we want to find all bean-definitions (and use their name) that satisfy the given
        // type, i.e. that implement that type
        var candidateNames = defs.stream()
                .filter(def -> def.satisfies(type))
                .map(BeanDefinition::getName)
                .toList();
        return switch (candidateNames.size()) {
            case 0 -> throw new NoSuchBeanDefinitionException(type);
            case 1 -> candidateNames.get(0);
            default -> throw new NoUniqueBeanDefinitionException(type);
        };
    }

}