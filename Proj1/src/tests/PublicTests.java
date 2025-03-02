package tests;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import zoo.*;




@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class PublicTests {
	
	/*---------Lion Tests---------------*/
	
	
    // Test the toString method of Lion
    @Test
    public void testLionToString() {
        Lion lion = new Lion("Leo", 5, true);
        String result =  lion.toString();
        assertTrue(TestsSupport.isCorrect("pubTest01.txt", result));

    }

	 
  
    
    /*---------Snake Tests---------------*/

    
    // Test the toString method of Snake
    @Test
    public void testSnakeToString() {
        Snake snake = new Snake("Slither", 3, true);   
        String result =  snake.toString();
        assertTrue(TestsSupport.isCorrect("pubTest02.txt", result));
     
    }


    
    

  /* ------------Habitat Methods--------------------------------------- */


    
  @Test
  /**
   * Tests the displayGrid method.
   * Verifies:
   * - The grid is correctly represented as a string.
   */
  public void testDisplayGrid() {
      String[] habitatTypes = {"Savannah", "Forest", "Mountain"};
      Habitat habitat = new Habitat(3, habitatTypes);

      Animal lion = new Lion("Simba", 5, true);
      Animal eagle = new Eagle("EagleOne", 7, 2.5);
      Animal snake = new Snake("Slither", 3, true);
      habitat.placeAnimal(lion);
      habitat.placeAnimal(eagle);
      habitat.placeAnimalAt(snake,1,1);
      String result = habitat.displayGrid();
      System.out.println(result);
      
      assertTrue(TestsSupport.isCorrect("pubTest03.txt", result));	
  
     }

    
  /* ------------Zoo Methods--------------------------------------- */
  
    
    @Test
    public void testPlaceCompatibleAnimalInHabitat() {
        String[] habitatTypes = {"Savannah", "Forest", "Desert"};
        Zoo zoo = new Zoo(1, habitatTypes);
        Animal lion = new Lion("Leo", 5, true);
        String result = zoo.addAnimal(lion, null, null); // This returns a success message
        assertTrue(TestsSupport.isCorrect("pubTest04.txt", result));	 // Check if the correct success message is returned
    }
    
    
    @Test
    public void testPlaceIncompatibleAnimalInHabitat() {
        String[] habitatTypes = {"Mountain", "Desert", "Forest"};
        Zoo zoo = new Zoo(3, habitatTypes);
        Animal lion = new Lion("Leo", 5, true);
        String result = zoo.addAnimal(lion, 0, 0); // This returns a fail message
        assertTrue(TestsSupport.isCorrect("pubTest05.txt", result)); //  Lion is not compatible with Mountain
    }
    
    
    @Test
    /**
     * Tests the addAnimal method.
     * Verifies:
     * - Animals are added to the zoo and placed in compatible habitat spots.
     * - Adding an animal to specific positions respects compatibility and uniqueness.
     */
    public void testAddAnimal() {
        String[] habitatTypes = {"Savannah", "Forest", "Mountain"};
        Zoo zoo = new Zoo(3, habitatTypes);

        Animal lion = new Lion("Simba", 5, true);
        Animal snake = new Snake("Slither", 3, true);
        Animal eagle = new Eagle("EagleOne", 7, 2.5);
        
        String result = zoo.addAnimal(lion, null, null)+"\n";
        result+=zoo.addAnimal(snake, null, null)+"\n";
        result+=zoo.addAnimal(eagle, 2, 0)+"\n";
        result+=zoo.addAnimal(lion, 0, 0);
        assertTrue(TestsSupport.isCorrect("pubTest06.txt", result)); 
        
    }
    
    
    @Test
    /**
     * Tests the showAllAnimals method.
     * Verifies:
     * - All animals are correctly displayed.
     */
    public void testShowAllAnimals() {
        String[] habitatTypes = {"Savannah", "Forest", "Mountain"};
        Zoo zoo = new Zoo(3, habitatTypes);

        Animal lion = new Lion("Simba", 5, true);
        Animal eagle = new Eagle("EagleOne", 7, 2.5);
        Snake snake = new Snake("Slither", 3, true);

        String result =zoo.addAnimal(lion, null, null)+"\n";
        result+=zoo.addAnimal(eagle, null, null)+"\n";
        result+= zoo.addAnimal(snake, 0, 1)+"\n";
        result+= zoo.addAnimal(snake, 2, 1)+"\n";
        result+=zoo.addAnimal(snake, 1, 2)+"\n";
        assertTrue(TestsSupport.isCorrect("pubTest07.txt", result)); 
    }
    
    
    
    @Test
    /**
     * Tests the showHabitat method.
     * Verifies:
     * - The habitat grid is correctly displayed.
     */
    public void testShowHabitat() {
        String[] habitatTypes = {"Savannah", "Forest", "Mountain"};
        Zoo zoo = new Zoo(3, habitatTypes);

        Animal lion = new Lion("Simba", 5, true);
        Animal eagle = new Eagle("EagleOne", 7, 2.5);
        Snake snake = new Snake("Slither", 3, true);

        zoo.addAnimal(lion, null, null);
        zoo.addAnimal(eagle, null, null);
       	zoo.addAnimal(snake, 0, 1);
        zoo.addAnimal(snake, 2, 1);
        zoo.addAnimal(snake, 1, 2);
        
        String result = zoo.showHabitat();
        assertTrue(TestsSupport.isCorrect("pubTest08.txt", result)); 
      
    }

    
    
    @Test
    public void testSortAnimals() {
        Zoo zoo = new Zoo(3, new String[]{"Savannah", "Desert", "Forest"});
        Animal lion1 = new Lion("Leo", 5, true);
        Animal lion2 = new Lion("Simba", 4, false);
        Animal snake1 = new Snake("Jake", 5, true);
        Animal snake2 = new Snake("Hiss", 4, false);
        Animal eagle = new Eagle("Simba", 5,2.5);
        Animal snake3 = new Snake("Simba", 3, true);
        Animal snake4 = new Snake("Simon", 3, true);
        zoo.addAnimal(lion1, null, null);
        zoo.addAnimal(lion2, null, null);
        zoo.addAnimal(snake1, null, null);
        zoo.addAnimal(snake2, null, null);
        zoo.addAnimal(eagle, null, null);
        zoo.addAnimal(snake3, null, null);
        zoo.addAnimal(snake4, null, null);
        zoo.sortAnimals();
        String result =  zoo.showAllAnimals();
        assertTrue(TestsSupport.isCorrect("pubTest09.txt", result));
    }
    
    
    @Test
    /**
     * Tests the findAnimalsByType method.
     * Verifies:
     * - Only animals of the specified type are returned.
     */
    public void testFindAnimalsByType() {
        String[] habitatTypes = {"Savannah", "Forest", "Mountain"};
        Zoo zoo = new Zoo(3, habitatTypes);

        Animal lion = new Lion("Simba", 5, true);
        Animal snake = new Snake("Slither", 3, true);
        Animal eagle = new Eagle("EagleOne", 7, 2.5);
        Animal snake1 = new RattleSnake("Slither", 3, 10);

        zoo.addAnimal(lion, null, null);
        zoo.addAnimal(snake, null, null);
        zoo.addAnimal(eagle, null, null);
        zoo.addAnimal(snake1, null, null);

        String result = zoo.findAnimalsByType(Snake.class);
        assertTrue(TestsSupport.isCorrect("pubTest10.txt", result)); 
    }
    
    
   
    
    
    
    
    
    
    
   
	
}