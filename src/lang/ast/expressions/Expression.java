package lang.ast.expressions;
import java.util.Map;

public interface Expression {
    int evaluate(Map<String, Integer> variables);
}