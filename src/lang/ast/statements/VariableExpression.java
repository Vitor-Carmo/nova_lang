package lang.ast.statements;
import java.util.Map;

import lang.ast.expressions.Expression;

public class VariableExpression implements Expression {

    String name;

    public VariableExpression(String name) {
        this.name = name;
    }
    
    @Override
    public int evaluate(Map<String, Integer> variables) {
        return variables.get(name);
    }
}