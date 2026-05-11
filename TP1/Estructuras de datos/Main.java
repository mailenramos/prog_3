public static void main(String[] args) {

    MySimpleLinkedList<Integer> lista1 = new MySimpleLinkedList<>();
    MySimpleLinkedList<Integer> lista2 = new MySimpleLinkedList<>();

    // Cargar lista1 (ordenada)
    lista1.insertFront(7);
    lista1.insertFront(5);
    lista1.insertFront(3);
    lista1.insertFront(1);
    // Queda: 1, 3, 5, 7

    // Cargar lista2 (ordenada)
    lista2.insertFront(8);
    lista2.insertFront(5);
    lista2.insertFront(4);
    lista2.insertFront(3);
    // Queda: 3, 4, 5, 8

    // Llamar al método
    MySimpleLinkedList<Integer> resultado =
        MySimpleLinkedList.getElementosComunesListasOrdenadas(lista1, lista2);

    // Mostrar resultado
    System.out.println("Elementos en común:");

    MyIterator<Integer> it = resultado.iterator();
    while (it.hasNext()) {
        System.out.println(it.next());
    }
}