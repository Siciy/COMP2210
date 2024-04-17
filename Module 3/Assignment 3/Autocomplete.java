import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;


/**
 * Autocomplete.
 */
public class Autocomplete {

	private Term[] terms;

	/**
	 * Initializes a data structure from the given array of terms.
	 * This method throws a NullPointerException if terms is null.
	 */

	public Autocomplete(Term[] terms) {
        if(terms == null) {
            throw new NullPointerException();
        }
        this.terms = terms;
        Term[] sorted = new Term[terms.length];
        for(int x = 0; x < terms.length; x++) {
            Term min = terms[x];
            for(int y = x; y < terms.length; y++) {
                if(terms[y].compareTo(min) < 0) {
                    min = terms[y];
                }
                sorted[x] = min;
            }
        }
        terms = sorted;
    }

	/** 
	 * Returns all terms that start with the given prefix, in descending order of weight. 
	 * This method throws a NullPointerException if prefix is null.
	 */
	public Term[] allMatches(String prefix) {
        if(prefix == null) {
            throw new NullPointerException();
        }
        ArrayList<Term> arr = new ArrayList<>();
        for(int x = 0; x < terms.length; x++) {
            if(terms[x].getQuery().startsWith(prefix)) {
                arr.add(terms[x]);
            }
        }
        Term[] a = new Term[arr.size()];
        a = arr.toArray(a);
        return a;
    }

}
