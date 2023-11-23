package datastructures;

import exceptions.EmptyStackException;
import utilities.Iterator;
import utilities.StackADT;


public class MyStack<E> implements StackADT<E> {
    private MyArrayList<E> list;

    public MyStack() {
        list = new MyArrayList<>();
    }

    @Override
    public void push(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null object to the stack");
        }
        list.add(toAdd);
    }

    @Override
    public E pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("Stack is empty");
        }
        return list.remove(list.size() - 1);
    }

    @Override
    public E peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("Stack is empty");
        }
        return list.get(list.size() - 1);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean isEmpty() {
        return list.size() == 0;
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
    public boolean contains(E toFind) throws NullPointerException {
        return list.contains(toFind);
    }

    @Override
    public int search(E toFind) {
        if (toFind == null) {
            return -1;
        }

        for (int i = list.size() - 1, position = 1; i >= 0; i--, position++) {
            if (toFind.equals(list.get(i))) {
                return position;
            }
        }

        return -1; // Element not found
    }


    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public boolean equals(StackADT<E> that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;

        MyStack<E> other = (MyStack<E>) that;
        if (this.size() != other.size()) return false;

        for (int i = 0; i < this.size(); i++) {
            if (!this.list.get(i).equals(other.list.get(i))) {
                return false;
            }
        }

        return true;
    }


    @Override
    public int size() {
        return list.size();
    }
}
