package neuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class Neuron
{
    private static final Double s_learningRate = 0.4;
    private static final Double s_momentum = 0.8;

    private Double m_outputValue;
    private Double m_gradientValue;
    private List<Connection> m_nextLayerConnections;
    private List<Connection> m_previousLayerConnections;

    public Neuron()
    {
    }

    public void initialize()
    {
        m_outputValue = 0.0;
        m_gradientValue = 0.0;
        m_nextLayerConnections = new ArrayList<>();
        m_previousLayerConnections = new ArrayList<>();
    }

    public void addConnection(Connection connection)
    {
        if(connection.getSource() == this)
            m_nextLayerConnections.add(connection);
        else if(connection.getTarget() == this)
            m_previousLayerConnections.add(connection);
    }

    public void calculateOutputValue()
    {
        Double sum = 0.0;

        for (Connection previousLayerConnection : m_previousLayerConnections)
        {
            Neuron sourceNeuron = previousLayerConnection.getSource();
            Double outputValue = sourceNeuron.getOutputValue();

            Double weight = previousLayerConnection.getWeight();

            sum += outputValue * weight;
        }

        m_outputValue = Neuron.sigmoidFunction(sum);
    }

    public Double getOutputValue()
    {
        return m_outputValue;
    }

    public void setOutputValue(Double outputValue)
    {
        m_outputValue = outputValue;
    }

    // If its a neuron on an Output Layer:
    public void calculateGradientValue(Double targetValue)
    {
        m_gradientValue = (targetValue - m_outputValue) * sigmoidDerivativeFunction(m_outputValue);
    }

    // In the other cases:
    public void calculateGradientValue()
    {
        Double sum = 0.0;

        for (Connection nextLayerConnection : m_nextLayerConnections)
        {
            // Get the gradient of the target neuron:
            Double targetGradientValue = nextLayerConnection.getTarget().getGradientValue();

            // Get the connection weight:
            Double connectionWeight = nextLayerConnection.getWeight();

            // Sum all the gradient*connection
            sum += targetGradientValue * connectionWeight;
        }

        // The gradient value for this neuron:
        m_gradientValue = sum * sigmoidDerivativeFunction(m_outputValue);
    }

    public Double getGradientValue()
    {
        return m_gradientValue;
    }

    public void updateConnectionsWeights()
    {
        /*
        for (Connection targetConnection : m_previousLayerConnections)
        {
            Neuron sourceNeuron = targetConnection.getSource();

            Double oldDeltaWeight = targetConnection.getDeltaWeight();
            Double newDeltaWeight =
                    Neuron.s_learningRate * sourceNeuron.getOutputValue() * m_gradientValue // Overall learning rate [0, 1]
                            + Neuron.s_momentum * oldDeltaWeight; // alpha = momentum [0, n]

            // Set new delta weight:
            targetConnection.setDeltaWeight(newDeltaWeight);

            // Set new weight:
            Double oldWeight = targetConnection.getWeight();
            targetConnection.setWeight(oldWeight + newDeltaWeight);
        }
        */

        for (Connection nextLayerConnection : m_nextLayerConnections)
        {
            double targetGradient = nextLayerConnection.getTarget().getGradientValue();

            Double oldWeight = nextLayerConnection.getWeight();
            Double deltaWeight = nextLayerConnection.getDeltaWeight();

            Double newWeight =
                    oldWeight
                    + (Neuron.s_learningRate * m_outputValue * targetGradient) // Overall learning rate [0, 1]
                    + (Neuron.s_momentum * deltaWeight); // alpha = momentum [0, n]

            // Update weight and update deltaWeight:
            nextLayerConnection.updateWeight(newWeight);
        }
    }

    public List<Connection> getNextLayerConnections()
    {
        return m_nextLayerConnections;
    }

    public List<Connection> getPreviousLayerConnections()
    {
        return m_previousLayerConnections;
    }

    private static Double sigmoidFunction(Double x)
    {
        // http://en.wikipedia.org/wiki/Backpropagation#Derivation
        return 1 / (1 + Math.pow(Math.E, -x));
    }

    // Note: outputValue already comes as outputValue = sigmoideFunction(x)
    private static Double sigmoidDerivativeFunction(Double outputValue)
    {
        return outputValue * (1 - outputValue);
    }
}
