import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Comparator;

public class Testing {

    private Autocomplete autocomplete;

    @BeforeEach
    public void setUp() {
        // Initialize the autocomplete object with sample terms for testing
        Term[] sampleTerms = {
            new Term("abcde", 6),
            new Term("abcd", 2),
            new Term("abc", 10),
            new Term("ab", 8),
            new Term("a", 4)
        };
        autocomplete = new Autocomplete(sampleTerms);
    }

    @Test
    public void testByPrefixOrder() {
        Comparator<Term> prefixComparator = Term.byPrefixOrder(3); // Using a prefix length of 3
        Term[] sortedTerms = {
            new Term("a", 4),
            new Term("ab", 8),
            new Term("abc", 10),
            new Term("abcd", 2),
            new Term("abcde", 6)
        };
        Arrays.sort(sortedTerms, prefixComparator);
        assertArrayEquals(sortedTerms, sortedTerms);
    }

    @Test
    public void testAllMatches() {
        String prefix = "a";
        Term[] expectedMatches = {
            new Term("abcde", 6),
            new Term("abcd", 2),
            new Term("abc", 10),
            new Term("ab", 8),
            new Term("a", 4)
        };
        Term[] matches = autocomplete.allMatches(prefix);
        assertArrayEquals(expectedMatches, matches);
    }
}
