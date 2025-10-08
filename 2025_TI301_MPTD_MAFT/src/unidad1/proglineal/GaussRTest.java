/*
 * ITESS - TICS 2025 ESTRUCTURA Y ORGANIZACIÓN DE DATOS
 * Periodo: Agosto - Diciembre 2025
 * Docente: Francisco Montecillo Puente 
 * Estudiante / Programador: Angel Flores
 * Descripción: Test de la clase GaussR
 * E-mail: miguelangelflto6@gmail.com
 * E-mail institucional: ti24110044@itess.edu.mx
 * Fecha: 08 - 10 - 2025
 */

package unidad1.proglineal;

public class GaussRTest {
    public static void main(String...args) {
        int r = 3;
        int c = 1;
        double[][] pl = {{1, -60, -30, -20, 0, 0, 0, 0, 0},
                         {0, 8, 6, 1, 1, 0, 0, 0, 48},
                         {0, 4, 2, 1.5, 0, 1, 0, 0, 20},
                         {0, 2, 1.5, 0.5, 0, 0, 1, 0, 8},
                         {0, 0, 1, 0, 0, 0, 0, 1, 5}};
        GaussR m = new GaussR(r, c, pl);
        
        // Test pivote()
        m.pivote();
        
        // Test print()
        m.print();
    }
}
