package zoo;

/**
 * Class representing a Snake, a specific type of Animal. Snakes are compatible
 * with Forest and Desert.
 */
public class Snake extends Animal {
	// Boolean for storing a snake whether or not a snake is venomous
	private boolean isVenomous;

	public Snake(String name, int age, boolean isVenomous) {
		// Call the animal constructor and initialize the isVenomous variable
		super(name, age);
		this.isVenomous = isVenomous;
	}

	public boolean isVenomous() {
		return this.isVenomous;
	}

	@Override
	public boolean isCompatibleWithHabitat(String habitatType) {
		// HabitatType cannot be null
		if (habitatType == null) {
			return false;
		}

		/*
		 * Since snakes are only compatible with Forests or Deserts, 
		 * check to see if the habitat is "forest" or "Desert" regardless of case
		 */
		return (habitatType.equalsIgnoreCase("Forest") || 
				habitatType.equalsIgnoreCase("Desert"));
	}

	public String toString() {
		/*
		 * Show a the snake is venomous or not depending on the isVenomous variable
		 */
		String venomous = isVenomous() ? "Venomous" : "Non-venomous";
		return super.toString() + " (" + venomous + ")";
	}
}
