import javax.swing.*;
import java.awt.event.*;

public class UnitDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textName;
    private JTextField textAmount;
    private JComboBox comboType;
    private JComboBox comboMeasurement;

    private String unitName;
    private float unitAmount;
    private MeasurementType unitType;
    private Measurement unitMeasurement;
    private int exitState = 1;

    public UnitDialog() {
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
            unitName = textName.getText();
            unitAmount = Float.parseFloat(textAmount.getText());
            unitType = (MeasurementType) comboType.getSelectedItem();
            unitMeasurement = (Measurement) comboMeasurement.getSelectedItem();
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
        comboMeasurement.removeAllItems();

        MeasurementType measurementType = (MeasurementType) comboType.getSelectedItem();
        switch (measurementType) {
            case Mass -> {
                for (Measurement m : Measurements.mass) {
                    comboMeasurement.addItem(m);
                }
            }
            case Volume -> {
                for (Measurement m : Measurements.volume) {
                    comboMeasurement.addItem(m);
                }
            }
            case Quantity -> {
                for (Measurement m : Measurements.quantity) {
                    comboMeasurement.addItem(m);
                }
            }
        }
    }

    public String getUnitName() {
        return unitName;
    }

    public float getUnitAmount() {
        return unitAmount;
    }

    public MeasurementType getUnitType() {
        return unitType;
    }

    public Measurement getUnitMeasurement() {
        return unitMeasurement;
    }

    public static void main(String[] args) {
        UnitDialog dialog = new UnitDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public int displayAdd(JPanel parent) {
        this.pack();
        this.setLocationRelativeTo(parent);
        this.setTitle("Add Entry");
        this.setVisible(true);
        return exitState;
    }

    public int displayEdit(JPanel parent, Unit ingredient) {
        textName.setText(ingredient.getName());
        textAmount.setText(((Float) ingredient.getValue()).toString());
        comboType.setSelectedItem(ingredient.getMeasurement().getType());
        comboMeasurement.setSelectedItem(ingredient.getMeasurement());

        this.pack();
        this.setLocationRelativeTo(parent);
        this.setTitle("Edit Entry");
        this.setVisible(true);

        return exitState;
    }
}
