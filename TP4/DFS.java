
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


/*
DFS (Depth-First Search)
Recorrido en profundidad
*/

public class DFS {

	private Grafo<?> grafo;
	private HashMap<Integer, String> colores;
	private List<Arco<?>> arcos_recorridos;
	private List<List<Integer>> caminos;

	/**
	 * Constructor de la clase
	 * 
	 * @param g Nuevo grafo para el recorrido.
	 */
	public DFS(Grafo<?> g) {
		this.grafo = g;
		this.colores = new HashMap<Integer, String>();
		this.arcos_recorridos = new ArrayList<Arco<?>>();
	}

	/**
	 * Inicializa el hash con los vertices todos en blanco. Key = vertice, Value un
	 * color. BLANCO = Estado inicial. AMARILLO = Estado intermedio. ROJO = Estado
	 * final.
	 */
	private void inicializarEstructura() {
		for (Iterator<Integer> iterator = grafo.obtenerVertices(); iterator.hasNext();) {
			Integer vertice = (Integer) iterator.next();
			colores.put(vertice, "BLANCO");
		}
	}

	/**
	 * Ejercicio 2. Implemente los recorridos Depth-First-Search y
	 * Breadth-First-Search.
	 * 
	 * Inicializa la estrucutra. Complejidad O(|V|+|A|). Va a pasar una vez por cada
	 * vertice y una vez por cada arco. Recorre todos los vertices que no hayan sido
	 * ni visitados ni tengo un estado final. O sea, que sea BLANCO. Entonces si
	 * inicia el recorrido desde ese v�rtice.
	 * 
	 * @return Lista del recorrido
	 */
	public List<Integer> getRecorrido() {
		this.inicializarEstructura();
		List<Integer> resultado = new ArrayList<Integer>();
		for (Iterator<Integer> iterator = grafo.obtenerVertices(); iterator.hasNext();) {
			Integer vertice = (Integer) iterator.next();
			if (colores.get(vertice).equals("BLANCO")) {
				resultado.addAll(getRecorrido(vertice));
			}
		}
		return resultado;
	}

	/**
	 * Se realiza el recorrido desde un v�rtice. Se coloca el estado parcial
	 * AMARILLO. Se agrega a la soluci�n. Se recorren los v�rtices adyancente y se
	 * llama recursivo con cada vertices adyacente en estado inicial, BLANCO. Cuando
	 * se recorrieron todos los adyacentes de ese vertice (sale del for) el vertice
	 * se marca en NEGRO
	 * 
	 * @param vertice punto de partida
	 * @return lista de camino
	 */
	private List<Integer> getRecorrido(Integer vertice) {
		List<Integer> resultado = new ArrayList<Integer>();
		colores.put(vertice, "AMARILLO");
		resultado.add(vertice);
		for (Iterator<Integer> it = grafo.obtenerAdyacentes(vertice); it.hasNext();) {
			Integer adyacente = (Integer) it.next();
			if (colores.get(adyacente).equals("BLANCO")) {
				resultado.addAll(getRecorrido(adyacente));
			}else if(colores.get(adyacente).equals("AMARILLO")){
				System.out.println("Hay ciclo");
			}
		}
		colores.put(vertice, "NEGRO");
		return resultado;
	}

