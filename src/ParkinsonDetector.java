import data.test.TestData;
import data.train.TrainingData;

/**
 * Created by Miguel on 17-05-2015.
 */
public class ParkinsonDetector
{
    private String m_filePath;

    public ParkinsonDetector(String filePath)
    {
        m_filePath = filePath;
    }

    public void run()
    {
        TrainingData lol = new TrainingData("data/train_data.txt");
        TestData lol2 = new TestData("data/test_data.txt");

        System.out.println("lol");
    }

    public static void main(String[] args)
    {
        try
        {
            ParkinsonDetector detector = new ParkinsonDetector("lol");
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
