package de.neuland.pug4j.parser.node;

import de.neuland.pug4j.compiler.IndentWriter;
import de.neuland.pug4j.exceptions.ExpressionException;
import de.neuland.pug4j.exceptions.PugCompilerException;
import de.neuland.pug4j.model.PugModel;
import de.neuland.pug4j.template.PugTemplate;

public class WhileNode extends Node {

	@Override
	public void execute(IndentWriter writer, PugModel model, PugTemplate template) throws PugCompilerException {
		try {
			model.pushScope();
			while (template.getExpressionHandler().evaluateBooleanExpression(value, model)) {
				block.execute(writer, model, template);
			}
			model.popScope();
		} catch (ExpressionException e) {
			throw new PugCompilerException(this, template.getTemplateLoader(), e);
		}
	}
}
