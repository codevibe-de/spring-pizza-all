package pizza;

import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpelParserApp {
    public static void main(String[] args) {
        // setup context, run data loader for initial data
        var applicationContext = new ClassPathXmlApplicationContext("beans/default-beans.xml");
        applicationContext.getBean(DataLoader.class).run();

        // prepare parser and evaluation context
        var parser = new SpelExpressionParser();
        var evaluationContext = new StandardEvaluationContext();
        evaluationContext.setBeanResolver(new BeanFactoryResolver(applicationContext));
        evaluationContext.setVariable("applicationContext", applicationContext);

        // work with it:
        Object value = parser
                .parseExpression("@productService.allProducts.![name]") // change this expression to test different SpEL expressions
                .getValue(evaluationContext);
        System.out.println(value);
    }
}


