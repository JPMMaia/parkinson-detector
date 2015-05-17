package neuralNetwork;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Miguel on 17-05-2015.
 */
public class TrainTester
{
    private final static List<Integer> s_TOPOLOGY_1 = Arrays.asList(3, 2, 3, 1);

    @Test
    public void testFeedForward()
    {
        // Create net:
        NeuralNetwork net = new NeuralNetwork();
        net.initialize(s_TOPOLOGY_1);
    }
}
