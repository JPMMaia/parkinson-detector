package neuralNetwork;

import data.DataSet;
import data.Example;
import data.test.TestData;
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

    @Test
    public void selectOnePerType()
    {
        DataSet testData = new TestData("data/test_data.txt");

        testData = new TestData(testData.selectOneSamplePerDataType());

        Assert.assertEquals(56, testData.getExamples().size());
    }

    @Test
    public void joinByType()
    {
        DataSet testData = new TestData("data/test_data.txt");

        DataSet testDataTemp = new TestData(testData.selectOneSamplePerDataType());
        testData = new TestData(testDataTemp.groupAtributesBySubjectID(DataSet.DataType.VowelA, DataSet.DataType.VowelO));

        Assert.assertEquals(28, testData.getExamples().size());
        Assert.assertEquals(52, testData.getNumAttributes());

        Assert.assertEquals(testDataTemp.getExamples().get(0).getAttributes().get(0), testData.getExamples().get(0).getAttributes().get(0), 0.000000000001);
        Assert.assertEquals(testDataTemp.getExamples().get(1).getAttributes().get(0), testData.getExamples().get(0).getAttributes().get(26), 0.000000000001);
        Assert.assertEquals(testDataTemp.getExamples().get(1).getAttributes().get(25), testData.getExamples().get(0).getAttributes().get(51), 0.000000000001);

        Assert.assertEquals(testDataTemp.getExamples().get(4).getAttributes().get(0), testData.getExamples().get(2).getAttributes().get(0), 0.000000000001);
        Assert.assertEquals(testDataTemp.getExamples().get(5).getAttributes().get(0), testData.getExamples().get(2).getAttributes().get(26), 0.000000000001);
        Assert.assertEquals(testDataTemp.getExamples().get(5).getAttributes().get(25), testData.getExamples().get(2).getAttributes().get(51), 0.000000000001);


    }
}
