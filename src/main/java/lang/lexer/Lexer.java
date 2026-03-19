package lang.lexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    public List<Token> tokenize(String line) {
        List<Token> tokens = new ArrayList<>();
        int i = 0;

        while (i < line.length()) {
            char c = line.charAt(i);

            if(c == '#') {
                break; // ignora o resto da linha
            }
            
            // pular espaços
            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }

            // reconhecer strings
            if (c == '"') {
                StringBuilder sb = new StringBuilder();
                i++; // pula a primeira "
                while (i < line.length() && line.charAt(i) != '"') {
                    sb.append(line.charAt(i));
                    i++;
                }
                i++; // pula a segunda "
                tokens.add(new Token(TokenType.STRING, sb.toString()));
                continue;
            }

            // reconhecer números
            if (Character.isDigit(c)) {
                StringBuilder sb = new StringBuilder();
                boolean hasDot = false;

                while (i < line.length()) {
                    char current = line.charAt(i);

                    if(Character.isDigit(current)){
                        sb.append(current);
                    } else if(current == '.' && !hasDot){
                        sb.append(current);
                        hasDot = false;
                    } else{
                        break;
                    }
                    
                    i++;
                }
                
                tokens.add(new Token(TokenType.NUMBER, sb.toString()));
                continue;
            }

            // reconhecer identificadores e keywords
            
            if (Character.isLetter(c)) {
                StringBuilder sb = new StringBuilder();
                while (i < line.length() && Character.isLetterOrDigit(line.charAt(i))) {
                    sb.append(line.charAt(i));
                    i++;
                }
                String word = sb.toString();

                if (Keywords.KEYWORDS.containsKey(word)) {
                    tokens.add(new Token(Keywords.KEYWORDS.get(word), word));
                } else {
                    tokens.add(new Token(TokenType.IDENTIFIER, word));
                }
                continue;
            }

            // operadores
            String s = String.valueOf(c);
            if (Keywords.OPERATORS.containsKey(s)) {
                tokens.add(new Token(Keywords.OPERATORS.get(s), s));
                i++;
                continue;
            }

            i++;
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