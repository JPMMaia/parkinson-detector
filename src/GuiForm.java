import javax.swing.*;
import java.awt.*;

/**
 * Created by Miguel on 29-05-2015.
 */
public class GuiForm extends JFrame
{
    private JPanel Container;
    private JPanel rootPanel;
    private JTextArea textArea1;
    private JTextField textField1;
    private JPanel Network;
    private JButton trainAndTestButton;
    private JSpinner spinner3;
    private JSpinner spinner4;
    private JCheckBox vowelACheckBox;
    private JCheckBox vowelOCheckBox;
    private JCheckBox vowelUCheckBox;
    private JCheckBox number1CheckBox;
    private JCheckBox number2CheckBox;
    private JCheckBox number3CheckBox;
    private JCheckBox number4CheckBox;
    private JCheckBox number5CheckBox;
    private JCheckBox number6CheckBox;
    private JCheckBox number7CheckBox;
    private JCheckBox number8CheckBox;
    private JCheckBox number9CheckBox;
    private JCheckBox number10CheckBox;
    private JCheckBox phrase1CheckBox;
    private JCheckBox phrase2CheckBox;
    private JCheckBox phrase3CheckBox;
    private JCheckBox phrase4CheckBox;
    private JCheckBox word2CheckBox;
    private JCheckBox word3CheckBox;
    private JCheckBox word4CheckBox;
    private JCheckBox word5CheckBox;
    private JCheckBox word6CheckBox;
    private JCheckBox word8CheckBox;
    private JCheckBox word7CheckBox;
    private JCheckBox word9CheckBox;
    private JCheckBox word1CheckBox;
    private JRadioButton allAtributesRadioButton;
    private JRadioButton allAtributesAORadioButton;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;

    public GuiForm()
    {
        super("Maiguel Parkinson Detector 1.0");
        setPreferredSize(new Dimension(900, 500));
        setContentPane(rootPanel);

        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setVisible(true);
    }

    private void createUIComponents()
    {
        textArea1 = new JTextArea();
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);

        spinner3 = new JSpinner();
        spinner3.setModel(new SpinnerNumberModel(0.1, 0.0, 1.0, 0.05));

        spinner4 = new JSpinner();
        spinner4.setModel(new SpinnerNumberModel(0.7, 0.0, 1.0, 0.05));
    }
}
