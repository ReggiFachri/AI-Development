
public class Tree {

    private Node root;

    public Node getRoot() {
        return this.root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    // Add a public method for building the tree
    // public Node buildTree(List<ArrayList<String>> data, List<String> attributes)
    // {
    // // Implement the logic for building the tree using ID3 algorithm (omitted for
    // brevity)
    // return root; // Return the constructed root node
    // }
    public void print() {
        if (root != null) {
            root.print("", true);
        }
    }
}
