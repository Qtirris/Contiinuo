import java.util.Scanner;

//es de vital importancia para leer los datos proporcionados por el usuario
//y para asi seguir las reglas de las otras clases.

public class Contiinuo{
    //atributos de la clase contiinuo, vitales para el funcionamiento del programa.
    private static Inventario inventario;
    private static Caja caja;
    private int consecutivofactura;
    private int consecutivoreposicion;
    private Scanner sc = new Scanner(System.in);

    public Contiinuo(){
        //Constructor y parámetros
        this.inventario= new Inventario();
        this.caja=new Caja();
        this.consecutivofactura=1;
        this.consecutivoreposicion=1;
    }

    //getters, para que las otras clases puedan tomar estos atributos.

    public Inventario getInventario(){
        return inventario;

    }

    public Caja getCaja(){
        return caja;
    }

    public int getConsecutivoFactura(){
        return consecutivofactura;
    }

    public int getConsecutivoReposicion(){
        return consecutivoreposicion;
    }

//ahora llamamos a caja, para imprimir y mostrar el cierre del día.

    public void cierreDelDia(){

    }


    //Aplica las reglas
    public static void administrarInventario(){}
    public static void regisitrarVenta(){}
    public static void buscarProducto(){}

    //Llama a caja para obtener los datos del cierre del dia e imprimirlos.
    public static void cerrarDia(){
        System.out.println("===CIERRE DEL DÍA===");
        System.out.println(caja.resumenDelDia());}
    
    }