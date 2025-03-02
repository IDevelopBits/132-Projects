package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import zoo.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class StudentTests {
	
	@Test
	public void testPlaceAt() {
		String[] habitatTypes = {"Forest"};
		
        Zoo zoo = new Zoo(3, habitatTypes);
        Animal lion = new Lion("Leo", 5, true);
        String result = zoo.addAnimal(lion, null, null); // This returns a success message
	    System.out.println(result);
	}
}



