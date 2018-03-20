
import fundamentos.*;

/**
 * Write a description of class distancia here.
 * 
 * @author (Javier Gutierrez) 
 * @version (24/1/2014)
 */
public class graficas
{
    public static void main(String args[]){
    
    /**
     * Declaracion de constantes
     */
    final double c=299792.458; //velocidad de la luz km/s
    final double sGyr=3.1536E16;//31536000 segundos por cada Gyr
    final double kmMp=3.08568E19;//km por Mpc
    
    /**
     * Declaracion de objetos y variables que le pasamos al programa
     */
    Lectura pantalla = new Lectura("Parametros cosmologicos");
    double omegaM,omegaLambda;
    double hCero;
    
    /**
     * Preparacion de pantalla
     */
    pantalla.creaEntrada("Densidad total de materia",0.24);
    pantalla.creaEntrada("Densidad de energia oscura",0.76);
    pantalla.creaEntrada("Parametro de Hubble en (km/s)/Mpc",71);
    
    /**
     * Lectura de datos por teclado
     */
    pantalla.espera("Introduce los valores y pulsa aceptar");
    omegaM=pantalla.leeDouble("Densidad total de materia");
    omegaLambda=pantalla.leeDouble("Densidad de energia oscura");
    hCero=pantalla.leeDouble("Parametro de Hubble en (km/s)/Mpc");
    
    double omegaK=1-omegaM-omegaLambda;//calculo de sigmak
    double z;//declaramos la variable z
    
    /**
     * Dibuja la grafica de distancia propia, de diametro angular y de luminosidad en funcion de z
     * en una misma gráfica
     */
    Grafica g=new Grafica("Distancia propia, de diametro angular y de luminosidad en funcion de z","z","d(z)/Mpc");

    g.ponTitulo("Distancia propia (rojo), distancia diametro angular (azul) , distancia luminosidad (negro)");
    g.ponColor(Grafica.rojo);
    
    double dp;//distancia propia
    for(double i=0;i<=15;i=i+0.1){
        double a=1/(1+i);
        double S1=new FuncionA(omegaLambda,omegaK,omegaM).integral(a,1,100);
        if(omegaK<0){//k=1
            dp=c*(1/(hCero*Math.sqrt(-omegaK)))*Math.sin(Math.sqrt(-omegaK)*S1);
        }else if(omegaK>0){//k=-1
             dp=c*(1/(hCero*Math.sqrt(omegaK)))*Math.sinh(Math.sqrt(omegaK)*S1);
        }else{//k=0
             dp=c*(1/hCero)*S1;
        }
      g.inserta(i,dp);
    }
    g.otraGrafica();
    g.ponColor(Grafica.azul);
    
    double da;//distancia angular
    for(double i=0;i<=15;i=i+0.1){
        double a=1/(1+i);
        double S1=new FuncionA(omegaLambda,omegaK,omegaM).integral(a,1,100);
        if(omegaK<0){//k=1
            da=c*(1/(hCero*Math.sqrt(-omegaK)))*Math.sin(Math.sqrt(-omegaK)*S1)*a;
        }else if(omegaK>0){//k=-1
             da=c*(1/(hCero*Math.sqrt(omegaK)))*Math.sinh(Math.sqrt(omegaK)*S1)*a;
        }else{//k=0
             da=c*(1/hCero)*S1*a;
        }
      g.inserta(i,da); 
    }
    g.otraGrafica();
    g.ponColor(Grafica.negro);
    
    double dl;//distancia luminosidad
    for(double i=0;i<=15;i=i+0.1){
        double a=1/(1+i);
        double S1=new FuncionA(omegaLambda,omegaK,omegaM).integral(a,1,100);
        if(omegaK<0){//k=1
            dl=c*(1/(hCero*Math.sqrt(-omegaK)))*Math.sin(Math.sqrt(-omegaK)*S1)*(1+i);
        }else if(omegaK>0){//k=-1
             dl=c*(1/(hCero*Math.sqrt(omegaK)))*Math.sinh(Math.sqrt(omegaK)*S1)*(1+i);
        }else{//k=0
             dl=c*(1/hCero)*S1*(1+i);
        }
      g.inserta(i,dl);
    }
    g.pinta();
    
    /**
     * Edad del universo en funcion del corrimiento al rojo
     */
    Grafica h=new Grafica("Edad del universo en funcion del corrimiento al rojo","z","t(z)/Gyr");
    h.ponColor(Grafica.rojo);
    h.ponTitulo("Edad del universo en funcion del corrimiento al rojo");
    
    for(double i=0;i<=15;i=i+0.1){
        double b=1/(1+i);
        double SC=new FuncionC(omegaLambda,omegaK,omegaM).integral(0.0001,b,1000);
        double edad=(kmMp/(hCero*sGyr))*SC;
      h.inserta(i,edad);
    }
    h.pinta();
   
   /**
    * Factor de escala en funcion de la edad del universo
    */
         
   Grafica p=new Grafica("Factor de escala","t/Gyr","R(t)");
   p.ponColor(Grafica.rojo);
   p.ponTitulo("Factor de escala en funcion de la edad del universo");
    
   for(double i=0;i<=15;i=i+0.1){
       double b=2/(1+i);//con el 2 representamos el factor de escala hasta t=2xedad del universo
       double SC=new FuncionC(omegaLambda,omegaK,omegaM).integral(0.0001,b,1000);
       double edad=(kmMp/(hCero*sGyr))*SC;
     p.inserta(edad,b);
   }
   p.pinta();
 }
}