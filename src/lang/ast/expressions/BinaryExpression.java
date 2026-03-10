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
    public int evaluate(Map<String, Integer> variables) {

        int l = left.evaluate(variables);
        int r = right.evaluate(variables);

        switch (operator) {
            case PLUS:
                return l + r;

            case MINUS:
                return l - r;

            default:
                throw new RuntimeException("Unknown operator");
        }
    }
}