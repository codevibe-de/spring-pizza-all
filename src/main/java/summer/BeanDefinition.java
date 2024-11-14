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

    /**
     * Returns {@code true}, if the bean created by this {@code BeanDefinition} could be assigned to
     * the given type, i.e., satisfies the given type requirement.
     * <p>Example: You have a bean definition for type {@code MyRunnable}. That class implements
     * {@code Runnable}. The bean
     * created from this definition could be assigned to both a variable of type {@code MyRunnable}
     * and {@code Runnable} (and even {@code Object}). Hence, this bean definition would satisfy the
     * required types {@code MyRunnable.class}, {@code Runnable.class} and {@code Object.class}.
     */
    public boolean satisfies(Class<?> requiredType) {
        // "isAssignableFrom" is hard to read/understand. It basically means if it is okay to write:
        // var <requiredType> = new <this.type>()
        return requiredType.isAssignableFrom(type);
    }
}
