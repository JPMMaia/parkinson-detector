package neuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class Net
{
    private List<Layer> m_layers;

    public Net(List<Integer> topology)
    {
        // Create layers:
        createLayers(topology);

        // Create connections:
        Net.createConnections(m_layers);
    }

    public void forwardPropagate(List<Double> inputValues)
    {
        // Set input values for the input layer neurons:
        getInputLayer().setOutputValues(inputValues);

        for (int layerIndex = 1; layerIndex < m_layers.size(); layerIndex++)
        {
            Layer previousLayer = m_layers.get(layerIndex - 1);
            Layer currentLayer = m_layers.get(layerIndex);

            // Forward propagate:
            currentLayer.forwardPropagate(previousLayer);
        }
    }

    public void backPropagate(List<Double> targetValues)
    {
        // Calculate output layer cost and gradients:
        Layer outputLayer = getOutputLayer();
        Double cost = outputLayer.calculateCost(targetValues);

        Net.calculateOutputGradients(outputLayer, targetValues);
        Net.calculateHiddenGradients(m_layers);
    }

    private static void calculateHiddenGradients(List<Layer> layers)
    {
        // Calculate hidden layers gradients in the inverse order:
        for (int i = layers.size() - 2; i >= 0; i--)
        {
            Layer hiddenLayer = layers.get(i);
            Layer nextLayer = layers.get(i + 1);

            // Calculate hidden gradients of the layer using the next layer:
            for (Neuron neuron : hiddenLayer.getNeurons())
            {
                // Calculate sum(Weight(i) * Gradient(i), i from 0 to nextLayer.size()):
                Double sum = neuron.sumConnectionsGradient(nextLayer);

                // Calculate gradient using the sum value:
                neuron.calculateGradient(sum);
            }
        }
    }

    private static void calculateOutputGradients(Layer outputLayer, List<Double> targetValues)
    {
        List<Neuron> neurons = outputLayer.getNeurons();
        for (int i = 0; i < neurons.size(); i++)
        {
            // Get neuron:
            Neuron neuron = neurons.get(i);

            // Calculate difference between target and output values:
            Double outputValue = neuron.getOutputValue();
            Double targetValue = targetValues.get(i);
            Double delta = targetValue - outputValue;

            // Calculate neuron gradient using the delta value:
            neuron.calculateGradient(delta);
        }
    }

    public Layer getInputLayer()
    {
        return m_layers.get(0);
    }

    public List<Layer> getLayers()
    {
        return m_layers;
    }

    public Layer getOutputLayer()
    {
        return m_layers.get(m_layers.size() - 1);
    }

    private void createLayers(List<Integer> topology)
    {
        // Create layers list:
        m_layers = new ArrayList<>(topology.size());

        for(int i = 0; i < topology.size(); i++)
        {
            // Number of neurons of the layer:
            int numberNeurons = topology.get(i);

            // Number of outputs of each neuron of the layer:
            int numberOutputs = i == topology.size() - 1 ? 0 : topology.get(i + 1);

            // Add new layer to the list:
            m_layers.add(new Layer(numberNeurons, numberOutputs));
        }
    }

    private static void createConnections(List<Layer> layers)
    {
        // Create connections between the layers:
        for(int i = 0; i < layers.size() - 1; i++)
        {
            Layer layer = layers.get(i);
            Layer nextLayer = layers.get(i + 1);

            layer.createConnections(nextLayer);
        }
    }
}
