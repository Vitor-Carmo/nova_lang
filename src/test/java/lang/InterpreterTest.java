package lang;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import lang.interpreter.Interpreter;
import lang.lexer.Lexer;
import lang.parser.Parser;

class InterpreterTest {

    private final Lexer lexer = new Lexer();
    private final Parser parser = new Parser();
    private final Interpreter interpreter = new Interpreter();

    @Test
    void testAddition() {
        assertEquals("5\n", run("print 2 + 3"));
        assertEquals("20\n", run("print 10 + 10"));
        assertEquals("2\n", run("print 1 + 1"));
        assertEquals("12\n", run("print 5 + 7"));
        assertEquals("3\n", run("print 1 + 1 + 1"));
        assertEquals("0.4\n", run("print 0.2 + 0.2"));
        assertEquals("0.4\n", run("print 0.2 + 0.1 + 0.1"));
    }

    @Test
    void testSubtraction() {
        assertEquals("-1\n", run("print 2 - 3"));
        assertEquals("0\n", run("print 10 - 10"));
        assertEquals("500\n", run("print 1000 - 500"));
        assertEquals("-500\n", run("print 500 - 1000"));
        assertEquals("-1\n", run("print 1 - 1 - 1"));
        assertEquals("0.1\n", run("print 0.2 - 0.1"));
        assertEquals("0.0\n", run("print 0.2 - 0.1 - 0.1"));
    }

    @Test
    void testMultiply() {
        assertEquals("6\n", run("print 2 * 3"));
        assertEquals("100\n", run("print 10 * 10"));
        assertEquals("8\n", run("print 2 * 2 * 2"));
        assertEquals("4.8\n", run("print 1.2 * 2 * 2"));
    }

    @Test
    void testDivide() {
        assertEquals("5.0\n", run("print 10 / 2"));
        assertEquals("1.0\n", run("print 1 / 1 / 1"));
        assertEquals("1.0\n", run("print 0.5/0.5"));
        assertEquals("1.0\n", run("print 0.5/0.5/1"));
    }

    @Test
    void testPrintString() {
        assertEquals("hello\n", run("print \"hello\""));
        assertEquals("hello world!\n", run("print \"hello\" + \" world!\""));
    }

    @Test
    void testVariableMath() {
        run("let x = 5");
        assertEquals("8\n", run("print x + 3"));
        run("let x = 0.5");
        assertEquals("1.0\n", run("print x / 0.5"));
    }

    @Test
    void testDotStartNumber() {
        assertEquals("1.0\n", run("print .5 + .5"));
    }

    @Test
    void testUndefinedVariable() {
        assertThrows(RuntimeException.class, () -> {
            run("print x");
        });
    }

    @Test
    void testVariableOverwrite() {
        run("let x = 5");
        run("let x = 10");

        assertEquals("10\n", run("print x"));
    }

    private String run(String code) {

        Object ast = parser.parse(lexer.tokenize(code));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        interpreter.execute(ast);

        return out.toString();
    }

}