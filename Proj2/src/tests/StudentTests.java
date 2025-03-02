package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import systemImp.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class StudentTests {
	
	@Test
    public void testGet() {
        MyArrayList list = new MyArrayList(String.class);
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        assertEquals("Apple", list.get(0)); 
        assertEquals("Banana", list.get(1)); 
        assertEquals("Cherry", list.get(2));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(-1);
        });

        MyArrayList emptyList = new MyArrayList(String.class);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            emptyList.get(0);
        });
    }
	
    @Test
    public void testArrayDoubling() {
        MyArrayList list = new MyArrayList(String.class);
        
        int elementsToAdd = 15; // Exceeding initial capacity to test doubling
        
        // Add elements exceeding initial capacity
        for (int i = 0; i < elementsToAdd; i++) {
            list.add("Element" + i);
        }
        
        // Check if size is correctly updated
        assertEquals(elementsToAdd, list.size());
    }
    
    @Test
    public void testAddAll() {
        MyArrayList list = new MyArrayList(String.class);
        String[] elements = {"A", "B", "C", "D", "E", "F", "G"};
   
        list.addAll(elements);
        
       
        assertEquals(elements.length, list.size());
        
        // Check if elements were added correctly
        for (int i = 0; i < elements.length; i++) {
            assertEquals(elements[i], list.get(i));
        }
    }
    
    @Test
    public void testSublist() {
        MyArrayList list = new MyArrayList(String.class);
        String[] elements = {"A", "B", "C", "D", "E", "F", "G"};
        list.addAll(elements);
        
   
        MyArrayList sublist = list.sublist(2, 5);
        

        assertEquals(3, sublist.size());
        
        // Verify sublist elements
        assertEquals("C", sublist.get(0));
        assertEquals("D", sublist.get(1));
        assertEquals("E", sublist.get(2));
        
        // Out of bounds index should throw the correct exception
        assertThrows(IndexOutOfBoundsException.class, () -> {
        	MyArrayList badSublist = list.sublist(-1, 5);
        	badSublist.clear();
        });
    }
    
    @Test
    public void testTrimToSize() {
    	Person person1 = new Person("John", 1);
        Person person2 = new Person("Jane", 2);
        MyArrayList arrayList = new MyArrayList(Person.class);
        arrayList.add(person1);
        arrayList.add(person2);
        arrayList.trimToSize();
        
        // After trimming, the internal array size should match the number of elements
        assertEquals(2, arrayList.size());
    }
    
    @Test
    public void testContains() {
        Student student1 = new Student("John", 1, 2025, 3.7);
        Student student2 = new Student("Jane", 2, 2021, 2.3);
        Student student3 = new Student("David", 3, 1977, 3.99);
        

        MyArrayList arrayList = new MyArrayList(Student.class);
        arrayList.add(student1);
        arrayList.add(student2);

        assertTrue(arrayList.contains(student1));
        assertTrue(arrayList.contains(student2));

        Person person = new Person("Jane", 2);
        
        // This shouldn't work since person wouldn't exactly match student fields
        assertFalse(arrayList.contains(person));
        
        MyArrayList arrayList2 = new MyArrayList(Person.class);
        
        Person person1 = new Person("David", 3);
        Person person2 = new Person("Diane", 4);
        arrayList2.add(person1);
        arrayList2.add(person2);
        
        // This should work since student would exactly match person fields
        assertTrue(arrayList2.contains(student3));
        
    }
    
    @Test
    public void testPush() {

        MyStack personStack = new MyStack(Person.class);
        Person person1 = new Person("John", 1);

        personStack.push(person1);
        assertFalse(personStack.isEmpty());
        assertEquals(person1, personStack.peek());


        MyStack studentStack = new MyStack(Student.class);
        Student student1 = new Student("Alice", 3, 2020, 3.5);

        studentStack.push(student1);
        assertFalse(studentStack.isEmpty());
        assertEquals(student1, studentStack.peek());
        
        assertThrows(IllegalArgumentException.class, () -> {
        	studentStack.push(1);
        });
    }


    @Test
    public void testPop() {

        MyStack personStack = new MyStack(Person.class);
        Person person1 = new Person("John", 1);
        Person person2 = new Person("Jane", 2);

        personStack.push(person1);
        personStack.push(person2);

        assertEquals(person2, personStack.pop());
        assertEquals(person1, personStack.pop());


        MyStack studentStack = new MyStack(Student.class);
        Student student1 = new Student("Alice", 3, 2020, 3.5);
        Student student2 = new Student("Bob", 4, 2021, 3.8);

        studentStack.push(student1);
        studentStack.push(student2);

        assertEquals(student2, studentStack.pop());
        assertEquals(student1, studentStack.pop());
    }


    @Test
    public void testPeek() {

        MyStack personStack = new MyStack(Person.class);
        Person person1 = new Person("John", 1);
        Person person2 = new Person("Jane", 2);

        personStack.push(person1);
        personStack.push(person2);

        assertEquals(person2, personStack.peek());


        MyStack studentStack = new MyStack(Student.class);
        Student student1 = new Student("Alice", 3, 2020, 3.5);
        Student student2 = new Student("Bob", 4, 2021, 3.8);

        studentStack.push(student1);
        studentStack.push(student2);

        assertEquals(student2, studentStack.peek());
    }


    @Test
    public void testSize() {

        MyStack personStack = new MyStack(Person.class);
        assertEquals(0, personStack.size());

        Person person1 = new Person("John", 1);
        personStack.push(person1);
        assertEquals(1, personStack.size());

        MyStack studentStack = new MyStack(Student.class);
        assertEquals(0, studentStack.size());

        Student student1 = new Student("Alice", 3, 2020, 3.5);
        studentStack.push(student1);
        assertEquals(1, studentStack.size());
    }

    @Test
    public void testIsEmpty() {
        // Testing for Person
        MyStack personStack = new MyStack(Person.class);
        assertTrue(personStack.isEmpty());

        Person person1 = new Person("John", 1);
        personStack.push(person1);
        assertFalse(personStack.isEmpty());

        // Testing for Student
        MyStack studentStack = new MyStack(Student.class);
        assertTrue(studentStack.isEmpty());

        Student student1 = new Student("Alice", 3, 2020, 3.5);
        studentStack.push(student1);
        assertFalse(studentStack.isEmpty());
    }

    @Test
    public void testClear() {
        MyStack personStack = new MyStack(Person.class);
        Person person1 = new Person("John", 1);
        personStack.push(person1);
        assertFalse(personStack.isEmpty());

        personStack.clear();
        assertTrue(personStack.isEmpty());

        MyStack studentStack = new MyStack(Student.class);
        Student student1 = new Student("Alice", 3, 2020, 3.5);
        studentStack.push(student1);
        assertFalse(studentStack.isEmpty());

        studentStack.clear();
        assertTrue(studentStack.isEmpty());
    }
}