package ru.vsu.cs.zagorodnev_g_a.TreeMapBidiMap;

public class TreeMap<K extends Comparable<K>, V> {

    private final RedBlackTree tree = new RedBlackTree();

    public void add(K key, V value) {
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

    public String toString(){
        StringBuilder str = new StringBuilder(" ");
        if (tree.size() == 0) {
            return "Map is empty";
        }
        else {
            Entry entry = (Entry) tree.getRoot().getValue();
            for (Object node :
                    tree) {
                str.append("Key: ").append(((Entry) ((Node) node).getValue()).getKey().toString())
                        .append(" ; Value: ").append(((Entry) ((Node) node).getValue()).getValue().toString())
                        .append("\n");
            }
            return String.valueOf(str);
        }
    }
}