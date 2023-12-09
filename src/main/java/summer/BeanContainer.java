package summer;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanContainer {

    // --- fields ---

    private final List<BeanDefinition> beanDefinitions = new ArrayList<>();

    private final Map<Class<?>, List<Object>> beansByTypeMap = new HashMap<>();

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
        return beanClass.getConstructors()[0];
    }

}
