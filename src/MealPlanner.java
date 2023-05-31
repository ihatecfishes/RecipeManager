import javax.swing.*;

public class MealPlanner extends JFrame {
    private JPanel panelMain;
    private JTabbedPane tabbedPane1;
    private JButton newButton;
    private JButton openButton;
    private JButton saveButton;
    private JButton saveAsButton;
    private JTextField textTitle;
    private JButton buttonAdd;
    private JButton removeButton;
    private JButton addFolderButton;
    private JButton buttonUpdate;
    private JTextField textField2;
    private JButton button1;
    private JTextPane textNotes;
    private JButton buttonTAdd;
    private JButton buttonTRemove;
    private JButton buttonTEdit;
    private JSpinner spinnerServings;
    private JComboBox comboMeasurement;
    private JTextArea textArea1;
    private JTextArea textBody;
    private JEditorPane editorPane2;
    private JTabbedPane tabbedPane3;
    private JTable tableIngredients;
    private JTable tableNutrition;
    private JPanel Calendar;
    private JPanel JPMealPlan;

    public MealPlanner(){
        setContentPane(JPMealPlan);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(900,600);
        setVisible(true);
    }

    public static void main(String[] args){
        new MealPlanner();
    }
}
