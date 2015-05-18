package data.test;

import data.DataSet;
import data.Example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miguel on 17-05-2015.
 */
public class TestData extends DataSet
{
    public TestData(String filePath)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            String line;
            int i = 0, j = 0; // counter for enum (i => VogalA or VogalO, j => how many examples of that vogal we have!)

            while ((line = br.readLine()) != null)
            {
                // Parse a training example and set a training type to it:
                TestExample example = new TestExample(line);
                example.setType(DataSet.DataType.intToType(i));

                m_examples.add(example);

                j++;

                if (j == 3)
                {
                    j = 0;
                    i++;
                    i = i % 2;
                }
            }
        }
        catch (IOException e)
        {
            System.err.println("File with path \"" + filePath + "\" could not be found!");
        }
    }
}
