import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Colin Vande Vijvere (ccv0004@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version TODAY
 *
 */
public final class Selector {

/**
 * Can't instantiate this class.
 *
 * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
 *
 */
   private Selector() { 
   }


   /**
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @param <T>     the type that will be returned from the method
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
     
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      /**creating the iterator*/
      Iterator <T> itr = coll.iterator();
      T min = itr.next();
      
      for (T element : coll) {
         if (comp.compare(element, min) < 0) min = element;
      }
     
      return min;
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @param <T>     the type that will be returned from the method
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
     
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      /**creating the iterator*/
      Iterator <T> itr = coll.iterator();
      T max = itr.next();
      
      for (T element : coll) {
         if (comp.compare(element, max) > 0) max = element;
      }
     
      return max;
   }


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @param <T>     the type that will be returned from the method
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty()  || (k > coll.size()) || (k < 1)) {
         throw new NoSuchElementException();
      }
      
      //creating a copy of the array so we don't alter the original array
      //second line is sorting the array with the comparator
      ArrayList<T> copyArray = new ArrayList<T>(coll);
      java.util.Collections.sort(copyArray, comp);
     
      //checking for arraysize and key 1
      if ((k == 1) && (copyArray.size() == 1)) {
         return copyArray.get(0);
      }
      
      for(int i = 1; i < copyArray.size(); i++) {
         if (copyArray.get(i) == copyArray.get(i -1)) {
            copyArray.remove(i);
            i--;
         }
      }
      
      //checking for errorcase (k bigger than arraysize)
      if (k > copyArray.size()) {
         throw new NoSuchElementException();
      }
       
      T result = copyArray.get(k -1);
         
      return result;
   }


   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @param <T>     the type that will be returned from the method
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
      if (( coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty() || (k > coll.size()) || (k < 1)) {
         throw new NoSuchElementException();
      }
      
      //creating a copy of the array so we don't alter the original array
      //second line is sorting the array with the comparator
      ArrayList<T> copyArray = new ArrayList<T>(coll);
      java.util.Collections.sort(copyArray, comp);
      
      //checking for arraysize and key 1
      if ((k == 1) && (copyArray.size() == 1)) {
         return copyArray.get(0);
      }
      
      for (int i = 1; i < copyArray.size(); i++) {
         if (copyArray.get(i) == copyArray.get(i - 1)) {
            copyArray.remove(i);
            i--;
         }  
      }
      
      //checking for errorcase (k bigger than arraysize)
      if (k > copyArray.size()) {
         throw new NoSuchElementException();
      }
      
      T result = copyArray.get(copyArray.size() - k);
      
      return result;  
   }


   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @param <T>     the type that will be returned from the method
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) {
    
    //Throws exception if comp or coll is null.                                     
      if (coll == null || comp == null)  throw new IllegalArgumentException();
        
         //Throws exception if coll is empty.
      if (coll.isEmpty()) throw new NoSuchElementException();
      
      //Creating the new list.
      Collection <T> range = new ArrayList<T>();
      
      //Scanning and evaluating the elements in the list.
      for(T element : coll) {
         if (comp.compare(element, low) >= 0 && comp.compare(element, high) <= 0) range.add(element);
      }
      
      //Throws exception if new list is empty.
      if (range.isEmpty()) throw new NoSuchElementException();
      
      //return the new list.
      return range;
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
     
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      Iterator <T> itr = coll.iterator();
      T max = max(coll,comp);
      T result = null;
      
      //for-each loop that searches the c
      for (T element : coll) {
         if (comp.compare(element, key) >= 0 && comp.compare(element, max) <= 0) { 
            result = element;
            max = element;
         }
      }
      
      //Throw error if there is no value that coresponds with what we look for.
      if (result == null){
         throw new NoSuchElementException();
      }
      
      return result;
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
     
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      T min = min(coll,comp);
      T result = null;
      
      //for-each loop to search for the value
      for (T element : coll) {
         if (comp.compare(element, key) <= 0 && comp.compare(element, min) >= 0) {
            result = element;
            min = element;
         }
      }
      
      //Throw error if there is no value that coresponds with what we look for.
      if (result == null){
         throw new NoSuchElementException();
      }
      return result;
   }

}
