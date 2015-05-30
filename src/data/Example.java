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
    protected DataSet.DataClass m_class;
    protected DataSet.DataType m_dataType;

    public Example()
    {
    }

    public Example(int subjectID, List<Double> attributes, DataSet.DataClass dataClass)
    {
        m_subjectID = subjectID;
        m_attributes.addAll(attributes);
        m_class = dataClass;
        m_dataType = null;
    }

    public void setType(DataSet.DataType type)
    {
        m_dataType = type;
    }

    public int getSubjectID()
    {
        return m_subjectID;
    }

    public List<Double> getAttributes()
    {
        return m_attributes;
    }

    public int getNumberAttributes()
    {
        return m_attributes.size();
    }

    public DataSet.DataClass getDataClass()
    {
        return m_class;
    }

    public DataSet.DataType getDataType()
    {
        return m_dataType;
    }

    // First element of the sublist: Parkinson, Second element of the sublist: Healthy
    public List<Double> getTargetList()
    {
        List<Double> targetList = new ArrayList<>();

        if (m_class == DataSet.DataClass.PARKINSON)
        {
            targetList.add(1.0);
            targetList.add(0.0);
        }
        else if (m_class == DataSet.DataClass.HEALTHY)
        {
            targetList.add(0.0);
            targetList.add(1.0);
        }
        else
            throw new IllegalArgumentException("Example::getTargetList - Invalid DataClass type");

        return targetList;
    }

    @Override
    public String toString()
    {
        StringBuilder string = new StringBuilder();

        for (Double attr: m_attributes)
        {
            string.append(attr);
            string.append(",");
        }

        List<Double> targetList = getTargetList();
        string.append(targetList.get(0)).append(",").append(targetList.get(1));

        return string.toString();
    }
}
