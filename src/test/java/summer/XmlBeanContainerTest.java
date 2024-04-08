package summer;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import pizza.product.ProductService;
import summer.exception.BeansException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class XmlBeanContainerTest {

    @Test
    void init__missingResource() {
        // when
        ThrowableAssert.ThrowingCallable callable = () -> new XmlBeanContainer("i-dont-exist");

        // then
        assertThatThrownBy(callable)
                .isInstanceOf(BeansException.class);
    }


    @Test
    void init() {
        // given
        var container = new XmlBeanContainer("/test-beans.xml");

        // then
        assertThat(container.getBean(ProductService.class)).isNotNull();
    }
}