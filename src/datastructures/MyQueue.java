package datastructures;

import utilities.QueueADT;
import exceptions.EmptyQueueException;
import utilities.Iterator;

public class MyQueue<E> implements QueueADT<E> {
    private MyDLL<E> list;

    public MyQueue() {
        list = new MyDLL<>();
    }

    @Override
    public void enqueue(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("Cannot enqueue null object");
        }
        list.add(toAdd);
    }

    @Override
    public E dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }
        return list.remove(0);
    }

    @Override
    public E peek() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }
        return list.get(0);
    }

    @Override
    public void dequeueAll() {
        list.clear();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public boolean equals(QueueADT<E> that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;
        MyQueue<E> other = (MyQueue<E>) that;
        return list.equals(other.list);
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        return list.toArray(holder);
    }

    @Override
    public boolean isFull() {
        // This method is optional and only necessary for fixed length queues.
        // We can leave it unimplemented or throw an UnsupportedOperationException.
        // throw new UnsupportedOperationException("isFull not supported");
    	return false;
    }

    @Override
    public int size() {
        return list.size();
    }
}
