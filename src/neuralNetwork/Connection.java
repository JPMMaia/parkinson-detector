package neuralNetwork;

public class Connection
{
    private Neuron m_source;
    private Neuron m_target;
    private Double m_weight;

    public Connection(Neuron source, Neuron target, Double weight)
    {
        m_source = source;
        m_target = target;
        m_weight = weight;
    }

    public Neuron getSource()
    {
        return m_source;
    }

    public Neuron getTarget()
    {
        return m_target;
    }

    public void setWeight(Double weight)
    {
        m_weight = weight;
    }

    public Double getWeight()
    {
        return m_weight;
    }
}
