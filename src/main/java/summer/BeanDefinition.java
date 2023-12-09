package summer;

public final class BeanDefinition {

    private final String name;
    private final Class<?> type;

    public BeanDefinition(String name, Class<?> type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Class<?> getType() {
        return type;
    }
}
