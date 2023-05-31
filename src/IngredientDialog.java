import javax.swing.*;
import java.awt.event.*;

public class IngredientDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textName;
    private JTextField textAmount;
    private JComboBox comboType;
    private JComboBox comboUnit;

    private String ingredientName;
    private float ingredientAmount;
    private MeasurementType ingredientType;
    private Measurement ingredientUnit;
    private int exitState = 1;

    public IngredientDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        // Fill comboType options
        for (MeasurementType measurementType : MeasurementType.values()) {
            comboType.addItem(measurementType);
        }

        updateUnits();

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        comboType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUnits();
            }
        });
    }

    private void onOK() {
        // Check if all fields are filled
        if (textName.getText().isEmpty() || textAmount.getText().isEmpty()) {
            JOptionPane.showMessageDialog(contentPane,
                    "All fields need to be filled.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            ingredientName = textName.getText();
            ingredientAmount = Float.parseFloat(textAmount.getText());
            ingredientType = (MeasurementType) comboType.getSelectedItem();
            ingredientUnit = (Measurement) comboUnit.getSelectedItem();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(contentPane,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        exitState = 0;
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        exitState = 1;
        dispose();
    }

    private void updateUnits() {
        comboUnit.removeAllItems();

        MeasurementType measurementType = (MeasurementType) comboType.getSelectedItem();
        switch (measurementType) {
            case Mass -> {
                for (Measurement measurement : Measurements.mass) {
                    comboUnit.addItem(measurement);
                }
            }
            case Volume -> {
                for (Measurement measurement : Measurements.volume) {
                    comboUnit.addItem(measurement);
                }
            }
            case Quantity -> {
                for (Measurement measurement : Measurements.quantity) {
                    comboUnit.addItem(measurement);
                }
            }
        }
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public float getIngredientAmount() {
        return ingredientAmount;
    }

    public MeasurementType getIngredientType() {
        return ingredientType;
    }

    public Measurement getIngredientUnit() {
        return ingredientUnit;
    }

    public static void main(String[] args) {
        IngredientDialog dialog = new IngredientDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public int display(JPanel parent) {
        this.pack();
        this.setLocationRelativeTo(parent);
        this.setTitle("Add Ingredient");
        this.setVisible(true);
        return exitState;
    }
}
