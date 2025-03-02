package myDeque;



public class CircularDequeDriver {
    public static void main(String[] args) {
        // Create a CircularDeque of integers with a capacity of 5
        CircularDeque<Integer> deque = new CircularDeque<>(5);
        
        System.out.println("Initial deque: " + deque);

        // Add elements to the front and rear
        deque.addRear(10);
        deque.addRear(20);
        deque.addFront(5);
        deque.addFront(1);
        deque.addRear(30);

        System.out.println("Deque after adding elements: " + deque);

        // Peek at front and rear
        System.out.println("Front element: " + deque.peekFront());
        System.out.println("Rear element: " + deque.peekRear());

        // Remove elements from front and rear
        System.out.println("Removed from front: " + deque.removeFront());
        System.out.println("Removed from rear: " + deque.removeRear());

        System.out.println("Deque after removals: " + deque);

        // Check if the deque is empty or full
        System.out.println("Is deque empty? " + deque.isEmpty());
        System.out.println("Is deque full? " + deque.isFull());

        // Clear the deque
        deque.clear();
        System.out.println("Deque after clearing: " + deque);
    }
}

