import java.util.ArrayList;
import java.util.List;

class Tree<T> {
    Node<T> root;

    public Tree() {
        root = null;
    }

    public void addNode(String key, String path) {
        addNode(key, null, path);
    }

    public void addNode(String key, T data, String path) {
        if (root == null) {
            root = new Node<>(key, data);
            return;
        }

        Node<T> parentNode = findNode(root, path);

        Node<T> newNode = new Node<>(key, data);
        parentNode.addChild(newNode);
    }

    public void removeNode(String key, String path) {
        Node<T> parentNode = findNode(root, path);


        parentNode.removeChild(key);
    }

    public Node<T> findNode(String path) {
        return findNode(root, path);
    }

    private Node<T> findNode(Node<T> currentNode, String path) {
        if (currentNode == null || path == null) {
            return null;
        }

        if (path.isEmpty()) {
            return currentNode;
        }

        int delimiterIndex = path.indexOf('/');
        if (delimiterIndex == -1) {
            return currentNode.getChild(path);
        }

        String currentKey = path.substring(0, delimiterIndex);
        String remainingPath = path.substring(delimiterIndex + 1);

        Node<T> nextNode = currentNode.getChild(currentKey);
        return findNode(nextNode, remainingPath);
    }


    public void display() {
        display(root, 0);
    }

    private void display(Node<T> node, int level) {
        if (node != null) {
            StringBuilder indent = new StringBuilder();
            for (int i = 0; i < level; i++) {
                indent.append("  ");
            }

            System.out.println(indent.toString() + node.key);
            for (Node<T> child : node.children) {
                display(child, level + 1);
            }
        }
    }

    public static class Node<T> {
        private String key;
        public T data;
        private List<Node<T>> children;

        public Node(String key, T data) {
            this.key = key;
            this.data = data;
            this.children = new ArrayList<>();
        }

        public String getKey() {
            return key;
        }

        public void setKey(String s){
            key = s;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public List<Node<T>> getChildren() {
            return children;
        }

        public void addChild(Node<T> child) {
            children.add(child);
        }

        public void removeChild(String key) {
            children.removeIf(node -> node.getKey().equals(key));
        }

        public Node<T> getChild(String key) {
            for (Node<T> child : children) {
                if (child.getKey().equals(key)) {
                    return child;
                }
            }
            return null;
        }

        public String getName() {
            return key;
        }
    }

    /*public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.addNode("A", 1, "/");

        tree.addNode("B", 2, "");

        tree.addNode("C", 3, "");
        tree.addNode("D", 4, "B/");
        tree.addNode("E", 5, "B/");
        tree.addNode("F", 6, "C/");
        tree.removeNode("E","B/");


        tree.display();

    }*/
}
