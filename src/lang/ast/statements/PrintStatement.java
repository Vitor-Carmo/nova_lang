package lang.ast.statements;

import lang.ast.expressions.Expression;

public class PrintStatement {

    public Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

}