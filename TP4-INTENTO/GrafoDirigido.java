

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class GrafoDirigido<T> implements Grafo<T> {
    private HashMap<Integer, LinkedList<Arco<T>>> vertices;
	@Override
	public void agregarVertice(int verticeId) {
		if(vertices.containsKey(verticeId)){
            System.out.println("el vertice ya existe");
        }
        else{
            vertices.put(verticeId,new LinkedList<Arco<T>>());//AGREGAR UN VERTICE AL GRAFO, SIN ARCOS
        }
	}
	@Override
	public void borrarVertice(int verticeId) {

		if(!vertices.containsKey(verticeId)){
            System.out.println("el vertice no existe");
        }else{
            
            for( Integer vecino: vertices.keySet()){
                Iterator<Arco<T>> it = vertices.get(vecino).iterator();
                
                while (it.hasNext()){
                    Arco<T> arco=it.next();
                    if(arco.getVerticeDestino()==verticeId){
                        it.remove();
                    }
                }
            }
            vertices.remove(verticeId);
        }
	}

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		// TODO Auto-generated method stub
	}

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean contieneVertice(int verticeId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int cantidadVertices() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cantidadArcos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<Integer> obtenerVertices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		// TODO Auto-generated method stub
		return null;
	}

}