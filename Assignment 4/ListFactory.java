/**
 * ListFactory.java.
 * Implements the factory method pattern (https://en.wikipedia.org/wiki/Factory_method_pattern)
 * for lists in this assignment.
 *
 * @author Colin Vande Vijvere(ccv0004@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version TODAY
 */
public class ListFactory {

   /**
    * Return an instance of a class that implements RandomizedList.
    */
   public static <T> RandomizedList<T> makeRandomizedList() {
   RandomizedList<T> list = new SecondTry<T>(10);
   return list;
  
   }

   /**
    * Return an instance of a class that implements DoubleEndedList.
    */
   public static <T> DoubleEndedList<T> makeDoubleEndedList() {
      // Replace the following return statement with one that returns
      // an instance of the class you wrote to implement the
      // DoubleEndedList interface.
      DoubleEndedList<T> list = new tryOne<T>();
      return list;
   }

}
