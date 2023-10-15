package ru.vsu.cs.zagorodnev_g_a.Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.vsu.cs.zagorodnev_g_a.TreeMapBidiMap.TreeMap;


import static org.junit.jupiter.api.Assertions.*;

public class TreeMapTest {
    private TreeMap<Integer, String> treeMap = new TreeMap<>();

    @BeforeEach
    void treeMapGenerate(){
        treeMap = new TreeMap<Integer, String>();
    }

    @Test
    void testAdd(){
        treeMap.add(15, "abc");
        treeMap.add(20, "bfb");
        treeMap.add(31, "dbd");
        assertTrue(treeMap.containsKey(15));
        assertTrue(treeMap.containsKey(20));
        assertEquals("dbd", (String) treeMap.getValue(31));
    }

    @Test
    void emptyTreeMap(){
        assertFalse(treeMap.containsKey(1));
    }

    @Test
    void testRemove(){
        treeMap.add(15, "abc");
        treeMap.add(20, "bfb");
        treeMap.add(31, "dbd");
        treeMap.remove(15);
        assertTrue(treeMap.containsKey(20));
        assertFalse(treeMap.containsKey(15));
        assertTrue(treeMap.containsKey(31));
        treeMap.remove(20);
        treeMap.remove(31);
        assertFalse(treeMap.containsKey(20));
        assertFalse(treeMap.containsKey(31));
    }

    @Test
    void testReplace(){
        treeMap.add(15, "abc");
        treeMap.add(20, "bfb");
        treeMap.add(31, "dbd");
        assertEquals("dbd", treeMap.getValue(31));
        treeMap.add(31, "add");
        assertEquals("add", treeMap.getValue(31));
    }

}
