package summer.exception;

public class BeanDefinitionStoreException extends BeansException {

    public BeanDefinitionStoreException(String msg) {
        super(msg);
    }

    public BeanDefinitionStoreException(String msg, Throwable t) {
        super(msg, t);
    }
}
