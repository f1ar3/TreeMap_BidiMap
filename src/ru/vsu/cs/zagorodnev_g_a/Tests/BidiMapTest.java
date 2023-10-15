package ru.vsu.cs.zagorodnev_g_a.Tests;

import ru.vsu.cs.zagorodnev_g_a.TreeMapBidiMap.BidiMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BidiMapTest {

    BidiMap<Integer, String> bidiMap = new BidiMap<>();

    @BeforeEach
    void bidiMapGenerate(){
        BidiMap<Integer, String> bidiMap = new BidiMap<>();
    }

    @Test
    public void testBidiMap() {
        bidiMap.add(15, "abc");
        bidiMap.add(20, "adb");
        bidiMap.add(31, "bfe");
        bidiMap.removeByKey(15);
        bidiMap.removeByValue("adb");
        bidiMap.add(31,"adb");
        assertFalse(bidiMap.containsKey(15));
        assertFalse(bidiMap.containsKey(20));
        assertTrue(bidiMap.containsValue("adb"));
        assertFalse(bidiMap.containsValue("bfe"));
    }

    @Test
    void emptyBidiMap(){
        assertFalse(bidiMap.containsKey(1));
        assertFalse(bidiMap.containsValue("AAA"));
    }

    @Test
    void testRemove(){
        bidiMap.add(15, "abc");
        bidiMap.add(20, "bfb");
        bidiMap.add(31, "dbd");
        bidiMap.removeByKey(15);
        assertTrue(bidiMap.containsKey(20));
        assertFalse(bidiMap.containsValue("abc"));
        assertTrue(bidiMap.containsValue("dbd"));
        bidiMap.removeByValue("bfb");
        bidiMap.removeByValue("dbd");
        assertFalse(bidiMap.containsKey(20));
        assertFalse(bidiMap.containsKey(31));
        assertFalse(bidiMap.containsValue("bfb"));
        assertFalse(bidiMap.containsValue("dbd"));
    }

    @Test
    void testReplace(){
        bidiMap.add(15, "abc");
        bidiMap.add(20, "bfb");
        bidiMap.add(31, "dbd");
        assertEquals("dbd", bidiMap.getValue(31));
        bidiMap.add(31, "add");
        assertEquals(31, (int) bidiMap.getKey("add"));
        bidiMap.add(27, "abc");
        assertEquals(27, (int) bidiMap.getKey("abc"));
        assertFalse(bidiMap.containsKey(15));
    }
}