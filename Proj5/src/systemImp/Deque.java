package systemImp;

/**
 * A class that implements a Deque (double-ended queue) using a custom Doubly Linked List.
 * All operations (insertion and removal from both ends) must be O(1).
 */
public class Deque<T extends Comparable<T>> {
    private DoublyLinkedList<T> list; // The doubly linked list backing the deque.

    public Deque() {
    	list = new DoublyLinkedList<T>();
    }
    
    public void addFirst(T data) {
    	list.addFirst(data);
    }
    
    public void addLast(T data) {
    	list.addLast(data);
    }
    
    // Returns front element
    public T peekFirst() {
    	if (isEmpty()) {
    		throw new IllegalStateException("Empty deque!");
    	}
    	return list.getFirst();
    }
    
    // Retruns rear element
    public T peekLast() {
    	if (isEmpty()) {
    		throw new IllegalStateException("Empty deque!");
    	}
    	return list.getLast();
    }
    
    public T removeFirst() {
    	if (isEmpty()) {
    		throw new IllegalStateException("Empty deque!");
    	}
    	T element = list.getFirst();
    	list.removeFirst();
    	return element;
    }
    
    public T removeLast() {
    	if (isEmpty()) {
    		throw new IllegalStateException("Empty deque!");
    	}
    	T element = list.getLast();
    	list.removeLast();
    	return element;
    }
      
    public boolean isEmpty() {
    	return size() == 0;
    }
       
    public int size() {
    	return list.getSize();
    }
  
}

