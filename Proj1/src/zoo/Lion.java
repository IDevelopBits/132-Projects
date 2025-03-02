package zoo;

/**
 * Class representing a Lion, a specific type of Animal. Lions are compatible
 * with the "Savannah" habitat type.
 */
public class Lion extends Animal {
	// boolean for storing the lion's alpha state
	private boolean isAlpha;

	public Lion(String name, int age, boolean isAlpha) {
		// Invoke the animal constructor and set the lion's alpha state
		super(name, age);
		this.isAlpha = isAlpha;
	}

	public boolean isAlpha() {
		return this.isAlpha;
	}

	@Override
	public boolean isCompatibleWithHabitat(String habitatType) {
		// Check for null beforehand to prevent a NullPointerException
		if (habitatType == null) {
			return false;
		}
		/*
		 * Since lions are only compatible with Savannah, 
		 * check to see if the habitat is "savannah" regardless of case
		 */
		return habitatType.equalsIgnoreCase("Savannah");
	}

	public String toString() {
		// Show if the lion is alpha or not depending on the isAlpha variable
		String alpha = isAlpha() ? " (Alpha)" : "";
		return super.toString() + alpha;
	}
}
