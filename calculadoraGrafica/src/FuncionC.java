/**
 * Write a description of class principal here.
 * 
 * @author (Javier Gutierrez) 
 * @version (27/1/2014)
 */
public class FuncionC extends Trapecio{
    
    double L, K, M;
    /**
     * Constructor de objetos de la clase Funcion
     */
    public FuncionC(double l, double k, double m){
        // initialise instance variables
        L=l;
        K=k;
        M=m;
    }
    public double f(double x){
        double y=(1/x);
        return 1/(x*Math.sqrt(L+K*y*y+M*y*y*y));
        //return 1/(x*Math.sqrt(L+K*(1/x)*(1/x)+M*(1/x)*(1/x)*(1/x)));
    }
}