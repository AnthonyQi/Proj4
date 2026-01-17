package systemImp;
import java.util.Arrays;

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
	
	public static <T extends Comparable<T>> int bidirectionalLinearSearch(
		T[] arr, T key, int left, int right, StringBuilder log) {
		//check if left index is larger than the right index
	    if (left > right) {
	        return -1;
	    }
	    log.append("Checking index (left): ").append(left)
	       .append(", value: ").append(arr[left]).append("\n");
	    //checks if the current element at index left is the element needed
	    if (arr[left].compareTo(key) == 0) {
	        return left;
	    }
	    //as long as the right and left indices dont match 
	    //it will continue to search
	    if (left != right) {
	        log.append("Checking index (right): ").append(right)
	           .append(", value: ").append(arr[right]).append("\n");
	        //check right side
	        if (arr[right].compareTo(key) == 0) {
	            return right;
	        }
	    }
	    //recursion: call method again
	    return bidirectionalLinearSearch(arr, key, left + 1, right - 1, log);
	}
	
	public static <T extends Comparable<T>> String bidirectionalBubbleSort
		(T[] arr) {
        StringBuilder log = new StringBuilder();
        //loop through half of the array as a full sweep is not really needed
        for (int i = 0; i < arr.length / 2; i++) {
            boolean swap = false;
            //bubble sort from left to right
            for (int j = i; j < arr.length - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    log.append("Swapped " + arr[j + 1] + " and " + arr[j] + ": ");
                    //swap using a temporary variable
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    log.append(Arrays.toString(arr)).append("\n");
                    swap = true;
                }
            }
            //bubble sort from right to left
            for (int j = arr.length - 2 - i; j > i; j--) {
                if (arr[j].compareTo(arr[j - 1]) < 0) {
                    log.append("Swapped " + arr[j - 1] + " and " + arr[j] + ": ");
                    //swap using a temporary variable
                    T temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    log.append(Arrays.toString(arr)).append("\n");
                    swap = true;
                }
            }
            //if nothing can be swapped, then break
            if (!swap) {
                break;
            }
        }
        return log.toString();
    }

	public static <T extends Comparable<T>> void 
		recursiveBidirectionalSelectionSort
		(T[] arr, int left, int right, StringBuilder log) {
        if (left >= right) {return;}
        //indices for min and max for when looking for the max and min
        //element during selection sort from both sides
        int min = findMin(arr, left, right);
        int max = findMax(arr, left, right);
        //min swap
        if (arr[left].compareTo(arr[min]) != 0) {
            T temp = arr[left];
            arr[left] = arr[min];
            arr[min] = temp;
            log.append("Swapped ").append(arr[min]).append(" and ")
            .append(arr[left]).append(": ").append(Arrays.toString(arr))
            .append("\n");
        }
        //when max index is equal to the left index, set max index to min
        if (max == left) {max = min;}
        //max swap
        if (arr[right].compareTo(arr[max]) != 0) {
            T temp = arr[right];
            arr[right] = arr[max];
            arr[max] = temp;
            log.append("Swapped ").append(arr[max]).append(" and ")
            .append(arr[right]).append(": ").append(Arrays.toString(arr))
            .append("\n");
        }
        //recursive call
        recursiveBidirectionalSelectionSort(arr, left + 1, right - 1, log);
    }
	
	//O(n) time complexity
	//helper method for left to right: finding the min element
    private static <T extends Comparable<T>> int findMin
    	(T[] arr, int left, int right) {
    	//base case, if done searching through entire array
    	if(left == right) {
    		return left;
    	}
    	//recursive call
    	int supposedMin = findMin(arr, left + 1, right);
    	//used ternary ops to reduce line amount
        return 
        	(arr[left].compareTo(arr[supposedMin]) < 0)? left : supposedMin;
    }
    
	//O(n) time complexity
    //helper method for right to left: finding the max element
    private static <T extends Comparable<T>> int findMax
    	(T[] arr, int left, int right) {
    	//base case, if done searching through entire array
    	if(left == right) {
    		return left;
    	}
    	//recursive call
    	int supposedMax = findMax(arr, left + 1, right);
    	//used ternary ops to reduce line amount
    	return 
    		(arr[left].compareTo(arr[supposedMax]) > 0)? left : supposedMax;
    }
}
