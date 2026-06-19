import java.util.Iterator;
import java.util.Objects;
public class LinkedList <T implements Comparable<T>> implements Iterable<T>{
    private Node<T> first;
    private int size;

    public MySimpleLinkedList(){
        this.first=null;
        this.size=0;
    }
    public MyIterator<T> iterator(){//LinkedList.iterator()
        return new MyIterator<>(this.first);//crea un iterador con el primer nodo de la lista
    }
    public void insertFront(T info){//insertar un nodo al principio solo con su info 

        Node <T> tmp=new Node<>(info,null);//creo el nodo tmp con info, pero sin "next"
        tmp.setNext(this.first);//seteo que el 'next' de tmp sea el 'first' de la lista
        this.first=tmp;//tmp ahora es el nodo first
        this.size+=1;//aumento el tamaño de la lista en 1
    }

    public T extractFront(){//elimino el primero y retorno su info
        T info=this.first.getInfo();
        this.first=this.first.getNext();//pone en first su nodo siguiente
        this.size-=1;
        return info;
    }
    public boolean isEmpty(){
        return this.first==null;
    }
    public int size(){
        return this.size;
    }
    public String ToString(){
        String resultado= " ";
        Node <T> tmp=this.first;
        while(tmp!=null){
            resultado+=tmp.getInfo();
            if(tmp.getNext()!=null){
                result+="->";
            }
            tmp=tmp.getNext();
        }
        return result;
    }
    public T get(int indexBuscado){
       Node <T> tmp=this.first;
       int i=0;
       while (tmp!=null) {
        if(i==indexBuscado){
            return tmp.getInfo();
        }
        tmp=tmp.getNext();
        i++;
       }
       return null;
    }
    public int indexOf(T info){
        Node<T>actual=this.first;
        int contador=0;
        while(actual!=null && contador<this.size()){
            if(actual.getInfo().equals(info)){
                return contador;
            }
            actual=actual.getNext();
            contador++;
        }
        return -1;
    }
    
    //a) Las listas están desordenadas y la lista resultante debe quedar ordenada. 
   public static MySimpleLinkedList<Integer> getElementosComunesListasDesordenadas(MySimpleLinkedList<Integer> lista1,MySimpleLinkedList<Integer> lista2) {

    MySimpleLinkedList<Integer> listaResultante = new MySimpleLinkedList<>();

    MyIterator<Integer> it1 = lista1.iterator();

    while (it1.hasNext()) {
        int info = it1.next();

        MyIterator<Integer> it2 = lista2.iterator();

        while (it2.hasNext()) {
            int info2 = it2.next();

            if (info == info2) {
                listaResultante.insertarOrdenado(info);
                break; // evita duplicados innecesarios
            }
        }
    }
 
    return listaResultante;
}
public void insertarOrdenado(T info) {
        Node<T> nuevo = new Node<T>(info, null);

        if (this.first == null || this.first.getInfo().compareTo(info) >= 0) {
            // Si la lista está vacía o el primero es mayor que el nuevo
            nuevo.setNext(this.first);
            //el siguiente del nuevo es el primero
            this.first = nuevo;
            //el nuevo se convierte en primero 
        } else {
            //si la lista NO está vacia o el primero es menor que el nuevo
            Node<T> actual = this.first;
            Node<T> tmp = null;

            //mientras actual no sea nulo y  la info actual es menor que la del nuevo
            while (actual != null && actual.getInfo().compareTo(info) < 0) {
                tmp = actual;
                actual = actual.getNext();

            }

            nuevo.setNext(actual);
            tmp.setNext(nuevo);
        }

        this.size++; // Incrementar tamaño de la lista
    }
    public static MySimpleLinkedList<Integer> getElementosComunesListasOrdenadas(
    MySimpleLinkedList<Integer> lista1,
    MySimpleLinkedList<Integer> lista2){

        MySimpleLinkedList<Integer> listaResultante = new MySimpleLinkedList<>();
        MyIterator<Integer> iter1 = lista1.iterator();
        MyIterator<Integer> iter2 = lista2.iterator();

        if (!iter1.hasNext() || !iter2.hasNext()) {
            return listaResultante; // Si alguna lista está vacía, el resultado también lo estará.
        }

        Integer info1 = iter1.next();
        Integer info2 = iter2.next();

         while (info1 != null && info2 != null) {
            if (info1.equals(info2)) {
                listaResultante.insertFront(info2);
                info1 = iter1.next();
                info2 = iter2.next();
                
            } else if (info1 < info2) {
                info1 = iter1.hasNext() ? iter1.next() : null;
            } else {
                info2 = iter2.hasNext() ? iter2.next() : null;
            }
        }
       
            return listaResultante; 
        }


/*Ejercicio 6
Escriba una función que dadas dos listas construya otra con los elementos que están en la
primera pero no en la segunda.
*/
    public static MySimpleLinkedList<Integer> getElementosQueNoEstanEnLaLista2(
        MySimpleLinkedList<Integer> lista1,
        MySimpleLinkedList<Integer> lista2){

            MySimpleLinkedList<Integer> listaResultante = new MySimpleLinkedList<>();
             MyIterator<Integer> iter1 = lista1.iterator();

        while (iter1.hasNext()) {
            Integer info1 = iter1.next();
            MyIterator<Integer> iter2 = lista2.iterator();
            boolean noComun = true;


            while (iter2.hasNext()) {
                Integer info2 = iter2.next();
                if (info1.equals(info2)) {
                    noComun = false;
                    break;
                }
            }
            

            // Agregar si es elemento no comun y ya se recorrió toda la lista 2
            if (noComun) {
                listaResultante.insertFront(info1);
            }
        }

        return listaResultante; 
    }
}
