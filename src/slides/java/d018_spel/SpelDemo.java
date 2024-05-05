package d018_spel;

import d017_resources.CreateEnvironment;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.context.expression.EnvironmentAccessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.io.IOException;

public class SpelDemo {

    public static void main(String[] args) throws IOException {
        useSimpleEvaluationContextWithEnvironment();
    }

    static void useStandardEvaluationContext() {
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


    static void useSimpleEvaluationContextWithEnvironment() throws IOException {
        var environment = CreateEnvironment.createStandardEnvironment();
        var parser = new SpelExpressionParser();
        var evaluationContext = SimpleEvaluationContext
                .forPropertyAccessors(new EnvironmentAccessor())
                .build();

        System.out.println(parser
                .parseExpression("${temp}")
                .getValue(evaluationContext, environment)
        );
    }
}
