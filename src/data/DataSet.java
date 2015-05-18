package data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miguel on 18-05-2015.
 */
public abstract class DataSet
{
    protected List<Example> m_examples = new ArrayList<>();

    public void normalize()
    {
        // Find max/min value for each attribute:
        List<Double> max = new ArrayList<>(m_examples.get(0).getAttributes());
        List<Double> min = new ArrayList<>(m_examples.get(0).getAttributes());

        for (Example currentExample : m_examples)
        {
            List<Double> currentAttributes = currentExample.getAttributes();

            for (int j = 0; j < currentAttributes.size(); j++)
            {
                Double currentAttribute = currentAttributes.get(j);
                Double maxAttribute = max.get(j);
                Double minAttribute = min.get(j);

                // Update max:
                if (currentAttribute > maxAttribute)
                    max.set(j, currentAttribute);

                // Update min:
                if (currentAttribute < minAttribute)
                    min.set(j, currentAttribute);
            }
        }

        // Normalize:
        for (Example currentExample : m_examples)
        {
            List<Double> currentAttributes = currentExample.getAttributes();

            for (int j = 0; j < currentAttributes.size(); j++)
            {
                Double currentAttribute = currentAttributes.get(j);
                Double maxAttribute = max.get(j);
                Double minAttribute = min.get(j);

                Double newValue = (currentAttribute - minAttribute) / (maxAttribute - minAttribute);
                currentAttributes.set(j, newValue);
            }
        }
    }

    public List<Example> getExamples()
    {
        return m_examples;
    }

    public int getNumAttributes()
    {
        return m_examples.get(0).getNumberAttributes();
    }


    public enum DataClass
    {
        PARKINSON, HEALTHY
    }

    public enum DataType
    {
        VowelA, VowelO, VowelU,
        Number1, Number2, Number3, Number4, Number5, Number6, Number7, Number8, Number9, Number10,
        Phrase1, Phrase2, Phrase3, Phrase4,
        Word1, Word2, Word3, Word4, Word5, Word6, Word7, Word8, Word9;

        public static DataType intToType(int type)
        {
            switch(type)
            {
                case 0:
                    return VowelA;
                case 1:
                    return VowelO;
                case 2:
                    return VowelU;
                case 3:
                    return Number1;
                case 4:
                    return Number2;
                case 5:
                    return Number3;
                case 6:
                    return Number4;
                case 7:
                    return Number5;
                case 8:
                    return Number6;
                case 9:
                    return Number7;
                case 10:
                    return Number8;
                case 11:
                    return Number9;
                case 12:
                    return Number10;
                case 13:
                    return Phrase1;
                case 14:
                    return Phrase2;
                case 15:
                    return Phrase3;
                case 16:
                    return Phrase4;
                case 17:
                    return Word1;
                case 18:
                    return Word2;
                case 19:
                    return Word3;
                case 20:
                    return Word4;
                case 21:
                    return Word5;
                case 22:
                    return Word6;
                case 23:
                    return Word7;
                case 24:
                    return Word8;
                case 25:
                    return Word9;

                default:
                    throw new IllegalArgumentException("Invalid index to enum: " + type);
            }
        }
    }
}
