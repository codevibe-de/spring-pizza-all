package summer.exception;

public class BeanInitializationException extends BeansException {
    public BeanInitializationException(String beanName, Throwable cause) {
        super("Failed to initialize bean '" + beanName + "'", cause);
    }
}
