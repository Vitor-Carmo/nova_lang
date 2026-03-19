package lang.lexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private String line;
    private int position;
    private char currentChar;

    public List<Token> tokenize(String line) {
        this.line = line;
        this.position = 0;
        this.currentChar = line.length() > 0 ? line.charAt(0) : '\0';

        List<Token> tokens = new ArrayList<>();

        while (currentChar != '\0') {

            if (currentChar == '#')
                break;

            if (Character.isWhitespace(currentChar)) {
                advance();
                continue;
            }

            if (currentChar == '"') {
                tokens.add(readString());
                continue;
            }

            if (Character.isDigit(currentChar) || isDotStartNumber()) {
                tokens.add(readNumber());
                continue;
            }

            if (Character.isLetter(currentChar)) {
                tokens.add(readIdentifier());
                continue;
            }

            String op = String.valueOf(currentChar);
            if (Keywords.OPERATORS.containsKey(op)) {
                tokens.add(new Token(Keywords.OPERATORS.get(op), op));
                advance();
                continue;
            }

            throw new RuntimeException("Caractere inválido: " + currentChar);
        }

        return tokens;
    }


    private void advance() {
        position++;
        if (position >= line.length()) {
            currentChar = '\0';
        } else {
            currentChar = line.charAt(position);
        }
    }

    private char peek() {
        int nextPos = position + 1;
        if (nextPos >= line.length())
            return '\0';
        return line.charAt(nextPos);
    }

    private boolean isDotStartNumber() {
        return currentChar == '.' && Character.isDigit(peek());
    }

    private Token readNumber() {
        StringBuilder sb = new StringBuilder();
        boolean hasDot = false;

        // caso comece com "."
        if (currentChar == '.') {
            sb.append("0.");
            hasDot = true;
            advance();
        }

        while (currentChar != '\0') {

            if (Character.isDigit(currentChar)) {
                sb.append(currentChar);
            } else if (currentChar == '.' && !hasDot) {
                sb.append('.');
                hasDot = true;
            } else {
                break;
            }

            advance();
        }

        return new Token(TokenType.NUMBER, sb.toString());
    }

    private Token readString() {
        StringBuilder sb = new StringBuilder();
        advance();

        while (currentChar != '\0' && currentChar != '"') {
            sb.append(currentChar);
            advance();
        }

        if (currentChar == '\0') {
            throw new RuntimeException("String não fechada");
        }

        advance();
        return new Token(TokenType.STRING, sb.toString());
    }

    private Token readIdentifier() {
        StringBuilder sb = new StringBuilder();

        while (currentChar != '\0' && Character.isLetterOrDigit(currentChar)) {
            sb.append(currentChar);
            advance();
        }

        String word = sb.toString();

        if (Keywords.KEYWORDS.containsKey(word)) {
            return new Token(Keywords.KEYWORDS.get(word), word);
        }

        return new Token(TokenType.IDENTIFIER, word);
    }

    public void printTokens(List<Token> tokens) {
        for (Token token : tokens) {
            System.out.println(token.getType() + " -> " + token.getValue());
        }
    }
}