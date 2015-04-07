package neuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class Neuron
{
    private List<Connection> m_connections;
    private Double m_outputValue;
    private Double m_gradient;

    public Neuron(int numberOutputs)
    {
        // Create connections list:
        m_connections = new ArrayList<>(numberOutputs);
    }

    public void forwardPropagate(List<Neuron> previousLayerNeurons)
    {
        // http://en.wikipedia.org/wiki/Artificial_neuron#Types_of_transfer_functions

        Double sum = 0.0;

        // For each neuron of the previous layer:
        for (Neuron neuron : previousLayerNeurons)
        {
            // Get connection between the previous layer neuron and this neuron:
            Connection connection = neuron.getConnection(this);

            // Add weight * output:
            sum += neuron.getOutputValue() * connection.getWeight();
        }

        // Calculate output value using the transfer function:
        m_outputValue = Neuron.transferFunction(sum);
    }

    public void calculateGradient(Double value)
    {
        m_gradient = value * Neuron.transferDerivativeFunction(m_outputValue);
    }

    public Double sumConnectionsGradient(Layer nextLayer)
    {
        Double sum = 0.0;

        List<Neuron> neurons = nextLayer.getNeurons();
        for(int i = 0; i < neurons.size(); i++)
        {
            Neuron neuron = neurons.get(i);

            // Get connection to that neuron:
            Connection connection = getConnection(neuron);

            sum += connection.getWeight() * neuron.getGradient();
        }

        return sum;
    }

    public void addConnection(Connection connection)
    {
        m_connections.add(connection);
    }

    public Connection getConnection(Neuron target)
    {
        for (Connection connection : m_connections)
        {
            if(connection.getTarget() == target)
                return connection;
        }

        return null;
    }

    public List<Connection> getConnections()
    {
        return m_connections;
    }

    public Double getGradient()
    {
        return m_gradient;
    }

    public Double getOutputValue()
    {
        return m_outputValue;
    }

    public void setOutputValue(Double outputValue)
    {
        m_outputValue = outputValue;
    }

    private static Double transferFunction(Double x)
    {
        // http://en.wikipedia.org/wiki/Backpropagation#Derivation
        return 1 / (1 + Math.pow(Math.E, -x));
    }

    private static Double transferDerivativeFunction(Double x)
    {
        return x * (1 - x);
    }
}
