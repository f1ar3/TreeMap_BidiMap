package ru.vsu.cs.zagorodnev_g_a.TreeMapBidiMap;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RedBlackTree<T extends ComparableBy<K>, K extends Comparable<K>> implements Iterable<T> {

    static final boolean RED = true;
    static final boolean BLACK = true;
    private Node<T,K> root = null;
    private int size = 0;

    protected Node<T,K> getRoot() {
        return root;
    }

    public int size(){
        return size;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    private int getHeight(Node<T,K> node) {
        return (node == null) ? -1 : Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1;
    }

    public int getHeight() {
        return getHeight(root);
    }
    private void setRoot(Node<T,K> node) {
        root = node;
        if (node != null) {
            node.setParent(null);
        }
    }

    private void setLeft(Node<T,K> node, Node<T,K> left) {
        if (node != null) {
            node.setLeft(left);
            if (left != null) {
                left.setParent(node);
            }
        }
    }

    private void setRight(Node<T,K> node, Node<T,K> right) {
        if (node != null) {
            node.setRight(right);
            if (right != null) {
                right.setParent(node);
            }
        }
    }

    private Node<T,K> checkLeft(Node<T,K> node) {
        if (node != null) {
            return node.getLeft();
        } else {
            return null;
        }
    }

    private Node<T,K> checkRight(Node<T,K> node) {
        if (node != null) {
            return node.getRight();
        } else {
            return null;
        }
    }

    private Node<T,K> checkParent(Node<T,K> node) {
        if (node != null) {
            return node.getParent();
        } else {
            return null;
        }
    }

    private Node<T,K> checkGrandparent(Node<T,K> node) {
        if (node == null || node.getParent() == null) {
            return null;
        } else {
            return node.getParent().getParent();
        }
    }

    private Node<T,K> checkBrother(Node<T,K> node) {
        if (node == null || node.getParent() == null) {
            return null;
        } else if (node == node.getParent().getLeft()) {
            return node.getParent().getRight();
        } else {
            return node.getParent().getLeft();
        }
    }

    private void setColor(Node<T,K> node, boolean red) {
        if (node != null) {
            node.setRed(red);
        }
    }
    private boolean colorOfNode(Node<T,K> node) {
        if (node == null) {
            return BLACK;
        } else {
            return node.isRed();
        }
    }

    private boolean isRed(Node<T,K> node) {
        return colorOfNode(node) == RED;
    }

    private boolean isBlack(Node<T,K> node) {
        return colorOfNode(node) == BLACK;
    }


    protected Node<T,K> getNode(Node<T,K> node, K value) {
        if (node == null) {
            return null;
        }
        int cmp = node.getValue().getComparable().compareTo(value);
        if (cmp == 0) {
            return node;
        } else if (cmp > 0) {
            return getNode(node.getLeft(), value);
        } else {
            return getNode(node.getRight(), value);
        }
    }

    private Node<T,K> getMinNode(Node<T,K> node) {
        if (node == null || node.getLeft() == null) {
            return node;
        } else {
            return getMinNode(node.getLeft());
        }
    }

    public void add(T value) {
        Node<T,K> newNode = new Node<>(value);
        if (root == null) {
            setRoot(newNode);
            size++;
            return;
        }
        Node<T,K> node = root;
        while (true) {
            int compare = value.getComparable().compareTo(node.getValue().getComparable());
            if (compare < 0) {
                if (node.getLeft() == null) {
                    setLeft(node,newNode);
                    size++;
                    treeBalancingAfterAdd(node.getLeft());
                    return;
                }
                node = node.getLeft();
            } else if (compare > 0) {
                if (node.getRight() == null) {
                    setRight(node, newNode);
                    size++;
                    treeBalancingAfterAdd(node.getRight());
                    return;
                }
                node = node.getRight();
            } else {
                node.setValue(value);
                return;
            }
        }
    }

    private void treeBalancingAfterAdd(Node<T,K> node) {
        if (node != null) {
            node.setRed(RED);
        }
        if (node != null && node != root && colorOfNode(node.getParent()) == RED) {
            if (isRed(checkBrother(checkParent(node)))) {
                setColor(checkParent(node), BLACK);
                setColor(checkBrother(checkParent(node)), BLACK);
                setColor(checkGrandparent(node), RED);
                treeBalancingAfterAdd(checkGrandparent(node));
            }
            else if (checkParent(node) == checkLeft(checkGrandparent(node))) {
                if (node == checkRight(checkParent(node))) {
                    leftRotate(node = checkParent(node));
                }
                setColor(checkParent(node), BLACK);
                setColor(checkGrandparent(node), RED);
                rightRotate(checkGrandparent(node));
            }
            else if (checkParent(node) == checkRight(checkGrandparent(node))) {
                if (node == checkLeft(checkParent(node))) {
                    rightRotate(node = checkParent(node));
                }
                setColor(checkParent(node), BLACK);
                setColor(checkGrandparent(node), RED);
                leftRotate(checkGrandparent(node));
            }
        }
        setColor(root, BLACK);
    }

    public T remove(K value) {
        Node<T,K> node = getNode(getRoot(), value);
        if (node == null){
            return null;
        }
        T oldValue = node.getValue();
        if (node.getLeft() != null && node.getRight() != null) {
            Node<T,K> nextValueNode = getMinNode(node.getRight());
            node.setValue(nextValueNode.getValue());
            node = nextValueNode;
        }
        Node<T,K> child = (node.getLeft() != null) ? node.getLeft() : node.getRight();
        if (child != null) {
            if (node == root) {
                setRoot(child);
                root.setRed(BLACK);
            } else if (node.getParent().getLeft() == node) {

                setLeft(node.getParent(), child);
            } else {
                setRight(node.getParent(), child);
            }
            if (node.isRed() == BLACK) {
                treeBalancingAfterRemove(child);
            }
        } else if (node == root) {
            root = null;
        } else {
            if (node.isRed() == BLACK) {
                treeBalancingAfterRemove(node);
            }
            removeFromParent(node);
        }
        size--;
        return oldValue;
    }

    private void removeFromParent(Node<T,K> node) {
        if (node.getParent() != null) {
            if (node.getParent().getLeft() == node) {
                node.getParent().setLeft(null);
            } else if (node.getParent().getRight() == node) {
                node.getParent().setRight(null);
            }
            node.setParent(null);
        }
    }

    private void treeBalancingAfterRemove(Node<T,K> node) {
        while (node != root && isBlack(node)) {
            if (node == checkLeft(checkParent(node))) {
                Node<T,K> brother = checkRight(checkParent(node));
                if (isRed(brother)) {
                    setColor(brother, BLACK);
                    setColor(checkParent(node), RED);
                    leftRotate(checkParent(node));
                    brother = checkRight(checkParent(node));
                }
                if (isBlack(checkLeft(brother)) && isBlack(checkRight(brother))) {
                    setColor(brother, RED);
                    node = checkParent(node);
                } else {
                    if (isBlack(checkRight(brother))) {
                        setColor(checkLeft(brother), BLACK);
                        setColor(brother, RED);
                        rightRotate(brother);
                        brother = checkRight(checkParent(node));
                    }
                    setColor(brother, colorOfNode(checkParent(node)));
                    setColor(checkParent(node), BLACK);
                    setColor(checkRight(brother), BLACK);
                    leftRotate(checkParent(node));
                    node = root;
                }
            } else {
                Node<T,K> brother = checkLeft(checkParent(node));
                if (isRed(brother)) {
                    setColor(brother, BLACK);
                    setColor(checkParent(node), RED);
                    rightRotate(checkParent(node));
                    brother = checkLeft(checkParent(node));
                }
                if (isBlack(checkLeft(brother)) && isBlack(checkRight(brother))) {
                    setColor(brother, RED);
                    node = checkParent(node);
                } else {
                    if (isBlack(checkLeft(brother))) {
                        setColor(checkRight(brother), BLACK);
                        setColor(brother, RED);
                        leftRotate(brother);
                        brother = checkLeft(checkParent(node));
                    }
                    setColor(brother, colorOfNode(checkParent(node)));
                    setColor(checkParent(node), BLACK);
                    setColor(checkLeft(brother), BLACK);
                    rightRotate(checkParent(node));
                    node = root;
                }
            }
        }
        setColor(node, BLACK);
    }

    private void leftRotate(Node<T,K> node) {
        if (node.getRight() == null) {
            return;
        }
        Node<T,K> right = node.getRight();
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

    private void rightRotate(Node<T,K> node) {
        if(node.getLeft() == null) {
            return;
        }
        Node<T,K> left = node.getLeft();
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
    public Iterator<T> iterator() {
        return new TreeIterator(getRoot());
    }

    class TreeIterator implements Iterator<T> {

        private Node<T,K> next;

        public TreeIterator(Node<T,K> root) {
            next = root;
            if (next == null)
                return;

            while (next.getLeft() != null)
                next = next.getLeft();
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public T next() {

            if (!hasNext()) throw new NoSuchElementException();
            Node<T,K> r = next;
            if (next.getRight() != null) {
                next = next.getRight();
                while (next.getLeft() != null)
                    next = next.getLeft();
                return r.getValue();
            }

            while (true) {
                if (next.getParent() == null) {
                    next = null;
                    return r.getValue();
                }
                if (next.getParent().getLeft() == next) {
                    next = next.getParent();
                    return r.getValue();
                }
                next = next.getParent();
            }
        }
    }
}
