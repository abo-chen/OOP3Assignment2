package tests;

import datastructures.MyDLL;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MyDLLTests {
    private MyDLL<Integer> list;

    @Before
    public void setUp() {
        list = new MyDLL<>();
    }

    @Test
    public void testIsEmptyOnNewList() {
        assertTrue("New list should be empty", list.isEmpty());
    }

    @Test
    public void testSizeOnNewList() {
        assertEquals("New list should have size 0", 0, list.size());
    }

    @Test
    public void testAddAndSize() {
        list.add(1);
        list.add(2);
        assertEquals("List should have size 2 after adding two elements", 2, list.size());
    }

    @Test
    public void testAddAndGet() {
        list.add(1);
        list.add(2);
        assertEquals("Get should return the first element", Integer.valueOf(1), list.get(0));
        assertEquals("Get should return the second element", Integer.valueOf(2), list.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetOutOfBounds() {
        list.get(0);
    }

    @Test
    public void testRemove() {
        list.add(1);
        list.add(2);
        list.remove(Integer.valueOf(1));
        assertEquals("List should have size 1 after removing an element", 1, list.size());
        assertEquals("Get should return the remaining element", Integer.valueOf(2), list.get(0));
    }

    @Test
    public void testClear() {
        list.add(1);
        list.add(2);
        list.clear();
        assertTrue("List should be empty after clear", list.isEmpty());
    }

    // Add more tests to cover all methods in MyDLL
}
