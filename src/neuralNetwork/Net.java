package neuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class Net
{
    private List<Layer> m_layers;

    public Net(List<Integer> topology)
    {
        // Create layers list:
        m_layers = new ArrayList<>(topology.size());

        // Add layers to list:
        for(int i = 0; i < topology.size(); i++)
        {
            // Number of neurons of the layer:
            int numberNeurons = topology.get(i);

            // Number of outputs of each neuron of the layer:
            int numberOutputs = i == topology.size() - 1 ? 0 : topology.get(i + 1);

            // Create layer:
            createLayer(numberNeurons, numberOutputs);
        }
    }

    public List<Layer> getLayers()
    {
        return m_layers;
    }

    private void createLayer(int numberNeurons, int numberOutputs)
    {
        // Create new layer:
        Layer layer = new Layer(numberNeurons);

        // Fill the layer with neurons:
        for(int j = 0; j < numberNeurons; j++)
        {
            Neuron neuron = new Neuron(numberOutputs);
            layer.addNeuron(neuron);
        }

        // Add layer to list:
        m_layers.add(layer);
    }
}
