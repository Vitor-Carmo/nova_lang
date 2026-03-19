package lang.ast.expressions;

import java.util.Map;

import lang.lexer.TokenType;

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

                if (l instanceof String || r instanceof String) {
                    return String.valueOf(l) + String.valueOf(r);
                }

                if (l instanceof Double || r instanceof Double) {
                    return ((Number) l).doubleValue() + ((Number) r).doubleValue();
                }

                return ((Number) l).longValue() + ((Number) r).longValue();

            case MINUS:
                if (l instanceof Double || r instanceof Double) {
                    return ((Number) l).doubleValue() - ((Number) r).doubleValue();
                }

                return ((Number) l).longValue() - ((Number) r).longValue();

            case MULTIPLY:
                if (l instanceof Double || r instanceof Double) {
                    return ((Number) l).doubleValue() * ((Number) r).doubleValue();
                }

                return ((Number) l).longValue() * ((Number) r).longValue();

            case DIVIDE:
                return ((Number) l).doubleValue() / ((Number) r).doubleValue();

            default:
                throw new RuntimeException("Unknown operator");
        }
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }
}