import java.util.*;
public class Sort {
	private static <T extends Comparable<T>> void swap(List<T> list, int i, int j) {
	    T temp = list.get(i);
	    list.set(i, list.get(j));
	    list.set(j, temp);
	 }

	 private static <T extends Comparable<T>> int findMin(List<T> list, int start) {
	    int min = start;
	    for (int i = start; i < list.size(); i++) {
	      if(list.get(i).compareTo(list.get(min))<=0) {
	        min = i;
	      }
	    }
	    return min;
	  }

	 public static <T extends Comparable<T>> void selectionSort(List<T> list) {
	    for(int i = 0; i < list.size(); i++) {
	      int minIndex = findMin(list, i);
	      swap(list, i, minIndex);
	    }
	 }
}
