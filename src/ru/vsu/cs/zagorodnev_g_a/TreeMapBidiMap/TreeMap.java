package ru.vsu.cs.zagorodnev_g_a.TreeMapBidiMap;

public class TreeMap<K extends Comparable<K>, V extends Comparable<V>> {

    private class EntryWrapperK implements ComparableBy<K> {
        private final Entry<K,V> e;

        public EntryWrapperK(Entry<K,V> e) {
            this.e = e;
        }
        @Override
        public K getComparable() {
            return e.getKey();
        }
    }

    private class EntryWrapperV implements ComparableBy<V> {
        private final Entry<K,V> e;

        public EntryWrapperV(Entry<K,V> e) {
            this.e = e;
        }
        @Override
        public V getComparable() {
            return e.getValue();
        }
    }
    private final RedBlackTree<EntryWrapperK,K> keysTree  = new RedBlackTree<>();
    private final RedBlackTree<EntryWrapperV,V> valuesTree = new RedBlackTree<>();

    public void add(K key, V value) {
        Entry<K,V> entry = new Entry<>(key,value);
        keysTree.add(new EntryWrapperK(entry));
        valuesTree.add(new EntryWrapperV(entry));
    }

    public V getValue(K key) {
        return keysTree.getNode(keysTree.getRoot(), key).getValue().e.getValue();
    }

    public V remove(K key) {
        V value = keysTree.remove(key).e.getValue();
        valuesTree.remove(value);
        return value;
    }

    public boolean containsKey(K key) {
        return keysTree.getNode(keysTree.getRoot(), key) != null;
    }

//    public String toString(){
//        StringBuilder str = new StringBuilder(" ");
//        if (keysTree.size() == 0) {
//            return "Map is empty";
//        }
//        else {
//           // Entry<K,V> entry = new Entry<>(keysTree.getRoot().getValue().e.getKey(),valuesTree.getRoot().getValue().e.getValue());
//
//            for (entry : keysTree) {
//                str.append("Key: ").append(((Entry) ((Node) node).getValue()).getKey().toString())
//                        .append(" ; Value: ").append(((Entry) ((Node) node).getValue()).getValue().toString())
//                        .append("\n");
//            }
//            return String.valueOf(str);
//        }
//    }
}