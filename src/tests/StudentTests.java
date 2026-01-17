package tests;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import systemImp.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class StudentTests {
	
	//Testing SearchAndSortClass
	
	@Test
	public void testLinearSearch() {
		//testing normal case
		Integer[] test1 = {1, 2, 4, 3, 5};
		StringBuilder log1 = new StringBuilder();
		int key1 = 4;
		String ans1 = "Checking index (left): 0, value: 1\n"
				+ "Checking index (right): 4, value: 5\n"
				+ "Checking index (left): 1, value: 2\n"
				+ "Checking index (right): 3, value: 3\n"
				+ "Checking index (left): 2, value: 4\n";
		int num1 = SearchAndSortUtil.bidirectionalLinearSearch
			(test1, key1, 0, test1.length-1, log1);
		assertTrue(num1 == 2);
		assertEquals(ans1, log1.toString());
		//testing one element
		Integer[] test2 = {0};
		int key2 = 0;
		StringBuilder log2 = new StringBuilder();
		int num2 = SearchAndSortUtil.bidirectionalLinearSearch
			(test2, key2, 0, test2.length, log2);
		assertTrue(num2 == 0);
	}
	
	@Test
	public void testBubbleSort() {
		//best case
		Integer[] test1 = {1, 2, 3};
		String ans1 = "";
		String log1 = SearchAndSortUtil.bidirectionalBubbleSort(test1);
		assertEquals(log1, ans1);
		//worst case
		Integer[] test2 = {3, 2, 1};
		String ans2 = "Swapped 2 and 3: [2, 3, 1]\n"
				+ "Swapped 1 and 3: [2, 1, 3]\n"
				+ "Swapped 2 and 1: [1, 2, 3]\n";
		String log2 = SearchAndSortUtil.bidirectionalBubbleSort(test2);
		assertEquals(log2, ans2);
	}
	
	@Test
	public void testSelectionSort() {
		Integer[] arr = {6, 3, 10, 1, 6, 8};
		StringBuilder log = new StringBuilder();
		String ans = "Swapped 6 and 1: [1, 3, 10, 6, 6, 8]\n"
				+ "Swapped 8 and 10: [1, 3, 8, 6, 6, 10]\n"
				+ "Swapped 6 and 8: [1, 3, 6, 6, 8, 10]\n";
		SearchAndSortUtil.recursiveBidirectionalSelectionSort
			(arr, 0, arr.length-1, log);
		assertEquals(ans, log.toString());
	}
	
	//Testing PriorityList Class

	@Test
	public void testingSizeEmptyFull() {
	    PriorityList<Integer> pl = new PriorityList<>(3, false, null);
	    //testing when empty
	    assertTrue(pl.isEmpty());
	    assertFalse(pl.isFull());
	    assertEquals(0, pl.size());
	}
	
	@Test
	public void testingAddRemove() {
		PriorityList<Integer> pl = new PriorityList<Integer>(3, false, null);
		pl.add(1);
		pl.add(2);
		pl.add(3);
		//testing when full
		try {
			pl.add(4);
		} catch(Exception e) {
			String errorAns = "Priority List is full";
			assertEquals(errorAns, e.getMessage());
		}
		//removing last element
		Integer three = 3;
		System.out.print(pl.toString());
		boolean removed = pl.remove(three);
		System.out.print(pl.toString());
		assertTrue(removed);
		//removing after already removed
		boolean notRemoved = pl.remove(three);
		assertFalse(notRemoved);
		PriorityList<Integer> lp = new PriorityList<Integer>(6, false, null);
		//inputing the out of order
		lp.add(8);
		lp.add(4);
		lp.add(5);
		lp.add(6);
		String ans = "[ 4 5 6 8 ]";
		System.out.print(lp.toString());
		assertEquals(ans, lp.toString());
		//removing values that don't exist
		assertFalse(lp.remove(three));
		//removing last value in an un-full array
		Integer eight = 8;
		assertTrue(lp.remove(eight));
		System.out.print(lp.toString());
		//removing first element, adding one more element
		lp.add(eight);
		System.out.print(lp.toString());
		Integer four = 4;
		assertTrue(lp.remove(four));
		System.out.print(lp.toString());
		//testing for duplicates
		lp.add(eight);
		lp.add(eight);
		System.out.print(lp.toString());
		assertTrue(lp.remove(eight));
		System.out.print(lp.toString());
		

	}
	
	@Test
	public void testingBinaryInsertionPoint() {
		PriorityList<Integer> pl = new PriorityList<Integer>(5, false, null);
		//testing empty array
		assertEquals(0, pl.binarySearchInsertionPoint(5));
		pl.add(5);
		pl.add(7);
		System.out.print(pl.toString());
		//testing with element valued in between 5 and 7
		assertEquals(1, pl.binarySearchInsertionPoint(6));
		//testing with element valued below 5 and 7
		assertEquals(0, pl.binarySearchInsertionPoint(1));
		//testing with element valued above 5 and 7
		assertEquals(2, pl.binarySearchInsertionPoint(9));
		//testing when full
		pl.add(6);
		pl.add(8);
		pl.add(9);
		System.out.print(pl.toString());
		//while it does return an index equal to size, it does not add it
		//therefore it shouldn't matter
		assertEquals(5, pl.binarySearchInsertionPoint(10));
		//testing if search gives correct index when adding smaller value to
		//full array
		assertEquals(0, pl.binarySearchInsertionPoint(-1));
	}

	@Test
	public void testingBinarySearchFind() {
		PriorityList<Integer> pl = new PriorityList<Integer>(5, false, null);
		//testing empty array
		assertEquals(-1, pl.binarySearchFind(2));
		pl.add(1);
		pl.add(2);
		System.out.print(pl.toString());
		//testing non-full
		assertEquals(1, pl.binarySearchFind(2));
		//testing when full
		pl.add(3);
		pl.add(4);
		pl.add(5);
		System.out.print(pl.toString());
		assertEquals(4, pl.binarySearchFind(5));
		//testing for non-existent values
		assertEquals(-1, pl.binarySearchFind(-1));
	}
	
	//Testing PriorityListUtils Class
	
	@Test
	public void testingMergePriorityList() {
	    PriorityList<Integer> list1 = new PriorityList<>(10, false, null);
	    PriorityList<Integer> list2 = new PriorityList<>(10, false, null);

	    //list 1
	    list1.add(3);
	    list1.add(8);
	    list1.add(13);
	    list1.add(18);
	    list1.add(23);
	    list1.add(28);
	    list1.add(33);
	    list1.add(38);
	    list1.add(43);
	    list1.add(48);

	    //list 2
	    list2.add(5);
	    list2.add(10);
	    list2.add(15);
	    list2.add(20);
	    list2.add(25);
	    list2.add(30);
	    list2.add(35);
	    list2.add(40);
	    list2.add(45);
	    list2.add(50);
	    
	    //merge the lists
	    PriorityList<Integer> mergedList = 
	    		PriorityListUtils.mergePriorityLists(list1, list2);
	    Integer[] ans = 
	    	{3, 5, 8, 10, 13,
	    	15, 18, 20, 23, 25,
	    	28, 30, 33, 35, 38,
	    	40, 43, 45, 48, 50};
	    assertEquals(20, mergedList.size());
	    for (int i = 0; i < ans.length; i++) {
	        assertEquals(ans[i], mergedList.get(i));
	    }
	    //testing with comparator
	    Comparator<Integer> descendingOrder = (a, b) -> Integer.compare(b, a);
	    PriorityList<Integer> invalidList = 
	    		new PriorityList<>(10, true, descendingOrder);
	    invalidList.add(95);
	    invalidList.add(75);
	    invalidList.add(55);
	    invalidList.add(35);
	    invalidList.add(15);

	    //testing for error
	    try {
	        PriorityListUtils.mergePriorityLists(list1, invalidList);
	    } catch (IllegalArgumentException e) {
	    	String errorAns = "One or both lists are"
	    			+ " not sorted using Comparable";
	        assertEquals(errorAns, e.getMessage());
	    }
	}
}