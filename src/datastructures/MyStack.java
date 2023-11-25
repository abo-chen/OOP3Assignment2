package datastructures;

import utilities.StackADT;
import utilities.Iterator;

public class MyStack<E> implements StackADT<E> {

	private static final long serialVersionUID = 1L;
	private MyArrayList<E> list;


    public MyStack() {
        this.list = new MyArrayList<>();
    }

    
    public static class StackEmptyException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		public StackEmptyException(String message) {
            super(message);
        }
    }


    @Override
    public void push(E toAdd) throws NullPointerException {
        list.add(size(), toAdd);
    }

    
     
      
   

    @Override
    public E pop() throws StackEmptyException {
        if (isEmpty()) {
            throw new StackEmptyException("Stack is empty");
        }

        return list.remove(size() - 1);
    }


    @Override
    public E peek() throws StackEmptyException {
        if (isEmpty()) {
            throw new StackEmptyException("Stack is empty");
        }

        return list.get(size() - 1);
    }


    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }


    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        int index = 0;

        for (int i = size() - 1; i >= 0; i--) {
            array[index++] = list.get(i);
        }

        return array;
    }

    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        if (holder.length < size()) {
            holder = (E[]) new Object[size()];
        } else {
            for (int i = 0; i < holder.length; i++) {
                holder[i] = null;
            }
        }

        int index = 0;
        for (int i = size() - 1; i >= 0; i--) {
            holder[index++] = list.get(i);
        }

        return holder;
    }


    @Override
    public boolean contains(E toFind) throws NullPointerException {
        return list.contains(toFind);
    }


    @Override
    public int search(E toFind) {
        for (int i = 0; i < size(); i++) {
            if (toFind.equals(list.get(i))) {
                return size() - i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }


    @Override
    public boolean equals(StackADT<E> that) {
        if (this.size() != that.size()) {
            return false;
        }

        Iterator<E> thisIterator = this.iterator();
        Iterator<E> thatIterator = that.iterator();

        while (thisIterator.hasNext() && thatIterator.hasNext()) {
            if (!thisIterator.next().equals(thatIterator.next())) {
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
