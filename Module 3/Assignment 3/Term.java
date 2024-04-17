import java.util.Comparator;

/**
 * Autocomplete term representing a (query, weight) pair.
 */

 
public class Term implements Comparable<Term> {
/*Q1: what is comparable interface, what is it used for?
 * 
 * Answer: Comparable interface is used to sort the objects with natural ordering. 
 * 
 * here is the link for comparator interface:
 *  https://www.geeksforgeeks.org/comparator-interface-java/
 * 
 * interface in the Java programming language is an abstract(抽象的) type that is used to specify a behavior that classes must implement（实施）. 
 * They are similar to protocols（协议）. Interfaces are declared申明 using the interface keyword
 */
    private final String query;
    private final long weight;

    /**
     * Initialize a term with the given query and weight.
     * This method throws a NullPointerException if query is null,
     * and an IllegalArgumentException if weight is negative.
     */
    public Term(String query, long weight) {
        if (query == null) {
            throw new NullPointerException();
        }
        if (weight < 0) {
            throw new IllegalArgumentException();
        }
        this.query = query;
        this.weight = weight;
        /**
         * this.name represents the instance variable
         *  while the name variable is a parameter that is in the scope of the function (constructor in this case).

          With this assignment, the value of the local variable is assigned to the instance variable.
         * 
         * This is not a javascript thing, it's an OOP thing. 
         * You want to leave the properties of an object isolated from other scopes.

var Animal = function(name) {
    this.name = name;
};
var name = "foo";
var dog = new Animal("bar");
// shows "foo"
console.log(name);
// shows "bar"
console.log(dog.name);
         */
    }

    /**
     * Compares the two terms in descending order of weight.
     */

     /*here is the reason this comparator return us the descending order of weight
      * As the name suggests, Comparable is an interface defining a strategy of comparing an object with other objects
       of the same type.
       This is called the class’s “natural ordering.”
     */
    public static Comparator<Term> byDescendingWeightOrder() {
        return new Comparator<Term>() {
            public int compare(Term t, Term t2) {
                return Long.compare(t2.weight, t.weight);
            }
        };
        /*
         * here is the link for how to use compare method under Long class
         * :https://www.javatpoint.com/java-long-compare-method
         * The above method returns:

                0 if x==y.
                A value less than 0 if x<y.
                A value greater than 0 if x>y.
         * 
         */

         /*
          * difference between interface / class/ exception/ error
          https://docs.oracle.com/javase/8/docs/api/java/lang/package-summary.html
         */
   }

    /**
     * Compares the two terms in ascending lexicographic order of query,
     * but using only the first length characters of query. This method
     * throws an IllegalArgumentException if length is less than or equal
     * to zero.
     */

     /* 1.here is the reason we use compareTo method:
      * The compareTo() method compares two strings lexicographically. 
      The comparison is based on the Unicode value of each character in the strings.
       The method returns 0 if the string is equal to the other string.
     */
    public static Comparator<Term> byPrefixOrder(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException();
        }
        return (a, b) -> {
            String aq = a.query.substring(0, Math.min(length, a.query.length()));
            String bq = b.query.substring(0, Math.min(length, b.query.length()));
            return aq.compareTo(bq);
        };
    }

    /**
     * Compares this term with the other term in ascending lexicographic order
     * of query.
     */
    @Override
    public int compareTo(Term other) {
        return query.compareTo(other.query);
    }

    /**
     * Returns a string representation of this term in the following format:
     * query followed by a tab followed by weight
     */
    @Override
    public String toString() {
        return String.format("%s\t%d", query, weight);
    }
    public String getQuery() {
        return query;
    }
}

