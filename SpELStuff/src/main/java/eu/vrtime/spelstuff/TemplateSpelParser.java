package eu.vrtime.spelstuff;

import java.util.Map;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

/**
 * Service to replace values in an String template using Spel {@link ExpressionParser} and {@link TemplateParserContext}.
 * 
 * @author tschwaiger
 *
 */

@Service
public class TemplateSpelParser {
	
	private static final ExpressionParser SPEL_PARSER = new SpelExpressionParser();
	private static final ParserContext TEMPLATE_CONTEXT = new TemplateParserContext();
	
	public TemplateSpelParser() {
		
	}
	
	/**
	 * Method to evulate a template. Replace spel placeholders with actual values in object.
	 * @param contextMap
	 * @param template
	 * @return
	 */
	
	public String evaluateTemplate(Map<String, Object> contextMap, String template) {
		StandardEvaluationContext evaluationContext = new StandardEvaluationContext(contextMap);
		Expression exp = SPEL_PARSER.parseExpression(template, TEMPLATE_CONTEXT);
		String evaluated = exp.getValue(evaluationContext, String.class);
		
		return evaluated;
		
	}

}
