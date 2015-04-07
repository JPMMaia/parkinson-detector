package neuralNetwork;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class NetTester
{
    private static List<Integer> s_TOPOLOGY_1 = Arrays.asList(3, 5, 1);

    @Test
    public void testInitialize()
    {
        // Create net:
        Net net = new Net(s_TOPOLOGY_1);

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
                List<Connection> connections = neuron.getConnections();

                if (i == layers.size() - 1)
                    Assert.assertEquals(0, connections.size());
                else
                    Assert.assertEquals((int) s_TOPOLOGY_1.get(i + 1), connections.size());
            }
        }
    }
}
