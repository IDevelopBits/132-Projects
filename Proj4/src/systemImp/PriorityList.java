
package systemImp;

import java.util.Comparator;

/**
 * A generic Priority List that maintains elements in sorted order.
 * Supports both natural ordering (Comparable) and custom ordering (Comparator).
 *
 * @param <T> The type of elements stored in the list. Must implement Comparable<T>.
 */
public class PriorityList<T extends Comparable<T>> {
    private final T[] data;
    private int size;//number of elements in list
    private final boolean useComparator; //if true use Comparator
    private final Comparator<T> comparator; // custom comparator
    
    @SuppressWarnings("unchecked")
	public PriorityList(int capacity, boolean useComparator, Comparator<T> comparator) {
		this.useComparator = useComparator;
    	this.comparator = useComparator ? comparator : null;
    	size = 0;
    	data = (T[]) new Comparable[capacity];
    }
    
    public int binarySearchFind(T item) {
        int left = 0, right = size - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Use Comparator if available, else use Comparable
            int compareValue = useComparator
                    ? comparator.compare(data[mid], item)
                    : data[mid].compareTo(item);

            if (compareValue == 0 && data[mid].equals(item)) {
                return mid; // Target found
            } else if (compareValue < 0) {
                left = mid + 1; // Search right half
            } else {
                right = mid - 1; // Search left half
            }
        }
        return -1; // Target not found
    }

    public int binarySearchInsertionPoint(T item) {
        int left = 0, right = size - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // Use Comparator if useComparator is true, else use Comparable
            int compareValue = useComparator
                    ? comparator.compare(data[mid], item) 
                    : data[mid].compareTo(item);
          
            if (compareValue < 0) {
                left = mid + 1; 
            } else {
                right = mid - 1;
            }
        }
        
        // The insertion point is where left ends up
        return left;
    }
    
    public void add(T item) {
    	if (isFull()) {
    		throw new IllegalStateException("Priority List is full");
    	}
    	
    	int indexToAdd = binarySearchInsertionPoint(item);
    	shiftData(indexToAdd, true);
    	data[indexToAdd] = item;
    	size++;
    }
    
    public boolean remove(T item) {
    	int indexToRemove = binarySearchFind(item);
    	if (indexToRemove == -1) {
    		return false;
    	}
    	
    	// shift data left to eliminate empty space after removing
    	data[indexToRemove] = null;
    	shiftData(indexToRemove, false);
    	size--;
    	return true;
    }
    
    // Shift data right (true) or left (false)
    private void shiftData(int index, boolean direction) {
        if (direction) {
            for (int i = size; i > index; i--) { 
                data[i] = data[i - 1];
            }
        } else { 
            for (int i = index; i < size - 1; i++) { 
                data[i] = data[i + 1];
            }
            // prevent duplicate elements after shifting
            data[size - 1] = null;
        }
    }
   
    public int size() {
    	return size;
    }
    
    public boolean isFull() {
    	return size >= data.length;
    }
    
    public boolean isEmpty() {
    	return size == 0;
    }
    
    public T get(int index) {
    	if (index < 0 || index >= size) {
    		throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    	}
    	
    	return data[index];
    }
    
    public String toString() {
    	StringBuilder listString = new StringBuilder();
    	listString.append("[ ");
    	
    	for (int i = 0; i < size; i++) {
    		listString.append(data[i] + " ");
    	}
    	listString.append("]");
    	
    	return listString.toString();
    }
   
}

