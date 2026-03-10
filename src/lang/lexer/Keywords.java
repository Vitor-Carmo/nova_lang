package lang.lexer;
import java.util.Map;
import java.util.HashMap;

public class Keywords {

    public static final Map<String, TokenType> KEYWORDS = new HashMap<>();

    static {

        KEYWORDS.put("let", TokenType.LET);
        KEYWORDS.put("print", TokenType.PRINT);

        KEYWORDS.put("=", TokenType.EQUAL);
        KEYWORDS.put("+", TokenType.PLUS);
        KEYWORDS.put("-", TokenType.MINUS);
    }

}