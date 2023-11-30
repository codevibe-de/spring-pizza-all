package mola;

public class BeanDefinition {

    private final String id;
    private final Class<?> beanClass;

    public BeanDefinition(String id, Class<?> beanClass) {
        this.id = id;
        this.beanClass = beanClass;
    }

    public String getId() {
        return id;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }
}
