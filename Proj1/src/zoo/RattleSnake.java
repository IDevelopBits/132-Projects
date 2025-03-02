package zoo;

/**
 * Represents a Rattlesnake, a specific type of Snake. Rattlesnakes have an
 * additional field for the number of rattle segments.
 */

public class RattleSnake extends Snake {
	// Stores the rattlesnake's number of rattle segments
	private int rattleSegments;

	public RattleSnake(String name, int age, int rattleSegments) {
		/*
		 * Since rattlesnakes are venomous set isVenemous to true in the snake
		 * constructor
		 */
		super(name, age, true);

		// Ensure rattle segments are between 8 to 13 segments
		if (rattleSegments < 8 || rattleSegments > 13) {
			throw new IllegalArgumentException("Segments must be between 8-13!");
		}
		
		/* Set the number of rattle segments to the number given the in
		 * rattlesnake constructor
		 */
		this.rattleSegments = rattleSegments;
	}

	public int getRattleSegments() {
		return this.rattleSegments;
	}

	// return the parent toString method along with its amount of rattleSegments
	public String toString() {
		return super.toString() + "[Rattle Segments: " + this.rattleSegments + "]";
	}
}
