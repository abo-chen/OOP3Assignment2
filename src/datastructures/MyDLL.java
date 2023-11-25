package datastructures;

import exceptions.NoSuchElementException;
import utilities.Iterator;
import utilities.ListADT;

/**
 * Custom implementation of a doubly linked list.
 * This class provides a linked list implementation that conforms to the ListADT interface,
 * allowing for efficient element insertion and removal at both ends of the list.
 *
 * @param <E> the type of elements held in this list
 */
public class MyDLL<E> implements ListADT<E> {
    private MyDLLNode<E> head;
    private MyDLLNode<E> tail;
    private int size;

    /**
     * Constructs an empty doubly linked list.
     */
    public MyDLL() {
        head = null;
        tail = null;
        size = 0;
    }

    // Implement ListADT methods here using MyDLLNode

    /**
     * Returns an iterator over elements of type {@code E}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private MyDLLNode<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
            	if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in the iterator");
                }
                E element = current.getElement();
                current = current.getNext();
                return element;
            }
        };
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
	@Override
	public int size() {
		return size;
	}

	/**
     * Removes all the elements from this list.
     * The list will be empty after this method returns.
     */
	@Override
	public void clear() {
		head = null;
	    tail = null;
	    size = 0;
	}

	/**
     * Adds an element at a specified index in this list.
     * Shifts the element currently at that position (if any) and any subsequent elements to the right.
     *
     * @param index index at which the specified element is to be inserted
     * @param toAdd element to be inserted
     * @return {@code true} (as specified by {@link ListADT#add})
     * @throws NullPointerException if the specified element is null
     * @throws IndexOutOfBoundsException if the index is out of range
     */
	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
	    if (toAdd == null) throw new NullPointerException("Cannot add null element");
	    if (index < 0 || index > size) throw new IndexOutOfBoundsException("Index out of bounds");

	    MyDLLNode<E> newNode = new MyDLLNode<>(toAdd, null, null);
	    if (head == null) {
	        head = newNode;
	        tail = newNode;
	    } else if (index == 0) {
	        newNode.setNext(head);
	        head.setPrevious(newNode);
	        head = newNode;
	    } else if (index == size) {
	        tail.setNext(newNode);
	        newNode.setPrevious(tail);
	        tail = newNode;
	    } else {
	        MyDLLNode<E> current = head;
	        for (int i = 0; i < index - 1; i++) {
	            current = current.getNext();
	        }
	        newNode.setNext(current.getNext());
	        newNode.setPrevious(current);
	        current.getNext().setPrevious(newNode);
	        current.setNext(newNode);
	    }
	    size++;
	    return true;
	}


	/**
     * Adds the specified element to the end of this list.
     *
     * @param toAdd element to be appended to this list
     * @return {@code true} (as specified by {@link ListADT#add})
     * @throws NullPointerException if the specified element is null
     */
	@Override
	public boolean add(E toAdd) throws NullPointerException {
		return add(size, toAdd);
	}

	/**
     * Adds all of the elements in the specified list to the end of this list.
     *
     * @param toAdd list containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws NullPointerException if the specified list is null
     */
	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
	    if (toAdd == null) throw new NullPointerException("Cannot add null list");
	    Iterator<? extends E> iter = toAdd.iterator();
	    while (iter.hasNext()) {
	        add(iter.next());
	    }
	    return true;
	}

	/**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
	    if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index out of bounds");
	    MyDLLNode<E> current = head;
	    for (int i = 0; i < index; i++) {
	        current = current.getNext();
	    }
	    return current.getElement();
	}


	/**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left.
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
	    if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index out of bounds");

	    MyDLLNode<E> toRemove;
	    if (index == 0) {
	        toRemove = head;
	        head = head.getNext();
	        if (head != null) head.setPrevious(null);
	    } else {
	        MyDLLNode<E> current = head;
	        for (int i = 0; i < index; i++) {
	            current = current.getNext();
	        }
	        toRemove = current;
	        if (current.getPrevious() != null) current.getPrevious().setNext(current.getNext());
	        if (current.getNext() != null) current.getNext().setPrevious(current.getPrevious());
	    }

	    if (index == size - 1) {
	        tail = toRemove.getPrevious();
	    }

	    size--;
	    return toRemove.getElement();
	}

	/**
     * Removes the first occurrence of the specified element from this list, if it is present.
     *
     * @param toRemove element to be removed from this list, if present
     * @return the element that was removed from the list, or null if the list did not contain the element
     * @throws NullPointerException if the specified element is null
     */
	@Override
	public E remove(E toRemove) throws NullPointerException {
	    if (toRemove == null) throw new NullPointerException("Cannot remove null element");
	    MyDLLNode<E> current = head;
	    for (int i = 0; i < size; i++) {
	        if (current.getElement().equals(toRemove)) {
	            return remove(i);
	        }
	        current = current.getNext();
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
     * @throws IndexOutOfBoundsException if the index is out of range
     */
	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
	    if (toChange == null) throw new NullPointerException("Cannot set null element");
	    if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index out of bounds");

	    MyDLLNode<E> current = head;
	    for (int i = 0; i < index; i++) {
	        current = current.getNext();
	    }
	    E oldElement = current.getElement();
	    current.setElement(toChange);
	    return oldElement;
	}

	/**
     * Returns {@code true} if this list contains no elements.
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
	    if (toFind == null) throw new NullPointerException("Cannot check null element");
	    MyDLLNode<E> current = head;
	    while (current != null) {
	        if (current.getElement().equals(toFind)) {
	            return true;
	        }
	        current = current.getNext();
	    }
	    return false;
	}

	/**
     * Returns an array containing all of the elements in this list in proper sequence.
     *
     * @param toHold the array into which the elements of this list are to be stored
     * @return an array containing the elements of this list
     * @throws NullPointerException if the specified array is null
     */
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
	    if (toHold == null) throw new NullPointerException("Input array cannot be null");
	    if (toHold.length < size) {
	        toHold = (E[])java.lang.reflect.Array.newInstance(toHold.getClass().getComponentType(), size);
	    }
	    MyDLLNode<E> current = head;
	    for (int i = 0; i < size; i++) {
	        toHold[i] = current.getElement();
	        current = current.getNext();
	    }
	    return toHold;
	}

	/**
     * Returns an array containing all of the elements in this list in proper sequence.
     *
     * @return an array containing all of the elements in this list
     */
	@Override
	public Object[] toArray() {
	    Object[] array = new Object[size];
	    MyDLLNode<E> current = head;
	    for (int i = 0; i < size; i++) {
	        array[i] = current.getElement();
	        current = current.getNext();
	    }
	    return array;
	}

    // Other methods implementation
}
