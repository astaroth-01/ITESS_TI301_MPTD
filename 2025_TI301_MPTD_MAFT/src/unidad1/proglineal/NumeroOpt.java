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

import java.util.Arrays;

public class NumeroOpt {
    private double[] arreglo;

    public NumeroOpt(double[] arreglo) {
        this.arreglo = arreglo;
    }
    
    public int resolver() {
        double[] arregloCopy = Arrays.copyOf(this.arreglo, this.arreglo.length);
        double minimo;
        
        // Ordenar arreglo
        Arrays.sort(arregloCopy);
        
        // Encontrar el indice del valor más pequeño
        minimo = arregloCopy[0];
        if(minimo < 0)
            for(int i = 0; i < this.arreglo.length; i++) 
                if(arreglo[i] == minimo)
                    return i;
            
        // Regresar -1
        return -1;
    }
    
    public void print() {
        for( int i = 0; i < this.arreglo.length; i++ )
            System.out.print(arreglo[i] + " ");
    }
}
