import java.util.Arrays;
import java.util.Comparator;

/**
 * Binary search.
 */
public class BinarySearch {

    /**
     * Returns the index of the first key in a[] that equals the search key, 
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if(a == null) {
            throw new NullPointerException();
        }
        for(int x = 0; x < a.length; x++) {
            if(comparator.compare(a[x], key) == 0) {
                return x;
            }
        }
        return -1;
    }

    /**
     * Returns the index of the last key in a[] that equals the search key, 
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if(a == null) {
            throw new NullPointerException();
        }
        int index = -1;
        for(int x = 0; x < a.length; x++) {
            if(comparator.compare(a[x], key) == 0) {
                index = x;
            }
        }
        return index;
    }

}
