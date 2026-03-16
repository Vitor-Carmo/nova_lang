package lang;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import lang.interpreter.Interpreter;
import lang.lexer.Lexer;
import lang.lexer.Token;
import lang.parser.Parser;

public class nova_lang {
    private final static Lexer lexer = new Lexer();
    private final static Parser parser = new Parser();
    private final static Interpreter interpreter = new Interpreter();

    /**
     * Valida se o arquivo pode ser processado.
     * Lança exceções descritivas em caso de erro.
     */
    private static void validate(String filePath) throws Exception {
        File file = new File(filePath);

        if (!filePath.endsWith(".nova"))
            throw new IllegalArgumentException("Extensão inválida. Use .nova");

        if (!file.exists())
            throw new FileNotFoundException("Arquivo não encontrado: " + filePath);

        if (!file.canRead())
            throw new IllegalAccessException("Sem permissão para ler: " + filePath);
    }

    public static void run(String filePath) {
        try {
            validate(filePath);

            List<String> lines = Files.readAllLines(Paths.get(filePath));

            for (String line : lines) {
                if (line.trim().isEmpty())
                    continue;

                List<Token> tokens = lexer.tokenize(line);
                Object ast = parser.parse(tokens);

                if (ast != null) {
                    interpreter.execute(ast);
                }
            }
        } catch (Exception e) {
            System.err.println("[Erro de Sistema]: " + e.getMessage());
        }
    }
}
