package datastructures;

import exceptions.EmptyStackException;
import utilities.Iterator;
import utilities.StackADT;

/**
 * A custom implementation of a stack data structure.
 * This class utilizes an array list (MyArrayList) to implement the StackADT interface.
 *
 * @param <E> the type of elements held in this stack
 */
public class MyStack<E> implements StackADT<E> {
    private MyArrayList<E> list;

    /**
     * Constructs an empty stack.
     */
    public MyStack() {
        list = new MyArrayList<>();
    }

    /**
     * Adds an element to the top of the stack.
     *
     * @param toAdd the element to add
     * @throws NullPointerException if the specified element is null
     */
    @Override
    public void push(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null object to the stack");
        }
        list.add(toAdd);
    }

    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws EmptyStackException if this stack is empty
     */
    @Override
    public E pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("Stack is empty");
        }
        return list.remove(list.size() - 1);
    }

    /**
     * Retrieves, but does not remove, the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws EmptyStackException if this stack is empty
     */
    @Override
    public E peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("Stack is empty");
        }
        return list.get(list.size() - 1);
    }

    /**
     * Removes all elements from the stack.
     */
    @Override
    public void clear() {
        list.clear();
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return list.size() == 0;
    }

    /**
     * Returns an array containing all elements in this stack.
     *
     * @return an array containing all elements in this stack
     */
    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    /**
     * Returns an array containing all elements in this stack; the runtime type of the returned array is that of the specified array.
     *
     * @param holder the array into which the elements of the stack are to be stored, if it is big enough; otherwise, a new array of the same runtime type is allocated for this purpose
     * @return an array containing the elements of the stack
     * @throws NullPointerException if the specified array is null
     */
    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        return list.toArray(holder);
    }

    /**
     * Checks if a specific element is in the stack.
     *
     * @param toFind the element to find
     * @return true if the element is found, false otherwise
     * @throws NullPointerException if the specified element is null
     */
    @Override
    public boolean contains(E toFind) throws NullPointerException {
        return list.contains(toFind);
    }

    /**
     * Determines the 1-based position of an element in the stack. The top of the stack is considered as position 1.
     *
     * @param toFind the element to search for
     * @return the 1-based position of the element from the top of the stack, or -1 if not found
     */
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

    /**
     * Returns an iterator over the elements in this stack in proper sequence.
     *
     * @return an iterator over the elements in this stack
     */
    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    /**
     * Compares this stack with another stack for equality.
     *
     * @param that the stack to compare with
     * @return true if the specified stack is equal to this stack
     */
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

    /**
     * Returns the number of elements in this stack.
     *
     * @return the number of elements in this stack
     */
    @Override
    public int size() {
        return list.size();
    }
}
