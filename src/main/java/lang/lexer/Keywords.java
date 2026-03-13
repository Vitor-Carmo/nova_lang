package lang.lexer;
import java.util.Map;
import java.util.HashMap;

public class Keywords {

    public static final Map<String, TokenType> KEYWORDS = new HashMap<>();
    public static final Map<String, TokenType> OPERATORS = new HashMap<>();

    static {

        KEYWORDS.put("let", TokenType.LET);
        KEYWORDS.put("print", TokenType.PRINT);

         // Operadores
        OPERATORS.put("=", TokenType.EQUALS);
        OPERATORS.put("+", TokenType.PLUS);
        OPERATORS.put("-", TokenType.MINUS);
        OPERATORS.put("*", TokenType.MULTIPLY);
        OPERATORS.put("/", TokenType.DIVIDE);
    }

}