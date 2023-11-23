package datastructures;

import exceptions.NoSuchElementException;
import utilities.Iterator;
import utilities.ListADT;

public class MyDLL<E> implements ListADT<E> {
    private MyDLLNode<E> head;
    private MyDLLNode<E> tail;
    private int size;

    public MyDLL() {
        head = null;
        tail = null;
        size = 0;
    }

    // Implement ListADT methods here using MyDLLNode

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

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		head = null;
	    tail = null;
	    size = 0;
	}

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


	@Override
	public boolean add(E toAdd) throws NullPointerException {
		return add(size, toAdd);
	}

	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
	    if (toAdd == null) throw new NullPointerException("Cannot add null list");
	    Iterator<? extends E> iter = toAdd.iterator();
	    while (iter.hasNext()) {
	        add(iter.next());
	    }
	    return true;
	}


	@Override
	public E get(int index) throws IndexOutOfBoundsException {
	    if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index out of bounds");
	    MyDLLNode<E> current = head;
	    for (int i = 0; i < index; i++) {
	        current = current.getNext();
	    }
	    return current.getElement();
	}


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

	@Override
	public boolean isEmpty() {
	    return size == 0;
	}

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
