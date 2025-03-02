package systemImp;

import java.util.Arrays;

/**
 * Custom implementation of an ArrayList that grows dynamically. The list uses
 * the doubling strategy to resize the underlying array. The list only allows
 * objects of a specific type (specified by the element class).
 */
public class MyArrayList {

	// Public final field to store the class type
	public final Class<?> elementType;

	// Underlying array to store the list's elements
	private Object[] elements;

	// Current size of the list
	private int size;

	public MyArrayList(Class<?> elementType) {
		if (elementType == null) {
			throw new IllegalArgumentException("Element type cannot be null.");
		}

		this.elementType = elementType;
		elements = new Object[10];
		size = 0;
	}

	public void add(Object element) {
		if (!elementType.isInstance(element)) {
			throw new IllegalArgumentException("Invalid element type.");
		}

		/*
		 * Add the element to the array if there is still space else the array 
		 * must be made larger first
		 */
		if (size >= elements.length) {
			elements = Arrays.copyOf(elements, elements.length * 2);
		}

		elements[size] = element;
		size++;
	}

	public void addAll(Object[] array) {
		/*
		 * Only add elements if the entire array is of the same type otherwise
		 * throw an exception!
		 */
		for (Object element : array) {
			if (!elementType.isInstance(element)) {
				throw new IllegalArgumentException("Invalid element type.");
			}
		}
		/*
		 * Two separate for loops to ensure we don't 
		 * add part of an array instead of the full array
		 */
		for (Object element : array) {
			add(element);
		}
	}

	public void remove(int index) {
		if (index >= size) {
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}

		/*
		 * Search through the elements array for the element in n index to 
		 * remove then shrink the list by 1
		 */
		for (int i = 0; i < size; i++) {
			if (i == index) {
				shiftLeftFromIndex(i);
				size--;
				break;
			}
		}
	}

	public void remove(Object element) {
	    for (int i = 0; i < size; i++) {
	        if ((elements[i].equals(element))) {
	            shiftLeftFromIndex(i);
	            size--;
	            return;
	        }
	    }
	    throw new IllegalArgumentException("Element not found.");
	}
	
	public int size() {
		return size;
	}

	public Object get(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}
		return elements[index];
	}

	public void trimToSize() {
		elements = Arrays.copyOf(elements, size);
	}

	public MyArrayList sublist(int fromIndex, int toIndex) {
		if (fromIndex > toIndex || fromIndex < 0 || toIndex > size) {
			throw new IndexOutOfBoundsException("Invalid index range.");
		}

		MyArrayList sublist = new MyArrayList(elementType);
		sublist.elements = Arrays.copyOfRange(elements, fromIndex, toIndex);
		/*
		 * After creating the sublist, in order to give the sublist the 
		 * appropriate size number, we need to subtract fromIndex from toIndex
		 */
		sublist.size = toIndex - fromIndex;
		return sublist;
	}
	
	public void clear() {
		size = 0;
		elements = new Object[10];
	}
	
	public boolean contains(Object element) {
	    for (int i = 0; i < size; i++) {	        
	        if (elements[i].equals(element)) {
	        	return true;
	        }
	    }
	    return false;
	}

	// Moves elements from n index to the left
	private void shiftLeftFromIndex(int index) {
	    for (int i = index; i < size - 1; i++) {
	        elements[i] = elements[i + 1]; 
	    }
	    // Clear the last position to prevent duplicate elements
	    elements[size - 1] = null; 
	}
	
	public String toString() {
		Object[] listToReturn = Arrays.copyOf(elements, size);
		return Arrays.toString(listToReturn);
	}
	
	public String printFullArray() {
		Object[] listToReturn = Arrays.copyOf(elements, elements.length);
		return Arrays.toString(listToReturn);
	}

}
