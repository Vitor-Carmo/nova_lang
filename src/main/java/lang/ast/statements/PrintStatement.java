package lang.ast.statements;

import lang.ast.expressions.Expression;

public class PrintStatement {

    private Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

}