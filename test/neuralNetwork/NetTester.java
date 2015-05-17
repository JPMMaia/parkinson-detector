package neuralNetwork;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class NetTester
{
    private final static List<Integer> s_TOPOLOGY_1 = Arrays.asList(3, 5, 7, 8,  1);

    @Test
    public void testInitialize()
    {
        // Create net:
        NeuralNetwork net = new NeuralNetwork();
        net.initialize(s_TOPOLOGY_1);

        // Get layers:
        List<Layer> layers = net.getLayers();

        // Number of layers must be equals to size of topology list:
        Assert.assertEquals(s_TOPOLOGY_1.size(), layers.size());

        for(int i = 0; i < layers.size(); i++)
        {
            Layer layer = layers.get(i);

            // Get neurons of layer:
            List<Neuron> neurons = layer.getNeurons();

            // Number of neurons in the layer must be equals to the value in the topology list:
            Assert.assertEquals((int) s_TOPOLOGY_1.get(i), neurons.size());

            for (Neuron neuron : neurons)
            {
                // Get connections of neuron:
                List<Connection> sourceConnections = neuron.getSourceConnections();
                List<Connection> targetConnections = neuron.getTargetConnections();

                if(i == 0)
                    Assert.assertEquals(0, targetConnections.size());
                else
                    Assert.assertEquals((int) s_TOPOLOGY_1.get(i - 1), targetConnections.size());

                if (i == layers.size() - 1)
                    Assert.assertEquals(0, sourceConnections.size());
                else
                    Assert.assertEquals((int) s_TOPOLOGY_1.get(i + 1), sourceConnections.size());
            }
        }
    }
}
