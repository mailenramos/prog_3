package TPE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Servicios {
    
    private Map<String, Camion>camiones;
    private Map<String, Paquete> paquetes; 

    public Servicios(String pathCamiones, String pathPaquetes) {
        this.camiones = new HashMap<>();
        this.paquetes = new HashMap<>();
        cargarCamiones(pathCamiones);
        cargarPaquetes(pathPaquetes);
    }
    private void cargarCamiones(String path){
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linea = br.readLine(); // Descartar primera línea--el 3

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                Camion c = new Camion(datos[0], datos[1], datos[2].equals("1"), Integer.parseInt(datos[3]));
                this.camiones.put(c.getId(), c);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar camiones: " + e.getMessage());
        }
    }
    
    private void cargarPaquetes(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linea = br.readLine(); 
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                Paquete p = new Paquete(datos[0], datos[1], Integer.parseInt(datos[2]), datos[3].equals("1") , Integer.parseInt(datos[4]) );
                this.paquetes.put(p.getCodId(), p);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar paquetes: " + e.getMessage());
        }
    }
    
    /*
    Servicio 1: Dado un código de paquete (String), retornar toda la información
    del paquete asociado. En caso de no existir, retornar null.
    */
    public Paquete servicio1(String codPaquete) { //Complejidad temporal: 0(1)
        return paquetes.get(codPaquete);
    }
    


    /*
    Servicio 2: Dado un booleano que indica si se buscan paquetes que
    contienen alimentos (true) o que no contienen alimentos (false), retornar el
    listado de paquetes correspondiente.
    */
    public List<Paquete> servicio2(boolean contieneAlimentos) { //Complejidad temporal: O(P), donde P es la cantidad total de paquetes almacenados en el mapa
        List<Paquete> resultado = new ArrayList<>();
        
        for (Paquete p : paquetes.values()) {
            if (p.contieneAlimentos() == contieneAlimentos) {
                resultado.add(p);
            }
        }
            return resultado;   
    }
    


    /* 
    Servicio 3: Dados dos valores enteros que representan un nivel de urgencia
    mínimo y máximo, retornar todos los paquetes cuyo nivel de urgencia se
    encuentre dentro de ese rango (inclusive).
    */
    public List<Paquete> servicio3(int urgenciaMinima, int urgenciaMaxima) { //Complejidad temporal: O(P), donde P es la cantidad total de paquetes almacenados en el mapa
        List<Paquete>resultado = new ArrayList<Paquete>();

        for(Paquete p : paquetes.values()){

            int n = p.getNivelUrgencia();
            
            if(n>=urgenciaMinima && n<=urgenciaMaxima )
                resultado.add(p);

        }
        
        return resultado;
    }
    
}

