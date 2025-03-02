package zoo;

/**
 * Abstract base class representing an Animal in the zoo.
 * Provides common properties and methods for all animals.
 */
public abstract class Animal implements Comparable<Animal> {
    private String name;
    private int age;

    /**
     * Constructs an Animal with the given name and age. <br> <br>
     *
     * Note: Any exception message is fine, but it should clearly describe the
     * reason for the exception (e.g., "Name cannot be null." or "Age must be greater than 0.").
     *  
     * @param name the name of the animal. Cannot be null.
     * @param age  the age of the animal. Must be greater than 0.
     * @throws IllegalArgumentException if name is null or age is less than or equal to 0. 
     */

    public Animal(String name, int age) {
    	if (name == null) {
    		// Name must be a non-null value
    		throw new IllegalArgumentException("Name cannot be null!");
    	} else if (age <= 0) {
    		// Age cannot be negative or zero
    		throw new IllegalArgumentException("Age must be greater than 0!");
    	}
    	
    	// Set the name an age based on the arguments passed
    	this.name = name;
    	this.age = age;
    }

    /**
     * Gets the name of the animal.
     *
     * @return the name of the animal.
     */
    public String getName() {
    	return this.name;
    }

    /**
     * Gets the age of the animal.
     *
     * @return the age of the animal.
     */
    public int getAge() {
    	return this.age;
    }

    /**
     * Increments the age of the animal by 1.
     */
    public void incrementAge() {
    	this.age += 1;
    }

    /**
     * Abstract method to determine if the animal is compatible with a specific habitat.
     *
     * @param habitatType the type of habitat. If null, it will just return false
     * @return true if the animal is compatible, false otherwise.
     */
    public abstract boolean isCompatibleWithHabitat(String habitatType);
       
    /**
     * Compares this animal to another based on name (case-insensitive) and then age. <br> <br>
     *
     * Note: Any exception message is fine, but it should clearly describe the
     * reason for the exception (e.g., "Cannot compare to a null animal.").
     * 
     * @param other the other animal to compare to. Can be null.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, 
     *         or greater than the specified object.
     * @throws NullPointerException if the other animal is null.
     */
    
    @Override
    public int compareTo(Animal other) {
    	if (other == null) {
    		throw new NullPointerException("Cannot compare to a null animal;");
    	}
    	
    	// Compare animals in alphabetic (name) and numeric order (age)
    	return (this.name.compareToIgnoreCase(other.name) 
    			+ this.age - other.age);
    }

    /**
     * Checks if this animal is equal to another object.
     * Two animals are considered equal if they have the same name (case-insensitive) and age.  <br> <br>
	 *
	 * Note: use getClass() not instanceof so you only consider animals of the same subtypes
	 *
     * @param obj the object to compare to. Can be null.
     * @return true if the objects are equal, false otherwise.
     */
    @Override 
    public boolean equals(Object obj) {
    	if (this == obj)
    		return true;
    	if (obj == null || getClass() != obj.getClass())
    		return false; 

    	Animal other = (Animal) obj;
    	/* Use equalIgnoreCase to make sure two names are equally regardless of 
    	 * casing
    	 */
    	return (this.name.equalsIgnoreCase(other.name) && 
    			this.age == other.age); 
    }

    /**
     * Returns a string representation of the animal. Do not change this! For text of overridden toString see output of public test 9
     *
     * @return a string representation of the animal.
     */
    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}
