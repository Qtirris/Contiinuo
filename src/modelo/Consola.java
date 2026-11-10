package modelo;

public class Consola extends Producto {
    private String plataforma;

    private boolean original;
    private boolean chipeada; //Viene con algun crack/juegos ya instalados o es pirata
    private boolean portatil; 
    
    /*
    Se añade una tercera lista con caracteristicas especiales de la clase Consola
    
    param3[]={original,chipeada,portatil}
    Se añade un elemento nuevo a `param1`

    param1[]={nombre,ubicacion,plataforma}         Strings
    */

    public Consola(String[] param1 , int[] param2, boolean[] param3){
        super(param1,param2);
        this.plataforma=param1[3];
        this.original=param3[0];
        this.chipeada=param3[1];
        this.portatil=param3[2];
        this.garantia=6;
    }

    public String detalle(){
        String detalle = "Consola " +nombre+ "\n"+ "Original: "+ (original?"Si":"No")+"\n"+
        "Portatil: "+ (portatil?"Si":"No")+"\n"+"Chipeada: "+ (chipeada?"Si":"No")+"\n"+
        " Plataforma: "+plataforma;

        return detalle;
    }
}
 