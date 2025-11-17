/*
 * ITESS - TICS 2025 ESTRUCTURA Y ORGANIZACIÓN DE DATOS
 * Periodo: Agosto - Diciembre 2025
 * Docente: Francisco Montecillo Puente 
 * Estudiante / Programador: Angel Flores
 * 
 * E-mail: miguelangelflto6@gmail.com
 * E-mail institucional: ti24110044@itess.edu.mx
 * 
 * Fecha: 17 - Noviembre - 2025
 */

package diagramasdered;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LinkedListGraphTest {
    
    public static LinkedListGraph<String, Integer> factoryGraph(String[][] edges, boolean isDirected) {
        
        LinkedListGraph<String, Integer> G = new LinkedListGraph<>(isDirected);
        
        Set<String> vert = new HashSet<>();
        for(String[] v : edges){
            vert.add(v[0]);
            vert.add(v[1]);
        }
        
        Map<String, Vertex<String>> vertices = new HashMap<>();
        
        for(String v : vert)
            vertices.put(v, G.addVertex(v));
        
        for(String[] edge : edges) {
            Integer weight = edges.length == 2 ? 1 : Integer.valueOf(edge[2]);
            G.addEdge(vertices.get(edge[0]), vertices.get(edge[1]), weight);
        }
        
        return G;
    }
    
    public static void graph1() {
        String[][] edges = {{"1","2","4"},
                         {"1","3","3"},
                         {"2","5","2"},
                         {"2","4","3"},
                         {"3","5","3"},
                         {"4","6","2"},
                         {"5","6","2"}};
        
        LinkedListGraph<String, Integer> G = factoryGraph(edges,true);
        System.out.println(G.toString());
    }
    
    public static void graph2() {
        String[][] edges = {{"1","2","7"},
                        {"1","3","12"},
                        {"1","4","21"},
                        {"1","5","31"},
                        {"1","6","44"},
                        {"2","3","7"},
                        {"2","4","12"},
                        {"2","5","21"},
                        {"2","6","31"},
                        {"3","4","7"},
                        {"3","5","12"},
                        {"3","6","21"},
                        {"4","5","7"},
                        {"4","6","12"},
                        {"5","6","7"}};
        
        LinkedListGraph<String, Integer> G = factoryGraph(edges,true);
        System.out.println(G.toString());
    }
    
    public static void graph3() {
        String[][] edges = {{"O","A","2"},
                         {"O","B","5"},
                         {"O","C","4"},
                         {"A","B","2"},
                         {"A","D","7"},
                         {"B","C","1"},
                         {"B","D","4"},
                         {"B","E","3"},
                         {"C","E","4"},
                         {"D","T","5"},
                         {"D","E","1"},
                         {"E","T","7"}};
        
        LinkedListGraph<String, Integer> G = factoryGraph(edges,false);
        System.out.println(G.toString());
    }
    
    public static void main(String...args) {
        //graph1();
        //graph2();
        graph3();
    }
        
}
