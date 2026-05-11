public class Main {
    public static void main(String[] args) {

        Tree arbol = new Tree();

        // Insertamos algunos valores
        arbol.add(50);
        arbol.add(30);
        arbol.add(70);
        arbol.add(20);
        arbol.add(40);
        arbol.add(60);
        arbol.add(80);

        // Imprimir recorridos
        arbol.printPreOrder();   // Raíz - Izq - Der
        arbol.printInOrder();    // Izq - Raíz - Der
        arbol.printPosOrder();   // Izq - Der - Raíz
    }
}