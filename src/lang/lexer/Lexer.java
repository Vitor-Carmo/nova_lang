package lang.lexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    public List<Token> tokenize(String line) {
        List<Token> tokens = new ArrayList<Token>();

        String[] parts = line.split(" ");

        for (String part : parts) {

            if (Keywords.KEYWORDS.containsKey(part)) {

                tokens.add(new Token(Keywords.KEYWORDS.get(part), part));

            } else if (part.matches("\\d+")) {

                tokens.add(new Token(TokenType.NUMBER, part));

            } else {

                tokens.add(new Token(TokenType.IDENTIFIER, part));

            }

        }

        return tokens;
    }

    // Método para imprimir os tokens
    public void printTokens(List<Token> tokens) {
        for (Token token : tokens) {
            System.out.println(token.getType() + " -> " + token.getValue());
        }
    }
}