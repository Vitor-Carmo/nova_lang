package lang.ast.expressions;
import java.util.Map;

public class NumberExpression implements Expression {

    int value;

    public NumberExpression(int value) {
        this.value = value;
    }

    @Override
    public Integer evaluate(Map<String, Object> variables) {
        return value;
    }
}