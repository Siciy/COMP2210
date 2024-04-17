
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Testing {
    private LinkedSet<Integer> set;

    @BeforeEach
    public void setUp() {
        set = new LinkedSet<>();
    }

    @Test
    public void testIterator() {
        set.add(3);
        set.add(2);
        set.add(1);

        Integer[] expectedOrder = {1, 2, 3};
        Integer[] actualOrder = new Integer[3];
        int i = 0;
        for (Integer element : set) {
            actualOrder[i++] = element;
        }
        assertArrayEquals(expectedOrder, actualOrder);
    }

    @Test
    public void testRemove() {
        assertFalse(set.contains(4));
        set.add(1);
        set.remove(2);
        set.remove(1);
        assertTrue(set.front == null);
        assertTrue(set.rear == null);
    }

    @Test
    public void testContains() {
        set.add(4);
        set.add(1);
        set.add(5);
        set.add(3);

        assertTrue(set.contains(4));
        assertTrue(set.contains(1));
        assertTrue(set.contains(5));
        assertTrue(set.contains(3));
        assertFalse(set.contains(2)); // Should not exist

        set.remove(4);
        assertFalse(set.contains(4)); // Should not exist after removal
    }
    @Test
    public void coreMethodsStructuralTest() {
        // Add and remove elements and perform structural verifications as necessary
        // Customize this test case based on your specific implementation and requirements
    }
}
