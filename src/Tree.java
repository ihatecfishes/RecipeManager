import java.util.ArrayList;
import java.util.List;

class Tree<T> {
    private Node<T> root;

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
        if (parentNode == null) {
            throw new IllegalArgumentException("Invalid path");
        }

        Node<T> newNode = new Node<>(key, data);
        parentNode.addChild(newNode);
    }

    public void removeNode(String key, String path) {
        Node<T> parentNode = findNode(root, path);
        if (parentNode == null) {
            throw new IllegalArgumentException("Invalid path");
        }

        parentNode.removeChild(key);
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

    public Node<T> findNode(String key) {
        return findNode(root, key);
    }

    private Node<T> findNode(Node<T> currentNode, String path) {
        String[] keys = path.split("/");
        for (String key : keys) {
            if (currentNode == null || currentNode.children == null) {
                return null;
            }
            currentNode = currentNode.findChild(key);
        }
        return currentNode;
    }

    public Node<T> getRoot() {
        return root;
    }

    public static class Node<T> {
        private String key;
        private T data;
        private List<Node<T>> children;

        public Node(String key, T data) {
            this.key = key;
            this.data = data;
            this.children = new ArrayList<>();
        }

        public void addChild(Node<T> child) {
            children.add(child);
        }

        public void removeChild(String key) {
            Node<T> nodeToRemove = findChild(key);
            if (nodeToRemove != null) {
                children.remove(nodeToRemove);
            }
        }

        public Node<T> findChild(String key) {
            for (Node<T> child : children) {
                if (child.key.equals(key)) {
                    return child;
                }
            }
            return null;
        }

        public List<Node<T>> getChildren() {
            return children;
        }

        public T getData() {
            return data;
        }
    }
}
