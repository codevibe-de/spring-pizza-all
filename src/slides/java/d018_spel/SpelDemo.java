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
//        useStandardEvaluationContext();
        useSimpleEvaluationContextWithEnvironment();
    }

    static void useStandardEvaluationContext() throws IOException {
        var parser = new SpelExpressionParser();
        var beanContainer = new ClassPathXmlApplicationContext("spel-beans.xml");

        var evaluationContext = new StandardEvaluationContext();
        evaluationContext.setBeanResolver(new BeanFactoryResolver(beanContainer));
        evaluationContext.addPropertyAccessor(new EnvironmentAccessor());

        // read property "someStr" from a bean named "dummy1"
        System.out.println(parser
                .parseExpression("@dummy1.someStr")
                .getValue(evaluationContext)
        );

        // evaluate a property expression -- this is done against the environment using the index
        // operator
        // TODO how to use ${app.my-value} in SpEL expression?
        var environment = CreateEnvironment.createStandardEnvironment();
        System.out.println(parser
                .parseExpression("['app.my-value']")
                .getValue(evaluationContext, environment)
        );
    }


    static void useSimpleEvaluationContextWithEnvironment() throws IOException {
        var environment = CreateEnvironment.createStandardEnvironment();
        var parser = new SpelExpressionParser();
        var evaluationContext = new StandardEvaluationContext();
        evaluationContext.addPropertyAccessor(new EnvironmentAccessor());

        System.out.println(parser
                .parseExpression("['app.my-value']")
                .getValue(evaluationContext, environment, String.class)
        );
    }
}
