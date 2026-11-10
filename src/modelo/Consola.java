package modelo;
import java.util.Arrays;

public class Consola extends Producto {
    private String plataforma;
    private boolean original;
    private boolean chipeada;
    private boolean portatil;
    
    
    public Consola(String[] param1 , int[] param2, boolean[] param3){
        super(param1,param2);
        this.plataforma=param1[3];
    }

    public String detalle(){
        String detalle = "Consola" +;
        return detalle;
    }
}
 