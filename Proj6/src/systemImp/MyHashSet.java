package systemImp;

/**
 * A simple implementation of a HashSet using open addressing with linear probing.<br>
 * This HashSet works specifically with Strings.<br>
 * 
 * It enforces the following constraints:<br>
 * - Strings must not be null, no longer than 5 characters, and only use a to z, otherwise an exception is thrown.<br>
 * - A custom hash code function uses hash codes based on the prime number 19.<br>
 * - The initial size of the table is 2, and it dynamically resizes to the next prime when necessary.<br>
 * 
 * <p>
 * The set uses open addressing and linear probing for collision resolution.<br>
 * When the load factor is >= a customized threshold (default is 0.75), the table is rehashed to a larger prime size.</p>
 * 
 * <p>
 * This class ensures that the set operates efficiently and does not allow duplicate entries.
 * </p>
 */
public class MyHashSet {

    private static final String DELETED = "DELETED";  // Sentinel for deleted entries
    private static final String EMPTY = null;         // Sentinel for empty entries
    
    // Array of primes for resizing the hash table
    // These primes are chosen to ensure that the table size grows by a "double prime" strategy.
    private static final int[] PRIMES = {2, 5, 11, 23, 47, 97, 197, 397, 797, 1597};
    
    private String[] table;      // The actual table where the strings are stored
    private int size;            // The current number of elements in the set
    private int primeIndex;      // Index of the current prime number used for table size
    private double loadFactorThreshold;  // The threshold at which to rehash the table
    
    public MyHashSet() {
    	primeIndex = 0;
    	loadFactorThreshold = 0.75;
    	table = new String[PRIMES[primeIndex]];
    }
    
    public void add(String key) {
        if (!isValidKey(key)) {
            throw new IllegalArgumentException("Key must be non-null and at most 5 characters long.");
        }
        
        // Key is already in set so looping is unnecessary
        if (contains(key)) {
        	return;
        }
        
        int index = hash(key);
        int firstDeletedIndex = -1;
        // Find the first open index (null or "DELETED")
        while (table[index] != EMPTY) {
        	 // Save the first deleted slot
            if (table[index].equals(DELETED) && firstDeletedIndex == -1) {
                firstDeletedIndex = index;
            }
        	// Use modulo to loop around the table once we get to the end
            index = (index + 1) % table.length;
            
        }
        
        // Place it into the table
        if (firstDeletedIndex != -1) {
        	index = firstDeletedIndex;
        }
        table[index] = key;
        size++;
        
    	// Rehash if loadFactor is greater than loadFactorThreshold
    	if (getLoadFactor() >= loadFactorThreshold) {
    		rehash();
    	}
    }
    
    private void rehash() {
        primeIndex++;  // Move to the next prime size
        if (primeIndex >= PRIMES.length) {
            throw new IllegalStateException("Maximum size reached");
        }

        int primeSize = PRIMES[primeIndex];
        String[] newTable = new String[primeSize];

        for (String str : table) {
            if (isValidKey(str)) {
            	// Create a new index with the new table size
                int index = myHashCode(str) % primeSize;

                // Linear probing to find next open index
                while (newTable[index] != EMPTY) {
                    index = (index + 1) % primeSize;
                }

                newTable[index] = str;
            }
        }

        // Set table to reference the new table
        table = newTable;
    }

    public void remove(String key) {
        if (!isValidKey(key)) {
            throw new IllegalArgumentException("Key must be non-null and at most 5 characters long.");
        }
        
        // No key is found in the set so looping is unnecessary
        if (!contains(key)) {
        	return;
        }

        int index = hash(key);
        while (table[index] != EMPTY) {
        	// When the key is found, set the element at index to "DELETED"
            if (key.equals(table[index])) {
                table[index] = DELETED;
                size--;
                return;
            }
            
            index = (index + 1) % table.length;
        }
    }
    
    public int myHashCode(String key) {
        if (!isValidKey(key)) {
            throw new IllegalArgumentException("Key must be non-null and at most 5 characters long.");
        }

        int hash = 0;
        int primeMultiplier = 19;

        // Hash based on the characters of the key
        for (int i = 0; i < key.length(); i++) {
            hash = hash * primeMultiplier + key.charAt(i);
        }

        return hash;
    }
    
    public boolean contains(String key) {
        if (!isValidKey(key)) {
            throw new IllegalArgumentException("Key must be non-null and at most 5 characters long.");
        }
        
    	for (String str : table) {
    		// Return true if the key is found in the table
    		if (key.equals(str)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean containsAll(String[] keys) {
    	for (String key : keys) {
    		// Return false if one key in the array is not found
    		 if (!contains(key)) {
    			 return false;
    		 }
    	}
    	return true;
    }
    
    private int hash(String key) {
    	return myHashCode(key) % table.length;
    }

    
    public void setLoadFactorThreshold(double threshold) {
    	double[] predefinedThresholds = {0.45, 0.60, 0.75, 0.85};
    	// Loop through the predefined values to fine a match
    	for (int i = 0; i < predefinedThresholds.length; i++) {
    		if (predefinedThresholds[i] == threshold) {
    			loadFactorThreshold = threshold;
    			return;
    		}
    	}
    	// This should only throw if nothing is found
    	throw new IllegalArgumentException("Invalid threshold value");
    }
    
    public double getLoadFactor() {
    	return size / (double) table.length;
    }
    
    private boolean isValidKey(String key) {
    	return key != null && isStringLowerCaseLetters(key) && key.length() <= 5;
    }
    
    private boolean isStringLowerCaseLetters(String key) {
    	// Loop through key and check for uppercase and non-letter characters
    	for (char letter : key.toCharArray()) {
    		if (Character.isUpperCase(letter) || !Character.isLetter(letter)) {
    			return false;
    		}
    	}
    	return true;
    }
	
	public int size() {
		return size;
	}
	
	@Override
	public String toString() {
	    StringBuilder set = new StringBuilder();
	    for (int i = 0; i < table.length; i++) {
	        set.append("Index ").append(i).append(": ");
	        
	        if (table[i] == EMPTY) {
	            set.append("null");
	        } else if (table[i] == DELETED) {
	            set.append("DELETED");
	        } else {
	            set.append(table[i]);
	        }
	        
	        set.append("\n");
	    }
	    return set.toString();
	}

    
}

