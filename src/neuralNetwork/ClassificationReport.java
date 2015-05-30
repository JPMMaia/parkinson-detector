package neuralNetwork;

/**
 * Created by Miguel on 27-05-2015.
 */
public class ClassificationReport
{
    private int m_truePositives=0, m_totalPositives=0, m_trueNegatives=0, m_totalNegatives=0;
    private double m_mse = 0.0;

    public void incTruePositives()
    {
        m_truePositives++;
    }

    public void incTotalPositives()
    {
        m_totalPositives++;
    }

    public void incTrueNegatives()
    {
        m_trueNegatives++;
    }

    public void incTotalNegatives()
    {
        m_totalNegatives++;
    }

    public int getTruePositives()
    {
        return m_truePositives;
    }

    public int getTotalPositives()
    {
        return m_totalPositives;
    }

    public int getTrueNegatives()
    {
        return m_trueNegatives;
    }

    public int getTotalNegatives()
    {
        return m_totalNegatives;
    }

    public int getTotalCases()
    {
        return m_totalPositives + m_totalNegatives;
    }

    public void setMSE(double mse)
    {
        m_mse = mse;
    }

    public double getMSE()
    {
        return m_mse;
    }

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();

        str.append("** Report **\n");
        str.append("MSE: " + getMSE() + "\n");
        str.append("Parkinson detection: " + getTruePositives() + "/" + getTotalPositives() + "\n");
        str.append("Healthy detection: " + getTrueNegatives() + "/" + getTotalNegatives() + "\n");
        str.append("-------");

        return str.toString();
    }
}
