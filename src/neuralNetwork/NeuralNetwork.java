package neuralNetwork;

import neuralNetwork.utils.IWeightAssigner;
import neuralNetwork.utils.RandomAssigner;
import neuralNetwork.utils.TestAssigner;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork
{
    private List<Layer> m_layers;
    private IWeightAssigner m_initialWeightAssigner;

    public NeuralNetwork()
    {
        m_initialWeightAssigner = new RandomAssigner(); // The default one!
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
            layer.initialize(numberNeurons, previousLayer, m_initialWeightAssigner);

            // Add new layer to the list:
            m_layers.add(layer);

            previousLayer = layer;
        }
    }

    public void feedForward(List<Double> inputValues)
    {
        Layer inputLayer = getInputLayer();
        if(!inputLayer.setOutputValues(inputValues))
            throw new IllegalArgumentException("Error in feedForward algorithm: inputValues do not match the number of input nodes");

        for(int i = 1; i < m_layers.size(); i++)
        {
            Layer layer = m_layers.get(i);
            layer.calculateOutputValues();
        }
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

    public Layer getInputLayer()
    {
        return m_layers.get(0);
    }

    public Layer getOutputLayer()
    {
        return m_layers.get(m_layers.size() - 1);
    }

    public void setWeightAssigner(IWeightAssigner testAssigner)
    {
        m_initialWeightAssigner = testAssigner;
    }
}
