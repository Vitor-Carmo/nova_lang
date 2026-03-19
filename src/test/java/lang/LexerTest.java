package lang;

import org.junit.jupiter.api.Test;

import lang.lexer.Lexer;
import lang.lexer.Token;
import lang.lexer.TokenType;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class LexerTest {
    private final Lexer lexer = new Lexer();

    @Test
    void testNumberToken() {
        List<Token> tokens = lexer.tokenize("123");
        assertEquals(TokenType.NUMBER, tokens.get(0).getType());
    }

    @Test
    void testVariableToken() {
        List<Token> tokens = lexer.tokenize("let x");
        assertEquals(TokenType.IDENTIFIER, tokens.get(1).getType());
    }

    @Test
    void testLetKeyword() {
        List<Token> tokens = lexer.tokenize("let");

        assertEquals(TokenType.LET, tokens.get(0).getType());
    }

    @Test
    void testStringToken() {
        List<Token> tokens = lexer.tokenize("\"hello\"");

        assertEquals(TokenType.STRING, tokens.get(0).getType());
    }

    @Test
    void testEqualsToken() {
        List<Token> tokens = lexer.tokenize("let x = 5");
        assertEquals(TokenType.EQUALS, tokens.get(2).getType());
    }

    @Test
    void testPlusToken() {
        List<Token> tokens = lexer.tokenize("2 + 2");
        assertEquals(TokenType.PLUS, tokens.get(1).getType());
    }

    @Test
    void testMultipleNumbers() {
        List<Token> tokens = lexer.tokenize("10 20 30 3.14159265359");

        assertAll(
                () -> assertEquals(TokenType.NUMBER, tokens.get(0).getType()),
                () -> assertEquals(TokenType.NUMBER, tokens.get(1).getType()),
                () -> assertEquals(TokenType.NUMBER, tokens.get(2).getType()),
                () -> assertEquals(TokenType.NUMBER, tokens.get(3).getType()));
    }

    @Test
    void testNumberExpression() {
        List<Token> tokens = lexer.tokenize("2+2");

        assertAll(
                () -> assertEquals(TokenType.NUMBER, tokens.get(0).getType()),
                () -> assertEquals(TokenType.PLUS, tokens.get(1).getType()),
                () -> assertEquals(TokenType.NUMBER, tokens.get(2).getType()));
    }

    @Test
    void testDoubleNumberExpression() {
        List<Token> tokens = lexer.tokenize("0.1+0.3");

        assertAll(
                () -> assertEquals(TokenType.NUMBER, tokens.get(0).getType()),
                () -> assertEquals(TokenType.PLUS, tokens.get(1).getType()),
                () -> assertEquals(TokenType.NUMBER, tokens.get(2).getType()));
    }

    @Test
    void testStringWithSpaces() {
        List<Token> tokens = lexer.tokenize("\"hello world\"");

        assertEquals(TokenType.STRING, tokens.get(0).getType());
    }

    @Test
    void testEmptyString() {
        List<Token> tokens = lexer.tokenize("\"\"");

        assertEquals(TokenType.STRING, tokens.get(0).getType());
    }

    @Test
    void testIdentifier() {
        List<Token> tokens = lexer.tokenize("myVariable");

        assertEquals(TokenType.IDENTIFIER, tokens.get(0).getType());
    }

    @Test
    void testIdentifierWithNumber() {
        List<Token> tokens = lexer.tokenize("var1");

        assertEquals(TokenType.IDENTIFIER, tokens.get(0).getType());
    }

    @Test
    void testSimpleExpression() {
        List<Token> tokens = lexer.tokenize("let x = 10 + 5");
        assertAll(
                () -> assertEquals(TokenType.LET, tokens.get(0).getType()),
                () -> assertEquals(TokenType.IDENTIFIER, tokens.get(1).getType()),
                () -> assertEquals(TokenType.EQUALS, tokens.get(2).getType()),
                () -> assertEquals(TokenType.NUMBER, tokens.get(3).getType()),
                () -> assertEquals(TokenType.PLUS, tokens.get(4).getType()),
                () -> assertEquals(TokenType.NUMBER, tokens.get(5).getType()));
    }

    @Test
    void testTokenCount() {
        List<Token> tokens = lexer.tokenize("let x = 5");

        assertEquals(4, tokens.size());
    }

    @Test
    void testBasicOperations() {
        List<Token> line1 = lexer.tokenize("10 + 5");
        List<Token> line2 = lexer.tokenize("10 - 5");
        List<Token> line3 = lexer.tokenize("10 * 5");
        List<Token> line4 = lexer.tokenize("10 / 5");

        assertAll(
                () -> assertEquals(TokenType.PLUS, line1.get(1).getType()),
                () -> assertEquals(TokenType.MINUS, line2.get(1).getType()),
                () -> assertEquals(TokenType.MULTIPLY, line3.get(1).getType()),
                () -> assertEquals(TokenType.DIVIDE, line4.get(1).getType()));
    }

}