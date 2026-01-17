package systemImp;

import java.util.Comparator;

/**
 * A generic Priority List that maintains elements in sorted order.
 * Supports both natural ordering (Comparable) and custom ordering (Comparator).
 *
 * @param <T> The type of elements stored in the list. Must implement Comparable<T>.
 */
public class PriorityList<T extends Comparable<T>> {
	private final T[] data;
	private int size;//number of elements in list
	private final boolean useComparator; //if true use Comparator
    private final Comparator<T> comparator; // custom comparator
    
	@SuppressWarnings("unchecked")
	public PriorityList(int capacity, boolean useComparator,
    	Comparator<T> comparator) {
		this.useComparator = useComparator;
        this.data = (T[])new Comparable[capacity];
		if(useComparator && comparator == null) {
			comparator = (a,b) -> a.compareTo(b);
			this.comparator = comparator;
		}
		else {
			this.comparator = comparator;
		}
        //sets generic array capacity
    	this.size = 0;
    }
   
	//O(1) time complexity
	//just returning int
    public int size() {
    	return this.size;
    }
    
    //O(1) time complexity
    //just returning true or false
    public boolean isEmpty() {
    	return this.size == 0;
    }
    
    //O(1) time complexity
    //just returning true or false
    public boolean isFull() {
    	return size == data.length;
    }
    
    //O(1) time complexity
  	//just returning generic
    public T get(int index) {
    	if(index < 0 || index >= size()) {
            throw new 
            	IndexOutOfBoundsException("Index out of bounds: " + index);
        }
    	return data[index];
    }
    
    //O(log(n)) time complexity
    //due to binary search
    public int binarySearchInsertionPoint(T item) {
    	int low = 0;
    	//search up the size of array
        int high = size() - 1;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            int comparison;
            if (data[mid].equals(item)) {
                return mid;
            }
            //check if using comparator for this search
            if(useComparator) {
                comparison = comparator.compare(item, data[mid]);
            } 
            else {
                comparison = item.compareTo(data[mid]);
            }
            //uses comparator to decide left or right side of mid
            if(comparison < 0) {
                high = mid - 1;
            } 
            else if(comparison > 0) {
                low = mid + 1;
            }
        }
        //returns low regardless due to empty arrays
        //to populate the array
        return low;
    }
    
    //O(n) despite calling binarySearch method
    //as O(n) rules over O(log(n))
    public void add(T item) {
    	//checks for full
    	if(isFull()) {
    		throw new IllegalStateException("Priority List is full");
    	}
    	int index = binarySearchInsertionPoint(item);
    	//moves elements over one to the right
    	for(int i = data.length-1; i > index; i--) {
    		data[i] = data[i-1];
    	}
    	data[index] = item;
    	size+=1;
    }
    
    //O(log(n)) time complexity
    //due to binary search
    //same as the previous binary search
    public int binarySearchFind(T item) {
    	if(item == null) {
    		return -1;
    	}
    	int low = 0;
    	int high = size() - 1;
    	while(low <= high) {
            int mid = low + (high - low) / 2;
            int comparison;
            //honestly could've stayed the same as the previous binary
            //search. uses custom comparator or not to see if mid matches
            if (data[mid].equals(item)) {
                return mid;
            }
            if(useComparator) {
            	comparison = comparator.compare(item, data[mid]);
                if(comparison == 0) {
                	return mid;
                }
            } 
            else {
            	comparison = item.compareTo(data[mid]);
                if(comparison == 0) {
                	return mid;
                }
            }
            if(comparison < 0) {
                high = mid - 1;
            } 
            else if(comparison > 0) {
                low = mid + 1;
            } 
    	}
    	//if not found return -1
    	return -1;
    }
    
    //same as add method: O(n)
    public boolean remove(T item) {
    	//calls the binary search to find the index of the item passed
    	//through the parameters
    	int removeIndex = binarySearchFind(item);
    	//check empty or not found
    	//empty check is a bit redundant. for caution
    	if(isEmpty() || removeIndex == -1) {
    		return false;
    	}
    	//sets the removed item to null
    	//data[removeIndex] = null;
    	//moves elements over one to the left
    	if(removeIndex == size()-1) {
    		data[removeIndex] = null;
    	}
    	else {
    		for(int i = removeIndex; i < data.length-1; i++) {
    			data[i] = data[i + 1];
    		}
    		//last element set to null
    		//set inside to reduce redundancy
        	data[size() - 1] = null;
    	}
    	//decrease size
    	size-=1;
    	return true;
    }
    
    //returns string representation of array
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("[");
    	for(int i = 0; i < size(); i++) {
    		sb.append(" " + data[i]);
    	}
    	sb.append(" ]");
    	return sb.toString();
    }

}

