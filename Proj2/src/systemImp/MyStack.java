package systemImp;

/**
 * A Stack class that is implemented using MyArrayList as the underlying data structure.
 * This class follows the Last-In-First-Out (LIFO) principle.
 */
public class MyStack {

    // The underlying MyArrayList instance
    private MyArrayList stackList;
    
    public MyStack(Class<?> elementType) {
    	if (elementType == null) {
    		throw new IllegalArgumentException("Element type cannot be null.");
    	}
    	this.stackList = new MyArrayList(elementType);
    }
    
    public Object pop() {
    	if (isEmpty()) {
    		throw new IllegalStateException("Stack is empty. Cannot pop.");
    	}
    	/* Since the top element of a stack is popped, we just need to retrieve
    	the last ArrayList element and then remove it */
    	Object obj = stackList.get(stackList.size() - 1);
    	stackList.remove(stackList.size() - 1);
    	return obj;
    }
    
    public void push(Object element) {
    	// Only push an element if there are of the same type
    	if (!stackList.elementType.isInstance(element)) {
    		throw new IllegalArgumentException("Element type "
    				+ "does not match stack element type.");
    	}
    	stackList.add(element);
    }
    
    public Object peek() {
    	if (isEmpty()) {
    		throw new IllegalStateException("Stack is empty. Cannot peek.");
    	}
    	// The peak element would just be the stack's last element
    	return stackList.get(stackList.size() - 1);
    }
    
    public boolean isEmpty() {
    	return stackList.size() == 0;
    }
    
    public int size() {
    	return stackList.size();
    }
    
    public void clear() {
    	stackList.clear();
    }
    
    public Class<?> elementType() {
    	return stackList.elementType;
    }
    
    public String toString() {
    	return "Stack: " + stackList.toString();
    }
   
}
