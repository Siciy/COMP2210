/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Thomas Brown (trb0057@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  8/22/23
*
*/
public final class Selector {

    private Selector() { }

    public static int min(int[] a) {
        if(a == null || a.length == 0){
            throw new IllegalArgumentException();
        }
        int min = a[0];
        for(int x = 0; x < a.length; x++) {
            if(a[x] < min) {
                min = a[x];
            }
        }
        return min;
    }

    public static int max(int[] a) {
        if(a == null || a.length == 0){
            throw new IllegalArgumentException();
        }
        int max = a[0];
        for(int x = 0; x < a.length; x++) {
            if(a[x] > max) {
                max = a[x];
            }
        }
        return max;
    }

    public static int kmin(int[] a, int k) {
        if(a == null || a.length == 0 || k > a.length || k < 1){
            throw new IllegalArgumentException();
        }
        if(a.length == 1){
            return a[0];
        }
        int[] sorted = new int[a.length];
        sorted = createCopy(sorted, a);
        sorted = sortIncreasing(sorted);
        return searchForK(sorted, k);
    }
    
    public static int kmax(int[] a, int k) {
        if(a == null || a.length == 0 || k > a.length || k < 1){
            throw new IllegalArgumentException();
        }
        if(a.length == 1){
            return a[0];
        }
        int[] sorted = new int[a.length];
        sorted = createCopy(sorted, a);
        sorted = sortDecreasing(sorted);
        return searchForK(sorted, k);
    }

    //Helper method for kmax only
    public static int[] sortDecreasing(int[]a){
        for(int x = 0; x < a.length; x++) {
            for(int y = x + 1; y < a.length; y++) {
                if(a[x] < a[y]) {
                    int val = a[x];
                    a[x] = a[y];
                    a[y] = val;
                }
            }
        }
        return a;
    }

    //Helper method for kmin only
    public static int[] sortIncreasing(int[]a){
        for(int x = 0; x < a.length; x++) {
            for(int y = x + 1; y < a.length; y++) {
                if(a[x] > a[y]) {
                    int val = a[x];
                    a[x] = a[y];
                    a[y] = val;
                }
            }
        }
        return a;
    }
    
    //Helper methods for kmax and kmin
    public static int searchForK(int[] arr, int k){
        if(k == 1) {
            return arr[0];
        }
        int index = 0;
        for(int n = 0; n < arr.length - 1; n++) {
            if(arr[n] != arr[n + 1]){
                index++;
            }
            if(k - 1 == index) {
                return arr[n+1];
            }
        }
        throw new IllegalArgumentException();
    }

    public static int[] createCopy(int[] arr, int[] copy) {
        for(int n = 0; n < copy.length; n++) {
            arr[n] = copy[n];
        }
        return arr;
    }

    
    public static int[] range(int[] a, int low, int high) {
        if(a == null || a.length == 0) {
            throw new IllegalArgumentException();
        }
        int count = 0;
        for(int x = 0; x < a.length; x++) {
            if(a[x] >= low && a[x] <= high) {
                count++;
            }
        }
        int[] newArr = new int[count];
        int arrCount = 0;
        for(int x = 0; x < a.length; x++) {
            if(a[x] >= low && a[x] <= high){
                newArr[arrCount] = a[x];
                arrCount++;
            }
        }
        return newArr;
    }

    public static int ceiling(int[] a, int key) {
        if(a == null || a.length == 0){
            throw new IllegalArgumentException();
        }
        int min = 0;
        int exceptionCounter = 0;
        for(int x : a) {
            if(x >= key){
                min = x;
                break;
            }
            exceptionCounter++;
        }
        if(exceptionCounter == a.length){
            throw new IllegalArgumentException();
        }
        for(int n : a) {
            if(n >= key && n < min){
                min = n;
            }
        }
        return min;
    }

    public static int floor(int[] a, int key) {
        if(a == null || a.length == 0){
            throw new IllegalArgumentException();
        }
        int maxVal = 0;
        int exceptionCounter = 0;
        for(int x : a) {
            if(x <= key) {
                maxVal = x;
                break;
            }
            exceptionCounter++;
        }
        if(exceptionCounter == a.length){
            throw new IllegalArgumentException();
        }
        for(int y : a) {
            if(y <= key && y > maxVal) {
                maxVal = y;
            }
        }
        return maxVal;
    }

}
