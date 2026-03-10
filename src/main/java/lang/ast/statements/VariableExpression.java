package main.java.lang.ast.statements;
import java.util.Map;

import main.java.lang.ast.expressions.Expression;

public class VariableExpression implements Expression {

    String name;

    public VariableExpression(String name) {
        this.name = name;
    }
    
    @Override
    public Object evaluate(Map<String, Object> variables) {
        if (!variables.containsKey(name)) {
            throw new RuntimeException("Variable not defined: " + name);
        }
        return variables.get(name);
    }
}