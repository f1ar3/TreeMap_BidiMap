package ru.vsu.cs.zagorodnev_g_a.TreeMapBidiMap;

public class TreeMap<K extends Comparable<K>, V> {

    private final RedBlackTree tree = new RedBlackTree();

    public TreeMap() {
    }

    public RedBlackTree getTree() {
        return tree;
    }

    public void put(K key, V value) {
        tree.add(new Entry(key, value));
    }

    public V getValue(K key) {
        return (V) (((Entry) tree.getNode(tree.getRoot(), new Entry(key, null)).getValue())).getValue();
    }

    public V remove(K key) {
        return (V) ((Entry) tree.remove(new Entry(key, null))).getValue();
    }

    public boolean containsKey(K key) {
        if (tree.getNode(tree.getRoot(), new Entry(key, null)) != null) {
            return true;
        }
        return false;
    }
}