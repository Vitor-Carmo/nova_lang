package main.java.lang.ast.statements;

import main.java.lang.ast.expressions.Expression;

public class VariableDeclaration {
    public String name;
    public Expression expression;

    public VariableDeclaration(String name, Expression expression) {
        this.name = name;
        this.expression = expression;
    }
}