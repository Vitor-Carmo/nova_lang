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

            Expression expr = parseExpression(tokens, 1); // começa do token depois de PRINT
            return new PrintStatement(expr);
        }

        throw new RuntimeException("Unknown statement");

    }

    public Expression parseExpression(List<Token> tokens, int start) {

        Expression left = parsePrimary(tokens.get(start));

        int i = start + 1;

        while (i < tokens.size()) {

            Token operator = tokens.get(i);

            // garante que tem um próximo token
            if (i + 1 >= tokens.size())
                break;

            Token rightToken = tokens.get(i + 1);
            Expression right = parsePrimary(rightToken);

            left = new BinaryExpression(left, operator.getType(), right);

            i += 2; // pula operador + próximo valor

        }

        return left;
    }

    private Expression parsePrimary(Token token) {
        if (token.getType() == TokenType.NUMBER) {
            return new NumberExpression(Integer.parseInt(token.getValue()));
        } else if (token.getType() == TokenType.STRING) {
            return new StringExpression(String.valueOf(token.getValue()));
        } else {
            return new VariableExpression(token.getValue());
        }
    }
}