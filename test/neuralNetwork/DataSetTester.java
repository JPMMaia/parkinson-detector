package neuralNetwork;

import data.DataSet;
import data.Example;
import data.train.TrainingData;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Miguel on 18-05-2015.
 */
public class DataSetTester
{
    @Test
    public void testRangeNormalization()
    {
        DataSet trainData = new TrainingData("data/train_data.txt");
        trainData.normalize();

        for (Example example : trainData.getExamples())
            for (Double attribute : example.getAttributes())
                Assert.assertTrue(attribute <= 1.0 && attribute >= 0.0);
    }

    @Test
    public void testTargetOutput()
    {
        DataSet trainData = new TrainingData("data/train_data.txt");

        List<Double> list1 =  trainData.getExamples().get(0).getTargetList();
        List<Double> list2 =  trainData.getExamples().get(520).getTargetList();

        Assert.assertEquals(1.0, list1.get(0), 0.00000001);
        Assert.assertEquals(0.0, list1.get(1), 0.00000001);
        Assert.assertEquals(0.0, list2.get(0), 0.00000001);
        Assert.assertEquals(1.0, list2.get(1), 0.00000001);
    }
}
