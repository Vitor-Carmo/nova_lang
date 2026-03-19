package lang.ast.expressions;

import java.util.Map;

public class NumberExpression implements Expression {

    private final Number value;

    public NumberExpression(Number value) {
        this.value = value;
    }

    @Override
    public Number evaluate(Map<String, Object> variables) {
        return value;
    }

    public Number getValue() {
        return value;
    }

    public boolean isInteger() {
        return value instanceof Integer;
    }

    public boolean isDouble() {
        return value instanceof Double;
    }

}