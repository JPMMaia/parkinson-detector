import data.DataSet;
import data.test.TestData;
import data.train.TrainingData;
import neuralNetwork.NeuralNetwork;

import java.util.Arrays;

/**
 * Created by Miguel on 17-05-2015.
 */
public class Program
{
    private String m_filePath;

    public Program()
    {
    }

    public void run()
    {
        // Read data:
        DataSet trainData = new TrainingData("data/train_data.txt");
        DataSet testData = new TestData("data/test_data.txt");

        // Normalize data:
        trainData.normalize();
        testData.normalize();

        NeuralNetwork network = new NeuralNetwork();
        network.initialize(Arrays.asList(trainData.getNumAttributes(), testData.getNumAttributes() + 2, 2), 0.3, 0.2);
        network.train(trainData, 0.001, 100);
        System.out.println("Ja deu");
    }

    public static void main(String[] args)
    {
        try
        {
            Program detector = new Program();
            detector.run();
        }
        catch(IllegalArgumentException e)
        {
            System.err.println("A problem was detected while parsing/running the neural network: " + e.getMessage());
            e.printStackTrace();
        }
        catch(Exception e)
        {
            System.err.println("A unknown problem was detected: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
