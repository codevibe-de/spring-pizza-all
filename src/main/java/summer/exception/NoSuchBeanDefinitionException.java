package summer.exception;

public class NoSuchBeanDefinitionException extends BeansException {

    public <T> NoSuchBeanDefinitionException(Class<?> type) {
        super("No bean of type " + type + " found.");
    }

    public NoSuchBeanDefinitionException(String name) {
        super("No bean with name '" + name + "' found.");
    }

}
