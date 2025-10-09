/*
 * ITESS - TICS 2025 ESTRUCTURA Y ORGANIZACIÓN DE DATOS
 * Periodo: Agosto - Diciembre 2025
 * Docente: Francisco Montecillo Puente 
 * Estudiante / Programador: Angel Flores
 * Descripción: Dada una matriz A de números que cumple con los criterios de un
 *              PL de maximización, con restricciones <=, y acotada. Aplique el 
 *              método simplex.
 * E-mail: miguelangelflto6@gmail.com
 * E-mail institucional: ti24110044@itess.edu.mx
 * Fecha: 07 - 10 - 2025
 */

package unidad1.proglineal;

public class Simplex {
    private double[][] pl;
    
    public Simplex(double[][]pl) {
        this.pl = pl;
    }
    public void resolver() {
        NumeroOpt pivotColumn;
    double[] column1 = new double[pl.length];
    double[] columnRHS = new double[pl.length]; // Renombrar para claridad
    
    int pivotColIndex;
    
    while(true) {
        pivotColumn = new NumeroOpt(pl[0]);
        pivotColIndex = pivotColumn.resolver();
        
        if(pivotColIndex == -1) break; // Condición de parada
        
        // Extraer columnas 
        for(int i = 0; i < pl.length; i++) {
            column1[i] = pl[i][pivotColIndex];
            columnRHS[i] = pl[i][pl[0].length - 1];
        }
        
        DivisionCrazy pivotRow = new DivisionCrazy(column1, columnRHS);
        int r = pivotRow.resolver();
        
        if(r == -1) break;

            GaussR m = new GaussR(r, pivotColIndex, pl);
            m.pivote();
            System.out.println("Iteración completada:");
            this.print();
            System.out.println();
        }
    }
    
    public void print() {
        for(double[] r : pl) {
            for(double c : r)
                System.out.printf("%15.4f", c);
            System.out.println();
        }
    }
}
