package neuralNetwork;

public class Random
{
    private static final java.util.Random s_RANDOM = new java.util.Random(System.currentTimeMillis());

    public static Double getRandomWeight()
    {
        return s_RANDOM.nextDouble();
    }
}
