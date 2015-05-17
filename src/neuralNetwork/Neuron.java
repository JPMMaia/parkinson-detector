package neuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class Neuron
{
    private static final Double s_ETA = 0.0;
    private static final Double s_ALPHA = 0.0;

    private Double m_outputValue;
    private Double m_gradientValue;
    private List<Connection> m_sourceConnections;
    private List<Connection> m_targetConnections;

    public Neuron()
    {
    }

    public void initialize()
    {
        m_outputValue = 0.0;
        m_gradientValue = 0.0;
        m_sourceConnections = new ArrayList<>();
        m_targetConnections = new ArrayList<>();
    }

    public void addConnection(Connection connection)
    {
        if(connection.getSource() == this)
            m_sourceConnections.add(connection);
        else if(connection.getTarget() == this)
            m_targetConnections.add(connection);
    }

    public void calculateOutputValue()
    {
        Double sum = 0.0;

        for (Connection targetConnection : m_targetConnections)
        {
            Neuron sourceNeuron = targetConnection.getSource();
            Double outputValue = sourceNeuron.getOutputValue();

            Double weight = targetConnection.getWeight();

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

        for (Connection targetConnection : m_targetConnections)
        {
            // Get the gradient of the target neuron:
            Neuron targetNeuron = targetConnection.getTarget();
            Double targetGradientValue = targetNeuron.getGradientValue();

            // Get the connection weight:
            Double connectionWeight = targetConnection.getWeight();

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
        for (Connection targetConnection : m_targetConnections)
        {
            Neuron sourceNeuron = targetConnection.getSource();

            Double oldDeltaWeight = targetConnection.getDeltaWeight();
            Double newDeltaWeight =
                    Neuron.s_ETA * sourceNeuron.getOutputValue() * m_gradientValue // Overall learning rate [0, 1]
                            + Neuron.s_ALPHA * oldDeltaWeight; // alpha = momentum [0, n]

            // Set new delta weight:
            targetConnection.setDeltaWeight(newDeltaWeight);

            // Set new weight:
            Double oldWeight = targetConnection.getWeight();
            targetConnection.setWeight(oldWeight + newDeltaWeight);
        }
        */

        for (Connection targetConnection : m_targetConnections)
        {
            double targetGradient = targetConnection.getTarget().getGradientValue();

            Double oldDeltaWeight = targetConnection.getDeltaWeight();
            Double newDeltaWeight =
                    Neuron.s_ETA * m_outputValue * targetGradient // Overall learning rate [0, 1]
                            + Neuron.s_ALPHA * oldDeltaWeight; // alpha = momentum [0, n]

            // Set new delta weight:
            targetConnection.setDeltaWeight(newDeltaWeight);

            // Set new weight:
            Double oldWeight = targetConnection.getWeight();
            targetConnection.setWeight(oldWeight + newDeltaWeight);
        }
    }

    public List<Connection> getSourceConnections()
    {
        return m_sourceConnections;
    }

    public List<Connection> getTargetConnections()
    {
        return m_targetConnections;
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
