package tests;

import datastructures.MyArrayList;
import utilities.ListADT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyArrayListTests {

    private ListADT<Integer> list;

    @BeforeEach
    void setUp() {
        list = new MyArrayList<>();
    }

    @Test
    void testIsEmptyOnNewList() {
        assertTrue(list.isEmpty());
    }

    @Test
    void testAddOneElement() {
        list.add(1);
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
    }

    @Test
    void testAddAndGetOneElement() {
        list.add(1);
        assertEquals(1, list.get(0));
    }

    @Test
    void testAddMultipleElements() {
        list.add(1);
        list.add(2);
        assertEquals(2, list.size());
    }

    @Test
    void testRemoveElementByIndex() {
        list.add(1);
        list.add(2);
        list.remove(0);
        assertEquals(1, list.size());
        assertEquals(2, list.get(0));
    }

    @Test
    void testRemoveElementByValue() {
        list.add(1);
        list.add(2);
        list.remove(Integer.valueOf(1));
        assertEquals(1, list.size());
        assertEquals(2, list.get(0));
    }

    @Test
    void testSetElement() {
        list.add(1);
        list.set(0, 2);
        assertEquals(2, list.get(0));
    }

    @Test
    void testContains() {
        list.add(1);
        assertTrue(list.contains(1));
        assertFalse(list.contains(2));
    }

    @Test
    void testToArray() {
        list.add(1);
        list.add(2);
        Object[] array = list.toArray();
        assertArrayEquals(new Integer[]{1, 2}, array);
    }

    @Test
    void testClear() {
        list.add(1);
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    void testAddAtSpecificPosition() {
        list.add(1);
        list.add(0, 2);
        assertEquals(2, list.get(0));
    }

    @Test
    void testIterator() {
        list.add(1);
        list.add(2);
        var iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertEquals(2, iterator.next());
        assertFalse(iterator.hasNext());
    }

    // Additional tests can be added here to cover edge cases and exception handling.

}
