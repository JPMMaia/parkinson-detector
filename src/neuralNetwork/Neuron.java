package neuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class Neuron
{
    private Double m_outputValue;
    private List<Connection> m_connections;

    public Neuron(int numberOutputs)
    {
        // Create connections list:
        m_connections = new ArrayList<>(numberOutputs);

        // Create a connection for each output:
        for(int i = 0; i < numberOutputs; i++)
        {
            // Create a new connection with a random weight:
            Connection connection = new Connection(Random.getRandomWeight());

            // Add connection to the list:
            m_connections.add(connection);
        }
    }

    public List<Connection> getConnections()
    {
        return m_connections;
    }
}
