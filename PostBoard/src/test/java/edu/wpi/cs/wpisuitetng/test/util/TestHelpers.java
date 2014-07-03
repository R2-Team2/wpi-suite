/**
 * 
 */
package edu.wpi.cs.wpisuitetng.test.util;

/**
 * Common helpers
 * 
 * @author Robert Smieja
 */
public class TestHelpers {
    
    /**
     * Check if the array contains the specified object
     * 
     * @param array The array to check
     * @param object The object to search for
     * @return True if the object is in the array, false otherwise
     */
    public static boolean contains(Object[] array, Object object) {
        for (Object iterator : array) {
            if (iterator.equals(object)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Check if the Iterable contains the specified object
     * 
     * @param list The object that implements the Iterable interface to check
     * @param object The object to search for
     * @return True if the object is in the list, false otherwise
     */
    public static boolean contains(Iterable<?> list, Object object) {
        for (Object iterator : list) {
            if (iterator.equals(object)) {
                return true;
            }
        }
        return false;
    }
}
