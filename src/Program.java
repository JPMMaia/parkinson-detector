import data.DataSet;
import data.Example;
import data.test.TestData;
import data.train.TrainingData;
import neuralNetwork.NeuralNetwork;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Miguel on 17-05-2015.
 */
public class Program
{
    private String m_filePath;

    public Program()
    {
    }

    public void run() throws InstantiationException, IllegalAccessException
    {
        // Read data:
        DataSet trainData = new TrainingData("data/train_data.txt");
        DataSet testData = new TestData("data/test_data.txt");

        // Normalize data:
        trainData.normalize();
        testData.normalize();

        // Get what we want:
        List<Example> newExamples = trainData.filterByType(DataSet.DataType.VowelA, DataSet.DataType.VowelO, DataSet.DataType.VowelU);
        DataSet trainData2 = new TrainingData(newExamples);

        trainData.toFile("data/new_train.txt");

        NeuralNetwork network = new NeuralNetwork();
        network.initialize(Arrays.asList(trainData.getNumAttributes(), trainData.getNumAttributes() + 20, 2), 0.4, 0.7);
        network.train(trainData, 0.001, 4000);
        System.out.println("Ja deu: " + (trainData.getNumAttributes() + 2));
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
