package neuralNetwork.utils;

public class RandomAssigner implements IWeightAssigner
{
    private static final java.util.Random s_RANDOM = new java.util.Random(System.currentTimeMillis());

    // Get a random weight between 0 and 1
    public Double assignWeight()
    {
        return s_RANDOM.nextDouble() - 0.5;
    }
}
