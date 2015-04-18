package neuralNetwork;

public class Connection
{
    private Neuron m_source;
    private Neuron m_target;
    private Double m_weight;
    private Double m_deltaWeight;

    public void initialize(Neuron source, Neuron target, Double weight)
    {
        m_source = source;
        m_target = target;
        m_weight = weight;
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

    public void setWeight(Double weight)
    {
        m_weight = weight;
    }

    public Double getDeltaWeight()
    {
        return m_deltaWeight;
    }

    public void setDeltaWeight(Double deltaWeight)
    {
        m_deltaWeight = deltaWeight;
    }
}
