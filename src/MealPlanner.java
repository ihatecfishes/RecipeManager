import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MealPlanner extends JFrame {
    private JPanel jPCalendar;
    private JPanel jPMealPlan;
    private JPanel panelMain;
    private JTabbedPane tabbedPane1;
    private JTree treeRecipes;
    private JButton newButton;
    private JButton openButton;
    private JButton saveButton;
    private JButton saveAsButton;
    private JButton buttonAdd;
    private JButton removeButton;
    private JButton addFolderButton;
    private JButton buttonUpdate;
    private JTextField textField2;
    private JButton searchButton;
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
    private JTextField textTitle;
    private JTextField textDate;
    private JButton setButton;

    Calendar cld = Calendar.getInstance();
    JDateChooser chosenDate = new JDateChooser(cld.getTime());



    public MealPlanner(){
        setContentPane(jPMealPlan);
        setTitle("Meal Planner");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(900,600);
        setVisible(true);

        // Calendar
        chosenDate.setDateFormatString("dd/MM/yyyy");
        jPCalendar.add(chosenDate);

        SetDate();

    }

    public void SetDate(){
        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dt = dateFormat.format(chosenDate.getDate());
                textDate.setText(dt);
            }
        });
    }

    public static void main(String[] args){
        new MealPlanner();
    }
}
