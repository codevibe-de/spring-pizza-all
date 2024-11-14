package summer;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import pizza.DataLoader;
import pizza.customer.CustomerService;
import pizza.product.HashMapProductRepository;
import pizza.product.ProductRepository;
import pizza.product.ProductService;
import summer.exception.BeansException;
import summer.exception.NoSuchBeanDefinitionException;
import summer.exception.NoUniqueBeanDefinitionException;

import java.io.Closeable;
import java.util.Map;
import java.util.Set;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BeanContainerTest {

    private final BeanContainer beanContainer = new BeanContainer();

    @Test
    void getBean() {
        // given
        beanContainer.defineBean("prodRepo", HashMapProductRepository.class);
        beanContainer.refresh();

        // when
        ProductRepository productRepository = beanContainer.getBean(ProductRepository.class);

        // then
        Assertions.assertThat(productRepository).isNotNull();
        Assertions.assertThat(productRepository).isInstanceOf(HashMapProductRepository.class);
    }


    @Test
    void getBean__NoUniqueBeanDefinitionException() {
        // given
        beanContainer.defineBean("prodRepo", HashMapProductRepository.class);
        beanContainer.defineBean("prodSrv", ProductService.class);
        beanContainer.defineBean("customerSrv", CustomerService.class);
        beanContainer.defineBean("sample", DataLoader.Sample.class);
        beanContainer.defineBean("none", DataLoader.None.class);
        beanContainer.refresh();

        // when
        ThrowingCallable callable = () -> beanContainer.getBean(Runnable.class);

        // then
        Assertions.assertThatThrownBy(callable).isInstanceOf(NoUniqueBeanDefinitionException.class);
    }


    @Test
    void refresh__failsForCircularDependencies() {
        // when
        beanContainer.defineBean("beanX", X.class);
        beanContainer.defineBean("beanY", Y.class);

        // given
        ThrowingCallable throwingCallable = beanContainer::refresh;

        // when
        assertThatThrownBy(throwingCallable)
                .hasMessageContaining("Circular dependency detected")
                .isInstanceOf(BeansException.class);
    }


    @Test
    void resolveBeanName_NoSuchBeanDefinitionException() {
        // given
        final Class<?> requestedType = String.class;

        // when
        Executable executable = () -> beanContainer.resolveBeanName(requestedType);

        // then
        assertThrows(NoSuchBeanDefinitionException.class, executable);
    }


    @Test
    void resolveBeanName_NoUniqueBeanDefinitionException() {
        // given
        beanContainer.defineBean("name1", String.class);
        beanContainer.defineBean("name2", String.class);

        // when
        Executable executable = () -> beanContainer.resolveBeanName(String.class);

        // then
        assertThrows(NoUniqueBeanDefinitionException.class, executable);
    }


    @Test
    void resolveBeanName() {
        // given
        final Class<?> requestedType = String.class;
        beanContainer.defineBean("name", String.class);
        beanContainer.defineBean("decoy1", Integer.class);
        beanContainer.defineBean("decoy2", StringBuilder.class);

        // when
        String result = beanContainer.resolveBeanName(requestedType);

        // then
        assertEquals("name", result);
    }


    @Test
    void resolveBeanNames() {
        // given
        Class<?>[] types = new Class<?>[]{CharSequence.class, A.class, AutoCloseable.class};
        beanContainer.defineBean("a", A.class);
        beanContainer.defineBean("sb", StringBuilder.class);
        beanContainer.defineBean("c", C.class);

        // when
        var beanNames = beanContainer.resolveBeanNames(types);

        // then
        assertThat(beanNames).containsExactly("sb", "a", "c");
    }


    @Test
    void createBeanDependencyMap() {
        // given
        beanContainer.defineBean("a", A.class);
        beanContainer.defineBean("b", B.class);
        beanContainer.defineBean("c", C.class);
        beanContainer.defineBean("d", D.class);

        // when
        Map<String, Set<String>> dependencyMap = beanContainer.createBeanDependencyMap();

        // then
        assertThat(dependencyMap).contains(
                entry("a", Set.of("b", "c", "d")),
                entry("b", Set.of()),
                entry("c", Set.of("d")),
                entry("d", Set.of())
        );
    }
}


// some test records requiring other beans

record A(B b, Runnable r, AutoCloseable ac) {
}

record B() {
}

record C(D d) implements Closeable {
    @Override
    public void close() {
    }
}

record D() implements Runnable {
    @Override
    public void run() {
    }
}


// circular dependent records:

record X(Y y) {
}

record Y(X x) {
}