package data.test;

import data.DataSet;
import data.Example;

/**
 * Created by Miguel on 17-05-2015.
 */
public class TestExample extends Example
{
    private static int s_numberOfColumns = 28;

    public TestExample(String line)
    {
        String[] fields = line.split(",");

        if (fields.length != s_numberOfColumns)
            throw new IllegalArgumentException("Invalid number of columns in test file: " + fields.length);


        for (int i = 0; i < fields.length; i++)
        {
            if (i == 0)
                m_subjectID = Integer.parseInt(fields[i]);
            else if (i >= 1 && i <= 26)
                m_attributes.add(Double.parseDouble(fields[i]));
            else if (i == 27)
            {
                int classValue = Integer.parseInt(fields[i]);

                if (classValue == 1)
                    m_class = DataSet.DataClass.PARKINSON;
                else if (classValue == 0)
                    m_class = DataSet.DataClass.HEALTHY;
                else
                    throw new IllegalArgumentException("Invalid class value in test file: " + classValue);

            }
        }
    }
}
