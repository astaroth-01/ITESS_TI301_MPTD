/*
 * ITESS - TICS 2025 ESTRUCTURA Y ORGANIZACIÓN DE DATOS
 * Periodo: Agosto - Diciembre 2025
 * Docente: Francisco Montecillo Puente 
 * Estudiante / Programador: Angel Flores
 * Descripción: Algoritmo que resuelve la busqueda del 
 *              número más negativo.
 * E-mail: miguelangelflto6@gmail.com
 * E-mail institucional: ti24110044@itess.edu.mx
 * Fecha: 01 - 10 - 2025
 */

package unidad1.proglineal;

public class NumeroOpt {
    private int[] arreglo;

    public NumeroOpt(int[] arreglo) {
        this.arreglo = arreglo;
    }
    
    public int resolver() {
        // Inicializar el valor minimo al elemento 0
        int minimo = arreglo[0];
        int idx = 0;
        // Recorrer el arreglo
        for( int i = 1; i < arreglo.length; i++ )
            // Comparar con el valor más pequeño e intercambiar posiciones
            if(minimo > arreglo[i]) {
                minimo = arreglo[i];
                idx = i;
            }
        
            // Si el valor es negativo
            if(minimo < 0)
                // Regresarlo
                return minimo;
            
        // Regresar -1
        return -1;
    }
    
    public void print() {
        for( int i = 0; i < this.arreglo.length; i++ )
            System.out.print(arreglo[i] + " ");
    }
}
