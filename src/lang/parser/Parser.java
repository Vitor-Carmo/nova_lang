package lang.parser;

import java.util.List;

import lang.ast.expressions.BinaryExpression;
import lang.ast.expressions.Expression;
import lang.ast.expressions.NumberExpression;
import lang.ast.expressions.StringExpression;
import lang.ast.statements.PrintStatement;
import lang.ast.statements.VariableDeclaration;
import lang.ast.statements.VariableExpression;
import lang.lexer.Token;
import lang.lexer.TokenType;

public class Parser {

    public Object parse(List<Token> tokens) {

        // print tokens for debugging

        // tokens.forEach(token -> System.out.println("Token: " + token.type + " - " +
        // token.value));

        if (tokens.isEmpty()) {
            return null;
        }

        Token first = tokens.get(0);

        if (first.getType() == TokenType.LET) {

            String name = tokens.get(1).getValue();

            Expression expr = parseExpression(tokens, 3);

            return new VariableDeclaration(name, expr);
        }

        if (first.getType() == TokenType.PRINT) {

            String name = tokens.get(1).getValue();

            return new PrintStatement(name);
        }

        throw new RuntimeException("Unknown statement");

    }

    public Expression parseExpression(List<Token> tokens, int start) {

        Token first = tokens.get(start);

        Expression left;

        if (first.getType() == TokenType.NUMBER) {
            left = new NumberExpression(Integer.parseInt(first.getValue()));
        } else if (first.getType() == TokenType.STRING) {
            left = new StringExpression(String.valueOf(first.getValue()));
        } else {
            left = new VariableExpression(first.getValue());
        }

        if (tokens.size() > start + 1) {

            Token operator = tokens.get(start + 1);
            Token rightToken = tokens.get(start + 2);

            Expression right;

            if (rightToken.getType() == TokenType.NUMBER) {
                right = new NumberExpression(Integer.parseInt(rightToken.getValue()));
            } else {
                right = new VariableExpression(rightToken.getValue());
            }

            return new BinaryExpression(left, operator.getType(), right);
        }

        return left;
    }
}