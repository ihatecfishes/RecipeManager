import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainForm {

    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private boolean change = false;
    private JPanel panelMain;
    private JList listRecipes;
    private JTabbedPane tabbedPane1;
    private JTextArea textBody;
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
    private JButton addFolderButton; // New "Add Folder" button
    private JSpinner spinner1;
    private JTabbedPane tabbedPane3;
    private JTable table1;
    private JTable table2;
    private JEditorPane editorPane2;
    private JTextArea textArea1;
    private JTree recipeTree;

    private Tree<Recipe> recipes = new Tree<>();

    private JTree treeRecipes;

    private JList<Ingredient> listIngredients;

    public MainForm() {
        updateTree();
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeRecipes.getLastSelectedPathComponent();
                if (selectedNode == null || !(selectedNode.getUserObject() instanceof Recipe))
                    return;

                Recipe recipe = (Recipe) selectedNode.getUserObject();
                recipes.removeNode(recipe.getName(), textField2.getText());

                updateTree();
                clearFields();
            }
        });

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField2.getText().isEmpty()) {
                    // Adding a recipe
                    // Find unique name
                    int number = 1;
                    Tree.Node<Recipe> rootNode = recipes.root;
                    if (rootNode != null) {
                        int childCount = rootNode.getChildren().size();
                        number = childCount + 1;
                    }

                    // Add recipe
                    Recipe newRecipe = new Recipe("Untitled Recipe " + number);
                    recipes.addNode(newRecipe.getName(), newRecipe, textField2.getText());
                } else {
                    // Adding a folder
                    String folderName = textField2.getText();
                    recipes.addNode(folderName, null, textField2.getText());
                }

                updateTree();
            }
        });

        addFolderButton.addActionListener(new ActionListener() { // ActionListener for "Add Folder" button
            @Override
            public void actionPerformed(ActionEvent e) {
                String folderName = JOptionPane.showInputDialog(panelMain, "Enter folder name:");
                if (folderName != null && !folderName.isEmpty()) {
                    recipes.addNode(folderName, null, textField2.getText());
                    updateTree();
                }
            }
        });

        treeRecipes.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeRecipes.getLastSelectedPathComponent();
                if (selectedNode == null)
                    return;

                Object userObject = selectedNode.getUserObject();
                String path = getPathFromTree(selectedNode);

                if (userObject instanceof Recipe) {
                    Recipe recipe = (Recipe) userObject;
                    updateSelection(recipe);
                }

                textField2.setText(path);

                if (change) {
                    int dialogResult = JOptionPane.showConfirmDialog(panelMain, "Do you want to save the changes?");
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        // Save changes
                        // TODO: Implement save logic
                    }
                }

                change = false;
                buttonUpdate.setEnabled(false);
            }
        });



        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeRecipes.getLastSelectedPathComponent();
                if (selectedNode == null || !(selectedNode.getUserObject() instanceof Recipe))
                    return;

                Recipe recipe = (Recipe) selectedNode.getUserObject();
                String newTitle = textTitle.getText();
                String newBody = textBody.getText();
                String newNotes = textNotes.getText();

                recipe.setName(newTitle);
                recipe.setContent(newBody);
                recipe.setNotes(newNotes);

                updateTree();
            }
        });

    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (UnsupportedLookAndFeelException e) {
        } catch (ClassNotFoundException e) {
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        }
        JFrame frame = new JFrame("Recipe Manager");
        frame.setContentPane(new MainForm().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    // Helper method to update the tree view
    private void updateTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Recipes");
        addNodes(root, recipes.root);
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        treeRecipes.setModel(treeModel);
    }

    // Helper method to add nodes recursively to the tree view
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


    // Helper method to update the selection fields
    private void updateSelection(Recipe recipe) {
        textTitle.setText(recipe.getName());
        textBody.setText(recipe.getContent());
        textNotes.setText(recipe.getNotes());
    }

    // Helper method to clear the selection fields
    private void clearFields() {
        textTitle.setText("");
        textBody.setText("");
    }

    // Helper method to update the change flag and button
    private void updateChanges() {
        change = true;
        buttonUpdate.setEnabled(true);
    }

    // Helper method to get the path from the selected node
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
}
