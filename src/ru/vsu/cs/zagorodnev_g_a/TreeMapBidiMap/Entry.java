package ru.vsu.cs.zagorodnev_g_a.TreeMapBidiMap;

public class Entry<K extends Comparable<K>, V> implements Comparable<Entry<K,V>> {

    private K key;
    private V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public String toString(){return "key: " + key + " , value: " + value;}

    @Override
    public int compareTo(Entry<K,V> o) {
        return this.key.compareTo(o.getKey());
    }
}
