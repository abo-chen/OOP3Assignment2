package datastructures;

import utilities.QueueADT;
import exceptions.EmptyQueueException;
import utilities.Iterator;

/**
 * A custom implementation of a queue data structure.
 * This class utilizes a doubly linked list (MyDLL) to implement the QueueADT interface.
 *
 * @param <E> the type of elements held in this queue
 */
public class MyQueue<E> implements QueueADT<E> {
    private MyDLL<E> list;

    /**
     * Constructs an empty queue.
     */
    public MyQueue() {
        list = new MyDLL<>();
    }

    /**
     * Adds an element to the rear of the queue.
     *
     * @param toAdd the element to add
     * @throws NullPointerException if the specified element is null
     */
    @Override
    public void enqueue(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("Cannot enqueue null object");
        }
        list.add(toAdd);
    }

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return the element at the front of the queue
     * @throws EmptyQueueException if this queue is empty
     */
    @Override
    public E dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }
        return list.remove(0);
    }

    /**
     * Retrieves, but does not remove, the element at the front of the queue.
     *
     * @return the element at the front of the queue
     * @throws EmptyQueueException if this queue is empty
     */
    @Override
    public E peek() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }
        return list.get(0);
    }

    /**
     * Removes all elements from the queue.
     */
    @Override
    public void dequeueAll() {
        list.clear();
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns an iterator over the elements in this queue in proper sequence.
     *
     * @return an iterator over the elements in this queue
     */
    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    /**
     * Compares this queue with another queue for equality.
     *
     * @param that the queue to compare with
     * @return true if the specified queue is equal to this queue
     */
    @Override
    public boolean equals(QueueADT<E> that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;
        MyQueue<E> other = (MyQueue<E>) that;
        return list.equals(other.list);
    }

    /**
     * Returns an array containing all elements in this queue.
     *
     * @return an array containing all elements in this queue
     */
    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    /**
     * Returns an array containing all elements in this queue; the runtime type of the returned array is that of the specified array.
     *
     * @param holder the array into which the elements of the queue are to be stored, if it is big enough; otherwise, a new array of the same runtime type is allocated for this purpose
     * @return an array containing the elements of the queue
     * @throws NullPointerException if the specified array is null
     */
    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        return list.toArray(holder);
    }

    /**
     * Checks if the queue is full.
     * This method is optional and only necessary for fixed length queues. It is not implemented in this class.
     *
     * @return always returns false as the queue is not fixed length
     */
    @Override
    public boolean isFull() {
        // This method is optional and only necessary for fixed length queues.
        // We can leave it unimplemented or throw an UnsupportedOperationException.
        // throw new UnsupportedOperationException("isFull not supported");
    	return false;
    }

    /**
     * Returns the number of elements in this queue.
     *
     * @return the number of elements in this queue
     */
    @Override
    public int size() {
        return list.size();
    }
}
