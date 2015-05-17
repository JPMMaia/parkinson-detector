package neuralNetwork;

import neuralNetwork.utils.TestAssigner;
import org.junit.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Miguel on 17-05-2015.
 */
public class TrainTester
{
    private final static List<Integer> s_TOPOLOGY_1 = Arrays.asList(2, 1, 2, 2);
    private final static List<Integer> s_TOPOLOGY_2 = Arrays.asList(2, 1);

    @Test
    public void testTrainNet()
    {
        // Create net (learn rate = 0.4, momentum = 0.8:
        NeuralNetwork net = new NeuralNetwork();
        net.setWeightAssigner(new TestAssigner(Arrays.asList(0.3, 0.6, 0.2, 0.7, 0.2, 0.5, 0.2, 0.6)));
        net.initialize(s_TOPOLOGY_1);
        List<Neuron> intputNeurons = net.getInputLayer().getNeurons();
        List<Neuron> hiddenNeurons1 = net.getLayers().get(1).getNeurons();
        List<Neuron> hiddenNeurons2 = net.getLayers().get(2).getNeurons();
        List<Neuron> outputNeurons = net.getOutputLayer().getNeurons();

        List<Connection> connectionsNode1 = intputNeurons.get(0).getNextLayerConnections();
        List<Connection> connectionsNode2 = intputNeurons.get(1).getNextLayerConnections();
        List<Connection> connectionsNode3 = hiddenNeurons1.get(0).getNextLayerConnections();
        List<Connection> connectionsNode4 = hiddenNeurons2.get(0).getNextLayerConnections();
        List<Connection> connectionsNode5 = hiddenNeurons2.get(1).getNextLayerConnections();

        // Test feed-forward:
        net.feedForward(Arrays.asList(4.0, 5.0));
        Assert.assertEquals(0.6089179027212841, outputNeurons.get(0).getOutputValue(), 0.000000000001);
        Assert.assertEquals(0.6246552673925295, outputNeurons.get(1).getOutputValue(), 0.000000000001);

        // Test back-propagation error calculation:
        net.backPropagate(Arrays.asList(0.1, 0.2));
        Assert.assertEquals(-0.1211921268569304, outputNeurons.get(0).getGradientValue(), 0.000000000001);
        Assert.assertEquals(-0.09956512595825087, outputNeurons.get(1).getGradientValue(), 0.000000000001);

        Assert.assertEquals(-0.01093141092744497, hiddenNeurons2.get(0).getGradientValue(), 0.000000000001);
        Assert.assertEquals(-0.02677218761431198, hiddenNeurons2.get(1).getGradientValue(), 0.000000000001);

        Assert.assertEquals(-3.046056684166268 * Math.pow(10, -4), hiddenNeurons1.get(0).getGradientValue(), 0.000000000001);

        // Test back-propagation weight correction:
        Assert.assertEquals(0.2995126309305334, connectionsNode1.get(0).getWeight(), 0.000000000001);
        Assert.assertEquals(0.5993907886631668, connectionsNode2.get(0).getWeight(), 0.000000000001);
        Assert.assertEquals(0.1956920360336197, connectionsNode3.get(0).getWeight(), 0.000000000001);
        Assert.assertEquals(0.6894493382136, connectionsNode3.get(1).getWeight(), 0.000000000001);
        Assert.assertEquals(0.1733812387756403, connectionsNode4.get(0).getWeight(), 0.000000000001);
        Assert.assertEquals(0.1781314151101193, connectionsNode4.get(1).getWeight(), 0.000000000001);
        Assert.assertEquals(0.4677197070833418, connectionsNode5.get(0).getWeight(), 0.000000000001);
        Assert.assertEquals(0.5734801961681019, connectionsNode5.get(1).getWeight(), 0.000000000001);
    }

    @Test
    public void testTrainNetMomentum()
    {
        // Create net (learn rate = 0.4, momentum = 0.8:
        NeuralNetwork net = new NeuralNetwork();
        net.setWeightAssigner(new TestAssigner(Arrays.asList(0.8, 0.3)));
        net.initialize(s_TOPOLOGY_2);
        List<Neuron> intputNeurons = net.getInputLayer().getNeurons();
        Neuron outputNeuron = net.getOutputLayer().getNeurons().get(0);

        List<Connection> connectionsNode1 = intputNeurons.get(0).getNextLayerConnections();
        List<Connection> connectionsNode2 = intputNeurons.get(1).getNextLayerConnections();

        // First iteration:
        net.feedForward(Arrays.asList(1.2, 0.7));
        Assert.assertEquals(0.7631450157268554, outputNeuron.getOutputValue(), 0.000000000001);
        net.backPropagate(Arrays.asList(0.2));
        Assert.assertEquals(0.751140267791676, connectionsNode1.get(0).getWeight(), 0.000000000001);
        Assert.assertEquals(0.2714984895451443, connectionsNode2.get(0).getWeight(), 0.000000000001);

        // Second iteration:
        net.feedForward(Arrays.asList(1.2, 0.7));
        Assert.assertEquals(0.7486485077059442, outputNeuron.getOutputValue(), 0.000000000001);
        net.backPropagate(Arrays.asList(0.2));
        Assert.assertEquals(0.6624966387356018, connectionsNode1.get(0).getWeight(), 0.000000000001);
        Assert.assertEquals(0.219789705929101, connectionsNode2.get(0).getWeight(), 0.000000000001);

        // Third iteration:
        net.feedForward(Arrays.asList(1.2, 0.7));
        Assert.assertEquals(0.7208835955611683, outputNeuron.getOutputValue(), 0.000000000001);
        net.backPropagate(Arrays.asList(0.2));
        Assert.assertEquals(0.5412742718110278, connectionsNode1.get(0).getWeight(), 0.000000000001);
        Assert.assertEquals(0.1490766585564328, connectionsNode2.get(0).getWeight(), 0.000000000001);
    }
}
