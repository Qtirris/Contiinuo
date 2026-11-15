package modelo;

public class Sonido extends Producto {
    private String marca;
    private boolean original;
    private boolean microfono;
    private boolean bateria; //Si es recargable o es solo con corriente
    private int potencia;
    private tipoSonido tipo;
    public static enum tipoSonido {CABINA, BAFLE, PARLANTE, AUDIFONO, RADIO};
    
    /*
    Se añade una tercera lista con caracteristicas especiales de la clase Sonido
    
    param3[]={original,microfono,bateria}
    Se añade un elemento nuevo a `param2` y `param1`

    param1[]={nombre,ubicacion,marca}
    param2[]={costoCompra,gananciaMinima,stock,stockMinimo,potencia}         Strings
    */

    public Sonido(String[] param1 , int[] param2, boolean[] param3, tipoSonido tipo){
        super(param1,param2);
        this.marca=param1[2];
        this.potencia=param2[4];
        this.original=param3[0];
        this.microfono=param3[1];
        this.bateria=param3[2];
        this.tipo=tipo;
        setGarantiaDefecto(tipo);
    }

    public String getMarca(){return marca;}
    public tipoSonido getTipo(){return tipo;}

    private void setGarantiaDefecto(tipoSonido tipo){
        switch (tipo) {
            case CABINA:
            case BAFLE:
                setGarantia(6);
            case PARLANTE:
                setGarantia(3);
            case AUDIFONO:
                setGarantia(2);
            case RADIO:
                setGarantia(1);
            default:
                setGarantia(0);
        }
    }
    private String tipoSonidoTexto(tipoSonido tipo){//Es solo para que la primera quede en mayuscula y el retso el minuscula
        String texto= tipo.name().charAt(0)+tipo.name().substring(1).toLowerCase();
        return texto;
    }

    public void detalle(){
        String detalle = tipoSonidoTexto(tipo)+" " +getNombre()+ "\n"+"Marca: "+marca+ "\n"+ "Potencia: "+potencia+
        "\n"+"Original: "+ (original?"Si":"No")+"\n"+"Microfono: "+ (microfono?"Si":"No")+"\n"+
        "Bateria: "+ (bateria?"Si":"No")+"\n";

        System.out.println( detalle);
    }
}
 