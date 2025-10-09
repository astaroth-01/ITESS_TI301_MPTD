/*
 * ITESS - TICS 2025 ESTRUCTURA Y ORGANIZACIÓN DE DATOS
 * Periodo: Agosto - Diciembre 2025
 * Docente: Francisco Montecillo Puente 
 * Estudiante / Programador: Angel Flores
 * Descripción: Dadas dos columnas de número decimales, relizar la división
 *              elemento a elemento de la segunda columnas entre la primera,
 *              contando a partir del segundo elemento. De los resultados de
 *              las divisiones, encontrar el número positivo más pequeño y 
 *              retornar el indice.
 * E-mail: miguelangelflto6@gmail.com
 * E-mail institucional: ti24110044@itess.edu.mx
 * Fecha: 06 - 10 - 2025
 */

package unidad1.proglineal;
import java.util.Arrays;

public class DivisionCrazy {
    double[] columna1;
    double[] columna2;
    double[] div;
    
    public DivisionCrazy(double[] columna1, double[] columna2) {
        this.columna1 = columna1;
        this.columna2 = columna2;
        div = new double[columna2.length];
    }
    
    public int resolver() {
        // Si los arreglos no contienen datos el programa termina
        if(columna1.length == 0 || columna2.length == 0)
            return -1;
        
        // Recorrer los arreglos columna1 y columna2 y operar
        for(int i = 0; i < columna2.length; i++) {
            if(columna1[i] == 0 || columna2[i] == 0) {
                div[i] = 0;
                continue;
            }
            div[i] = columna2[i] / columna1[i];
        }
        
        //Ordenar
        double[] divSort = Arrays.copyOf(this.div, div.length);
        Arrays.sort(divSort);
        
        // Buscar el indice del elemento más pequeño
        if(divSort[divSort.length - 1] <= 0)
            return -1;
        
        double numPequeño = 0;
        for(int i = 0; i < divSort.length; i++) {
            if(divSort[i] > 0) {
                numPequeño = divSort[i];
                break;
            }
        }
        
        for(int i = 0; i < this.div.length; i++) 
            if(numPequeño == this.div[i])
                return i;
        
        return -1;
    }
}
