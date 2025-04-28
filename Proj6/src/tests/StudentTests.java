package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import systemImp.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class StudentTests {
	// ChainedHashMap Tests
	
    // Tests putting and getting a key-value pair
    @Test
    public void testPutAndGet() {
        ChainedHashMap map = new ChainedHashMap();
        map.put(1234, "Alice");
        assertEquals("Alice", map.get(1234));
    }

    // Tests updating the value of an existing key
    @Test
    public void testUpdateValue() {
        ChainedHashMap map = new ChainedHashMap();
        map.put(4321, "Bob");
        map.put(4321, "UpdatedBob");
        assertEquals("UpdatedBob", map.get(4321));
    }

    // Tests checking if containsKey works for both existing and non-existing keys
    @Test
    public void testContainsKey() {
        ChainedHashMap map = new ChainedHashMap();
        map.put(3456, "Charlie");
        assertTrue(map.containsKey(3456));
        assertFalse(map.containsKey(7890));
    }

    // Tests getting all keys that map to a specific value
    @Test
    public void testGetValues() {
        ChainedHashMap map = new ChainedHashMap();
        map.put(1967, "Roblox");
        map.put(2567, "Fortnite");
        map.put(2568, "Fortnite");
        map.put(2013, "Pubg");
        
        assertEquals(2, map.getValues("Fortnite").size());
        assertTrue(map.getValues("Pubg").contains(2013));
        assertTrue(map.getValues("Roblox").contains(1967));
    }
    
    // Tests removing a key and checking it's no longer present
    @Test
    public void testRemove() {
        ChainedHashMap map = new ChainedHashMap();
        map.put(9999, "DeleteMe");
        assertTrue(map.containsKey(9999));
        map.remove(9999);
        
        assertTrue(!map.containsKey(9999));
        assertTrue(map.get(9999) == null);
    }
    
    // Tests adding a key that is outside the vaild range
    @Test
    public void testBadKey() {
        ChainedHashMap map = new ChainedHashMap();
        try {
        	map.put(10000, "This shouldn't work");
        	fail("Key must be between 1000-9999");
        } catch (IllegalArgumentException e) {
        	// Do nothing it worked
        }
        
        try {
        	map.put(999, "This shouldn't work");
        	fail("Key must be between 1000-9999");
        } catch (IllegalArgumentException e) {
        	// Do nothing it worked
        }
    }
    
    // MyHashSet tests
    
    // Tests adding and checking if an element exists
    @Test
    public void testAddAndContains() {
        MyHashSet set = new MyHashSet();
        set.add("apple");
        assertTrue(set.contains("apple"));
    }

    // Tests adding a duplicate does not increase size
    @Test
    public void testDuplicateAddDoesNotIncreaseSize() {
        MyHashSet set = new MyHashSet();
        set.add("bat");
        set.add("bat");
        assertEquals(1, set.size());
    }

    // Tests removing element and verify it's gone
    @Test
    public void testRemoveSet() {
        MyHashSet set = new MyHashSet();
        set.add("dog");
        set.remove("dog");
        assertFalse(set.contains("dog"));
        assertEquals(0, set.size());
    }

    // Tests checking if containsAll returns true for all present keys
    @Test
    public void testContainsAll() {
        MyHashSet set = new MyHashSet();
        set.add("cat");
        set.add("rat");
        set.add("bat");
        assertTrue(set.containsAll(new String[]{"cat", "rat", "bat"}));
    }

    // Tests setting a valid threshold and rehashing
    @Test
    public void testRehashOnThreshold() {
        MyHashSet set = new MyHashSet();
        set.setLoadFactorThreshold(0.45); // Force early rehash
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d"); // Should rehash now
        assertTrue(set.size() >= 4); // Ensure all elements are retained
        assertTrue(set.contains("a"));
        assertTrue(set.contains("d"));
    }
}