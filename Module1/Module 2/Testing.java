import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Arrays;
import java.util.ArrayList;

public class Testing {
    @Test
    public void minTest() {
        Collection<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 3));
        Comparator<Integer> comparator = Comparator.naturalOrder();
        int expected = 1;
        int actual = Selector.<Integer>min(a, comparator);
        assertEquals(expected, actual);
    }
    @Test
    public void minTest2() {
        Collection<String> a = new ArrayList<>(Arrays.asList("1", "2", "3"));
        Comparator<String> comparator = Comparator.naturalOrder();
        String expected = "1";
        String actual = Selector.<String>min(a, comparator);
        assertEquals(expected, actual);
    }
    @Test
    public void maxTest() {
        Collection<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 3));
        Comparator<Integer> comparator = Comparator.naturalOrder();
        int expected = 3;
        int actual = Selector.<Integer>max(a, comparator);
        assertEquals(expected, actual);
    }
    @Test
    public void maxTest2() {
        Collection<String> a = new ArrayList<>(Arrays.asList("1", "2", "3"));
        Comparator<String> comparator = Comparator.naturalOrder();
        String expected = "3";
        String actual = Selector.<String>max(a, comparator);
        assertEquals(expected, actual);
    }
   @Test
    public void rangeTest() {
        Collection<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 3));
        Comparator<Integer> comparator = Comparator.naturalOrder();
        Collection<Integer> expected = Arrays.asList(1, 2, 3);
        Collection<Integer> actual = Selector.<Integer>range(a, 0, 3, comparator);
        assertIterableEquals(expected, actual);
    }
    @Test
    public void rangeTest2() {
        Collection<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 3));
        Comparator<Integer> comparator = Comparator.naturalOrder();
        Collection<Integer> expected = Arrays.asList(1, 2);
        Collection<Integer> actual = Selector.<Integer>range(a, 0, 2, comparator);
        assertIterableEquals(expected, actual);
    }
    @Test
    public void rangeTest4() {
        Collection<String> a = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
        Comparator<String> comparator = Comparator.comparing(String::toString);
        Collection<String> expected = Arrays.asList("b", "c");
        Collection<String> actual = Selector.<String>range(a, "b", "c", comparator);
        assertIterableEquals(expected, actual);
    }
    @Test
    public void ceilingTest() {
        Collection<Integer> a = new ArrayList<>(Arrays.asList(1,2,3,4));
        Comparator<Integer> comparator = Comparator.naturalOrder();
        int expected = 1;
        int actual = Selector.<Integer>ceiling(a, 0, comparator);
        assertEquals(actual, expected);
    }
    @Test
    public void celingTest2() {
        Collection<Integer> a = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        Comparator<Integer> comparator = Comparator.naturalOrder();
        int key = 6;
        assertThrows(NoSuchElementException.class, ()-> Selector.ceiling(a, key, comparator));
    }
    @Test
    public void kminTest() {
        Collection<Integer> a = new ArrayList<>(Arrays.asList(0,0,1,1,2,3));
        Comparator<Integer> comparator = Comparator.naturalOrder();
        int expected = 2;
        int k = 3;
        int actual = Selector.<Integer>kmin(a, k, comparator);
        assertEquals(expected, actual);
    }
    @Test
    public void kminTest2() {
        Collection<Integer> a = new ArrayList<>(Arrays.asList(5,7));
        Comparator<Integer> comparator = Comparator.naturalOrder();
        int expected = 5;
        int k = 1;
        int actual = Selector.<Integer>kmin(a, k, comparator);
        assertEquals(expected, actual);
    }
    @Test
    public void kminTest3() {
        Collection<Integer> a = new ArrayList<>();
        Comparator<Integer> comparator = Comparator.naturalOrder();
        int k = 0;
        assertThrows(NoSuchElementException.class, ()-> Selector.kmax(a, k, comparator));
    }
    @Test
    public void kmaxTest() {
        Collection<Integer> a = new ArrayList<>(Arrays.asList(0,0,1,1,2,3));
        Comparator<Integer> comparator = Comparator.naturalOrder();
        int expected = 2;
        int k = 2;
        int actual = Selector.<Integer>kmax(a, k, comparator);
        assertEquals(expected, actual);
    }
    @Test
    public void kmaxTest2() {
        Collection<Integer> a = new ArrayList<>(Arrays.asList(5,7));
        Comparator<Integer> comparator = Comparator.naturalOrder();
        int expected = 7;
        int k = 1;
        int actual = Selector.<Integer>kmax(a, k, comparator);
        assertEquals(expected, actual);
    }
    @Test
    public void kmaxTest3() {
        Collection<Integer> a = new ArrayList<>(Arrays.asList(2,4,56,7,8,5,34));
        Comparator<Integer> comparator = Comparator.naturalOrder();
        int expected = 8;
        int k = 3;
        int actual = Selector.<Integer>kmax(a, k, comparator);
        assertEquals(expected, actual);
    }
    @Test
    public void kmaxTest4() {
        Collection<Integer> a = new ArrayList<>(Arrays.asList());
        Comparator<Integer> comparator = Comparator.naturalOrder();
        assertThrows(NoSuchElementException.class, ()-> Selector.kmax(a, 0, comparator));
    }
    @Test
    public void kmaxTest5() {
        Collection<Integer> a = new ArrayList<>(Arrays.asList(1,2,3));
        Comparator<Integer> comparator = Comparator.naturalOrder();
        int k = 4;
        assertThrows(NoSuchElementException.class, ()-> Selector.kmax(a, k, comparator));
    }
    @Test
    public void floorTest() {
        Collection<Integer> a = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        Comparator<Integer> comparator = Comparator.naturalOrder();
        int key = 3;
        int expected = 3;
        assertEquals(expected, Selector.floor(a, key, comparator));
    }
    @Test
    public void floorTest2() {
        Collection<Integer> a = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        Comparator<Integer> comparator = Comparator.naturalOrder();
        int key = 0;
        assertThrows(NoSuchElementException.class, ()-> Selector.floor(a, key, comparator));
    }

}






