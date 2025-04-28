package systemImp;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A doubly linked list implementation for storing elements.
 * This list supports basic operations like adding and removing elements at both ends.
 * @class Inner class description.
 * @param <E> The type of elements in this list, which must extend Comparable.
 */
public class DoublyLinkedList<E extends Comparable<E>> implements Iterable<E> {
    private Node head; //points to the first node
    private Node tail; //points to the last node
    private int size;  //number of elements in the list

    /**
     * Node represents a single element in the doubly linked list.
     * It contains references to the data and the next and previous nodes in the list.
     */
    private class Node {
        E data;
        Node next, prev;

        /**
         * Constructor for creating a new node.
         * 
         * @param data The data to be stored in the node.
         */
        Node(E data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    
    public void addFirst(E e) {
        if (e == null) {
            throw new NullPointerException("Element cannot be null");
        }

        Node newNode = new Node(e);

        if (size == 0) { 
            head = tail = newNode; 
        } else {
            newNode.next = head; // Link new node to the current head
            head.prev = newNode; // Set the previous pointer of old head
            head = newNode;
        }

        head.prev = null; // Ensure the first node's prev is null
        size++;
    }

    public void addLast(E e) {
        if (e == null) {
            throw new NullPointerException("Element cannot be null");
        }

        Node newNode = new Node(e);

        // If the list is empty, both head and tail should point to the new node
        if (size == 0) {
            head = tail = newNode;
        } else {
        	// Link new node to the current tail and update the old tail's next pointer
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }

        tail.next = null; // Ensure the last node's next is null
        size++;
    }

    
    public E getFirst() {
    	if (size == 0) {
    		throw new NoSuchElementException("List is empty!");
    	}
    	return head.data;
    }
    
    public E getLast() {
    	if (size == 0) {
    		throw new NoSuchElementException("List is empty!");
    	}
    	return tail.data;
    }
    
    public void removeFirst() {
    	if (size == 0) {
    		throw new NoSuchElementException("List is empty!");
    	}
    	head = head.next;
    	if (size != 1) {
    		head.prev = null;
    	}
    	size--;
    }
    
    public void removeLast() {
    	if (size == 0) {
    		throw new NoSuchElementException("List is empty!");
    	}
    	tail = tail.prev;
    	if (size != 1) {
    		tail.next = null;
    	}
    	size--;
    }
    
    public void removeAllInRange(E start, E end) {
        if (start == null || end == null) {
            throw new NullPointerException("Start or end is null");
        }
        if (!anyElementsInRange(start, end)) {
            throw new NoSuchElementException("No elements in specified range");
        }

        Node current = head;
        
        while (current != null) {
            Node nextNode = current.next;

            if (start.compareTo(current.data) <= 0 && end.compareTo(current.data) >= 0) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next; // Update head if first node is removed
                }

                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev; // Update tail if last node is removed
                }

                size--;
            }

            current = nextNode; // Move to the next node
        }
    }


    /* Loop through the linked list and if any elements are between start 
	 * and end return true else false */
    private boolean anyElementsInRange(E start, E end) {
        Node node = head;
        while (node != null) {
            if (start.compareTo(node.data) <= 0 && end.compareTo(node.data) >= 0) {
                return true;
            }
            node = node.next;
        }
        return false;
    }


    
    public int getSize() {
    	return size;
    }
    
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private Node current = head;
            private Node lastReturned = null;
			
            public boolean hasNext() {
                return current != null;
            }
            
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                lastReturned = current;
                E data = current.data;
                current = current.next;
                return data;
            }
            

            public void remove() {
                if (lastReturned == null) {
                    throw new IllegalStateException("next() must be called before remove()");
                }

                Node prevNode = lastReturned.prev;
                Node nextNode = lastReturned.next;

                if (prevNode != null) {
                    prevNode.next = nextNode;
                } else {
                    head = nextNode;
                }

                if (nextNode != null) {
                    nextNode.prev = prevNode;
                } else {
                    tail = prevNode;
                }

                lastReturned = null;
                size--;
            }
		};
	}
	
	public String insertionSort() {
	    if (head == null || head.next == null) {
	        return toString(); // No need to sort if the list has 0 or 1 element
	    }

	    StringBuilder result = new StringBuilder(); 
	    
	    // Print initial state (only first element is considered sorted)
	    result.append(printListState(head.next));
	    
	    Node current = head.next; 

	    while (current != null) {
	        Node key = current;
	        current = current.next;
	        
	        Node previous = key.prev; 

	        // Move backward and find the correct position for the key
	        while (previous != null && previous.data.compareTo(key.data) > 0) {
	            previous = previous.prev;
	        }

	        // Disconnect key from the current position
	        if (key.next != null) {
	            key.next.prev = key.prev;
	        }
	        if (key.prev != null) {
	            key.prev.next = key.next;
	        }

	        // Insert key in the correct position
	        if (previous == null) {
	            key.next = head;
	            key.prev = null;
	            head.prev = key;
	            head = key;
	        } else {
	            key.prev = previous;
	            key.next = previous.next;
	            if (previous.next != null) {
	                previous.next.prev = key;
	            }
	            previous.next = key;
	        }

	        // Print state after insertion (if there are more elements to process)
	        if (current != null) {
	            result.append(printListState(current));
	        } else {
	            // Special case for final state (everything sorted)
	            result.append(printListState(null));
	        }
	    }
	    return result.toString();
	}

	private String printListState(Node unsortedStart) {
	    StringBuilder state = new StringBuilder();
	    Node node = head;

	    // Append sorted part
	    while (node != unsortedStart) {
	        state.append(node.data).append(" ");
	        node = node.next;
	    }

	    // Append separator and unsorted part
	    state.append("| ");
	    while (node != null) {
	        state.append(node.data).append(" ");
	        node = node.next;
	    }

	    state.append("\n");
	    return state.toString();
	}


    
	@Override
	public String toString() {
	    if (head == null) {
	        return "[]"; // Empty list case
	    }

	    StringBuilder list = new StringBuilder("[");
	    Node node = head;

	    while (node != null) {
	        list.append(node.data);
	        if (node.next != null) {
	            list.append(", ");
	        }
	        node = node.next;
	    }

	    list.append("]");
	    return list.toString();
	}
}
