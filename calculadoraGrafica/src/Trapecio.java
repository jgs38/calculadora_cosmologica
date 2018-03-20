/**
 * Write a description of class Trapecio here.
 * 
 * @author (Javier Gutierrez) 
 * @version (24/1/14)
 */
public abstract class Trapecio {
    public double integral (double a, double b, int n){
        double h=Math.abs((b-a)/n);
        double suma=(f(a)+f(b))/2;
        for(int i=1; i<n; i++){
            suma+=f(a+i*h);
        }
        return suma*h;
    }
    abstract public double f(double x);
} 
