package zoo;

import java.util.Arrays;

/**
 * Class representing a habitat grid in the zoo. Each habitat has a type for
 * each row and can house animals based on their compatibility.
 */
public class Habitat {
	private Animal[][] habitatGrid; // 2D grid to store animals
	private String[] rowHabitatTypes; // Types for each row of habitatGrid

	public Habitat(int cols, String[] habitatTypes) {
		if (cols < 1 || habitatTypes == null || habitatTypes.length == 0) {
			throw new IllegalArgumentException("Invalid value given!");
		}
		
		this.rowHabitatTypes = habitatTypes;
		this.habitatGrid = new Animal[rowHabitatTypes.length][cols];
	}

	public boolean placeAnimal(Animal animal) {
		/*
		 * Make sure that for both placeAnimal methods that the same animal has not
		 * already been added before
		 */
		if (wasAdded(animal)) {
			return false;
		}

		for (int i = 0; i < habitatGrid.length; i++) {
			// Loop through the compatible row and find a space for the animal
			if (animal.isCompatibleWithHabitat(rowHabitatTypes[i])) {
				for (int j = 0; j < habitatGrid[i].length; j++) {
					if (habitatGrid[i][j] == null) {
						habitatGrid[i][j] = animal;
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean placeAnimalAt(Animal animal, int row, int col) {
		if (row >= habitatGrid.length || row < 0 || 
				col >= habitatGrid[0].length || col < 0) {
			return false;
		}

		if (wasAdded(animal)) {
			return false;
		}

		// Make sure the row is empty before attempting to put any animal in
		if (habitatGrid[row][col] == null && 
				animal.isCompatibleWithHabitat(rowHabitatTypes[row])) {
			habitatGrid[row][col] = animal;
			return true;
		}
		return false;
	}

	/*
	 * Private method for the placeAnimal methods to check that the animal doesn't
	 * already exist
	 */
	private boolean wasAdded(Animal animal) {
		for (int i = 0; i < habitatGrid.length; i++) {
			for (int j = 0; j < habitatGrid[i].length; j++) {
				if (animal.equals(habitatGrid[i][j])) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean removeAnimal(Animal animal) {
		for (int i = 0; i < habitatGrid.length; i++) {
			for (int j = 0; j < habitatGrid[i].length; j++) {
				if (habitatGrid[i][j] != null && 
						animal.equals(habitatGrid[i][j])) {
					habitatGrid[i][j] = null;
					return true;
				}
			}
		}
		return false;
	}

	public Animal getAnimal(int row, int col) {
		if (row >= habitatGrid.length || row < 0 || 
				col >= habitatGrid[0].length || col < 0) {
			return null;
		}
		return habitatGrid[row][col];
	}

	public String displayGrid() {
		String grid = "";
		// Loop through every row and add its string to the String grid
		for (Animal[] row : habitatGrid) {
			grid += Arrays.toString(row) + '\n';
		}
		return grid;
	}
}
