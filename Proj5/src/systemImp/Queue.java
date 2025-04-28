package systemImp;

/**
 * A class that implements a Queue using your custom Doubly Linked List.
 * All operations must be O(1).
 */
public class Queue<T extends Comparable<T>> {
    private DoublyLinkedList<T> list; // The doubly linked list that backs the queue.
    
    public Queue() {
    	list = new DoublyLinkedList<T>();
    }
    
    public void enqueue(T data) {
    	list.addLast(data);
    }
    
    // Removes the front element
    public T dequeue() {
    	if (isEmpty()) {
    		throw new IllegalStateException("Empty queue!");
    	}
    	T element = list.getFirst();
    	list.removeFirst();
    	return element;
    }
    
    // Returns the front element
    public T peek() {
    	if (isEmpty()) {
    		throw new IllegalStateException("Empty queue!");
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

