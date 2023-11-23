package tests;

import datastructures.MyStack;
import exceptions.EmptyStackException;
import utilities.StackADT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyStackTests {
    private StackADT<Integer> stack;

    @BeforeEach
    public void setUp() {
        stack = new MyStack<>();
    }

    @Test
    public void testPushAndPeek() {
        stack.push(1);
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.peek());
    }

    @Test
    public void testPop() {
        stack.push(2);
        assertEquals(2, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPopEmptyStack() {
        assertThrows(EmptyStackException.class, () -> stack.pop());
    }

    @Test
    public void testPeekEmptyStack() {
        assertThrows(EmptyStackException.class, () -> stack.peek());
    }

    @Test
    public void testClear() {
        stack.push(3);
        stack.clear();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testToArray() {
        stack.push(4);
        stack.push(5);
        Object[] array = stack.toArray();
        assertArrayEquals(new Object[]{4, 5}, array);
    }

    @Test
    public void testContains() {
        stack.push(6);
        assertTrue(stack.contains(6));
        assertFalse(stack.contains(7));
    }

    @Test
    public void testSearch() {
        stack.push(8);
        stack.push(9);
        assertEquals(1, stack.search(9));
        assertEquals(2, stack.search(8));
        assertEquals(-1, stack.search(10));
    }

    @Test
    public void testEquals() {
        MyStack<Integer> otherStack = new MyStack<>();
        stack.push(10);
        otherStack.push(10);

        assertTrue(stack.equals(otherStack));
        otherStack.push(11);
        assertFalse(stack.equals(otherStack));
    }

    @Test
    public void testSize() {
        assertEquals(0, stack.size());
        stack.push(12);
        assertEquals(1, stack.size());
    }
}
