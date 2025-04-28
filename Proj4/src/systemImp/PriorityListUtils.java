package systemImp;

/**
 * Utility class for working with {@code PriorityList} objects. Provides method
 * to merge two sorted priority lists while ensuring they are sorted using
 * natural ordering (Comparable) rather than a custom comparator.
 */
public class PriorityListUtils {
	public static <T extends Comparable<T>> PriorityList<T> 
	mergePriorityLists(PriorityList<T> list1, PriorityList<T> list2) {
		// If either are not Comparable throw an exception
		if (!isComparable(list1, list2)) {
			throw new IllegalArgumentException("One or both lists are not "
					+ "sorted using Comparable");
		}
		
		// The size of the merged list is the size of the two separate lists combined
		int mergedListSize = list1.size() + list2.size();
		PriorityList<T> mergedList = new PriorityList<T>(mergedListSize, false, null);
		
		// Adds the list1 then list2 to mergedList list to combine them
		for (int i = 0; i < list1.size(); i++) {
			mergedList.add(list1.get(i));
		}
		for (int i = 0; i < list2.size(); i++) {
			mergedList.add(list2.get(i));
		}
		
		return mergedList;
	}

	private static <T extends Comparable<T>> boolean 
	isComparable(PriorityList<T> list1, PriorityList<T> list2) {
		// Create comparable only version of both list
		PriorityList<T> newList1 = new PriorityList<T>(list1.size(), false, null);
		PriorityList<T> newList2 = new PriorityList<T>(list2.size(), false, null);

		/* All the code below copies everything from the original list into
		 * natural order. It then checks whether or not the lists are in the same
		 * order which indicates if the list is using Comparable or Comparator.
		 */
		for (int i = 0; i < list1.size(); i++) {
			newList1.add(list1.get(i));
		}
		
		for (int i = 0; i < list1.size(); i++) {
			if (!newList1.get(i).equals(list1.get(i))) {
				return false;
			}
		}

		for (int i = 0; i < list2.size(); i++) {
			newList2.add(list2.get(i));
		}

		for (int i = 0; i < list2.size(); i++) {
			if (!newList2.get(i).equals(list2.get(i))) {
				return false;
			}
		}

		return true;
	}
}
