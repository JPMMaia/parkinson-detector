package neuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class Layer
{
    private List<Neuron> m_neurons;

    public void initialize(Integer numberNeurons)
    {
        m_neurons = new ArrayList<>(numberNeurons);

        for (int i = 0; i < numberNeurons; i++)
        {
            Neuron neuron = new Neuron();
            neuron.initialize();

            m_neurons.add(neuron);
        }
    }

    public void initialize(Integer numberNeurons, Layer previousLayer)
    {
        m_neurons = new ArrayList<>(numberNeurons);
        List<Neuron> previousNeurons = previousLayer.getNeurons();

        for(int i = 0; i < numberNeurons; i++)
        {
            Neuron currentNeuron = new Neuron();
            currentNeuron.initialize();

            for(Neuron previousNeuron : previousNeurons)
            {
                Connection connection = new Connection();
                connection.initialize(previousNeuron, currentNeuron, Random.getRandomWeight());

                previousNeuron.addConnection(connection);
                currentNeuron.addConnection(connection);
            }

            m_neurons.add(currentNeuron);
        }
    }

    public void calculateOutputValues()
    {
        m_neurons.forEach(Neuron::calculateOutputValue);
    }

    public boolean setOutputValues(List<Double> outputValues)
    {
        if(outputValues.size() != m_neurons.size())
            return false;

        for(int i = 0; i < outputValues.size(); i++)
        {
            Double outputValue = outputValues.get(i);
            Neuron neuron = m_neurons.get(i);

            neuron.setOutputValue(outputValue);
        }

        return true;
    }

    public Double calculateCost(List<Double> targetValues)
    {
        Double cost = 0.0;

        for(int i = 0; i < targetValues.size(); i++)
        {
            Neuron neuron = m_neurons.get(i);
            Double targetValue = targetValues.get(i);
            Double outputValue = neuron.getOutputValue();

            Double delta = targetValue - outputValue;
            cost += delta * delta;
        }

        cost /= 2.0;

        return cost;
    }

    // If its a neuron on an Output Layer:
    public void calculateGradientValues(List<Double> targetValues)
    {
        for (int i = 0; i < m_neurons.size(); i++)
        {
            Neuron neuron = m_neurons.get(i);
            Double targetValue = targetValues.get(i);

            // Calculate neuron gradient using the target value:
            neuron.calculateGradientValue(targetValue);
        }
    }

    // In the other cases:
    public void calculateGradientValues()
    {
        m_neurons.forEach(Neuron::calculateGradientValue);
    }

    public void updateConnectionsWeights()
    {
        m_neurons.forEach(Neuron::updateConnectionsWeights);
    }

    public List<Neuron> getNeurons()
    {
        return m_neurons;
    }
}
