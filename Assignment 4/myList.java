import java.util.*;
public class myList<T> implements RandomizedList<T>{

LinkedList <T> list;

    private int currentSize;
public myList(int size) {
//arrayList = new T[size];
list = new LinkedList<T>();
currentSize = 0;
}

public void add(T element) {
System.out.print(element);
}

public T remove() {

}

public T sample(){

}

     @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && list.size() != 0;
            }

            @Override
            public T next() {
               // return arrayList[currentIndex++];
                return list.get(currentIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
    
    @Override
    public boolean isEmpty() {
    return true;
    }
    
    @Override
    public int size() {
    return currentSize;
    }

}