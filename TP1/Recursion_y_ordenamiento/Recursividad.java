package Recursion_y_ordenamiento;

import java.util.Stack;

public class Recursividad {
    //EJERCICIO 1
    /*1-La complejidad temporal del algoritmo en el peor caso es O(n), ya que recorre el arreglo una 
      sola vez realizando comparaciones entre elementos consecutivos.

    2-Sí, el uso de recursividad puede traer un problema. Cada llamada recursiva consume espacio en
      la pila de ejecución, por lo que en el peor caso se generan n llamadas, resultando en un uso 
      de memoria O(n). Esto puede provocar un desbordamiento de pila (Stack Overflow) si el arreglo 
      es muy grande. 
    3-Si la estructura fuera una lista en lugar de un arreglo, cambiaría la forma de acceso a los 
      elementos. En un arreglo el acceso es directo mediante índices (acceso O(1)), mientras que en
      una lista enlazada se debe recorrer nodo por nodo para acceder al siguiente elemento.

      Sin embargo, si la lista es simplemente enlazada y se recorre secuencialmente comparando 
      cada nodo con su siguiente, la complejidad temporal sigue siendo O(n).    
      La diferencia principal está en que:
        En listas no se utilizan índices, sino referencias (nodos).
        El recorrido es necesariamente secuencial.*/
        
    public boolean ArregloEstaOrdenado(int[] arr, int ini) {
        if (ini >= arr.length - 1) {//si ini es mayor= que el largo->true
            return true;
        }
        if (arr[ini] > arr[ini + 1]) {
            return false;
        }
        return ArregloEstaOrdenado(arr, ini + 1);
    }

    
    public int buscar(int[]arr,int buscado,int ini,int fin){
        if(ini>fin){
            return -1;
        }
        int medio=(ini+fin)/2;
        if(arr[medio]==buscado)
            return medio;
        
        if(arr[medio]<buscado)
            return buscar(arr, buscado, ini, medio-1);
        else{
            return buscar(arr, buscado, medio+1, fin);
        }
    }

    public String convertirBinario(int numero) {
        if (numero == 0) {
            return "";
        }
        return convertirBinario(numero / 2) + (numero % 2);
    }
    public int fibonacci(int n){
        if(n == 0) return 0;
        if(n == 1) return 1;

        return fibonacci(n-1) + fibonacci(n-2);
    }

    public String secuenciaFibonacci(int N, int i){
        if(i == N){
            return "";
        }

        return fibonacci(i) + "-" + secuenciaFibonacci(N, i+1);
    }

    public boolean existeIndiceIgual(int[] A, int ini, int fin){

        if(ini > fin){
            return false;
        }

        int medio = (ini + fin) / 2;

        if(A[medio] == medio){
            return true;
        }

        if(A[medio] > medio){
            return existeIndiceIgual(A, ini, medio - 1);
        } else {
            return existeIndiceIgual(A, medio + 1, fin);
        }
    }

    public static void main(String[] args) {
        Recursividad r = new Recursividad();
        int[]arr ={-3, -1, 0, 2, 4, 6, 10};
        System.out.println(r.existeIndiceIgual(arr,0,arr.length));

        /*
        ejercicio 4
        int N=6;
        System.out.println(r.secuenciaFibonacci(N,0));
          */
        /* 
        ejercicio 3
        int numero=26;
        System.out.println(r.convertirBinario(numero));
        */
        /* 
        ejercicio 2
        int[] arregloOrdenado = {10, 20, 33, 44, 55};
        int buscado=33;

        System.out.println(r.buscar(arregloOrdenado, buscado, 0, arregloOrdenado.length - 1));
        */

        /* ejercicio 1
        int[] arreglo1 = {1, 2, 3, 4, 5};
        int[] arreglo2 = {1, 2, 2, 4, 1};

        System.out.println("Arreglo 1 ordenado: " + r.ArregloEstaOrdenado(arreglo1, 0));
        System.out.println("Arreglo 2 ordenado: " + r.ArregloEstaOrdenado(arreglo2, 0));*/
    }
        
    
    
}