	/**
	 * Ejercicio 3. Implemente un algoritmo que determine si un grafo dirigido tiene
	 * alg�n ciclo.
	 */
	public boolean tieneCiclos() {
		this.inicializarEstructura();
		for (Iterator<Integer> iterator = grafo.obtenerVertices(); iterator.hasNext();) {
			Integer vertice = (Integer) iterator.next();
			if (colores.get(vertice).equals("BLANCO")) {
				if (tieneCiclos(vertice)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean tieneCiclos(Integer vertice) {
		colores.put(vertice, "AMARILLO");

		for (Iterator<Integer> it = grafo.obtenerAdyacentes(vertice); it.hasNext();) {
			Integer adyacente = (Integer) it.next();
			if (colores.get(adyacente).equals("BLANCO")) {
				if (tieneCiclos(adyacente)) {
					return true;
				}
			}
			if (colores.get(adyacente).equals("AMARILLO")) {
				return true;
			}
		}
<<<<<<< HEAD

		colores.put(vertice, "BLANCO");
=======
		colores.put(vertice, "NEGRO");
>>>>>>> 67a41e00f99d14987d3cbf5a85b6b79400af2b40
		return false;
	}

	public List<List<Integer>> caminosConCiclos() {
		this.inicializarEstructura();
		caminos = new ArrayList<List<Integer>>();
		List<Integer> camino = new ArrayList<Integer>();
		for (Iterator<Integer> iterator = grafo.obtenerVertices(); iterator.hasNext();) {
			Integer vertice = (Integer) iterator.next();
			if (colores.get(vertice).equals("BLANCO")) {
				caminosConCiclos(vertice, camino);
			}
		}
		return caminos;
	}

	private void caminosConCiclos(Integer vertice, List<Integer> camino) {
		camino.add(vertice);
		colores.put(vertice, "AMARILLO");
		for (Iterator<Integer> it = grafo.obtenerAdyacentes(vertice); it.hasNext();) {
			Integer adyacente = (Integer) it.next();
			if (colores.get(adyacente).equals("BLANCO")) {
				caminosConCiclos(adyacente, camino);
			}
			if (colores.get(adyacente).equals("AMARILLO")) {
				camino.add(adyacente);
				caminos.add(new ArrayList<Integer>(camino));
				camino.remove(camino.size() - 1);
			}
		}
		camino.remove(camino.size() - 1);
		colores.put(vertice, "BLANCO");
	}

	/**
	 * Ejercicio 4. Escribir un algoritmo que, dado un grafo dirigido y dos v�rtices
	 * i, j de este grafo, devuelva el camino simple (sin ciclos) de mayor longitud
	 * del v�rtice i al v�rtice j. Puede suponerse que el grafo de entrada es
	 * ac�clico.
	 */
	public List<Integer> getCaminoSimple(Integer i, Integer j) {
		this.inicializarEstructura();
		List<Integer> caminoFinal = new ArrayList<Integer>();
		List<Integer> caminoAux = new ArrayList<Integer>();
		getCaminoSimple(i, j, caminoFinal, caminoAux);
		return caminoFinal;
	}

	private void getCaminoSimple(Integer i, Integer j, List<Integer> caminoFinal, List<Integer> caminoAux) {
		colores.put(i, "AMARILLO");
		caminoAux.add(i);
		if (i == j) {
			if (caminoFinal.size() < caminoAux.size()) {
				caminoFinal.clear();
				caminoFinal.addAll(new ArrayList<Integer>(caminoAux));
			}
		} else {
			for (Iterator<Integer> it = grafo.obtenerAdyacentes(i); it.hasNext();) {
				Integer adyacente = (Integer) it.next();
				if (colores.get(adyacente).equals("BLANCO")) {
					getCaminoSimple(adyacente, j, caminoFinal, caminoAux);
				}
			}
		}
		colores.put(i, "BLANCO");
		caminoAux.remove(caminoAux.size() - 1);
	}

	/* 
	 * Variedad del ejercicio para 4 para que devuelva todos los caminos.
	 * 
	 * @param i v�rtice de inicio
	 * @param j v�rtice final
	 * @return Lista con todos los caminos posibles para llegar de i a j
	 */
	public List<List<Integer>> getCaminosSimpleMasLargo(Integer i, Integer j) {
		this.inicializarEstructura();
		List<List<Integer>> caminosFinal = new ArrayList<List<Integer>>();
		List<Integer> caminoAux = new ArrayList<Integer>();
		getCaminosSimpleMasLargo(i, j, caminosFinal, caminoAux);
		return caminosFinal;
	}

	private void getCaminosSimpleMasLargo(Integer i, Integer j, List<List<Integer>> caminosFinal, List<Integer> caminoAux) {
		colores.put(i, "AMARILLO");
		caminoAux.add(i);
		if (i == j) {
			caminosFinal.add(new ArrayList<Integer>(caminoAux));
		} else {
			for (Iterator<Integer> it = grafo.obtenerAdyacentes(i); it.hasNext();) {
				Integer adyacente = (Integer) it.next();
				if (colores.get(adyacente).equals("BLANCO")) {
					getCaminosSimpleMasLargo(adyacente, j, caminosFinal, caminoAux);
				}
			}
		}
		colores.put(i, "BLANCO");
		caminoAux.remove(caminoAux.size() - 1);
	}

	/**
	 * Ejercicio 5. Escriba un algoritmo que dado un grafo G y un v�rtice v de dicho
	 * grafo, devuelva una lista con todos los v�rtices a partir de los cuales
	 * exista un camino en G que termine en v.
	 */

	public List<Integer> getVerticesQueTerminanEnV(int v) {
    this.inicializarEstructura();
    
    List<Integer> verticesGanadores = new ArrayList<Integer>();
    
    for (Iterator<Integer> it = grafo.obtenerVertices(); it.hasNext();) {
        Integer verticeActual = it.next();
        
        if (!verticeActual.equals(v)) {
            // CORRECCIÓN: Ahora cambiamos el tipo a List<List<Integer>>
            List<List<Integer>> todosLosCaminos = getCaminosSimpleMasLargo(verticeActual, v);
            
            // Si la lista de caminos no está vacía, es porque el vértice puede llegar a v
            if (!todosLosCaminos.isEmpty()) {
                verticesGanadores.add(verticeActual);
            }
        }
    }
    
    return verticesGanadores;
}

<<<<<<< HEAD
	/**
	 * Ejercicio 6. Supongamos una conexion entre computadoras (1, ... ,n) que se
	 * encuentra modelada mediante un grafo.
	 *  Se requiere, si existe, dar una conexion entre dos computadoras a y b existentes sabiendo que la computadora
	 * i esta fuera de servicio.
	 */
	public boolean existeConeccion(int a, int b, int i) {
		this.inicializarEstructura();
		colores.put(i, "NEGRO");
		boolean existe = _existeConeccion(a, b);
		return existe;
=======
	public List<List<Integer>> caminosAlternativos(int buenosAires,int tandil,int lasFlores,int rauch) {
    this.inicializarEstructura();
    List<List<Integer>> caminos = new ArrayList<>();
    List<Integer> caminoActual = new ArrayList<>();

    caminosAlternativos(buenosAires,tandil,lasFlores,rauch,caminoActual,caminos);
    return caminos;

>>>>>>> 67a41e00f99d14987d3cbf5a85b6b79400af2b40
	}
	private void caminosAlternativos(int actual, int destino, int lasFlores,int rauch,List<Integer> caminoActual,List<List<Integer>> caminos) {

    colores.put(actual, "AMARILLO");
    caminoActual.add(actual);

    if (actual == destino) {

        caminos.add(new ArrayList<>(caminoActual));

    } else {

        Iterator<Integer> it = grafo.obtenerAdyacentes(actual);

        while (it.hasNext()) {

            Integer ady = it.next();

            boolean tramoCortado =
                    (actual == lasFlores && ady == rauch)
                    ||
                    (actual == rauch && ady == lasFlores);

            if (!tramoCortado &&
                colores.get(ady).equals("BLANCO")) {

                caminosAlternativos(ady,destino,lasFlores,rauch,caminoActual,caminos);
            }
        }
    }

    colores.put(actual, "BLANCO");
    caminoActual.remove(caminoActual.size() - 1);
}
	
	 
}
