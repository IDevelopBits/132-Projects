package zoo;

/**
 * Represents an Eagle, a specific type of Animal.
 * Eagles have an additional field for wingspan and are compatible with "Mountain" and "Forest" habitats.
 */
public class Eagle extends Animal {
	// A decimal representing the length of an eagle's wingspan (meters)
    private double wingspan;
    
    public Eagle(String name, int age, double wingspan) {
    	// Set the eagle's name and age using the animal (parent) constructor
    	super(name, age);
    	// Ensure the wingspan is between 1.5 to 3.0
    	if (wingspan < 1.5 || wingspan > 3.0) {
    		throw new IllegalArgumentException("Invalid wingspan length!");
    	}
    	this.wingspan = wingspan;
    }
    

    public boolean isCompatibleWithHabitat(String habitatType) {
    	if (habitatType == null) {
			return false;
		}
    	
    	/*
		 * Since eagles are only compatible with Mountains or Forests, 
		 * check to see if the habitat is "Mountain" or "forest" regardless of case
		 */
    	return (habitatType.equalsIgnoreCase("Mountain") || 
    			habitatType.equalsIgnoreCase("Forest"));
    }
    
    
    public double getWingspan() {
    	return this.wingspan;
    }
    
    // Return the name, age, and the wingspan of the eagle in meters as a String
    public String toString() {
    	return super.toString() + " [Wingspan: " + this.wingspan + " meters]";
    } 
}
