package neuralNetwork;

public class Connection
{
    private Double m_weight;

    public Connection(Double weight)
    {
        m_weight = weight;
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
