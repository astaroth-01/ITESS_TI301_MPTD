/*
 * ITESS - TICS 2025 ESTRUCTURA Y ORGANIZACIÓN DE DATOS
 * Periodo: Agosto - Diciembre 2025
 * Docente: Francisco Montecillo Puente 
 * Estudiante / Programador: Angel Flores
 * Descripción: Dada una matriz A de números y dos números (R y C, índice de 
 *              renglón e índice de columna pivote), dividir el renglón en la
 *              posición R (0-indexing) de la matriz A por el numero en el 
 *              renglón R y columna C (siempre diferente de cero). Luego usando
 *              este nuevo renglón R, haga operaciones renglón de forma que 
 *              todos otros renglones en la comuna C sean cero
 * E-mail: miguelangelflto6@gmail.com
 * E-mail institucional: ti24110044@itess.edu.mx
 * Fecha: 07 - 10 - 2025
 */

package unidad1.proglineal;

public class GaussR {
    private int r;
    private int c;
    private double[][] pl;
    
    public GaussR(int r, int c, double[][] pl) {
        this.r = r;
        this.c = c;
        this.pl = pl;
    }
    
    public void pivote(){
        // dividir el renglon r por el elemento pivote (r, c)
        double pivotElement = this.pl[r][c];
        for(int i = 0; i < this.pl[r].length; i++) {
            if(this.pl[r][i] == 0)
                continue;
            
            this.pl[r][i] /= pivotElement;
        }
        
        // Hacer 0 los elementos de la columna pivote
        double outgoingVar;
        for(int i = 0; i < this.pl.length; i++) {
            outgoingVar = pl[i][c];
            for(int j = 0; j < this.pl[i].length; j++) {
                if(i == r)
                    continue;
                this.pl[i][j] -= outgoingVar * this.pl[r][j];
            }
        }
    }
    
    public void print(){
        for(double[] m : this.pl) {
            for(double n : m)
                System.out.printf("%15.4f", n);
            System.out.println("");
        }
    }
}
