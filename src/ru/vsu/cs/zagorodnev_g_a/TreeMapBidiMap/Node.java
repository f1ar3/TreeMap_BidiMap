package ru.vsu.cs.zagorodnev_g_a.TreeMapBidiMap;

class Node<T extends ComparableBy<K>, K extends Comparable<K>>{

    private T value;
    private boolean red;
    private Node<T,K> left, right, parent;

    public Node(T value, boolean red, Node<T,K> left, Node<T,K> right, Node<T,K> parent) {
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

    public Node<T,K> getLeft() {
        return left;
    }

    public void setLeft(Node<T,K> left) {
        this.left = left;
    }

    public Node<T,K> getRight() {
        return right;
    }

    public void setRight(Node<T,K> right) {
        this.right = right;
    }

    public Node<T,K> getParent() {
        return parent;
    }

    public void setParent(Node<T,K> parent) {
        this.parent = parent;
    }

}
