package data.train;

import data.DataClass;
import data.DataType;
import data.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miguel on 17-05-2015.
 */
public class TrainingExample extends Example
{
    private static int s_numberOfColumns = 29;

    public TrainingExample(String line)
    {
        String[] fields = line.split(",");

        if (fields.length != s_numberOfColumns)
            throw new IllegalArgumentException("Invalid number of columns in train file: " + fields.length);


       for (int i = 0; i < fields.length; i++)
        {
            // ignore index 27 because it is UPDRS
            if (i == 0)
                m_subjectID = Integer.parseInt(fields[i]);
            else if (i >= 1 && i <= 26)
                m_attributes.add(Double.parseDouble(fields[i]));
            else if (i == 28)
            {
                int classValue = Integer.parseInt(fields[i]);

                if (classValue == 1)
                    m_class = DataClass.PARKINSON;
                else if (classValue == 0)
                    m_class = DataClass.HEALTHY;
                else
                    throw new IllegalArgumentException("Invalid class value in train file: " + classValue);

            }
        }
    }
}
