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

        m_outputValue = Neuron.transferFunction(sum);
    }

    public Double getOutputValue()
    {
        return m_outputValue;
    }

    public void setOutputValue(Double outputValue)
    {
        m_outputValue = outputValue;
    }

    public void calculateGradientValue(Double targetValue)
    {
        m_gradientValue = (targetValue - m_outputValue) * transferDerivativeFunction(m_outputValue);
    }

    public void calculateGradientValue()
    {
        Double sum = 0.0;

        for (Connection sourceConnection : m_sourceConnections)
        {
            Neuron targetNeuron = sourceConnection.getTarget();
            Double gradientValue = targetNeuron.getGradientValue();
            Double connectionWeight = sourceConnection.getWeight();

            sum += gradientValue * connectionWeight;
        }

        m_gradientValue = sum * transferDerivativeFunction(m_outputValue);
    }

    public Double getGradientValue()
    {
        return m_gradientValue;
    }

    public void updateConnectionsWeights()
    {
        for (Connection targetConnection : m_targetConnections)
        {
            Neuron sourceNeuron = targetConnection.getSource();

            Double oldDeltaWeight = targetConnection.getDeltaWeight();
            Double newDeltaWeight =
                    Neuron.s_ETA * sourceNeuron.getOutputValue() * m_gradientValue +
                            Neuron.s_ALPHA * oldDeltaWeight;

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

    private static Double transferFunction(Double x)
    {
        // http://en.wikipedia.org/wiki/Backpropagation#Derivation
        return 1 / (1 + Math.pow(Math.E, -x));
    }

    private static Double transferDerivativeFunction(Double outputValue)
    {
        return outputValue * (1 - outputValue);
    }
}
