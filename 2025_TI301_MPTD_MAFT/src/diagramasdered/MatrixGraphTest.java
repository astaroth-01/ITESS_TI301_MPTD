/*
 * ITESS - TICS 2025 MATEMATICAS PARA LA TOMA DE DECISIONES
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

public class MatrixGraphTest {
    
    private static MatrixGraph factoryGraph(int numOfVertices, int[][] edges, boolean isDirected) {
        MatrixGraph G = new MatrixGraph(numOfVertices, isDirected);
        
        for (int[] edge : edges) {
            if (edge.length > 3 || edge.length < 2) {
                throw new IllegalArgumentException("Arista inválida");
            }
        }
        for (int[] edge : edges) {
            for (int j = 0; j < edge.length; j++) {
                if(edge.length == 3)
                    G.addEdge(edge[0], edge[1], edge[2]);
                else
                    G.addEdge(edge[0], edge[1], -1);
            } 
        }
        
        return G;
    }

    public static void graph1() {
        int numOfVertices = 6;
        int[][] edges = {{1,2,4},
                         {1,3,3},
                         {2,5,2},
                         {2,4,3},
                         {3,5,3},
                         {4,6,2},
                         {5,6,2}};
        
        MatrixGraph G = factoryGraph(6, edges, true);
        System.out.println(G.toString());
    }
    
    public static void graph2() {
        int numOfVertices = 6;
        int[][] edges ={{1,2,7},
                        {1,3,12},
                        {1,4,21},
                        {1,5,31},
                        {1,6,44},
                        {2,3,7},
                        {2,4,12},
                        {2,5,21},
                        {2,6,31},
                        {3,4,7},
                        {3,5,12},
                        {3,6,21},
                        {4,5,7},
                        {4,6,12},
                        {5,6,7}};
        
        MatrixGraph G = factoryGraph(numOfVertices, edges, true);
        System.out.println(G.toString());
    }
    
    public static void graph3() {
        int numOfVertices = 7;
        int[][] edges = {{1,2,2},
                         {1,3,5},
                         {1,4,4},
                         {2,3,2},
                         {2,5,7},
                         {3,4,1},
                         {3,5,4},
                         {3,6,3},
                         {4,6,4},
                         {5,7,5},
                         {5,6,1},
                         {6,7,7}};
        
        MatrixGraph G = factoryGraph(numOfVertices, edges, false);
        System.out.println(G.toString());
    } 
    
    public static void main(String...args) {
        //graph1();
//        graph2();
        graph3();
    }
}
