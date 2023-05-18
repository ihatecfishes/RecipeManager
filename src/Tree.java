public class Tree <T> {
    private Node root;

    public Tree() {

    }

    public void addNode(String key, String path) {

    }

    public void addNode(String key, T data, String path) {

    }

    public void removeNode(String key, String path) {

    }


    private static class Node <T> {
        private String key;
        private T data;
        private Node[] children;

        public Node() {

        }

        public String getPath() {
            return null;
        }
    }
}
