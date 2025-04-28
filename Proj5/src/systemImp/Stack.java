package systemImp;

/**
 * A class that implements a Stack using your custom Doubly Linked List.  All operations must be O(1).
 */
public class Stack<T extends Comparable<T>> {
    private DoublyLinkedList<T> list; // The doubly linked list that will back the stack.

    public Stack() {
    	list = new DoublyLinkedList<T>();
    }
    
    public void push(T data) {
    	list.addFirst(data);
    }
    
    // Removes the top element
    public T pop() {
    	if (isEmpty()) {
    		throw new IllegalStateException("Empty stack!");
    	}
    	T element = list.getFirst();
    	list.removeFirst();
    	return element;
    }
    
    // Returns the top element
    public T peek() {
    	if (isEmpty()) {
    		throw new IllegalStateException("Empty stack!");
    	}
    	return list.getFirst();
    }
    
    public boolean isEmpty() {
    	return size() == 0;
    }
    
    public int size() {
    	return list.getSize();
    }
 
}

