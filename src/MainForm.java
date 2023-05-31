import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;


public class MainForm {
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
    private JSpinner spinnerServings;
    private JTabbedPane tabbedPane3;
    private JTable tableIngredients;
    private JTable tableNutrition;
    private JEditorPane editorPane2;
    private JTextArea textArea1;
    private JTree recipeTree;
    //private JButton imagesButton;

    private Tree<Recipe> recipes = new Tree<>();
    private Tree<Ingredient> ingredients = new Tree<>();

    private JTree treeRecipes;
    private JButton buttonTAdd;
    private JButton buttonTRemove;
    private JButton buttonTEdit;

    private JList<Ingredient> listIngredients;

    public MainForm() {

        updateTree();
        updateIngredients();
        updateNutrition();

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
                    Recipe recipe = recipes.findNode(path).getData();
                    updateSelection(recipe);
                }

//                textField2.setText(path);
//
//                if (change) {
//                    int dialogResult = JOptionPane.showConfirmDialog(panelMain, "Do you want to save the changes?");
//                    if (dialogResult == JOptionPane.YES_OPTION) {
//                        // Save changes
//                        // TODO: Implement save logic
//                        saveRecipe();
//                    }
//                }

                change = false;
                buttonUpdate.setEnabled(true);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveRecipe();
            }
        });

        saveAsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files (*.txt)", "txt"));

                int result = fileChooser.showSaveDialog(panelMain);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    saveRecipeAs(selectedFile);
                }
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

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files (*.txt)", "txt"));

                int result = fileChooser.showOpenDialog(panelMain);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    openRecipe(selectedFile);
                }
            }
        });

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        /*
        imagesButton.addActionListener(new ActionListener() { // ActionListener for "Images" button
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("JPEG Images", "jpg", "jpeg"));

                int option = fileChooser.showOpenDialog(panelMain);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    displayImage(selectedFile);
                }
            }
        });
         */
    }

    class JPanelGradient extends JPanel {

        protected void paintComponent(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();

            Color color1 = new Color(52,143,80);
            Color color2 = new Color(86,180,211);
            GradientPaint gp = new GradientPaint(0,0, color1, 180, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0,0,width,height);
        }
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Recipe Manager");
        frame.setContentPane(new MainForm().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        /*
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // Set the color theme
        Color blueColor = new Color(84, 84, 180);
        Color purpleColor = new Color(120, 120, 255);

        UIManager.put("TabbedPane.selected", blueColor);
        UIManager.put("TabbedPane.contentAreaColor", purpleColor);
        UIManager.put("List.selectionBackground", blueColor);
        UIManager.put("List.selectionForeground", Color.WHITE);
        UIManager.put("TextArea.selectionBackground", blueColor);
        UIManager.put("TextArea.selectionForeground", Color.WHITE);
        UIManager.put("TextField.selectionBackground", purpleColor);
        UIManager.put("TextField.selectionForeground", Color.WHITE);
        UIManager.put("TextPane.selectionBackground", blueColor);
        UIManager.put("TextPane.selectionForeground", Color.WHITE);
        UIManager.put("EditorPane.selectionBackground", blueColor);
        UIManager.put("EditorPane.selectionForeground", Color.WHITE);
        UIManager.put("Tree.selectionBackground", blueColor);
        UIManager.put("Tree.selectionForeground", Color.WHITE);
         */

    }


    // Helper method to update the tree view
    private void updateTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Recipes");
        addNodes(root, recipes.root);
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        treeRecipes.setModel(treeModel);
    }

    private void updateIngredients() {
        String[] columns = new String[] {"Ingredient", "Amount", "Unit"};
        TableModel ingredientsModel = new AbstractTableModel() {
            @Override
            public int getRowCount() {
                // TODO: implement after update button is fixed
                return 3;
            }

            @Override
            public int getColumnCount() {
                return 3;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                // TODO: implement after update button is fixed
                return null;
            }

            @Override
            public String getColumnName(int column) {
                return columns[column];
            }
        };

        tableIngredients.setModel(ingredientsModel);
    }

    private void updateNutrition() {
        String[] columns = new String[] {"Nutrition", "Amount", "Unit"};
        TableModel nutritionModel = new AbstractTableModel() {
            @Override
            public int getRowCount() {
                // TODO: implement after update button is fixed
                return 3;
            }

            @Override
            public int getColumnCount() {
                return 3;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                // TODO: implement after update button is fixed
                return null;
            }

            @Override
            public String getColumnName(int column) {
                return columns[column];
            }
        };

        tableIngredients.setModel(nutritionModel);
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
        textNotes.setText("");
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

    // Helper method to save the current recipe
    private void saveRecipe() {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeRecipes.getLastSelectedPathComponent();
        if (selectedNode == null || !(selectedNode.getUserObject() instanceof Recipe))
            return;

        Recipe recipe = (Recipe) selectedNode.getUserObject();
        String filePath = recipe.getName() + ".txt";

        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(textBody.getText());
            writer.close();
            JOptionPane.showMessageDialog(panelMain, "Recipe saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panelMain, "Failed to save recipe.");
        }
    }

    // Helper method to save the current recipe as a new file
    private void saveRecipeAs(File selectedFile) {
        if (selectedFile == null)
            return;

        String filePath = selectedFile.getAbsolutePath();

        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(textBody.getText());
            writer.close();
            JOptionPane.showMessageDialog(panelMain, "Recipe saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panelMain, "Failed to save recipe.");
        }
    }

    // Helper method to open a recipe file
    private void openRecipe(File file) {
        try {
            String content = new String(java.nio.file.Files.readAllBytes(file.toPath()));
            textBody.setText(content);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panelMain, "Failed to open recipe file.");
        }
    }

    /*
    // Helper method to display the selected image
    private void displayImage(File imageFile) {
        try {
            String imagePath = imageFile.getAbsolutePath();
            String imageTag = "<img src='file:" + imagePath + "'>";

            Document doc = editorPane2.getDocument();
            doc.insertString(doc.getLength(), imageTag, null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
     */
}
