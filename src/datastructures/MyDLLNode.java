package datastructures;

public class MyDLLNode<E> {
	
	E data;
	MyDLLNode<E> next;
	MyDLLNode<E> prev;
	
	public MyDLLNode(E item) {
		this.data = item;
		this.next = null;
		this.prev = null;
	}

}
