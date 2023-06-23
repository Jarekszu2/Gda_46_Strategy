package strategies;

import java.util.Random;
import java.util.UUID;

/**
 * Created by amen on 8/17/17.
 */
public class RandomStrategy implements IInputStrategy {
    Random random = new Random();
    @Override
    public int getInt() {
        return random.nextInt(100);
    }

    @Override
    public String getString() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    @Override
    public double getDouble() {
        return random.nextDouble();
    }
}
