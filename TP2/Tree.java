import org.w3c.dom.Node;
import java.util.ArrayList;
import java.util.List;
public class Tree {

    private TreeNode raiz;

    public Tree() {
    this.raiz = null;
    }

    public void add(int valor) {
        if (this.raiz == null)
            this.raiz = new TreeNode(valor);
        else
            this.add(this.raiz,valor);
    }
     public void add(TreeNode nodo, int valor) {
        if (nodo.getValor() > valor) {

            if (nodo.getIzq() == null) {
                TreeNode temp = new TreeNode(valor);
                nodo.setIzq(temp);
            } 
            else {
                add(nodo.getIzq(),valor);
            }
        } else if (nodo.getValor() < valor) {

            if (nodo.getDer() == null) {
                TreeNode temp = new TreeNode(valor);
                nodo.setDer(temp);
            } 
            else {
                add(nodo.getDer(),valor);
            }
        }
    }
    public void delete(int elemento){
        raiz=eliminarNodo(raiz,elemento);
    }

    private TreeNode eliminarNodo(TreeNode actual, int valor) {
        if (actual == null) {
            return null;
        }
            if (valor < actual.getValor()) {
                actual.setIzq(eliminarNodo(actual.getIzq(), valor));
            } 
            else if (valor > actual.getValor()) {
                actual.setDer(eliminarNodo(actual.getDer(), valor));
            } 
        else {
            // 🔥 Encontramos el nodo a eliminar

            // Caso 1: no tiene hijo izquierdo
            if (actual.getIzq() == null) {
                return actual.getDer();
            } 
            // Caso 2: no tiene hijo derecho
            else if (actual.getDer() == null) {
                return actual.getIzq();
            }
            // Caso 3: tiene ambos hijos
            int valorMasIzq = obtenerMenor(actual.getDer());
            actual.setValor(valorMasIzq);
            actual.setDer(eliminarNodo(actual.getDer(), valorMasIzq));
        }
        return actual;
    }
    private int obtenerMenor(TreeNode nodo) {
        while (nodo.getIzq() != null) {
            nodo = nodo.getIzq();
        }
        return nodo.getValor();
    }
    public TreeNode getRoot(){//getRaiz
        return raiz;
    }
    public boolean isEmpty(){
        return this.raiz==null;
    }

    public boolean hasElem(Integer value) {
		return this.hasElem(this.raiz, value);
	}

    public boolean hasElem(TreeNode nodo, int elemento){
        if(this.isEmpty())
            return false;

        if(nodo == null){
            return false;
        }
        if(nodo.getValor() == elemento){
            return true;
        } else if(elemento < nodo.getValor()){
            return hasElem(nodo.getIzq(), elemento);
        } else {
            return hasElem(nodo.getDer(), elemento);
        }
    }
    public int getHeight() {
		return getHeight(raiz);
	}
    private int getHeight(TreeNode nodo) {
		if (nodo == null) {
			return 0;
		}
		int alturaIzq = getHeight(nodo.getIzq());
		int alturaDer = getHeight(nodo.getDer());
		return Math.max(alturaIzq, alturaDer) + 1;
	}

    public void printPosOrder() {//controlar que el arbol no este vacio,sino imprimirlo(raiz)
		if (isEmpty()) {
			System.out.println("Arbol vacío");
		} else {
			System.out.println("\nImpresión Pos Order");
			printPosOrder(this.raiz);
		}
	}
	private void printPosOrder(TreeNode nodo) {
		if (nodo != null) {
			printPosOrder(nodo.getIzq());
			printPosOrder(nodo.getDer());
			System.out.print(" - " + nodo.getValor());
		}
	}
    public void printPreOrder() {
		if (isEmpty()) {
			System.out.println("Arbol vacío");
		} else {
			System.out.println("\nImpresión Pre Order");
			printPreOrder(this.raiz);
		}
	}

	private void printPreOrder(TreeNode node) {
		if (node != null) {
			if (node.hasCharacter()) {
				System.out.print(" - " + node.getValor() + "|" + node.getCharacter());
			} else {
				System.out.print(" - " + node.getValor());
			}
			printPreOrder(node.getIzq());
			printPreOrder(node.getDer());
		}
	}
    public void printInOrder(){
        if (isEmpty()) {
			System.out.println("Arbol vacío");
		} else {
			System.out.println("\nImpresión IN Order");
			printInOrder(this.raiz);
		}
    }
    private void printInOrder(TreeNode node) {
		if (node != null) {
			printInOrder(node.getIzq());
			System.out.print(" - " + node.getValor());
			printInOrder(node.getDer());
		}
	}
    public ArrayList<TreeNode> getRamaMasLarga(){
        ArrayList<TreeNode> ramaActual = new ArrayList<>();
		ArrayList<TreeNode> ramaMasLarga = new ArrayList<>();
		getRamaMasLarga(raiz,ramaActual, ramaMasLarga);
		return ramaMasLarga;
	}

