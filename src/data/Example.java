package data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miguel on 17-05-2015.
 */
public class Example
{
    protected int m_subjectID;
    protected List<Double> m_attributes = new ArrayList<>();
    protected DataClass m_class;
    protected DataType m_dataType;

    public void setType(DataType type)
    {
        m_dataType = type;
    }
}
