package eu.vrtime.spelstuff;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpElStuffApplicationTests {

	public static final String TEMPLATE = "<root>" +"\n"
										+ "<firstname>#{[Person].firstname}</firstname>" +"\n"
										+ "<lastname>#{[Person].lastname}</lastname>" +"\n"
										+ "<value>#{[Person].value}</value>" +"\n"
										+ "<age>#{[Person].property.age}</age>" +"\n"
										+ "<mail>#{[Person].property.mail}</mail>" +"\n"
										+ "</root>";

	public static final String TEXT_TEMPLATE = "static0: abc\n"
											 + "static1: yxc\n"
											 + "static2: qwe\n"
											 + "firstname: #{[Person].firstname}\n"
											 + "lastname: #{[Person].lastname}\n" 
											 + "value: #{[Person].value}\n"
											 + "age: #{[Person].property.age}\n"
											 + "mail: #{[Person].property.mail}\n"
											 + "system: #{[ExternalProperty].system}\n"
											 + "reason: #{[ExternalProperty].reason}\n"
											 + "dummy: dummy\n";

	private Person person = new Person("Tom", "Turbo", "is supaaaa", 41, "asdf@yxcv.com");
	private ExternalProperty extProp = new ExternalProperty("eclipse", "jUnit test");

	@Autowired
	private TemplateSpelParser templateParser;

	@Test
	public void contextLoads() {
	}

	@Ignore
	@Test
	public void testSpel() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("'Hello World'.concat('!')");
		String message = exp.getValue(String.class);

		System.out.println(message);
	}

	@Ignore
	@Test
	public void testSpelOnObject() {

		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("firstname");

		EvaluationContext context = new StandardEvaluationContext(person);
		String firstname = (String) exp.getValue(context);

		System.out.println(firstname);
	}

	@Ignore
	@Test
	public void testTemplate() {
		Map<String, Object> contextMap = new HashMap<>();
		contextMap.put(person.getClass().getSimpleName(), person);
		StandardEvaluationContext evalContext = new StandardEvaluationContext(contextMap);

		TemplateParserContext templateContext = new TemplateParserContext();

		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression(TEMPLATE, templateContext);

		String evaluated = exp.getValue(evalContext, String.class);

		System.out.println(evaluated);
	}

	@Test
	public void testSpelParserService() {
		
		System.out.println("SOURCE TEMPLATE: " + "\n" + TEMPLATE + "\n");

		Map<String, Object> contextMap = new HashMap<>();
		
		contextMap.put(person.getClass().getSimpleName(), person);
		String evaluatedTemplate = templateParser.evaluateTemplate(contextMap, TEMPLATE);

		System.out.println("EVALUATED TEMPLATE: " + "\n" +  evaluatedTemplate + "\n");
	}

	@Test
	public void testSpelParserServiceTextTemplate() {

		System.out.println("SOURCE TEMPLATE: " + "\n" + TEXT_TEMPLATE + "\n");

		Map<String, Object> contextMap = new HashMap<>();

		contextMap.put(person.getClass().getSimpleName(), person);
		contextMap.put(extProp.getClass().getSimpleName(), extProp);

		String evaluatedTemplate = templateParser.evaluateTemplate(contextMap, TEXT_TEMPLATE);

		System.out.println("EVALUATED TEMPLATE: " + "\n" + evaluatedTemplate + "\n");
	}

}
