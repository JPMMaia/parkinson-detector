package neuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class Layer
{
    private List<Neuron> m_neurons;

    public Layer(int numberNeurons)
    {
        // Create neurons list:
        m_neurons = new ArrayList<>(numberNeurons);
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
