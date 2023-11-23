package datastructures;

import utilities.ListADT;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements ListADT<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] data;
    private int size;

    public MyArrayList() {
        this.data = (E[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }
    
    private E[] copyOf(E[] original, int newLength, Class<? extends Object[]> class1) {
        @SuppressWarnings("unchecked")
        E[] copy = ((Object)class1 == (Object)Object[].class)
            ? (E[]) new Object[newLength]
            : (E[]) java.lang.reflect.Array.newInstance(class1.getComponentType(), newLength);
        System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
        return copy;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        this.data = (E[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    private void ensureCapacity() {
        if (size >= data.length) {
            E[] newData = (E[]) new Object[data.length * 2];
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }
    }

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

    @Override
    public boolean add(E toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException();

        ensureCapacity();

        data[size++] = toAdd;
        return true;
    }

    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException();

        utilities.Iterator<? extends E> it = toAdd.iterator();
        while (it.hasNext()) {
            add(it.next());
        }

        return true;
    }


    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return data[index];
    }

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

    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null) throw new NullPointerException();
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        E oldElement = data[index];
        data[index] = toChange;

        return oldElement;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

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

    @Override
    public Object[] toArray() {
        return copyOf(data, size, Object[].class);
    }

    private class MyArrayListIterator implements utilities.Iterator<E> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements to iterate over.");
            }
            return data[currentIndex++];
        }
    }

    @Override
    public utilities.Iterator<E> iterator() {
        return new MyArrayListIterator();
    }
}
