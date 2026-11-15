package modelo;

/*
Todo lo demas: cables, adaptadores, gamepads,
power banks, maletas, sombrillas, etc.
*/
public class Varios extends Producto {
    private String marca;
    private String especificacion;  
    private boolean original;      
    private tipoVarios tipo;
    public static enum tipoVarios {CABLE, GAMEPAD, POWERBANK, MALETA, SOMBRILLA, OTRO};

    /*
    Lista de caracteristicas especiales de la clase Varios

    Se añaden dos elementos nuevos a `param1`

    param1[]={nombre,ubicacion,marca,especificacion}                  
    param2[]={costoCompra,gananciaMinima,stock,stockMinimo}           

    `original` es un solo boolean por eso va suelto y no en una lista
    */

    public Varios(String[] param1, int[] param2, boolean original, tipoVarios tipo){
        super(param1, param2);
        this.marca=param1[2];
        this.especificacion=param1[3];
        this.original = original;
        this.tipo =tipo;
        setGarantiaPorDefecto(this.tipo);
    }

    public String getMarca(){return marca;}
    public String getEspecificacion(){return especificacion;}
    public tipoVarios getTipo(){return tipo;};

    // Meses de garantía según el tipo. Valores de ejemplo: confirmarlos con el señor.
    private void setGarantiaPorDefecto(tipoVarios tipo){
        switch (tipo) {
            case GAMEPAD:
            case POWERBANK:
                setGarantia( 3);
                break;
            case CABLE:
                setGarantia(  1);
                break;
            default:
                setGarantia( 0);
                break;
        }
    }

    // Primera letra en mayúscula y el resto en minúscula (CABLE -> Cable)
    private String tipoTexto(){
        String nombreTipo = tipo.name();
        return nombreTipo.charAt(0) + nombreTipo.substring(1).toLowerCase();
    }

    @Override
    public void detalle(){
        String detalle = tipoTexto() + ": " + getNombre() + "\n"
                + "Marca: " + (marca.isEmpty() ? "Sin marca" : marca) + "\n"
                + "Original: " + (original ? "Si" : "No") + "\n";

        if (!especificacion.isEmpty()) {
            detalle += "Especificacion: " + especificacion + "\n";
        }

        System.out.println(detalle);
    }
}