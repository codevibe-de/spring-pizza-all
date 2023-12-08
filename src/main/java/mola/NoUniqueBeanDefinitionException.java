package mola;

public class NoUniqueBeanDefinitionException extends BeansException {
    public <T> NoUniqueBeanDefinitionException(Class<T> type) {
        super("More than one bean of type " + type + " found");
    }
}
