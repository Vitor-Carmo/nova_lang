package lang.ast.statements;

import lang.ast.expressions.Expression;

public class VariableDeclaration {
    private String name;
    private Expression expression;

    public VariableDeclaration(String name, Expression expression) {
        this.name = name;
        this.expression = expression;
    }

    public String getName() {
        return name;
    }
    public Expression getExpression() {
        return expression;
    }
}