package datastructures;

import utilities.QueueADT;
import exceptions.EmptyQueueException;
import utilities.Iterator;

public class MyQueueLL<E> implements QueueADT<E> {
    private MyArrayList<E> list;

    public MyQueueLL() {
        list = new MyArrayList<>();
    }

    @Override
    public void enqueue(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null object to the queue");
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
        return list.size() == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public boolean equals(QueueADT<E> that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;

        MyQueueLL<E> otherQueue = (MyQueueLL<E>) that;
        if (this.size() != otherQueue.size()) return false;

        Iterator<E> thisIterator = this.iterator();
        Iterator<E> otherIterator = otherQueue.iterator();

        while (thisIterator.hasNext() && otherIterator.hasNext()) {
            if (!thisIterator.next().equals(otherIterator.next())) {
                return false;
            }
        }

        return true;
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
        // Assuming MyArrayList has no fixed size, hence Queue is never full
        // If MyArrayList has a size limit, replace the return statement with a check
        return false;
    }

    @Override
    public int size() {
        return list.size();
    }
}
