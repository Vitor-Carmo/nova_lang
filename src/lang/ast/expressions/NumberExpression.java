package lang.ast.expressions;
import java.util.Map;

public class NumberExpression implements Expression {

    int value;

    public NumberExpression(int value) {
        this.value = value;
    }

    @Override
    public int evaluate(Map<String, Integer> variables) {
        return value;
    }
}