package systemImp;

import java.util.LinkedList; 
import java.util.ArrayList; //ONLY use for return value of getValues method
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A HashMap implementation using separate chaining with LinkedLists.<br>
 * 
 * - Stores Integer keys and String values. <br>
 * - Uses a hash function with compression: ((A * key + B) % P) % table.length<br>
 * - Rehashes when the average chain length exceeds a threshold.<br>
 * - Does not allow duplicate keys (updates value instead), and keys must be 4-digit integers from 1000 (inclusive) to 9999 (inclusive).<br>
 * - Does not allows null values.<br>
 * - Uses an initial size of 2 to encourage collisions.<br>
 * - Since keys are 4-digit numbers,  integer overflow is not a concern.<br>
 */
public class ChainedHashMap implements Iterable<ChainedHashMap.Entry> {
    
    /** A large prime number for hashing */
    private static final int P = 104729;
    /** Large prime multiplier for hashing */
    private static final int A = 2347;
    /** Large prime offset for hashing */
    private static final int B = 7919;

    /** Initial size of the hash table */
    private static final int INITIAL_SIZE = 2;
    /** Threshold for rehashing: when avg chain length exceeds this, we rehash */
    private static final int RESIZE_THRESHOLD = 3;
    /** Prime sizes for resizing */
    private static final int[] PRIMES = {2, 5, 11, 23, 47, 97, 197, 397};

    /** The hash table, where each index contains a linked list of entries */
    private LinkedList<Entry>[] table;
    /** The number of key-value pairs stored */
    private int size;
    /** The index of the current prime in PRIMES */
    private int primeIndex;

    /**
     * Entry class for storing key-value pairs in the linked list.<br>
     * Do NOT MAKE ANY CHANGES to  public static class Entry
     */
    public static class Entry {
        int key;
        String value;
        
        Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
        public int getKey() {  //used for testing only.  NOT needed in code you write
            return key;
        }
    }
    
    @SuppressWarnings("unchecked")
	public ChainedHashMap() {
    	size = 0;
    	primeIndex = 0;
    	table = (LinkedList<Entry>[]) new LinkedList<?>[INITIAL_SIZE];
    }
    
    public String get(int key) {
    	if (!isValidKey(key)) {
    		throw new IllegalArgumentException("Key must be between 1000-9999");
    	}
    	
    	for (int i = 0; i < table.length; i++) {
    		if (table[i] != null) {
	    		for (Entry entry : table[i]) {
	    			if (entry.key == key) {
	    				return entry.value;
	    			}
	    		}
    		}
    	}
    	return null;
    }
    
    public boolean containsKey(int key) {
    	if (!isValidKey(key)) {
    		throw new IllegalArgumentException("Key must be between 1000-9999");
    	}
    	
    	// Loop through the array of LinkedList
    	for (int i = 0; i < table.length; i++) {
    		// Loop through the linked list at index i and find the key
    		if (table[i] != null) {
	    		for (Entry entry : table[i]) {
	    			if (entry.key == key) {
	    				return true;
	    			}
	    		}
    		}
    	}
    	return false;
    }
    
    public int getSize() {
    	return size;
    }
    
    public int getTableLength() {
    	return table.length;
    }
    
    public ArrayList<Integer> getValues(String target) {
    	if (target == null) {
    		throw new IllegalArgumentException("Target value cannot be null");
    	}
    	
    	ArrayList<Integer> keys = new ArrayList<>();
    	// Loop through the array of LinkedList
    	for (int i = 0; i < table.length; i++) {
    		// Loop through the linked list at the bucket (i) and find the value
    		if (table[i] != null) {
	    		for (Entry entry : table[i]) {
	    			if (entry.value == target) {
	    				keys.add(entry.key);
	    			}
	    		}
    		}
    	}
    	return keys;
    }
    
    private int hash(int key) {
    	return ((A * key + B) % P) % table.length;
    }
    
