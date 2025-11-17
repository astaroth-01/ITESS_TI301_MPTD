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

public interface LinkedListGraphInterface<V, E> {
    
    Vertex<V> addVertex(V v);
    void addEdge(Vertex<V> v, Vertex<V> u, E w);
}
