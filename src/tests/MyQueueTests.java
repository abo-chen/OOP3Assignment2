package tests;

import datastructures.MyQueue;
import exceptions.EmptyQueueException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MyQueueTests {

    private MyQueue<Integer> queue;

    @Before
    public void setUp() {
        queue = new MyQueue<>();
    }

    @Test
    public void testEnqueueAndPeek() {
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
        assertEquals(1, (int)queue.peek());
    }

    @Test(expected = EmptyQueueException.class)
    public void testDequeueOnEmptyQueue() {
        queue.dequeue();
    }

    @Test
    public void testDequeue() {
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(1, (int)queue.dequeue());
        assertEquals(1, queue.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, queue.size());
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(2, queue.size());
    }

    /* for LL
    @Test
    public void testEquals() {
        MyQueue<Integer> anotherQueue = new MyQueue<>();
        queue.enqueue(1);
        anotherQueue.enqueue(1);
        assertTrue(queue.equals(anotherQueue));
    }
    */
    public void testEquals() {
        MyQueue<Integer> anotherQueue = new MyQueue<>();
        queue.enqueue(1);
        anotherQueue.enqueue(1);
        assertTrue(queue.equals(anotherQueue));
    }


    @Test
    public void testToArray() {
        queue.enqueue(1);
        queue.enqueue(2);
        Object[] array = queue.toArray();
        assertArrayEquals(new Object[]{1, 2}, array);
    }

    @Test
    public void testIsFull() {
        assertFalse(queue.isFull());
    }

    // Additional tests can be added here to test other methods
}
