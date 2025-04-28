  package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import systemImp.*;

import java.util.Arrays;
import java.util.Comparator;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class StudentTests {
    @Test
    public void testBidirectionalLinearSearchEdgeCases() {
        String[] words = {"zebra", "elephant", "lion", "giraffe", "tiger", "bear"};
        
        StringBuilder log = new StringBuilder();
        
        // Search for first element
        assertEquals(0, SearchAndSortUtil.bidirectionalLinearSearch(words, 
        		"zebra", 0, words.length - 1, log));
        
        // Search for last element
        assertEquals(5, SearchAndSortUtil.bidirectionalLinearSearch(words, 
        		"bear", 0, words.length - 1, log));
        
        // Search for non-existing element
        assertEquals(-1, SearchAndSortUtil.bidirectionalLinearSearch(words, 
        		"wolf", 0, words.length - 1, log));
    }
    
    @Test
    public void testBidirectionalBubbleSortNegativeNumbers() {
        Integer[] numbers = {-5, -1, -10, -3, -7};
        
        SearchAndSortUtil.bidirectionalBubbleSort(numbers);
        
        Integer[] expected = {-10, -7, -5, -3, -1};
        assertArrayEquals(expected, numbers);
    }
    
    
    @Test
    public void testRecursiveBidirectionalSelectionSortDescending() {
        Integer[] arr = {100, 80, 60, 40, 20, 0};
        StringBuilder log = new StringBuilder();
        
        SearchAndSortUtil.recursiveBidirectionalSelectionSort(arr, 0, 
        		arr.length - 1, log);
        
        Integer[] expected = {0, 20, 40, 60, 80, 100};
        assertArrayEquals(expected, arr);
    }
    

    @Test
    public void testPriorityListRemoveAllElements() {
        PriorityList<String> list = new PriorityList<>(5, false, null);
        
        list.add("Gamma");
        list.add("Alpha");
        list.add("Beta");
        
        assertEquals(3, list.size());
        
        // Remove elements one by one
        assertTrue(list.remove("Alpha"));
        assertTrue(list.remove("Beta"));
        assertTrue(list.remove("Gamma"));
        
        assertTrue(list.isEmpty());
    }
    
    @Test
    public void testPriorityListAddAndGet() {
        PriorityList<Double> list = new PriorityList<>(10, false, null);
        
        list.add(3.14);
        list.add(1.41);
        list.add(2.71);
        list.add(0.577);
        
        assertEquals(4, list.size());
        assertEquals((Double) 0.577, list.get(0));
        assertEquals((Double) 3.14, list.get(3));
    }
    
    @Test
    public void testMergePriorityListsWithRandomNumbers() {
        PriorityList<Integer> list1 = new PriorityList<>(5, false, null);
        PriorityList<Integer> list2 = new PriorityList<>(5, false, null);
        
        list1.add(45);
        list1.add(3);
        list1.add(0);
        list1.add(-5);
        
        list2.add(13);
        list2.add(1);
        list2.add(5);

        PriorityList<Integer> merged = PriorityListUtils.mergePriorityLists(list1, list2);
        
        Integer[] expected = {-5, 0, 1, 3, 5, 13, 45};
        // Loop and check that merged is exactly as expected
        for (int i = 0; i < expected.length; i++) {
        	assertEquals(expected[i], merged.get(i));
        }
    }
    
    @Test
    public void testPriorityListWithPersonsAndStudents() {
        PriorityList<Person> list = new PriorityList<>(5, false, null);

        Person p1 = new Person("Emma", 4);
        Student s1 = new Student("Noah", 2, 3.9);
        Student s2 = new Student("Olivia", 1, 3.7);
        Person p2 = new Person("Liam", 3);
        
        list.add(p1);
        list.add(s1);
        list.add(s2);
        list.add(p2);
        
       /* Index 0 should be Olivia since she has id 1 and index 3 should be 
        * Emma since she has id 4
        */
        assertEquals("Olivia", list.get(0).getName());
        assertEquals("Emma", list.get(3).getName());
    }
    
    @Test
    public void testRemoveStudentFromPriorityList() {
        PriorityList<Person> list = new PriorityList<>(4, false, null);

        Student s1 = new Student("Sophia", 3, 3.8);
        Student s2 = new Student("Mason", 1, 3.5);
        Person p1 = new Person("Ava", 2);
        
        list.add(s1);
        list.add(s2);
        list.add(p1);
        
        assertEquals(3, list.size());
        
        // Remove Mason (id=1)
        assertTrue(list.remove(s2));
        assertEquals(2, list.size());
        assertEquals("Ava", list.get(0).getName());
    }
}