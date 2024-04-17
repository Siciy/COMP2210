import java.util.*;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Thomas Brown (trb0057@auburn.edu)
 *
 */
public final class Selector {

/**
 * Can't instantiate this class.
 *
 * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
 *
 */
    private Selector() { }

    public static <T> T min(Collection<T> coll, Comparator<T> comp) {
        if( coll == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if(coll.isEmpty()) {
            throw new NoSuchElementException();
        }
        T min = coll.iterator().next();
        for(T element: coll) {
            if(comp.compare(element, min) < 0) {
                min = element;
            }
        }
        return min;
    }

    public static <T> T max(Collection<T> coll, Comparator<T> comp) {
        if( coll == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if(coll.isEmpty()) {
            throw new NoSuchElementException();
        }
        T min = coll.iterator().next();
        for(T element: coll) {
            if(comp.compare(element, min) > 0) {
                min = element;
            }
        }
        return min;
    }

    public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
        if( coll == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if(coll.isEmpty()) {
            throw new NoSuchElementException();
        }
        List<T> arr = new ArrayList<>(coll);
        Collections.sort(arr, comp);
        Iterator<T> it = arr.iterator();
        if(k == 1) {
            return it.next();
        }
        int count = 0;
        T current = it.next();
        while(it.hasNext()) {
            T next = it.next();
            if(comp.compare(current, next) != 0) {
                count++;
            }
            if(count == k-1) {
                return next;
            }
            current = next;
        }
        throw new NoSuchElementException();
    }

    public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
        if( coll == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if(coll.isEmpty()) {
            throw new NoSuchElementException();
        }
        List<T> arr = new ArrayList<>(coll);
        Collections.sort(arr, comp);
        Collections.reverse(arr);
        Iterator<T> it = arr.iterator();
        if(k == 1) {
            return it.next();
        }
        int count = 0;
        T current = it.next();
        while(it.hasNext()) {
            T next = it.next();
            if(comp.compare(current, next) != 0) {
                count++;
            }
            if(count == k-1) {
                return next;
            }
            current = next;
        }
        throw new NoSuchElementException();
    }

    public static <T> Collection<T> range(Collection<T> coll, T low, T high, Comparator<T> comp) {
        if( coll == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if(coll.isEmpty()) {
            throw new NoSuchElementException();
        }
        Collection<T> arr = new ArrayList<T>();
        for(T element: coll) {
            if(comp.compare(high, element) >= 0 && comp.compare(low, element) <= 0) {
                arr.add(element);
            }
        }
        if(arr.size() == 0) {
            throw new NoSuchElementException();
        }
        return arr;
    }

    public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
        if( coll == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if(coll.isEmpty()) {
            throw new NoSuchElementException();
        }
        Collection<T> arr = new ArrayList<>();
        for(T element: coll) {
            if(comp.compare(element, key) >= 0) {
                arr.add(element);
            }
        }
        return min(arr, comp);
    }

    public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
        if( coll == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if(coll.isEmpty()) {
            throw new NoSuchElementException();
        }
        Collection<T> arr = new ArrayList<>();
        for(T element: coll) {
            if(comp.compare(element, key) <= 0) {
                arr.add(element);
            }
        }
        return max(arr, comp);
    }

}
