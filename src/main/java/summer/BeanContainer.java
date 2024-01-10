package summer;

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
    }

    public void refresh() {
    }

    public <T> T getBean(Class<T> requiredType) {
        return null;
    }

    public <T> T getBean(String name, Class<T> requiredType) {
        return null;
    }

    public Object getBean(String name) {
        return null;
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