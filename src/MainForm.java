import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainForm {
    private ArrayList<Recipe> recipes = new ArrayList<>();
    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private boolean change = false;
    private JPanel panelMain;
    private JList listRecipes;
    private JTabbedPane tabbedPane1;
    private JTextPane textBody;
    private JButton newButton;
    private JButton openButton;
    private JButton saveButton;
    private JButton saveAsButton;
    private JTextPane textNotes;
    private JTextField textTitle;
    private JButton buttonAdd;
    private JButton removeButton;
    private JTextField textField2;
    private JButton button1;
    private JButton buttonUpdate;
    private JSpinner spinner1;
    private JTabbedPane tabbedPane2;
    private JTabbedPane tabbedPane3;
    private JTable table1;
    private JTable table2;
    private JTextPane textPane1;

    public MainForm() {


        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selection = listRecipes.getSelectedIndex();
                if (selection >= 0) {
                    recipes.remove(selection);
                    updateList();
                    textTitle.setText("");
                    textBody.setText("");
                    textNotes.setText("");
                }
            }
        });

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Find unique name
                int number = 1;
                for (Recipe recipe : recipes) {
                    if (recipe.getName().startsWith("Untitled Recipe ")) {
                        try {
                            int smallest = Integer.parseInt(recipe.getName().split(" ")[2]);
                            if (number == smallest) {
                                number++;
                            } else {
                                break;
                            }
                        } catch (NumberFormatException ex) {
                            continue;
                        }

                    }
                }

                // Add recipe
                Recipe newRecipe = new Recipe("Untitled Recipe " + number);
                recipes.add(newRecipe);
                updateList();
            }
        });

        listRecipes.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selection = listRecipes.getSelectedIndex();

                updateSelection(selection);

                change = false;
                updateChanges();
            }
        });

        DocumentListener contentChange = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                change = true;
                updateChanges();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                change = true;
                updateChanges();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                change = true;
                updateChanges();
            }
        };

        textTitle.getDocument().addDocumentListener(contentChange);
        textBody.getDocument().addDocumentListener(contentChange);
        textNotes.getDocument().addDocumentListener(contentChange);
        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selection = listRecipes.getSelectedIndex();
                recipes.get(selection).setName(textTitle.getText());
                recipes.get(selection).setContent(textBody.getText());
                recipes.get(selection).setNotes(textNotes.getText());

                updateList();
                change = false;
                updateChanges();
            }
        });
    }

    public void updateList() {
        DefaultListModel<Recipe> listModel = new DefaultListModel<>();
        int recipesLength = recipes.size();

        for (int i = 0; i < recipesLength; i++) {
            listModel.addElement(recipes.get(i));
        }

        int previousSelection = listRecipes.getSelectedIndex();
        listRecipes.setModel(listModel);
        listRecipes.setSelectedIndex(previousSelection);
    }

    public void updateSelection(int selection) {
        if (selection == -1) {
            // No selection
            textTitle.setText("");
            textBody.setText("");
            textNotes.setText("");
            textTitle.setEnabled(false);
            textBody.setEnabled(false);
            textNotes.setEnabled(false);
        } else {
            textTitle.setText(recipes.get(selection).getName());
            textBody.setText(recipes.get(selection).getContent());
            textNotes.setText(recipes.get(selection).getNotes());

            textTitle.setEnabled(true);
            textBody.setEnabled(true);
            textNotes.setEnabled(true);
        }
    }

    public void updateChanges() {
        if (change) {
            buttonUpdate.setEnabled(true);
        } else {
            buttonUpdate.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (UnsupportedLookAndFeelException e) {
        } catch (ClassNotFoundException e) {
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        }

        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new MainForm().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
