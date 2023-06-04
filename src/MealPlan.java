import com.toedter.calendar.JDateChooser;

import javax.swing.*;

public class MealPlan extends JFrame {
    private JPanel MealPlanner;
    private JPanel panelMain;
    private JTextPane textNotes;
    private JButton buttonTAdd;
    private JButton buttonTRemove;
    private JButton buttonTEdit;
    private JTabbedPane tabbedPane3;
    private JList list1;
    private JPanel Calendar;
    private JTextField textField2;
    private JButton searchButton;

    java.util.Calendar cld = java.util.Calendar.getInstance();
    JDateChooser chosenDate = new JDateChooser(cld.getTime());



    public MealPlan(){
        setContentPane(MealPlanner);
        setTitle("Meal Plan");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(600,400);
        setVisible(true);

        // Calendar
        chosenDate.setDateFormatString("dd/MM/yyyy");
        Calendar.add(chosenDate);

        //SetDate();

    }

//    public void SetDate(){
//        setButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//                String dt = dateFormat.format(chosenDate.getDate());
//                textDate.setText(dt);
//            }
//        });
//    }

    public static void main(String[] args){
        new MealPlan();
    }


}

