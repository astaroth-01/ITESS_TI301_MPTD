/*
 * ITESS - TICS 2025 ESTRUCTURA Y ORGANIZACIÓN DE DATOS
 * Periodo: Agosto - Diciembre 2025
 * Docente: Francisco Montecillo Puente 
 * Estudiante / Programador: Angel Flores
 * Descripción: Prueba de NumerOpt
 *              i. e. [1, -3, -6, 9, 0, 1, -2]; R: -6
 *              i. e. [1, 2, 3, 4]; R: -1
 * E-mail: miguelangelflto6@gmail.com
 * E-mail institucional: ti24110044@itess.edu.mx
 * Fecha: 01 - 10 - 2025
 */

package unidad1.proglineal;

public class NumeroOptTest {
    public static void main(String...args) {
        // Test 1
        System.out.println("Test número 1");
        NumeroOpt numOpt = new NumeroOpt(new int[] {1, -3, -6, 9, 0, 1, -2});
        
        // Test Obj1 resolver()
        System.out.println("resolver(): " + numOpt.resolver());
        
        // Test Obj1 print()
        System.out.print("print(): ");
        numOpt.print();
        
        //Test 2
        System.out.println("\nTest número 2");
        
        numOpt = new NumeroOpt(new int[] {1, 2, 3, 4});
        
        // Test obj2 resolver()
        System.out.println("resolver(): " + numOpt.resolver());
        
        //Test obj2 print()
        System.out.print("print(): ");
        numOpt.print();
    }
}
