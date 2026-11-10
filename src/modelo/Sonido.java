package modelo;

public class Sonido extends Producto {
    private boolean original;
    private boolean microfono;
    private boolean bateria;
    private int potencia;
    private tipoSonido tipo;
    private enum tipoSonido {CABINA, BAFLE, PARLANTE, AUDIFONO, RADIO};
    
    /*
    Se añade una tercera lista con caracteristicas especiales de la clase Sonido
    
    param3[]={original,microfono,bateria}
    Se añade un elemento nuevo a `param1`

    param2[]={costoCompra,gananciaMinima,stock,stockMinimo,potencia}         Strings
    */

    public Sonido(String[] param1 , int[] param2, boolean[] param3, tipoSonido tipo){
        super(param1,param2);
        this.potencia=param2[4];
        this.original=param3[0];
        this.microfono=param3[1];
        this.bateria=param3[2];
        this.tipo=tipo;
        this.garantia=setGarantiaDefecto(tipo);
    }

    private int setGarantiaDefecto(tipoSonido tipo){
        switch (tipo) {
            case CABINA:
            case BAFLE:
                return 6;
            case PARLANTE:
                return 3;
            case AUDIFONO:
                return 2;
            case RADIO:
                return 1;
            default:
                return 0;
        }
    }
    private String tipoSonidoTexto(tipoSonido tipo){//Es solo para que la primera quede en mayuscula y el retso el minuscula
        String texto= tipo.name().charAt(0)+tipo.name().substring(1).toLowerCase();
        return texto;
    }
    public String detalle(){
        String detalle = tipoSonidoTexto(tipo)+" " +nombre+ "\n"+ "Potencia: "+potencia+
        "Original: "+ (original?"Si":"No")+"\n"+"Microfono: "+ (microfono?"Si":"No")+"\n"+
        "Bateria: "+ (bateria?"Si":"No")+"\n";

        return detalle;
    }
}
 