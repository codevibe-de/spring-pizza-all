package scratch;

import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpelDemo {

    public static void main(String[] args) {
        var parser = new SpelExpressionParser();
        var beanContainer = new ClassPathXmlApplicationContext("beans/default-beans.xml");

        var evaluationContext = new StandardEvaluationContext();
        evaluationContext.setBeanResolver(new BeanFactoryResolver(beanContainer));
        // read property "someStr" from a bean named "dummy1"
        System.out.println(parser
                .parseExpression("@dummy1.someStr")
                .getValue(evaluationContext)
        );
    }
}
