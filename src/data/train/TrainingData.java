package data.train;

import data.DataClass;
import data.DataType;
import data.Example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miguel on 16-05-2015.
 */
public class TrainingData
{
    private List<Example> m_examples = new ArrayList<>();

    public TrainingData(String filePath)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            String line;
            int i = 0; // counter for enum

            while ((line = br.readLine()) != null)
            {
                // Parse a training example and set a training type to it:
                TrainingExample example = new TrainingExample(line);
                example.setType(DataType.intToType(i));

                m_examples.add(example);

                i++;
                i = i % 26;
            }
        }
        catch (IOException e)
        {
            System.err.println("File with path \"" + filePath + "\" could not be found!");
        }
    }
}