	private void getRamaMasLarga(TreeNode nodo, ArrayList<TreeNode> ramaActual, ArrayList<TreeNode> ramaMasLarga) {
		if (nodo == null) {
			return;
		}
		ramaActual.add(nodo);
		if (nodo.noTieneHijos()) {
			if (ramaActual.size() > ramaMasLarga.size()) {
				ramaMasLarga.clear();
				ramaMasLarga.addAll(ramaActual);
			}
		}
		getRamaMasLarga(nodo.getIzq(), ramaActual, ramaMasLarga);
		getRamaMasLarga(nodo.getDer(), ramaActual, ramaMasLarga);
		ramaActual.remove(ramaActual.size() - 1);
    }
    public ArrayList<TreeNode> getFrontera() {//TRAE LOS NODOS HOJAS
		ArrayList<TreeNode> arrFrontera = new ArrayList<TreeNode>();
		getFrontera(raiz, arrFrontera);
		return arrFrontera;
	}

	private void getFrontera(TreeNode nodo, List<TreeNode> arrFrontera) {
		if (nodo == null) {
			return;
		}
		if (nodo.noTieneHijos()) {
			arrFrontera.add(nodo);
		}
		getFrontera(nodo.getIzq(), arrFrontera);
		getFrontera(nodo.getDer(), arrFrontera);
	}
    public TreeNode getMaxElem() {
		return getMaxElem(raiz);
	}

	private TreeNode getMaxElem(TreeNode nodo) {
		if (nodo == null) {
			return null;
		}
		if (nodo.getDer() == null) {
			return nodo;
		}
		return getMaxElem(nodo.getDer());
	}
    public List<Integer> getElemsEnNivel(int nivel) {
		List<Integer> listaElementos = new ArrayList<Integer>();
		getElemsEnNivel(raiz, 0, nivel, listaElementos);
		return listaElementos;
	}'¿
	private void getElemsEnNivel(TreeNode nodo, int nivelActual, int nivel, List<Integer> listaElementos) {
        if (nodo == null) {
			return;
		}
		if (nivelActual == nivel) {
			listaElementos.add(nodo.getValor());
		}
		nivelActual += 1;
		getElemsEnNivel(nodo.getIzq(), nivelActual, nivel, listaElementos);
		getElemsEnNivel(nodo.getDer(), nivelActual, nivel, listaElementos);
		nivelActual -= 1;
	}

    //Dado un árbol binario de búsquedas que almacena números enteros, 
    // implementar un algoritmo que retorne la suma de todos los nodos internos del árbol
    public int sumaNodosInternos() {
        return sumaNodosInternos(this.raiz);
    }

    private int sumaNodosInternos(TreeNode nodo) {
        if (nodo == null) {
            return 0;
        }

        // Si es hoja → no suma
        if (nodo.getIzq() == null && nodo.getDer() == null) {
            return 0;
        }

        // Es nodo interno → suma su valor
        return nodo.getValor() 
            + sumaNodosInternos(nodo.getIzq()) 
            + sumaNodosInternos(nodo.getDer());
    }

    // Dado un A.B.B que almacena números enteros y un valor de entrada K, 
    // implementar un algoritmo que permita obtener un listado 
    // con los valores de todas las hojas cuyo valor supere K. 
    // Ejemplo: para el árbol de la derecha, con un valor K = 8, el resultado debería ser [9, 11].
    public List<Integer> hojasMayoresA(int k) {
        List<Integer> resultado = new ArrayList<>();
        hojasMayoresA(this.raiz, k, resultado);
        return resultado;
    }

    private void hojasMayoresA(TreeNode nodo, int k, List<Integer> lista) {
        if (nodo == null) {
            return;
        }

        // Si es hoja
        if (nodo.getIzq() == null && nodo.getDer() == null) {
            if (nodo.getValor() > k) {
                lista.add(nodo.getValor());
            }
            return; // importante: ya no seguimos bajando
        }

        hojasMayoresA(nodo.getIzq(), k, lista);
        hojasMayoresA(nodo.getDer(), k, lista);
    }
}   