package systemImp;
/**
 * Utility class providing search and sorting algorithms using generic types.
 * <p>
 * This class includes:
 * - Recursive Bidirectional Linear Sort
 * - Non-Recursive Bidirectional Bubble Sort
 * - Recursive Bidirectional Selection Sort
 * </p>
 *
 * 
 */
public class SearchAndSortUtil {
	public static <T extends Comparable<T>> String bidirectionalBubbleSort(T[] arr) {
	    StringBuilder log = new StringBuilder();
	    boolean swapped;
	    int left = 0, right = arr.length - 1;

	    while (left < right) {
	        swapped = false;

	        // Left-to-right pass
	        for (int i = left; i < right; i++) {
	            if (arr[i].compareTo(arr[i + 1]) > 0) {
	                // Swap elements
	                T temp = arr[i];
	                arr[i] = arr[i + 1];
	                arr[i + 1] = temp;

	                // Log swap
	                log.append("Swapped ").append(arr[i]).append(" and ")
	                .append(arr[i + 1]).append(": ").append(arrayToString(arr))
	                .append("\n");
	                
	                swapped = true;
	            }
	        }
	        right--; // Reduce range for next pass

	        // Stop early if no swaps occurred
	        if (!swapped) {
	        	break;
	        }

	        swapped = false;

	        // Right-to-left pass 
	        for (int i = right; i > left; i--) {
	            if (arr[i - 1].compareTo(arr[i]) > 0) {
	                // Swap elements
	                T temp = arr[i - 1];
	                arr[i - 1] = arr[i];
	                arr[i] = temp;

	                // Log swap
	                log.append("Swapped ").append(arr[i]).append(" and ")
	                .append(arr[i - 1]).append(": ").append(arrayToString(arr))
	                .append("\n");
	                swapped = true;
	            }
	        }
	        left++; // Reduce range for next pass
	    }

	    return log.toString();
	}
	
	
	public static <T extends Comparable<T>> int bidirectionalLinearSearch(T[] arr, 
	        T key, int left, int right, StringBuilder log) {

	    // if left > right,  means no element is found
	    if (left > right) {
	        return -1;
	    }

	    // Log and check the left index
	    log.append("Checking index (left): ").append(left).append(", value: ")
	    .append(arr[left]).append("\n");
	    if (key.compareTo(arr[left]) == 0) {
	        return left; // Found the key at the left index
	    }

	    // Log and check the right index if it's not the same as left
	    if (left != right) {
	        log.append("Checking index (right): ").append(right).append(", value: ")
	        .append(arr[right]).append("\n");
	        if (key.compareTo(arr[right]) == 0) {
	            return right; // Found the key at the right index
	        }
	    }

	    // Recursively check the next pair of indices
	    return bidirectionalLinearSearch(arr, key, left + 1, right - 1, log);
	}




	
	public static <T extends Comparable<T>> void recursiveBidirectionalSelectionSort(T[] arr, 
	        int left, int right, StringBuilder log) {
	    if (left >= right) {
	        return;
	    }

	    // Grab the indices with the highest and lowest values
	    int[] minMax = findMinMax(arr, left, right, left, right);
	    int minIndex = minMax[0];
	    int maxIndex = minMax[1];

	    if (minIndex != left) {
	        log.append("Swapped ").append(arr[left]).append(" and ")
	        .append(arr[minIndex]).append(": ");
	        
	        // Swap the smallest element with the leftmost element
	        T temp = arr[left];
	        arr[left] = arr[minIndex];
	        arr[minIndex] = temp;
	        log.append(arrayToString(arr)).append("\n");
	    }

	    // If maxIndex is pointing to the leftmost element, update it
	    if (maxIndex == left) {
	        maxIndex = minIndex;
	    }

	    if (maxIndex != right) {
	        log.append("Swapped ").append(arr[right]).append(" and ")
	        .append(arr[maxIndex]).append(": ");
	        
	        // Swap the largest element with the rightmost element if different
	        T temp = arr[right];
	        arr[right] = arr[maxIndex];
	        arr[maxIndex] = temp;
	        log.append(arrayToString(arr)).append("\n");
	    }

	    // Recursively call the function for the next pair of elements
	    recursiveBidirectionalSelectionSort(arr, left + 1, right - 1, log);
	}


	// Helper method to find the minimum and maximum indices
	private static <T extends Comparable<T>> int[] findMinMax(T[] arr, int left, 
			int right, int minIndex, int maxIndex) {
	    // When left is greater than right, return the min and max indices
	    if (left > right) {
	        return new int[]{minIndex, maxIndex};
	    }

	    // Compare the current element with the min and max elements
	    if (arr[left].compareTo(arr[minIndex]) < 0) {
	        minIndex = left;
	    }
	    if (arr[left].compareTo(arr[maxIndex]) > 0) {
	        maxIndex = left;
	    }

	    return findMinMax(arr, left + 1, right, minIndex, maxIndex);
	}

	// toString helper method for Array since the Arrays class can't be used
	private static <T> String arrayToString(T[] arr) {
		String arrayString = "[";
		for (int i = 0; i < arr.length; i++) {
			if (i < arr.length - 1) {
				arrayString += arr[i] + ", ";
			} else {
				arrayString += arr[i];
			}
		}
		arrayString += "]";
		return arrayString;
	}
   
}
