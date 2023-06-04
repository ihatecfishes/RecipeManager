import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MealPlanner extends JDialog {
    private JPanel contentPane;
    private JButton buttonAdd;
    private JButton buttonRemove;
    private JTabbedPane tabbedPane3;
    private JList<String> listPlanner;
    private JPanel Calendar;
    private JTextField textPath;
    private JTree treeRecipes;
    private Tree<Recipe> recipes;
    private HashMap<Date, ArrayList<String>> planner;
    private String path = "";
    private int exitState = -1;
    java.util.Calendar calendar = java.util.Calendar.getInstance();
    JDateChooser chosenDate = new JDateChooser(calendar.getTime());



    public MealPlanner(){
        setContentPane(contentPane);
        setModal(true);

        // Calendar
        chosenDate.setDateFormatString("dd/MM/yyyy");
        Calendar.add(chosenDate);

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

        treeRecipes.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeRecipes.getLastSelectedPathComponent();
                if (selectedNode == null)
                    return;

                String path = getPathFromTree(selectedNode);
                textPath.setText(path);
            }
        });

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date selectedDate = chosenDate.getDate();
                ArrayList<String> entries = planner.get(selectedDate);
                if (entries == null) {
                    entries = new ArrayList<>();
                }

                entries.add(getSelectedPath());

                planner.put(selectedDate, entries);
                updateList();
            }
        });

        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date selectedDate = chosenDate.getDate();
                ArrayList<String> entries = planner.get(selectedDate);
                if (entries == null) {
                    entries = new ArrayList<>();
                }

                if (listPlanner.getSelectedIndex() != -1) {
                    entries.remove(listPlanner.getSelectedIndex());

                }

                planner.put(selectedDate, entries);
                updateList();
            }
        });

        chosenDate.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                updateList();
            }
        });
    }

    private void onCancel() {
        // add your code here if necessary
        exitState = 0;
        dispose();
    }

    private void updateList() {
        DefaultListModel<String> listModel = new DefaultListModel<>();

        ArrayList<String> entries = planner.get(chosenDate.getDate());
        if (entries == null) {
            listPlanner.setModel(listModel);
            return;
        }

        for (String path : entries) {
            if (recipes.findNode(path).getData() == null) {
                continue;
            }
            listModel.addElement(recipes.findNode(path).getData().getName());
        }

        listPlanner.setModel(listModel);
    }

    private String getSelectedPath() {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeRecipes.getLastSelectedPathComponent();
        if (selectedNode == null)
            return "";

        String path = getPathFromTree(selectedNode);
        return path;
    }

    private void updateTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Recipes");
        addNodes(root, recipes.root);
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        treeRecipes.setModel(treeModel);
    }

    private void addNodes(DefaultMutableTreeNode parent, Tree.Node<Recipe> node) {
        if (node == null)
            return;

        for (Tree.Node<Recipe> child : node.getChildren()) {
            if (child.getData() != null) {
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child.getKey()); // Updated line
                parent.add(childNode);
            } else {
                String folderName = child.getName();
                DefaultMutableTreeNode folderNode = new DefaultMutableTreeNode(folderName);
                parent.add(folderNode);
                addNodes(folderNode, child);
            }
        }
    }

    private String getPathFromTree(DefaultMutableTreeNode node) {
        StringBuilder pathBuilder = new StringBuilder();
        Object[] nodes = node.getPath();
        for (int i = 1; i < nodes.length; i++) {
            DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) nodes[i];
            Object userObject = currentNode.getUserObject();
            if (userObject instanceof String) {
                String nodeName = (String) userObject;
                pathBuilder.append(nodeName);
                if (i != nodes.length - 1) {
                    pathBuilder.append("/");
                }
            }
        }
        return pathBuilder.toString();
    }

    public static void main(String[] args) {
        MealPlanner dialog = new MealPlanner();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public int display(JPanel parent) {
        updateTree();

        this.pack();
        this.setLocationRelativeTo(parent);
        this.setTitle("Meal Planner");
        this.setSize(600,400);
        this.setVisible(true);
        return exitState;
    }

    public Tree<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Tree<Recipe> recipes) {
        this.recipes = recipes;
    }

    public HashMap<Date, ArrayList<String>> getPlanner() {
        return planner;
    }

    public void setPlanner(HashMap<Date, ArrayList<String>> planner) {
        this.planner = planner;
    }

    public String getPath() {
        return path;
    }
}

