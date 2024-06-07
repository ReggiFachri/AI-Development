package binarytree;

public class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        left = null;
        right = null;

    }

    public void setLeft(Node node) {
        if (left == null)
            left = node;
    }

    public void setRight(Node node) {
        if (right == null)
            right = node;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void visualizeTree() {
        visualizeTree(this, 0);
    }

    private void visualizeTree(Node node, int level) {
        if (node == null)
            return;

        visualizeTree(node.right, level + 1);

        for (int i = 0; i < level; i++)
            System.out.print("   ");

        System.out.println(node.data);

        visualizeTree(node.left, level + 1);
    }

    void printPreorder(Node node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        printPreorder(node.left);
        printPreorder(node.right);
    }

    void printPostorder(Node node) {
        if (node == null)
            return;
        printPostorder(node.left);
        printPostorder(node.right);
        System.out.print(node.data + " ");
    }

    void printInorder(Node node) {
        if (node == null)
            return;
        printInorder(node.left);
        System.out.print(node.data + " ");
        printInorder(node.right);
    }
}