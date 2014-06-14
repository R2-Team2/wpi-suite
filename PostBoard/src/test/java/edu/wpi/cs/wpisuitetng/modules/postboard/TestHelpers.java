/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.postboard;

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
}
