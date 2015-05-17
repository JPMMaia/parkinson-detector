package neuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork
{
    private List<Layer> m_layers;

    public NeuralNetwork()
    {
    }

    public void initialize(List<Integer> topology)
    {
        // Create layers list:
        m_layers = new ArrayList<>(topology.size());

        // Create input layer:
        Layer inputLayer = new Layer();
        inputLayer.initialize(topology.get(0));
        m_layers.add(inputLayer);

        Layer previousLayer = inputLayer;
        for(int i = 1; i < topology.size(); i++)
        {
            // Number of neurons of the layer:
            int numberNeurons = topology.get(i);

            Layer layer = new Layer();
            layer.initialize(numberNeurons, previousLayer);

            // Add new layer to the list:
            m_layers.add(layer);

            previousLayer = layer;
        }
    }

    public boolean feedForward(List<Double> inputValues)
    {
        Layer inputLayer = getInputLayer();
        if(!inputLayer.setOutputValues(inputValues))
            return false;

        for(int i = 1; i < m_layers.size(); i++)
        {
            Layer layer = m_layers.get(i);
            layer.calculateOutputValues();
        }

        return true;
    }

    public void backPropagate(List<Double> targetValues)
    {
        Layer outputLayer = getOutputLayer();

        // Calculate output layer cost:
        // Double cost = outputLayer.calculateCost(targetValues);

        // Calculate output layer gradients:
        outputLayer.calculateGradientValues(targetValues);

        // Calculate hidden layer gradients:
        for (int i = m_layers.size() - 2; i > 0; i--)
        {
            Layer hiddenLayer = m_layers.get(i);

            hiddenLayer.calculateGradientValues();
        }

        // Update all connection weights:
        /*
        for (int i = m_layers.size() - 1; i > 0; i--)
        {
            Layer currentLayer = m_layers.get(i);
            currentLayer.updateConnectionsWeights();
        }
        */

        for (int i = m_layers.size() - 2; i >= 0; i--)
        {
            Layer currentLayer = m_layers.get(i);
            currentLayer.updateConnectionsWeights();
        }

    }

    public List<Layer> getLayers()
    {
        return m_layers;
    }

    private Layer getInputLayer()
    {
        return m_layers.get(0);
    }

    private Layer getOutputLayer()
    {
        return m_layers.get(m_layers.size() - 1);
    }
}
