package myDeque;

import java.util.Arrays;

public class CircularDeque<T> {
    
    private T[] deque; // The circular array to store deque elements
    private int front;  // Index of the front of the deque
    private int rear;   // Index of the rear of the deque
    private int size;   // Current number of elements in the deque
    private final int capacity; // Maximum capacity of the deque
    
    @SuppressWarnings("unchecked")
	public CircularDeque(int capacity) {
    	if (capacity <= 0) {
    		throw new IllegalArgumentException("Capacity must be greater than 0.");
    	}
    	
    	this.capacity = capacity;
    	deque = (T[]) new Object[capacity];
    	
    	front = 0;
    	rear = 0;
    	size = 0;
    }
    
    /* 
     * All the add and remove methods use the modulo operator to allow
     * the program to easily wrap around the deque array.
     */
    
    public void addFront(T element) {
    	if (isFull()) {
    		throw new IllegalStateException("Deque is full.");
    	}
    	
    	/* 
    	 * Subtracting front and using modulo is a shorter way
    	 * of getting the rightmost index
    	 */
        front = (front - 1 + capacity) % capacity;
        deque[front] = element;
    	
    	size++;
    }
    
    public void addRear(T element) {
    	if (isFull()) {
    		throw new IllegalStateException("Deque is full.");
    	}
    	
    	deque[rear] = element;
    	rear = (rear + 1) % capacity;
    	
    	size++;
    }
    
    public T removeFront() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty.");
        }

        T removedElement = deque[front];
        
        // Move front forward since it is the rightmost index
        front = (front + 1)% capacity;
        size--;
        
        return removedElement;
    }

    
    public T removeRear() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty.");
        }
        
        // Move rear back since it is the leftmost index
        rear = (rear - 1 + capacity) % capacity;
        
        T removedElement = deque[rear];
        size--;

        return removedElement;
    }

    
    public T peekFront() {
    	if (isEmpty()) {
    		throw new IllegalStateException("Deque is empty.");
    	}
    	
    	return deque[front];
    }
    
    public T peekRear() {
    	if (isEmpty()) {
    		throw new IllegalStateException("Deque is empty.");
    	}
    	
    	// Subtract 1 from rear to get the correct index
    	int peekIndex = (rear - 1 + capacity) % capacity;
    	return deque[peekIndex];
    }
    
    public boolean isEmpty() {
    	return size == 0;
    }
    
    public boolean isFull() {
    	return size >= capacity;
    }
    
    public int size() {
    	return size;
    }
    
    // Illusion of clearing, do not actually remove any elements
    public void clear() {
    	front = 0;
    	rear = 0;
    	size = 0;
    }
    
    public String toString() {
    	return "Circular Deque [deque=" + Arrays.toString(deque) + ", front="
    	+ front + ", rear=" + rear + ", size=" + size + 
    	", capacity=" + capacity + "]";
    }
}

