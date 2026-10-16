import java.util.Scanner;

//es de vital importancia para leer los datos proporcionados por el usuario
//y para asi seguir las reglas de las otras clases.

public class Contiinuo{
    //atributos de la clase contiinuo, vitales para el funcionamiento del programa.
    private Inventario inventario;
    private Caja caja;
    private int consecutivodefactura;
    private int consecutivodereposicion;
    private Scanner sc = new Scanner(System.in);

    public Contiinuo(){
        //Constructor y parámetros
        this.inventario= new Inventario();
        this.caja=new Caja();
        this.consecutivodefactura=1;
        this.consecutivodereposicion=1;
    }

    //Aplica las reglas
    public static void administrarInventario(){}
    public static void regisitrarVenta(){}
    public static void buscarProducto(){}
    public static void cerrarDia(){}
    
    }