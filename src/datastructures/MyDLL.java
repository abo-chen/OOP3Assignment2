package datastructures;

import utilities.ListADT;
import utilities.Iterator;

public class MyDLL<E> {
	private MyDLLNode<E> first;
	private MyDLLNode<E> last;
	private int size;
	private ListADT<E> elements;
	
	public MyDLL() {
		this.first = null;
		this.last = null;
		this.size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public void clear() {
		elements.clear();
	}
	
	@SuppressWarnings("unchecked")
	public boolean add( int index, E toAdd ) throws NullPointerException, IndexOutOfBoundsException {
		MyDLLNode newNode = new MyDLLNode<>(toAdd);
		if (index == size) {
			if (isEmpty()) {
				first = newNode;
				last = newNode;
			}
			else {
				newNode.prev = last;
				last.next = newNode;
				last = newNode;
			}
		}
		else {
			if (isEmpty()) {
				first = newNode;
				last = newNode;
			}
			else {
				newNode.next = first;
				first.prev = newNode;
				first = newNode;
			}
		}
		size++;
		return true;
	}
	
	public boolean add( E toAdd ) throws NullPointerException {
		return elements.add(toAdd);
	}
	
	public boolean addAll( ListADT<? extends E> toAdd ) throws NullPointerException {
		return elements.addAll(elements);
	}
	
	public E get( int index ) throws IndexOutOfBoundsException {
		return elements.get(index);
	}
	
	public E remove( int index ) throws IndexOutOfBoundsException {
		return elements.remove(index);
	}
	
	public void set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		elements.set(index, toChange);
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public Iterator<E> iterator() {
        return new Iterator<E>() {
            private MyDLLNode<E> current = first;

            public boolean hasNext() {
                return current != null;
            }

            public E next() {
                if (!hasNext()) {
                    throw new IllegalStateException("No more elements to iterate");
                }
                E data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}
