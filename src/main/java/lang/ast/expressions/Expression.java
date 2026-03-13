package lang.ast.expressions;
import java.util.Map;

public interface Expression {
    Object evaluate(Map<String, Object> variables);
}