package lang.ast.statements;
import java.util.Map;

import lang.ast.expressions.Expression;

public class VariableExpression implements Expression {

    String name;

    public VariableExpression(String name) {
        this.name = name;
    }
    
    @Override
    public Integer evaluate(Map<String, Object> variables) {
        return (Integer) variables.get(name);
    }
}