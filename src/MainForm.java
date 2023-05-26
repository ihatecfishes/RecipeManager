import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.awt.event.*;

public class MainForm {
    private Tree<Recipe> recipes = new Tree<>();
    private Boolean change = false;
    private JPanel panelMain;
    private JTree treeRecipes;
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
                // Find unique name
                int number = 1;
                Tree.Node<Recipe> rootNode = recipes.getRoot();
                if (rootNode != null) {
                    int childCount = rootNode.getChildren().size();
                    number = childCount + 1;
                }

                // Add recipe
                Recipe newRecipe = new Recipe("Untitled Recipe " + number);
                recipes.addNode(newRecipe.getName(), newRecipe, textField2.getText());

                updateTree();
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

                change = false;
                updateChanges();
            }

            // Helper method to get the path of the selected node
            private String getPathFromTree(DefaultMutableTreeNode node) {
                StringBuilder pathBuilder = new StringBuilder();
                TreeNode[] nodes = node.getPath();
                for (int i = 0; i < nodes.length; i++) {
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
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeRecipes.getLastSelectedPathComponent();
                if (selectedNode == null || !(selectedNode.getUserObject() instanceof Recipe))
                    return;

                Recipe recipe = (Recipe) selectedNode.getUserObject();
                recipe.setName(textTitle.getText());
                recipe.setContent(textBody.getText());
                recipe.setNotes(textNotes.getText());

                // Update the node's key with the new recipe name
                selectedNode.setUserObject(recipe.getName());

                updateTree();
                change = false;
                updateChanges();
            }
        });
    }

    public void updateTree() {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Recipes");

        Tree.Node<Recipe> rootRecipeNode = recipes.getRoot();
        if (rootRecipeNode != null) {
            addNodeToTree(rootNode, rootRecipeNode);
        }

        treeRecipes.setModel(new DefaultTreeModel(rootNode));
    }

    private void addNodeToTree(DefaultMutableTreeNode parentNode, Tree.Node<Recipe> recipeNode) {
        Recipe recipe = recipeNode.getData();
        DefaultMutableTreeNode recipeTreeNode = new DefaultMutableTreeNode(recipe);

        for (Tree.Node<Recipe> childNode : recipeNode.getChildren()) {
            addNodeToTree(recipeTreeNode, childNode);
        }

        parentNode.add(recipeTreeNode);
    }

    public void updateSelection(Recipe recipe) {
        textTitle.setText(recipe.getName());
        textBody.setText(recipe.getContent());
        textNotes.setText(recipe.getNotes());
    }

    public void clearFields() {
        textTitle.setText("");
        textBody.setText("");
        textNotes.setText("");
    }

    public void updateChanges() {
        if (change) {
            saveButton.setEnabled(true);
            saveAsButton.setEnabled(true);
        } else {
            saveButton.setEnabled(false);
            saveAsButton.setEnabled(false);
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
