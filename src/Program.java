import data.test.TestData;
import data.train.TrainingData;

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
        TrainingData train = new TrainingData("data/train_data.txt");
        TestData test = new TestData("data/test_data.txt");

        System.out.println("lol");
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
