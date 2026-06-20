package TPE;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Servicios {
    
    private Map<String, Camion> camiones;
    private Map<String, Paquete> paquetes; 
    private int estadosGenerados;
    private Solucion mejorSolucion;

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

    /*
      ESTRATEGIA GREEDY:
       Para este algoritmo elegimos la estrategia de ordenar los paquetes de mayor a menor peso. 
       La idea es meter primero los bultos más grandes para asegurarnos de que entren y no nos 
       arruinen el total de "peso no asignado", que es lo que queremos minimizar.
       En cada paso elegimos un paquete y recorremos los camiones; el primero que tenga espacio 
       libre suficiente y cumpla con la heladera (si el paquete lleva comida) se lo queda.
       Es una decisión rápida e irrevocable: paquete que se sube a un camión, no se baja más.
    */
    public Solucion greedy() {
        Solucion S = new Solucion();
        int candidatosConsiderados = 0;
        int pesoTotalNoAsignado = 0;

        // Conjunto de candidatos inicial C
        List<Paquete> candidatos = new ArrayList<>(this.paquetes.values());
        
        // Estructura auxiliar para controlar el espacio de los camiones durante el proceso
        Map<Camion, Integer> capacidadesRestantes = new HashMap<>();
        for (Camion c : this.camiones.values()) {
            capacidadesRestantes.put(c, c.getCapacidadMax());
        }

        // CONTROL PRINCIPAL: while (!C.isEmpty() && !solucion(S))
        while (!candidatos.isEmpty() && !Solucion(S, candidatos)) {
            
            // 1. funcion seleccionar(C) -> determina el mejor candidato actual
            Paquete x = Seleccionar(candidatos);
            
            // C.remove(x) -> Se saca irrevocablemente del conjunto de candidatos
            candidatos.remove(x);
            candidatosConsiderados++;

            // 2. Buscamos si es factible meterlo en algún camión
            boolean asignado = false;
            for (Camion camion : this.camiones.values()) {
                
                // funcion factible(S, x)
                if (Factible(S, x, camion, capacidadesRestantes)) {
                    // S.add(x) -> Se agrega a la solución
                    S.asignarPaquete(camion, x);
                    
                    // Actualizamos el estado del contenedor auxiliar
                    int espacioActual = capacidadesRestantes.get(camion);
                    capacidadesRestantes.put(camion, espacioActual - x.getPeso());
                    
                    asignado = true;
                    break; // Candidato ubicado, rompemos bucle de camiones
                }
            }

            // Si tras evaluar todos los camiones no fue factible ubicarlo, queda afuera
            if (!asignado) {
                pesoTotalNoAsignado += x.getPeso();
            }
        }

        // Seteamos las métricas requeridas en el objeto Solucion
        S.setPesoNoAsignado(pesoTotalNoAsignado);
        S.setMetricaCosto(candidatosConsiderados);

        return S;
    }
    
    private Paquete Seleccionar(List<Paquete> candidatos) {
        Paquete mejorCandidato = candidatos.get(0);
        for (Paquete p : candidatos) {
            if (p.getPeso() > mejorCandidato.getPeso()) {
                mejorCandidato = p;
            }
        }
        return mejorCandidato;
    }

    /*
       RESTRICCIONES IMPLÍCITAS: Capacidad de peso del camión y compatibilidad de refrigeración.
     */
    private boolean Factible(Solucion s, Paquete x, Camion camion, Map<Camion, Integer> capacidadesRestantes) {
        int espacioDisponible = capacidadesRestantes.get(camion);
        
        boolean entraPorPeso = (x.getPeso() <= espacioDisponible);
        boolean cumpleRefrigeracion = !x.contieneAlimentos() || camion.esRefrigerado();

        return entraPorPeso && cumpleRefrigeracion;
    }

    /*
       FUNCIÓN SOLUCIÓN: Determina si los candidatos seleccionados han alcanzado una solución.
       En este problema, el algoritmo frena cuando se acaban los paquetes (C vacío).
       Retorna false para permitir que el bucle continúe explorando todos los elementos de C.
     */
    private boolean Solucion(Solucion s, List<Paquete> candidatos) {
        return false; 
    }

    /*
       ESTRATEGIA BACKTRACKING:
       Armamos un árbol de exploración donde cada nivel es un paquete. 
       Para cada paquete probamos todas las opciones posibles: meterlo en el camión 1, en el 2, etc. 
       Estrategias de PODA:
       1. Poda por espacio/frío: Si el camión no tiene lugar o no es refrigerado, descartamos la rama.
       2. Poda con Greedy: Como corremos Greedy primero, ya sabemos cuál es el "récord" a batir. 
       Si en medio de una combinación el peso que venimos dejando afuera ya empata o supera 
       a ese récord, dejamos de buscar, no va a ser la solución óptima.
    */

    public Solucion backtracking() {
        this.estadosGenerados = 0;
        
        // Solución inicial usando Greedy para maximizar las podas desde el inicio
        Solucion solGreedy = this.greedy();
        this.mejorSolucion = new Solucion();
        this.mejorSolucion.setPesoNoAsignado(solGreedy.getPesoNoAsignado());
        this.mejorSolucion.getAsignacion().putAll(solGreedy.getAsignacion());

        Solucion solucionActual = new Solucion();
        List<Paquete> listaPaquetes = new ArrayList<>(this.paquetes.values());
        List<Camion> listaCamiones = new ArrayList<>(this.camiones.values());

        // Control de capacidades de camiones en la simulación
        Map<Camion, Integer> capacidades = new HashMap<>();
        for (Camion c : listaCamiones) {
            capacidades.put(c, c.getCapacidadMax());
        }

        // Llamada recursiva arrancando en el paquete indexado 0
        ejecutarBacktracking(0, listaPaquetes, listaCamiones, capacidades, solucionActual, 0);

        this.mejorSolucion.setMetricaCosto(this.estadosGenerados);
        return this.mejorSolucion;
    }

    private void ejecutarBacktracking(int idxP, List<Paquete> paquetes, List<Camion> camiones, 
                                      Map<Camion, Integer> capacidades, Solucion solActual, int pesoAfueraActual) {
        
        this.estadosGenerados++;

        // 1. CASO BASE: Si procesamos todos los paquetes, evaluamos si superamos el récord
        if (idxP == paquetes.size()) {
            if (pesoAfueraActual < mejorSolucion.getPesoNoAsignado()) {
                mejorSolucion.setPesoNoAsignado(pesoAfueraActual);
                mejorSolucion.getAsignacion().clear();
                for (Map.Entry<Camion, List<Paquete>> entry : solActual.getAsignacion().entrySet()) {
                    mejorSolucion.getAsignacion().put(entry.getKey(), new ArrayList<>(entry.getValue()));
                }
            }
            return;
        }

        // 2. Poda de Greedy
        if (pesoAfueraActual > mejorSolucion.getPesoNoAsignado()) {
            return;
        }

        Paquete p = paquetes.get(idxP);

        // RAMAS ALTERNATIVAS 1 a N: Intentar meter el paquete en cada camión
        for (Camion c : camiones) {
            int espacio = capacidades.get(c);

            // (Factibilidad de peso y refrigeración)
            if (p.getPeso() <= espacio && (!p.contieneAlimentos() || c.esRefrigerado())) {
                
                solActual.asignarPaquete(c, p);
                capacidades.put(c, espacio - p.getPeso());

                
                ejecutarBacktracking(idxP + 1, paquetes, camiones, capacidades, solActual, pesoAfueraActual);

                solActual.getAsignacion().get(c).remove(p);
                capacidades.put(c, espacio);
            }
        }

        if (pesoAfueraActual + p.getPeso() <= mejorSolucion.getPesoNoAsignado()) {
            ejecutarBacktracking(idxP + 1, paquetes, camiones, capacidades, solActual, pesoAfueraActual + p.getPeso());
        }
    }
}

