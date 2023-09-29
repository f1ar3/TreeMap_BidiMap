package ru.vsu.cs.zagorodnev_g_a;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class RebBlackTree<T extends Comparable> implements Iterable {

    static final boolean RED = true;
    static final boolean BLACK = true;
    private Node root = null;
    private int size = 0;

    public Node getRoot() {
        return root;
    }

    public int size(){
        return size;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    private void setRoot(Node node) {
        root = node;
        if (node != null) {
            node.setParent(null);
        }
    }

    private void setLeft(Node node, Node left) {
        if (node != null) {
            node.setLeft(left);
            if (left != null) {
                left.setParent(node);
            }
        }
    }

    private void setRight(Node node, Node right) {
        if (node != null) {
            node.setRight(right);
            if (right != null) {
                right.setParent(node);
            }
        }
    }

    private Node checkLeft(Node node) {
        if (node != null) {
            return node.getLeft();
        } else {
            return null;
        }
    }

    private Node checkRight(Node node) {
        if (node != null) {
            return node.getRight();
        } else {
            return null;
        }
    }

    private Node checkParent(Node node) {
        if (node != null) {
            return node.getParent();
        } else {
            return null;
        }
    }

    private void setColor(Node node, boolean red) {
        if (node != null) {
            node.setRed(red);
        }
    }
    private boolean colorOfNode(Node node) {
        if (node == null) {
            return BLACK;
        } else {
            return node.isRed();
        }
    }

    private boolean isRed(Node node) {
        return colorOfNode(node) == RED;
    }

    private boolean isBlack(Node node) {
        return colorOfNode(node) == BLACK;
    }



    public void add(T value) {
        Node newNode = new Node<>(value);
        if (root == null) {
            setRoot(newNode);
            size++;
            return;
        }
        Node node = root;
        while (true) {
            int compare = value.compareTo(node.getValue());
            if (compare < 0) {
                if (node.getLeft() == null) {
                    setLeft(node,newNode);
                    size++;
                    treeBalancing(node.getLeft());
                    return;
                }
            }
        }
    }

    private void treeBalancing(Node node) {
        while (node != root && isBlack(node)) {
            if (node == checkLeft(checkParent(node))) {
                Node brother = checkRight(checkParent(node));
                if (isRed(brother)) {
                    setColor(brother, BLACK);
                    setColor(checkParent(node), RED);
                    leftRotate(checkParent(node));
                    brother = checkRight(checkParent(node));
                }
            }
        }
    }

    private void leftRotate(Node node) {
        if (node.getRight() == null) {
            return;
        }
        Node right = node.getRight();
        setRight(node,right.getLeft());
        if (node.getParent() == null) {
            setRoot(right);
        } else if (node.getParent().getLeft() == node) {
            setLeft(node.getParent(), right);
        } else {
            setRight(node.getParent(), right);
        }
        setLeft(right, node);
    }

    private void rightRotate(Node node) {
        if(node.getLeft() == null) {
            return;
        }
        Node left = node.getLeft();
        setLeft(node, left.getRight());
        if (node.getParent() == null) {
            setRoot(left);
        } else if (node.getParent().getLeft() == node) {
            setLeft(node.getParent(), left);
        } else {
            setRight(node.getParent(), left);
        }
        setRight(left, node);
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator spliterator() {
        return Iterable.super.spliterator();
    }
}
