package mola;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanContainer {

    private final List<BeanDefinition> beanDefinitions = new ArrayList<>();

    private final Map<Class<?>, List<Object>> beansByTypeMap = new HashMap<>();

    public BeanContainer() {
    }

    public void defineBean(String id, Class<?> beanClass) {
        this.beanDefinitions.add(
                new BeanDefinition(id, beanClass)
        );
    }

    public void refresh() {
        this.beansByTypeMap.clear();
        var queue = new ArrayList<>(beanDefinitions);
        while (queue.size() > 0) {
            try {
                var beanDefinition = queue.get(0);
                Constructor<?> constructor = findConstructor(beanDefinition.getBeanClass());
                constructor.newInstance();
            } catch (Exception e) {
                System.out.println("Failed to create bean:");
                e.printStackTrace();
            }
        }
    }

    private Constructor<?> findConstructor(Class<?> beanClass) {
        return beanClass.getConstructors()[0];
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> requiredType) {
        List<Object> beansList = beansByTypeMap.get(requiredType);
        switch (beansList.size()) {
            case 0 -> throw new NoSuchBeanDefinitionException(requiredType);
            case 1 -> {
                return (T) beansList.get(0);
            }
            default -> throw new NoUniqueBeanDefinitionException(requiredType);
        }
    }

}
