import fundamentos.*;

/**
 * Write a description of class distancia here.
 * 
 * @author (Javier Gutierrez) 
 * @version (27/1/2014)
 */
public class distancia
{
    public static void main(String args[]){
    
    /**
     * Declaracion de constantes
     */
    final double c=299792.458; //velocidad de la luz km/s
    final double sGyr=3.1536E16;//segundos por cada Gyr
    final double kmMp=3.08568E19;//km por Mpc
    
    /**
     * Declaracion de objetos y variables que le pasamos al programa
     */
    Lectura pantalla = new Lectura("Distancias y edad actual del universo");
    double z;
    double omegaM,omegaLambda;
    double hCero;
    
    /**
     * Preparacion de pantalla
     */
    pantalla.creaEntrada("Corrimiento al rojo",3);
    pantalla.creaEntrada("Densidad total de materia",0.24);
    pantalla.creaEntrada("Densidad de energia oscura",0.76);
    pantalla.creaEntrada("Parametro de Hubble en (km/s)/Mpc",71);
    
    /**
     * Lectura de datos por teclado
     */
    pantalla.espera("Introduce los valores y pulsa aceptar");
    z=pantalla.leeDouble("Corrimiento al rojo");
    omegaM=pantalla.leeDouble("Densidad total de materia");
    omegaLambda=pantalla.leeDouble("Densidad de energia oscura");
    hCero=pantalla.leeDouble("Parametro de Hubble en (km/s)/Mpc");
    
    
    double omegaK=1-omegaM-omegaLambda;//calculo de sigmaK
    double a=1/(1+z);//limite de integracion inferior
    
    double S1=new FuncionA(omegaLambda,omegaK,omegaM).integral(a,1,1000);
   
    double dp;//distancia propia
    if(omegaK<0){//k=1
        dp=c*(1/(hCero*Math.sqrt(-omegaK)))*Math.sin(Math.sqrt(-omegaK)*S1);
    }else if(omegaK>0){//k=-1
         dp=c*(1/(hCero*Math.sqrt(omegaK)))*Math.sinh(Math.sqrt(omegaK)*S1);
    }else{//k=0
         dp=c*(1/hCero)*S1;
    }
    
    double da=dp*a;//distancia angular
    double dl=dp*(1+z);//distancia luminosidad
    
    /**
     * Calculo de la edad del universo
     * 
     * Se obtiene la integral SC de la funcion definida en la clase FuncionC
     */
   
    double SC=new FuncionC(omegaLambda,omegaK,omegaM).integral(0.0001,1,1000);//No se puede poner 0 como inicio
    double edad=(kmMp/(hCero*sGyr))*SC;
    
    /**
     * Muestra resultados 
     */
    pantalla.println("Distancia propia: "+dp+" Mpc");
    pantalla.println("Distancia angular: "+da+" Mpc");
    pantalla.println("Distancia luminosidad: "+dl+" Mpc");
    pantalla.println("Edad actual del universo es de "+edad+" Gyr");

    }
}