    private void rehash() {
    	// Move to the next prime
        primeIndex++;
        if (primeIndex >= PRIMES.length) {
            return;
        }

        // Get the next prime size for the table
        int primeSize = PRIMES[primeIndex];
        
        // Create a new table with the new prime size
        @SuppressWarnings("unchecked")
		LinkedList<Entry>[] newTable = (LinkedList<Entry>[]) new LinkedList<?>[primeSize];
        
        // Initialize the new table's buckets
        for (int i = 0; i < newTable.length; i++) {
            newTable[i] = new LinkedList<>();
        }

        // Rehash all entries in the current table and insert into the new table
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (Entry entry : table[i]) {
                    // Apply the hash formula with the new table size
                    int newIndex = ((A * entry.key + B) % P) % primeSize;
                    newTable[newIndex].add(entry);
                }
            }
        }

        // Now the table reference points to the new table
        table = newTable;
    }
 
    private boolean isValidKey(int key) {
    	return key >= 1000 && key <= 9999;
    }
       
    public void put(int key, String value) {
    	if (!isValidKey(key)) {
    		throw new IllegalArgumentException("Key must be between 1000-9999");
    	} else if (value == null) {
    		throw new IllegalArgumentException("Value cannot be null");
    	}
    	
    	int index = hash(key);
    	if (table[index] == null) {
    		table[index] = new LinkedList<Entry>();
    	}
    		
    	for (Entry entry : table[index]) {
			if (entry.key == key) {
				entry.value = value;
				/* Return statement so the code below doesn't get executed
				 * if there id already a key in the table
				 */
				return;
			}
    	} 
    	
    	// Insert a new key value pair into the HashTable at index i
    	Entry entry = new Entry(key, value);
    	table[index].add(entry);
    	size++;
    	
    	// Rehash if greater than the set resize threshold
    	if ((double) size / table.length > RESIZE_THRESHOLD) {
    		rehash();
    	}
    }
    
    public void remove(int key) {
    	if (!isValidKey(key)) {
    		throw new IllegalArgumentException("Key must be between 1000-9999");
    	}
    	
    	int index = hash(key);
    	// Loop through the bucket and remove the matching key
    	for (int i = 0; i < table[index].size(); i++) {
    		Entry entry = table[index].get(i);
    		if (entry.key == key) {
    			table[index].remove(i);
    			size--;
    		}
    	}
    }
    
    @Override
    public Iterator<ChainedHashMap.Entry> iterator() {
        return new Iterator<ChainedHashMap.Entry>() {
            private int bucketIndex = 0;
            private Iterator<ChainedHashMap.Entry> bucketIterator = getNextBucketIterator();

            // Helper method to advance to the next non-empty bucket
            private Iterator<ChainedHashMap.Entry> getNextBucketIterator() {
                while (bucketIndex < table.length) {
                    LinkedList<ChainedHashMap.Entry> bucket = table[bucketIndex++];
                    if (bucket != null && !bucket.isEmpty()) {
                        return bucket.iterator();
                    }
                }
                return null;
            }

            @Override
            public boolean hasNext() {
                while (bucketIterator == null || !bucketIterator.hasNext()) {
                    bucketIterator = getNextBucketIterator();
                    if (bucketIterator == null) {
                        return false;
                    }
                }
                return true;
            }

            @Override
            public ChainedHashMap.Entry next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No elements left");
                }
                return bucketIterator.next();
            }
        };
    }
    
    @Override
    public String toString() {
        StringBuilder map = new StringBuilder();
        
        for (int i = 0; i < table.length; i++) {
            LinkedList<Entry> bucket = table[i];
            map.append(i).append(" -> ");
            
            if (bucket != null && !bucket.isEmpty()) {
                // Print each entry in the bucket
                Iterator<Entry> iterator = bucket.iterator();
                
                while (iterator.hasNext()) {
                    Entry entry = iterator.next();
                    map.append("(").append(entry.key).append(", ")
                    .append(entry.value).append(")");
                    if (iterator.hasNext()) {
                        map.append(" ");
                    }
                }
            }
            
            // New line after each bucket
            map.append("\n");
        }
        
        return map.toString();
    }


}
