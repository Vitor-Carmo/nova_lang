package lang.interpreter;
import java.util.HashMap;
import java.util.Map;

import lang.ast.statements.PrintStatement;
import lang.ast.statements.VariableDeclaration;

public class Interpreter {

    Map<String, Integer> variables = new HashMap<>();

    public void execute(Object node) {

        if (node instanceof VariableDeclaration) {

            VariableDeclaration var = (VariableDeclaration) node;

            int value = var.expression.evaluate(variables);

            variables.put(var.name, value);
        }

        if (node instanceof PrintStatement) {

            PrintStatement print = (PrintStatement) node;

            System.out.println(variables.get(print.name));

        }

    }
}