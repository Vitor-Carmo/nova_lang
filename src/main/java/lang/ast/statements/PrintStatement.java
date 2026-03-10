package main.java.lang.ast.statements;

import main.java.lang.ast.expressions.Expression;

public class PrintStatement {

    public Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

}