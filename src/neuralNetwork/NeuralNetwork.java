package neuralNetwork;

import data.DataSet;
import data.Example;
import neuralNetwork.utils.IWeightAssigner;
import neuralNetwork.utils.RandomAssigner;

import java.util.*;

public class NeuralNetwork
{
    private List<Layer> m_layers;
    private IWeightAssigner m_initialWeightAssigner;

    public NeuralNetwork()
    {
        m_initialWeightAssigner = new RandomAssigner(); // The default one!
    }

    public void initialize(List<Integer> topology, Double learningRate, Double momentum)
    {
        // Create layers list:
        m_layers = new ArrayList<>(topology.size());

        // Create input layer:
        Layer inputLayer = new Layer();
        inputLayer.initialize(topology.get(0), learningRate, momentum);
        m_layers.add(inputLayer);

        Layer previousLayer = inputLayer;
        for(int i = 1; i < topology.size(); i++)
        {
            // Number of neurons of the layer:
            int numberNeurons = topology.get(i);

            // Check if this is the output layer:
            boolean isOutputLayer = (i == topology.size() - 1);

            Layer layer = new Layer();
            layer.initialize(numberNeurons, previousLayer, m_initialWeightAssigner, isOutputLayer, learningRate, momentum);

            // Add new layer to the list:
            m_layers.add(layer);

            previousLayer = layer;
        }
    }

    public void train(DataSet trainData, double desiredError, int maxIterations)
    {
        ClassificationReport reportTrain;
        List<Example> m_shuffledExamples = new ArrayList<>(trainData.getExamples());
        int iteration = 0;

        do
        {
            // Randomize examples:
            Collections.shuffle(m_shuffledExamples);
            // Propagate:
            for (Example example : m_shuffledExamples)
            {
                feedForward(example.getAttributes(), false);
                backPropagate(example.getTargetList());
            }

            iteration++;

            reportTrain = getClassificationReport(trainData);

            System.out.println("Iter " + iteration + " c/ erro " + reportTrain.getMSE());

        } while(reportTrain.getMSE() > desiredError && iteration < maxIterations);

        // Check current error:
        double finalError = reportTrain.getMSE();
        System.out.println("MSE: " + finalError);
    }

    public ClassificationReport test(DataSet data)
    {
        return getClassificationReport(data);
    }

    public ClassificationReport getClassificationReport(DataSet data)
    {
        ClassificationReport report = new ClassificationReport();

        // Check current error:
        Double meanSquaredError = 0.0;
        for (Example example : data.getExamples())
        {
            feedForward(example.getAttributes(), false);
            meanSquaredError += calculateIterationError(example.getTargetList()); // Get the mean of the MSE

            double parkinsonOutput = getOutputLayer().getNeurons().get(0).getOutputValue();
            double healthyOutput = getOutputLayer().getNeurons().get(1).getOutputValue();

            if (example.getDataClass() == DataSet.DataClass.PARKINSON)
            {
                report.incTotalPositives();

                if (parkinsonOutput >= healthyOutput)
                    report.incTruePositives();
            }
            else
            {
                report.incTotalNegatives();

                if (parkinsonOutput < healthyOutput)
                    report.incTrueNegatives();
            }
        }

        meanSquaredError /= (data.getExamples().size());
        report.setMSE(meanSquaredError);

        return report;
    }

    public void feedForward(List<Double> inputValues, boolean prints)
    {
        Layer inputLayer = getInputLayer();
        if(!inputLayer.setOutputValues(inputValues))
            throw new IllegalArgumentException("Error in feedForward algorithm: inputValues do not match the number of input nodes");

        for(int i = 1; i < m_layers.size(); i++)
        {
            Layer layer = m_layers.get(i);
            layer.calculateOutputValues();
        }

        if (prints)
            System.out.println("P: " + getOutputLayer().getNeurons().get(0).getOutputValue() + " H: " + getOutputLayer().getNeurons().get(1).getOutputValue());
    }

    public void backPropagate(List<Double> targetValues)
    {
        Layer outputLayer = getOutputLayer();

        // Calculate output layer cost:
        //Double mse = outputLayer.getClassificationReport(targetValues);
        //System.out.println("Erro: " + outputLayer.calculateCost(targetValues));
        //System.out.println("Erro: " + mse);

        // Calculate output layer gradients:
        outputLayer.calculateGradientValues(targetValues);

        // Calculate hidden layer gradients:
        for (int i = m_layers.size() - 2; i > 0; i--)
        {
            Layer hiddenLayer = m_layers.get(i);

            hiddenLayer.calculateGradientValues();
        }

        // Update all connection weights:
        for (int i = m_layers.size() - 2; i >= 0; i--)
        {
            Layer currentLayer = m_layers.get(i);
            currentLayer.updateConnectionsWeights();
        }

    }

    public double calculateIterationError(List<Double> targetValues)
    {
        return getOutputLayer().calculateError(targetValues);
    }

    public List<Layer> getLayers()
    {
        return m_layers;
    }

    public Layer getInputLayer()
    {
        return m_layers.get(0);
    }

    public Layer getOutputLayer()
    {
        return m_layers.get(m_layers.size() - 1);
    }

    public void setWeightAssigner(IWeightAssigner testAssigner)
    {
        m_initialWeightAssigner = testAssigner;
    }
}
