package zoo;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a zoo that manages animals and their habitats.
 */
public class Zoo {
	private List<Animal> animals;
	private Habitat habitat;

	public Zoo(int cols, String[] habitatTypes) {
		animals = new ArrayList<Animal>();
		this.habitat = new Habitat(cols, habitatTypes);
	}

	public String addAnimal(Animal animal, Integer row, Integer col) {
		// Boolean for checking if the animal was successfully placed or not
		boolean placed;
		animals.add(animal);
		
		if (row == null || col == null) {

			placed = habitat.placeAnimal(animal);
			if (!placed) {
				return ("Failed to place " + animal.getName() + 
						" in the habitat.");
			}

		} else {

			placed = habitat.placeAnimalAt(animal, row, col);
			if (!placed) {
				return ("Failed to place " + animal.getName() + 
						" at row " + row + ", col " + col + ".");
			}

		}

		return (animal.getName() + " has been added to the zoo.");
	}

	public String showAllAnimals() {
		String animalList = "Animals in the Zoo:\n";
		// Loop through the list of animals and add every one to the String
		for (Animal animal : animals) {
			animalList += animal;
			animalList += "\n";
		}
		return animalList;
	}

	public String showHabitat() {
		return "Zoo Habitat:\n" + habitat.displayGrid();
	}

	// The animals are sorted in order by the name + age.
	public void sortAnimals() {
		animals.sort(null);
	}

	public String findAnimalsByType(Class<?> type) {
		String typeStr = "\nFinding Animals of Type: " + type.getSimpleName();
		/* Loop through the list of animals and add the animal to the String
		 * if is the same type given
		 */
		for (Animal animal : animals) {
			if (type.isInstance(animal)) {
				typeStr += "\n" + animal;
			}
		}
		return typeStr;
	}

	public Snake[] findVenomousSnakes() {
		ArrayList<Snake> snakes = new ArrayList<>();

		/* Loop through the list of animals and only add animals that are both
		 * snakes and venomous
		 */
		for (Animal animal : animals) {
			if (animal instanceof Snake && ((Snake) animal).isVenomous()) {
				snakes.add((Snake) animal);
			}
		}

		Snake[] snakesArr = new Snake[snakes.size()];
		return snakes.toArray(snakesArr);
	}
}