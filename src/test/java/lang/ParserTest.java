package lang;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lang.ast.expressions.BinaryExpression;
import lang.ast.expressions.Expression;
import lang.ast.expressions.NumberExpression;
import lang.ast.expressions.StringExpression;
import lang.ast.statements.PrintStatement;
import lang.ast.statements.VariableExpression;
import lang.lexer.Lexer;
import lang.lexer.Token;
import lang.parser.Parser;

public class ParserTest {

    private final Lexer lexer = new Lexer();
    private final Parser parser = new Parser();

    @ParameterizedTest
    @ValueSource(strings = {
            "print 10 + 5",
            "print 10 - 5",
            "print 10 * 5",
            "print 10 / 5",
    })
    public void testBinaryExpressionNumberExpression(String code) {
        List<Token> tokens = lexer.tokenize(code);
        Object ast = parser.parse(tokens);

        assertTrue(ast instanceof PrintStatement);

        PrintStatement stmt = (PrintStatement) ast;

        Expression expr = stmt.getExpression();

        assertTrue(expr instanceof BinaryExpression);

        BinaryExpression bin = (BinaryExpression) expr;

        assertTrue(bin.getLeft() instanceof NumberExpression);
        assertTrue(bin.getRight() instanceof NumberExpression);

        NumberExpression left = (NumberExpression) bin.getLeft();
        NumberExpression right = (NumberExpression) bin.getRight();

        assertEquals(10, left.getValue().intValue());
        assertEquals(5, right.getValue().intValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "print 2 + 2 + 2",
            "print 3 + 3 + 3",
            "print 5 + 5 + 5"
    })
    public void testMultipleBinaryExpression(String code) {

        List<Token> tokens = lexer.tokenize(code);
        Object ast = parser.parse(tokens);

        assertTrue(ast instanceof PrintStatement);

        PrintStatement stmt = (PrintStatement) ast;
        Expression expr = stmt.getExpression();

        assertTrue(expr instanceof BinaryExpression);
        BinaryExpression outer = (BinaryExpression) expr;

        assertTrue(outer.getLeft() instanceof BinaryExpression);

        assertTrue(outer.getRight() instanceof NumberExpression);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "print \"Hello\" + \" World\"",
            "print \"10\" + \"10\"",
    })
    public void testBinaryExpressionStringExpression(String code) {
        List<Token> tokens = lexer.tokenize(code);
        Object ast = parser.parse(tokens);

        assertTrue(ast instanceof PrintStatement);

        PrintStatement stmt = (PrintStatement) ast;

        Expression expr = stmt.getExpression();

        assertTrue(expr instanceof BinaryExpression);

        BinaryExpression bin = (BinaryExpression) expr;

        assertTrue(bin.getLeft() instanceof StringExpression);
        assertTrue(bin.getRight() instanceof StringExpression);

        StringExpression left = (StringExpression) bin.getLeft();
        StringExpression right = (StringExpression) bin.getRight();

        assertEquals(code.split("\"")[1], left.getValue());
        assertEquals(code.split("\"")[3], right.getValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "print \"Hello\" + \" World\" + \" !\"",
            "print \"10\" + \"10\" + \"10\"",
    })
    public void testMultiBinaryExpressionStringExpression(String code) {
        List<Token> tokens = lexer.tokenize(code);
        Object ast = parser.parse(tokens);

        assertTrue(ast instanceof PrintStatement);

        PrintStatement stmt = (PrintStatement) ast;

        Expression expr = stmt.getExpression();

        assertTrue(expr instanceof BinaryExpression);

        BinaryExpression bin = (BinaryExpression) expr;

        assertTrue(bin.getLeft() instanceof BinaryExpression);
        assertTrue(bin.getRight() instanceof StringExpression);

        BinaryExpression leftBin = (BinaryExpression) bin.getLeft();

        assertTrue(leftBin.getLeft() instanceof StringExpression);
        assertTrue(leftBin.getRight() instanceof StringExpression);

        StringExpression leftLeftBin = (StringExpression) leftBin.getLeft();
        StringExpression rightLeftBin = (StringExpression) leftBin.getRight();

        assertEquals(code.split("\"")[1], leftLeftBin.getValue());
        assertEquals(code.split("\"")[3], rightLeftBin.getValue());

        StringExpression right = (StringExpression) bin.getRight();
        assertEquals(code.split("\"")[5], right.getValue());
    }

    @Test
    public void testStringExpression() {
        List<Token> tokens = lexer.tokenize("print \"hello\"");

        Object ast = parser.parse(tokens);

        assertTrue(ast instanceof PrintStatement);

        PrintStatement stmt = (PrintStatement) ast;

        assertTrue(stmt.getExpression() instanceof StringExpression);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "print x",
            "print y",
            "print var123",
            // "print _var",
            "print varName"

    })
    public void testVariableExpression(String code) {
        List<Token> tokens = lexer.tokenize(code);

        Object ast = parser.parse(tokens);

        assertTrue(ast instanceof PrintStatement);

        PrintStatement stmt = (PrintStatement) ast;

        assertTrue(stmt.getExpression() instanceof VariableExpression);

        VariableExpression varExpr = (VariableExpression) stmt.getExpression();
        assertEquals(code.split(" ")[1], varExpr.getName());
    }


    @Test
    public void testNumberWithDotStart() {
        List<Token> tokens = lexer.tokenize("print .5 + .5");

        Object ast = parser.parse(tokens);

        assertTrue(ast instanceof PrintStatement);
    }

}
