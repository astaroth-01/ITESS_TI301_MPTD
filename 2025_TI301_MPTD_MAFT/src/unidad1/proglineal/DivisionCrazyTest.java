/*
 * ITESS - TICS 2025 ESTRUCTURA Y ORGANIZACIÓN DE DATOS
 * Periodo: Agosto - Diciembre 2025
 * Docente: Francisco Montecillo Puente 
 * Estudiante / Programador: Angel Flores
 * Descripción: Prueba de DivisionCrazy
 *              i. e. [[1, 1],[2, 0],[0, -6], [1, 8], [-3, -4]]; R: 4
 *              i. e. [[-1, 1],[1, -1]]; R: -1
 * E-mail: miguelangelflto6@gmail.com
 * E-mail institucional: ti24110044@itess.edu.mx
 * Fecha: 06 - 10 - 2025
 */

package unidad1.proglineal;

import java.util.Arrays;

public class DivisionCrazyTest {
    public static void main(String[] args) {
        double[] columna1 = {1, 2, 0, 1, -3};
        double[] columna2 = {1, 0, -6, 8, -4};
        
        DivisionCrazy dv = new DivisionCrazy(columna1, columna2);
        
        // Test resolver()
        System.out.println("resolver(): " + dv.resolver());
        System.out.println("Divisiones: " + Arrays.toString(dv.div));
    }
}
