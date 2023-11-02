package ru.vsu.cs.zagorodnev_g_a.TreeMapBidiMap;

class Node<T extends ComparableBy<K>, K extends Comparable<K>>{

    private T value;
    private boolean red;
    private Node<T,K> left, right, parent;

    public Node(T value, boolean red, Node left, Node right, Node parent) {
        this.value = value;
        this.red = red;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }

    public boolean isRed() {
        return red;
    }

    public void setRed(boolean red) {
        this.red = red;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

}
