import strategies.IInputStrategy;
import strategies.NoDoubleException;

public class InputReader {
    private IInputStrategy strategy;

    public int requestInt(){
        return strategy.getInt();
    }
    public String requestString(){
        return strategy.getString();
    }
    public double requestDouble() throws NoDoubleException {
        return strategy.getDouble();
    }

    public void setStrategy(IInputStrategy strategy) {
        this.strategy = strategy;
    }
}
