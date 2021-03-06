package ru.job4j.max;

/**
 * @author vsokolov
 * @version $Id$
 * @since 0.1
 */
public class Max {
	
	/**
	 * Method compares two int's and returns their maximum.
	 * @param first
	 * @param second
	 * @return result - maximum
	 */	
	 public int max(int first, int second) {
		 return (first > second) ? first : second;
	 }
	 
	 /**
	 * Method compares three int's and returns their maximum.
	 * @param first
	 * @param second
	 * @param third
	 * @return result - maximum
	 */	
	 public int max(int first, int second, int third) {
		 return max(max(first, second), third);
	 }
}
