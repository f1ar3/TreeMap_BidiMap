package ru.vsu.cs.zagorodnev_g_a.Tests;

import ru.vsu.cs.zagorodnev_g_a.TreeMapBidiMap.BiDiMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BiDiMapTest {

    BiDiMap<Integer, String> biDiMap = new BiDiMap<>();

    @BeforeEach
    void biDiMapGenerate(){
        BiDiMap<Integer, String> biDiMap = new BiDiMap<>();
    }

    @Test
    public void testBiDiMap() {
        biDiMap.add(15, "abc");
        biDiMap.add(20, "adb");
        biDiMap.add(31, "bfe");
        biDiMap.removeByKey(15);
        biDiMap.removeByValue("adb");
        biDiMap.add(31,"adb");
        assertFalse(biDiMap.containsKey(15));
        assertFalse(biDiMap.containsKey(20));
        assertTrue(biDiMap.containsValue("adb"));
        assertFalse(biDiMap.containsValue("bfe"));
    }

    @Test
    void emptyBiDiMap(){
        assertFalse(biDiMap.containsKey(1));
        assertFalse(biDiMap.containsValue("AAA"));
    }

    @Test
    void testRemove(){
        biDiMap.add(15, "abc");
        biDiMap.add(20, "bfb");
        biDiMap.add(31, "dbd");
        biDiMap.removeByKey(15);
        assertTrue(biDiMap.containsKey(20));
        assertFalse(biDiMap.containsValue("abc"));
        assertTrue(biDiMap.containsValue("dbd"));
        biDiMap.removeByValue("bfb");
        biDiMap.removeByValue("dbd");
        assertFalse(biDiMap.containsKey(20));
        assertFalse(biDiMap.containsKey(31));
        assertFalse(biDiMap.containsValue("bfb"));
        assertFalse(biDiMap.containsValue("dbd"));
    }

    @Test
    void testReplace(){
        biDiMap.add(15, "abc");
        biDiMap.add(20, "bfb");
        biDiMap.add(31, "dbd");
        assertEquals("dbd", biDiMap.getValue(31));
        biDiMap.add(31, "add");
        assertEquals(31, biDiMap.getKey("add"));
        biDiMap.add(27, "abc");
        assertEquals(27, biDiMap.getKey("abc"));
        assertFalse(biDiMap.containsKey(15));
    }
}