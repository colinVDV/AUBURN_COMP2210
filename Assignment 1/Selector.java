import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Colin Vande Vijvere (ccv0004@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  TODAY
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


   /**
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int min(int[] a) {
   
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Arraylength can not be zero!");
      }
      else {
         int result = a[0];
         for (int i = 0; i < a.length; i++) {
            if (a[i] < result) {
               result = a[i];
            }
         }
         return result;
      }
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int max(int[] a) {
      
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Arraylength can not be zero!");
      }
      else {
         int result = a[0];
         for (int i = 0; i < a.length; i++) {
            if (a[i] > result) {
               result = a[i];
            }
         }
         return result;
      }
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmin(int[] a, int k) {
       //counter for the size of the non-dupe array.
      int counter = 1;
      
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Arraylength can not be zero!");
      }
      
      if (k > a.length || k <= 0) {
         throw new IllegalArgumentException("K is not a correct value for this array.");
      }
      
      //creating a copy of the array so that we do not alter the original array.
      int [] copyArray = Arrays.copyOf(a, a.length);
      Arrays.sort(copyArray);
      if (copyArray.length == 1) {
         return  copyArray[0];
      }
      
      //checking how big our non-dupes array needs to be
      for(int i = 1; i < copyArray.length; i++) {
         if (copyArray[i] != copyArray[i-1]) {
            counter++;
         }
      }
      
      //creating a new array to store non-duplicates
      int []noDupes = new int[counter];
      
      //for loop to read in non-dup. elements to the new array
      int j = 0;
      boolean booleanCheck= true;
      int first = copyArray[0];
      for(int i = 1; i < a.length; i++) {
         if (copyArray[i] != copyArray[i-1]) {
            noDupes[j++] = copyArray[i-1];
            
            if (copyArray[i] == copyArray[copyArray.length-1]) {
               noDupes[noDupes.length-1] = copyArray[copyArray.length-1];
            }
         }
      }
      
      for (int z = 1; z<copyArray.length && booleanCheck; z++) {
         if (copyArray[z]!=first){
            booleanCheck= false;
         }
         else {
            noDupes[0] = copyArray[z];
         }
      }
      
      //checking if k is smaller than the new array's length
      if (k > noDupes.length || k <= 0) {
         throw new IllegalArgumentException("K is not too large for this array.");
      }
      
          

      return noDupes[k-1];
   }

   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmax(int[] a, int k) {
      //counter for the size of the non-dupe array.
      int counter = 1;
      
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Arraylength can not be zero!");
      }
      
      if (k > a.length || k <= 0) {
         throw new IllegalArgumentException("K is not a correct value for this array.");
      }
      
      //creating a copy of the array so that we do not alter the original array.
      int [] copyArray = Arrays.copyOf(a, a.length);
      Arrays.sort(copyArray);
      if (copyArray.length == 1) {
         return  copyArray[0];
      }
      
      //checking how big our non-dupes array needs to be
      for(int i = 1; i < copyArray.length; i++) {
         if (copyArray[i] != copyArray[i-1]) {
            counter++;
         }
      }
      
      //creating a new array to store non-duplicates
      int []noDupes = new int[counter];
      
      //for loop to read in non-dup. elements to the new array
      int j = 0;
      boolean booleanCheck= true;
      int first = copyArray[0];
      for(int i = 1; i < a.length; i++) {
         if (copyArray[i] != copyArray[i-1]) {
            noDupes[j++] = copyArray[i-1];
            
            if (copyArray[i] == copyArray[copyArray.length-1]) {
               noDupes[noDupes.length-1] = copyArray[copyArray.length-1];
            }
         }
      }
      
      for (int z = 1; z < copyArray.length && booleanCheck; z++) {
         if (copyArray[z]!=first){
            booleanCheck= false;
         }
         else {
            noDupes[0] = copyArray[z];
         }
      }
      
      //checking if k is smaller than the new array's length
      if (k > noDupes.length || k <= 0) {
         throw new IllegalArgumentException("K is not too large for this array.");
      }
      
          
      return noDupes[noDupes.length-k];
   }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    */
   public static int[] range(int[] a, int low, int high) {
      int n = 0;
      int count = 0;
      
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Arraylength can not be zero!");
      }
      
      //determining the size that the array needs to be.
      for (int i = 0; i < a.length; i++) {
         if (a[i] <= high && a[i] >= low) {
            count ++;
         }
      }
      
      //the array that will be returned.
      int []result = new int [count];
      
      for (int i = 0; i < a.length; i++) {
         if (a[i] <= high && a[i] >= low) {
            result[n] = a[i];
            n++;
         }
      }
      return result;
   }
   


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int ceiling(int[] a, int key) {
      
      //checking if arraylength is not zero
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Arraylength can not be zero!");
      }
      
      int error = 1;
      int max = max(a);
      int storedInt = max;
      
      //checking if there is a value 
      for (int i = 0; i < a.length; i++){
         
         if (a[i] >= key && a[i] <= storedInt) {
            error = 0;
            storedInt = a[i];
         }
      }  
      if (error == 1) {
         throw new IllegalArgumentException("Value is not in array!");
      }   
      
      return storedInt;
   }


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int floor(int[] a, int key) {
      //checking if arraylength is not zero
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Arraylength can not be zero!");
      }
      
      int error = 1;
      int storedInt = min(a);
      
      //checking if there is a value 
      for (int i = 0; i < a.length; i++){
         
         if (a[i] <= key && a[i] >= storedInt) {
            error = 0;
            storedInt = a[i];
         }
      }
            
      if (error == 1) {
         throw new IllegalArgumentException("Arraylength can not be zero!");
      }   
      
      return storedInt;
   }
   
   private static int[] noDupes(int []a) {
      int count = 1;
      if (a[0] != a[1]) {
         count = 1;
      }
   
      for (int i = a.length; i > 0; i--) {
         if (a[i] == a[i-1]){
            count = count;
         } else {
            count ++;
         }
      }
      
      int []newArray = new int [count];
      int j = 0;
      for (int i = a.length; i > 0; i--) {
         if (a[i] != a[i-1]) {
            newArray[j] = a[i];
            j++;
            if (i == 1) {
               newArray[j] = a[0];
            }
         }
      
      }
      return newArray;
   }
   /**
   public static void main(String[] args) {
   int []TestCase1 = new int[] {4,4,4,4,4,4};
   int []TestCase2 = new int[] {4,4};
   
   System.out.println(kmax(TestCase1, 1));
   System.out.println(kmax(TestCase2, 1));
   System.out.println(kmin(TestCase1, 1));
   System.out.println(kmin(TestCase2, 1));
   }*/
}

