package neuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class Layer
{
    protected List<Neuron> m_neurons;

    public Layer(int numberNeurons, int numberOutputs)
    {
        // Create neurons list:
        m_neurons = new ArrayList<>(numberNeurons);
        for(int i = 0; i < numberNeurons; i++)
            m_neurons.add(new Neuron(numberOutputs));
    }

    protected void forwardPropagate(Layer previousLayer)
    {
        // Get previous layer neurons:
        List<Neuron> previousLayerNeurons = previousLayer.getNeurons();

        // For each neuron in this layer:
        for (Neuron neuron : m_neurons)
        {
            // Forward propagate:
            neuron.forwardPropagate(previousLayerNeurons);
        }
    }

    public void setOutputValues(List<Double> inputValues)
    {
        // For each input layer neuron, set the input value:
        for (int i = 0; i < inputValues.size(); i++)
        {
            Neuron inputNeuron = m_neurons.get(i);
            Double inputValue = inputValues.get(i);

            inputNeuron.setOutputValue(inputValue);
        }
    }

    public void createConnections(Layer nextLayer)
    {
        for (Neuron neuron : m_neurons)
        {
            for (Neuron nextLayerNeuron : nextLayer.getNeurons())
            {
                // Create a connection between the neuron and next layer neuron with a random weight:
                Connection connection = new Connection(neuron, nextLayerNeuron, Random.getRandomWeight());

                // Add connection to neuron:
                neuron.addConnection(connection);
            }
        }
    }

    public Double calculateCost(List<Double> targetValues)
    {
        // http://en.wikipedia.org/wiki/Backpropagation#Derivation
        Double cost = 0.0;

        for(int i = 0; i < m_neurons.size(); i++)
        {
            Double targetValue = targetValues.get(i);
            Double outputValue = m_neurons.get(i).getOutputValue();

            Double delta = targetValue - outputValue;
            cost += delta * delta;
        }

        cost /= 2.0;

        return cost;
    }

    public void addNeuron(Neuron neuron)
    {
        m_neurons.add(neuron);
    }

    public List<Neuron> getNeurons()
    {
        return m_neurons;
    }
}
