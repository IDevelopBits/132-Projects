package zoo;

public class Driver {

	public static void main(String[] args) {
	
		        // Create habitat types
		        String[] habitatTypes = {"Savannah", "Forest", "Desert"};

		        // Initialize the zoo with a 3x3 habitat grid
		        Zoo zoo = new Zoo(3, habitatTypes);

		        // Create animals
		        Animal lion = new Lion("Leo", 5, true); // Alpha lion
		        Animal snake = new Snake("Slither", 3, true); // Venomous snake
		        Animal rattlesnake = new RattleSnake("Rattle", 2, 10); // Rattlesnake with 10 rattle segments
		        Animal eagle = new Eagle("Skyler", 4, 2.5); // Eagle with 2.5m wingspan

		        // Add animals to the zoo
		        System.out.println(zoo.addAnimal(lion, null, null)); // Place lion in the first compatible habitat
		        System.out.println(zoo.addAnimal(snake, 1, 0)); // Place snake in the second row, first column
		        System.out.println(zoo.addAnimal(rattlesnake, 2, 1)); // Place rattlesnake in the third row, second column
		        System.out.println(zoo.addAnimal(eagle, 0, 2)); // Place eagle in the first row, third column.  Will not work

		        // Show all animals in the zoo
		        System.out.println(zoo.showAllAnimals());

		        // Show the habitat grid
		        System.out.println(zoo.showHabitat());

		        // Sort the animals by name and age
		        zoo.sortAnimals();
		        System.out.println("Animals sorted by name and age:\n" + zoo.showAllAnimals());

		        // Find animals of type Snake
		        System.out.println(zoo.findAnimalsByType(Snake.class));

		        // Find venomous snakes
		        Snake[] venomousSnakes = zoo.findVenomousSnakes();
		        System.out.println("\nVenomous Snakes:");
		        for (Snake venomousSnake : venomousSnakes) {
		            System.out.println(venomousSnake);
		        }
		         
		        System.out.println("\n\n-------New Zoo-----------\n"); 

		        // Create another zoo with different habitat types
		        String[] newHabitatTypes = {"Savannah", "Mountain", "Jungle"};
		        Zoo newZoo = new Zoo(3, newHabitatTypes);

		        // Create more animals
		        Animal lion2 = new Lion("Simba", 7, false); // Non-alpha lion
		        Animal eagle2 = new Eagle("Hawk", 3, 3.0); // Eagle with 3.0m wingspan

		        // Add more animals to the new zoo
		        System.out.println(newZoo.addAnimal(lion2, 0, 0)); // Place lion in first row, first column
		        System.out.println(newZoo.addAnimal(eagle2, 1, 1)); // Place eagle in second row, second column

		        // Show all animals in the new zoo
		        System.out.println(newZoo.showAllAnimals());

		        // Show the new zoo's habitat grid
		        System.out.println(newZoo.showHabitat());

		        // Try to add a non-compatible animal (this should fail)
		        Animal incompatibleAnimal = new Snake("Cobra", 1, true); // Snake incompatible with the mountain habitat
		        System.out.println(newZoo.addAnimal(incompatibleAnimal, 1, 2)); // Try to place snake in second row, third column

		        // Demonstrating sorting, finding specific types of animals, and handling the habitat grid
		        newZoo.sortAnimals();
		        System.out.println("Animals sorted by name and age in new zoo:\n" + newZoo.showAllAnimals());
		    }
	


	}


