package mola;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanContainer {

    private final List<BeanDefinition> beanDefinitions = new ArrayList<>();

    private final Map<Class<?>, List<Object>> beansByTypeMap = new HashMap<>();

    private final Map<String, Object> beansByIdMap = new HashMap<>();

    public BeanContainer() {
    }

    public void defineBean(String id, Class<?> beanClass) {
    }

    public void refresh() {
    }

    public <T> T getBean(Class<T> requiredType) {
        return null;
    }

    private Constructor<?> findConstructor(Class<?> beanClass) {
        return beanClass.getConstructors()[0];
    }

}
