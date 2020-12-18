import java.util.Iterator;
import java.util.NoSuchElementException;
public class tryOne <T> implements DoubleEndedList <T> {
   private Node front;
   private Node rear;
   private int size;
   
   public tryOne() {
      front = null;
      rear = null;
      size = 0;
   }

   public void addFirst(T element) {
      if(element == null) {
         throw new IllegalArgumentException();
      }
      
      Node  n = new Node (element);
      
      if (size== 0) {
         front = n;
         rear = n;
      } 
      
      else {
         n.next = front;
         front = n;
      }
      size++;
   }

  // @SuppressWarnings ("unchecked")
   public void addLast(T element) {
      if(element == null) {
         throw new IllegalArgumentException();
      }
      Node n = new Node(element);
      if (size == 0) {
         front = n;
         rear = n;
      } else {
         rear.next = n;
         rear = n;
      }
      size++;
   }
   
   
   public T removeFirst() {
      if(isEmpty()) {
         return null;
      }
      
      T delete = front.element;
      front = front.next;
      
      size--;
      return delete;
   }

  
   public T removeLast() {
      if(size == 0) {
         return null;
      } 
      
      else if (size == 1) {
         T delete = front.element;
         front = null;
         rear = null;
         size--;
         return delete;
      }
      
      else {
         Node n = front;
         while (n.next.next != null) {
            n = n.next;
         }
      
         T delete = rear.element;
         n.next = null;
         rear = n;
         size--;
         return delete;
      }
   }

   public int size() {
      return size;}

   public boolean isEmpty() {
      return (size == 0);
   }

   public Iterator<T> iterator(){
      Iterator <T> itr = new Iterator <T>() {
            private Node current = front;
         
            @Override
            public boolean hasNext() {
               return current != null;
            }
         
            @Override
            public T next(){
               if (!hasNext()) {
                  throw new NoSuchElementException();
               }
               T result = current.element;
               current = current.next;
               return result;
            }
         
            @Override
            public void remove() {
               throw new UnsupportedOperationException();
            }
         };
      return itr;
   }
   
   
   //creating the node class
   private class Node {
      private T element;
      private Node next;
     // private Node prev;
   
   
      public Node() {
         element = null;
         next = null;
         //prev = null;
      }
      
      public Node(T elementIn) {
         element = elementIn;
         next = null;
        // prev = null;
      }
      
      public Node(T elementIn, Node n) {
      element = elementIn;
      next = n;
      }
   
        
   }
}

