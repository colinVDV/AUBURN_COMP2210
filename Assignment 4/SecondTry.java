import java.util.Iterator;
import java.util.Random;
import java.util.NoSuchElementException;

public class SecondTry <T> implements RandomizedList <T> {

   private static final int DEFAULT_CAPACITY = 1;
   private T[] elements;
   private int size;
   private int capacity;
   private int sizeTrack = size;
   Random rand = new Random();

   @SuppressWarnings("unchecked")
   public SecondTry(int capacityIn) {
      size = 0;
      capacity = capacityIn;
      elements =(T[]) new Object[capacityIn];
   }



   public void add(T element){
      if(element == null) {
         throw new IllegalArgumentException();
      }
      elements[size] = element;
      size++;
   }

   public T remove(){
      if (isEmpty()) {
         return null;
      }
      
      int random = rand.nextInt(size);
      T obj = elements[random];
      elements[random] = null;
      //shift left the elements
      for (int i = capacity - 1; i > random; i--){
         elements [i-1] = elements[i];
      }
      size--;
      return obj;
   }
   

   public T sample() {
      if (isEmpty()) {
         return null;
      }
      
      int random = rand.nextInt(size);
      T obj = elements[random];
      return obj;
   }
      
      
   @SuppressWarnings("unchecked")
   public Iterator <T> iterator() {
      Iterator<T> it = 
         new Iterator<T>() {
            
         
            private int currentIndex = rand.nextInt(sizeTrack);
         
            @Override
            public boolean hasNext() {
               return currentIndex < size && size() != 0;
            }
         
            @Override
            public T next() {
               if (isEmpty()){
                  throw new NoSuchElementException();
               }  
               T[] array = (T[])new Object[size];
               
               for (int i = 0; i < sizeTrack; i++) {
               array[i] = elements[i];
               }
               
               T obj = array[currentIndex];
               //array[currentIndex] = array[sizeTrack-1];
               //shift left the elements
               for (int i = sizeTrack - 1; i > currentIndex; i--){
               elements [i-1] = elements[i];
               }
               sizeTrack--;
               /**else if (size == 1) {
                  return elements[0];
               }else {
                  T result = elements[currentIndex];
                  elements[currentIndex] = elements[sizeTrack-1];
                  elements[sizeTrack-1] = result;
                  sizeTrack--;*/
                  return obj;
               }
            
         
            @Override
            public void remove() {
               throw new UnsupportedOperationException();
            }
         };
      return it;
   }




   public boolean isEmpty() {
      if (size <= 0) {
         return true;
      }else {
         return false;
      }
   }
   
   
   

   public int size() {
      return size;
   }
      
/**      
private class myIterator implements Iterator<T> {
public boolean hasNext(){
 if (!isEmpty()) {
            return true;
         }
         return false;
}

public T next() {
int x = rand.nextInt(size());
return elements[x];
}

public void remove() {
throw new UnsupportedOperationException();
}

}**/
}
