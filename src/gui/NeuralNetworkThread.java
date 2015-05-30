package gui;

import data.DataSet;
import neuralNetwork.ClassificationReport;
import neuralNetwork.NeuralNetwork;

/**
 * Created by Miguel on 30-05-2015.
 */
public class NeuralNetworkThread extends Thread
{
    private NeuralNetwork m_net;
    private DataSet m_trainingData, m_testData;
    private GuiForm m_app;

    public NeuralNetworkThread(NeuralNetwork net, DataSet trainingData, DataSet testData, GuiForm app)
    {
        super();
        m_net = net;
        m_trainingData = trainingData;
        m_testData = testData;
        m_app = app;
    }
    public void run()
    {
        m_net.train(m_trainingData, 0.01, 4000);
        ClassificationReport testReport = m_net.test(m_testData);

        System.out.println(testReport);
        m_app.setEnabledInterface(true);
    }
}
