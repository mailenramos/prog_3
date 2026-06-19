import java.util.Iterator;
public class MyIterator<T> implements Iterator<T>{
    private Node<T>cursor;

    public MyIterator(Node<T> cursor){
        this.cursor=cursor;
    }

    @Override
    public boolean hasNext(){
        return cursor!=null;
    }

    @Override
    public T next(){
        T info = this.cursor.getInfo();//guardo la info del nodo actual

        this.cursor= this.cursor.getNext();//avanzo al nodo siguiente
        
        return info;//retorno la info del nodo que ya no es actual
    }

    public T value(){
        return cursor.getInfo();
    }
}
