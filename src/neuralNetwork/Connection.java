package neuralNetwork;

public class Connection
{
    private Neuron m_source;
    private Neuron m_target;

    private Double m_weight;
    private Double m_weightN_1;
    private Double m_weightN_2;
    private Double m_deltaWeight;

    public void initialize(Neuron source, Neuron target, Double weight)
    {
        m_source = source;
        m_target = target;
        m_weight = weight;
        m_weightN_1 = null;
        m_deltaWeight = 0.0;
    }

    public Neuron getSource()
    {
        return m_source;
    }

    public Neuron getTarget()
    {
        return m_target;
    }

    public Double getWeight()
    {
        return m_weight;
    }

    public Double getDeltaWeight()
    {
        return m_deltaWeight;
    }

    public void updateWeight(Double weight)
    {
        // The weight from past iteration becomes the weight from past-past iteration, etc...
        m_weightN_1 = m_weight;
        m_weight = weight;

        // Only write a deltaWeight different from 0 if we have enough past iterations!
        if (m_weightN_1 != null)
            m_deltaWeight = m_weight - m_weightN_1;
    }
}
