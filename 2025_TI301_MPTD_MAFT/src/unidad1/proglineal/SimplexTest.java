/*
 * ITESS - TICS 2025 ESTRUCTURA Y ORGANIZACIÓN DE DATOS
 * Periodo: Agosto - Diciembre 2025
 * Docente: Francisco Montecillo Puente 
 * Estudiante / Programador: Angel Flores
 * Descripción: Prueba de la clase simplex
 * E-mail: miguelangelflto6@gmail.com
 * E-mail institucional: ti24110044@itess.edu.mx
 * Fecha: 07 - 10 - 2025
 */

package unidad1.proglineal;

public class SimplexTest {
    public static void main(String...args) {
        double[][] pl = {{1,-4,-3,0,0,0},
                         {0,1,1,1,0,40},
                         {0,2,1,0,1,60}};
        
        Simplex PlSolve = new Simplex(pl);
        // resolver()
        PlSolve.resolver();
    }
}
