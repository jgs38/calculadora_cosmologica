/**
 * Write a description of class a here.
 * 
 * @author (Javier Gutierrez) 
 * @version (24/1/2014)
 */

public class FuncionA extends Trapecio{
    
    double L, K, M;
    /**
     * Constructor de objetos de la clase FuncionS1
     */
    public FuncionA(double l, double k, double m){
        // initialise instance variables
        L=l;
        K=k;
        M=m;
    }
    public double f(double x){
        double y=(1/x);
        return 1/(x*x*Math.sqrt(L+K*y*y+M*y*y*y));
    }
}