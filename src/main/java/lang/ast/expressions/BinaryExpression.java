package main.java.lang.ast.expressions;

import java.util.Map;

import main.java.lang.lexer.TokenType;

public class BinaryExpression implements Expression {

    Expression left;
    Expression right;
    TokenType operator;

    public BinaryExpression(Expression left, TokenType operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public Object evaluate(Map<String, Object> variables) {

        Object l = left.evaluate(variables);
        Object r = right.evaluate(variables);

        switch (operator) {
            case PLUS:
                if (l instanceof Integer && r instanceof Integer) {
                    return (Integer) l + (Integer) r;
                }

                return String.valueOf(l) + String.valueOf(r);

            case MINUS:
                return (Integer) l - (Integer) r;

            default:
                throw new RuntimeException("Unknown operator");
        }
    }
}