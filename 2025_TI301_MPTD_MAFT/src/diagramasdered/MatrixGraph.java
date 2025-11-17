/*
 * ITESS - TICS 2025 MATEMATICAS PARA LA TOMA DE DECISIONES
 * Periodo: Agosto - Diciembre 2025
 * Docente: Francisco Montecillo Puente 
 * Estudiante / Programador: Angel Flores
 * Representación de un grafo matricialmente
 * E-mail: miguelangelflto6@gmail.com
 * E-mail institucional: ti24110044@itess.edu.mx
 * 
 * Fecha: 17 - Noviembre - 2025
 */

package diagramasdered;

import java.util.ArrayList;

public class MatrixGraph implements MatrixGraphInterface{
    private int numOfVertices;
    private ArrayList<int[]> edges;
    private boolean isDirected;

    public MatrixGraph(int numOfVertices, boolean isDirected) {
        this.numOfVertices = numOfVertices;
        this.edges = new ArrayList<>();
        this.isDirected = isDirected;
    }
    
    @Override
    public void addVertex(int n) {
        this.numOfVertices += n;
    }

    @Override
    public void addEdge(int v, int u, int w) {
        if(v > numOfVertices )
            throw new IllegalArgumentException("Vertice v inexistente");
        if(u > numOfVertices)
            throw new IllegalArgumentException("Vertice u inexistente");
        
        edges.add(new int[] {v, u, w});
    }
    
    public int[][] getAdjacencyMatrix() {
        int[][] matrix = new int[numOfVertices][numOfVertices];
        
        for(int[] edge : edges) {
            int from = edge[0] - 1;
            int to = edge[1] - 1;
            int weigth = edge[2];
            
            matrix[from][to] = weigth;
            if(!isDirected)
                matrix[to][from] = weigth;
        }
            
        return matrix;
    }
    
    @Override
    public String toString() {
        StringBuilder graph = new StringBuilder();
        int[][] matrix = getAdjacencyMatrix();
        
        System.out.printf("%4s", "");
        for (int i = 0; i < numOfVertices; i++)
            System.out.printf("%-4d", i+1);
        System.out.println();
        
        for (int i = 0; i < numOfVertices; i++) {
        System.out.printf("%-2d| ", i+1);
        for (int j = 0; j < numOfVertices; j++) {
            System.out.printf("%-4d", matrix[i][j]);
        }
        System.out.println();
    }
        
        return String.valueOf(graph);
    }
}
