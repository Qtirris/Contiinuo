import java.util.Scanner;

//es de vital importancia para leer los datos proporcionados por el usuario
//y para asi seguir las reglas de las otras clases.

public class Tienda {
    //atributos de la clase contiinuo, vitales para el funcionamiento del programa.
    private Inventario inventario;
    private Caja caja;
    private int consecutivofactura;
    private int consecutivoreposicion;
    private Scanner sc;


    public Tienda(Scanner sc){
        //Constructor y parámetros
        this.inventario= new Inventario();
        this.caja=new Caja();
        this.consecutivofactura=1;
        this.consecutivoreposicion=1;
        this.sc=sc;
    }

    //getters, para que las otras clases puedan tomar estos atributos.

    public Inventario getInventario(){return inventario;}

    public Caja getCaja(){
        return caja;
    }

    public int getConsecutivoFactura(){
        return consecutivofactura;
    }

    public int getConsecutivoReposicion(){
        return consecutivoreposicion;
    }


    //registrar un producto nuevo.

    public void registrarProducto(){
        System.out.println("====REGISTRAR PRODUCTO====");

        String nombre="";
        while (nombre.equals("")){
            System.out.print("Nombre: ");
            nombre=sc.nextLine();

            System.out.print("Categoria: ");
            String Categoria=sc.nextLine();

            System.out.print("Ubicación: ");
            String Ubicacion=sc.nextLine();
        }
    }



    //Aplica las reglas


// Se crea la regla para buscar productos, en donde se llama a inventario para ver si está.
    public void administrarInventario(){}
    public void regisitrarVenta(){}
    public void buscarProducto(){
        System.out.println("===BUSCAR PRODUCTO===");
        System.out.print("Ingrese el nombre del producto: ");
        String nombreproducto= sc.nextLine().trim();
        Producto prdt=inventario.buscarNombre(nombreproducto);
    }

    //Llama a caja para obtener los datos del cierre del dia e imprimirlos.
    public void cerrarDia(){
        System.out.println("===CIERRE DEL DÍA===");
        System.out.println(caja.resumenDelDia());}
    
    }