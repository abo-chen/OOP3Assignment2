package datastructures;

import utilities.ListADT;
import utilities.Iterator;
import exceptions.NoSuchElementException;

/**
 * A custom implementation of an array list.
 * This class provides an array-based list implementation and conforms to the ListADT interface.
 * 
 * @param <E> the type of elements in this list
 */
public class MyArrayList<E> implements ListADT<E> {
     /**
	 * The default initial capacity of the array list.
     */
    private static final int DEFAULT_CAPACITY = 10;
	/**
     * The array buffer into which the elements of the MyArrayList are stored.
     */
    private E[] data;
	/**
     * The size of the MyArrayList (the number of elements it contains).
     */
    private int size;
	/**
     * Constructs an empty list with an initial capacity of ten.
     */
    public MyArrayList() {
        this.data = (E[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }
    
    /**
     * Creates a new array of the specified size and type, and copies the specified range from the original array to the new array.
     *
     * @param original the original array to be copied from
     * @param newLength the length of the new array
     * @param class1 the class of the array to be copied into
     * @return a new array containing the specified range from the original array
     * @throws NullPointerException if the original array or class1 is null
     */
    private E[] copyOf(E[] original, int newLength, Class<? extends Object[]> class1) {
        @SuppressWarnings("unchecked")
        E[] copy = ((Object)class1 == (Object)Object[].class)
            ? (E[]) new Object[newLength]
            : (E[]) java.lang.reflect.Array.newInstance(class1.getComponentType(), newLength);
        System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
        return copy;
    }

    /**
     * Returns the number of elements in this list.
     * 
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
     */
    @Override
    public void clear() {
        this.data = (E[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Ensures that the capacity is sufficient to hold at least the number of elements specified.
     * If necessary, it increases the capacity of the array list.
     */
    private void ensureCapacity() {
        if (size >= data.length) {
            E[] newData = (E[]) new Object[data.length * 2];
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }
    }

    /**
     * Adds the specified element at the specified position in this list.
     *
     * @param index index at which the specified element is to be inserted
     * @param toAdd element to be inserted
     * @return {@code true} (as specified by {@link ListADT#add})
     * @throws NullPointerException if the specified element is null
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size)
     */
    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (toAdd == null) throw new NullPointerException();
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        ensureCapacity();

        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = toAdd;
        size++;
        return true;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param toAdd element to be appended to this list
     * @return {@code true} (as specified by {@link ListADT#add})
     * @throws NullPointerException if the specified element is null
     */
    @Override
    public boolean add(E toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException();

        ensureCapacity();

        data[size++] = toAdd;
        return true;
    }

    /**
     * Appends all of the elements in the specified list to the end of this list.
     *
     * @param toAdd list containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws NullPointerException if the specified list is null
     */
    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException();

        utilities.Iterator<? extends E> it = toAdd.iterator();
        while (it.hasNext()) {
            add(it.next());
        }

        return true;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return data[index];
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        E element = data[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(data, index + 1, data, index, numMoved);
        }
        data[--size] = null; // clear to let GC do its work

        return element;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * If the list does not contain the element, it is unchanged.
     *
     * @param toRemove element to be removed from this list, if present
     * @return the element that was removed from the list, or null if the list did not contain the element
     * @throws NullPointerException if the specified element is null
     */
    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null) throw new NullPointerException();

        for (int i = 0; i < size; i++) {
            if (toRemove.equals(data[i])) {
                return remove(i);
            }
        }

        return null;
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index index of the element to replace
     * @param toChange element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws NullPointerException if the specified element is null
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null) throw new NullPointerException();
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        E oldElement = data[index];
        data[index] = toChange;

        return oldElement;
    }

    /**
     * Checks if the list is empty.
     *
     * @return {@code true} if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns {@code true} if this list contains the specified element.
     *
     * @param toFind element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     * @throws NullPointerException if the specified element is null
     */
    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) throw new NullPointerException();

        for (E element : data) {
            if (toFind.equals(element)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence (from first to last element).
     *
     * @param toHold the array into which the elements of the list are to be stored
     * @return an array containing the elements of this list
     * @throws NullPointerException if the specified array is null
     */
    @Override
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) throw new NullPointerException();

        if (toHold.length < size) {
            return copyOf(data, size, toHold.getClass());
        }

        System.arraycopy(data, 0, toHold, 0, size);
        if (toHold.length > size) {
            toHold[size] = null;
        }

        return toHold;
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence (from first to last element).
     *
     * @return an array containing all of the elements in this list
     */
    @Override
    public Object[] toArray() {
        return copyOf(data, size, Object[].class);
    }

    /**
     * Private class for MyArrayList iterator.
     */
    private class MyArrayListIterator implements utilities.Iterator<E> {
    	/**
         * Current index of the iterator.
         */
        private int currentIndex = 0;

        /**
         * Returns {@code true} if the iteration has more elements.
         * 
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements to iterate over.");
            }
            return data[currentIndex++];
        }
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * 
     * @return an iterator over the elements in this list in proper sequence
     */
    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }
}
