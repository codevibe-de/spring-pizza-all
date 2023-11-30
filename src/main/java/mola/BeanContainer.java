package mola;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanContainer {

    private final List<BeanDefinition> beanDefinitions = new ArrayList<>();

    private final Map<String, Object> beans = new HashMap<>();

    public BeanContainer() {
    }

    public void defineBean(String id, Class<?> beanClass) {
        this.beanDefinitions.add(
                new BeanDefinition(id, beanClass)
        );
    }

    public void refresh() {
        this.beans.clear();
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

    public <T> T getBean(Class<T> ofClass) {
        return null;
    }

}
