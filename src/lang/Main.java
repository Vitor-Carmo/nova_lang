package lang;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import lang.interpreter.Interpreter;
import lang.lexer.Lexer;
import lang.lexer.Token;
import lang.parser.Parser;

public class Main {

    public static void main(String[] args) {

        try {

            // caminho do arquivo da linguagem
            String filePath = args[0];

            // lê todas as linhas do arquivo
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            System.out.println("File read successfully: " + filePath);
            
            // conteudo do arquivo
            System.out.println("File content:\n");
            lines.forEach(System.out::println);
            System.out.println("\n--- End of file content. ---\n");


            // cria o interpretador
            Lexer lexer = new Lexer();
            Parser parser = new Parser();
            Interpreter interpreter = new Interpreter();

            for (String line : lines) {
                System.out.println("\n\nProcessing line: " + line);
                List<Token> tokens = lexer.tokenize(line);

                System.out.println("\nTokens:");
                lexer.printTokens(tokens);

                
                Object ast = parser.parse(tokens);

                interpreter.execute(ast);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}