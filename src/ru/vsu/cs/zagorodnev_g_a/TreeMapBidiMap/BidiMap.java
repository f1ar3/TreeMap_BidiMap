package ru.vsu.cs.zagorodnev_g_a.TreeMapBidiMap;

public class BidiMap<K extends Comparable<K>, V extends Comparable<V>>{

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

    public BidiMap(){
    }

    public void add(K key, V value){
        if (containsKey(key)) {
            V oldValue = keysTree.getNode(keysTree.getRoot(), key).getValue().e.getValue();
            valuesTree.remove(oldValue);
        }
        if (containsValue(value)){
            K oldKey = valuesTree.getNode(valuesTree.getRoot(), value).getValue().e.getKey();
            keysTree.remove(oldKey);
        }
        Entry<K,V> entry = new Entry<>(key,value);
        keysTree.add(new EntryWrapperK(entry));
        valuesTree.add(new EntryWrapperV(entry));
    }

    public K getKey(V value){
        return valuesTree.getNode(valuesTree.getRoot(), value).getValue().e.getKey();
    }

    public V getValue(K key){
        return keysTree.getNode(keysTree.getRoot(), key).getValue().e.getValue();
    }

    public V removeByKey(K key){
        V value = keysTree.remove(key).e.getValue();
        valuesTree.remove(value);
        return value;
    }

    public K removeByValue(V value){
        K key = valuesTree.remove(value).e.getKey();
        keysTree.remove(key);
        return key;
    }

    public boolean containsKey(K key){
        if (keysTree.getNode(keysTree.getRoot(), key) != null){
            return true;
        }
        return false;
    }

    public boolean containsValue(V value){
        if (valuesTree.getNode(valuesTree.getRoot(), value) != null){
            return true;
        }
        return false;
    }

    public String toString(){
        StringBuilder str = new StringBuilder(" ");
        if (keysTree.size() == 0) {
            return "Map is empty";
        }
        else {
            for (Object node : keysTree) {
                str.append("Key: ").append(((Entry) ((Node) node).getValue()).getKey().toString())
                        .append(" ; Value: ").append(((Entry) ((Node) node).getValue()).getValue().toString())
                        .append("\n");
            }
            return String.valueOf(str);
        }
    }
}
