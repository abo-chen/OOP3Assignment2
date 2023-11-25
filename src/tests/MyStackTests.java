package tests;

import utilities.Iterator;
import utilities.StackADT;
import datastructures.MyStack;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyStackTests {

    private StackADT<String> myStack;

    @Before
    public void setUp() {
        myStack = new MyStack<>();
    }

    @Test
    public void testPushAndPop() {
        myStack.push("Element 1");
        myStack.push("Element 2");

        assertEquals("Element 2", myStack.pop());
        assertEquals("Element 1", myStack.pop());
    }

    @Test
    public void testPeek() {
        myStack.push("Element 1");
        myStack.push("Element 2");

        assertEquals("Element 2", myStack.peek());
        assertEquals("Element 2", myStack.peek()); 
    }

    @Test
    public void testClear() {
        myStack.push("Element 1");
        myStack.push("Element 2");

        myStack.clear();
        assertTrue(myStack.isEmpty());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(myStack.isEmpty());

        myStack.push("Element 1");
        assertFalse(myStack.isEmpty());
    }

    @Test
    public void testToArray() {
        myStack.push("Element 1");
        myStack.push("Element 2");

        Object[] array = myStack.toArray();
        assertArrayEquals(new Object[]{"Element 2", "Element 1"}, array);
    }

    @Test
    public void testToArrayWithArgument() {
        myStack.push("Element 1");
        myStack.push("Element 2");

        String[] array = myStack.toArray(new String[myStack.size()]);
        assertArrayEquals(new String[]{"Element 2", "Element 1"}, array);
    }

    @Test
    public void testContains() {
        myStack.push("Element 1");
        myStack.push("Element 2");

        assertTrue(myStack.contains("Element 1"));
        assertFalse(myStack.contains("Element 3"));
    }
    
    @Test
    public void testSearch() {
        myStack.push("Element 1");
        myStack.push("Element 2");
        myStack.push("Element 3");

        assertEquals(1, myStack.search("Element 3"));
        assertEquals(2, myStack.search("Element 2"));
        assertEquals(3, myStack.search("Element 1"));
        assertEquals(-1, myStack.search("Element 4"));
    }

    @Test
    public void testIterator() {
        myStack.push("Element 1");
        myStack.push("Element 2");

        Iterator<String> iterator = myStack.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Element 1", iterator.next()); 
        assertEquals("Element 2", iterator.next()); 
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testEquals() {
        StackADT<String> anotherStack = new MyStack<>();
        myStack.push("Element 1");
        myStack.push("Element 2");
        anotherStack.push("Element 1");
        anotherStack.push("Element 2");

        assertTrue(myStack.equals(anotherStack));
    }

    @Test
    public void testSize() {
        myStack.push("Element 1");
        myStack.push("Element 2");

        assertEquals(2, myStack.size());
    }

}
