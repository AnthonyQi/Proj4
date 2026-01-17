package systemImp;


/**
 * Utility class for working with {@code PriorityList} objects.
 * Provides method to merge two sorted priority lists while ensuring they are sorted
 * using natural ordering (Comparable) rather than a custom comparator.
 */
public class PriorityListUtils {
	
	//O(n1 + n2) due to each list so time complexity is still O(n)
	public static <T extends Comparable<T>>PriorityList<T> mergePriorityLists
		(PriorityList<T> list1, PriorityList<T> list2) {
		//using help method to check for natural order
		checkSort(list1);
		checkSort(list2);
		//creates size integer
		int mergeSize = list1.size() + list2.size();
		//natural ordering no need for custom comparator
		PriorityList<T> listMerge = new PriorityList<>(mergeSize, false, null);
		for (int i = 0; i < mergeSize; i++) {
			//add first list elements
			if(i < list1.size()) {
				listMerge.add(list1.get(i));
			}
			//add second list elements
			else {
				int ind2 = i - list1.size();
				listMerge.add(list2.get(ind2));
			}
	    }
		return listMerge;
	}
	
	//O(n), because of simple single loop through array
	private static <T extends Comparable<T>> void checkSort
		(PriorityList<T> list) {
		T prev = list.get(0);
		for(int i = 1; i < list.size(); i++) {
			T curr = list.get(i);
			//check ordering of the list
			if(prev.compareTo(curr) > 0) {
				throw new IllegalArgumentException
				("One or both lists are not sorted using Comparable");			
			}
			//set the previous term to the current one for the next loop
			prev = curr;
		}
	}
    
}
