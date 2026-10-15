import java.util.Scanner;


public class Main{
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        
        System.out.println("Bienvenido :)");
        System.out.println("Seleccione una opción");

        System.out.println("    1. Administrar Inventario");
        System.out.println("    2. Registrar Venta");
        System.out.println("    3. Buscar Producto");
        System.out.println("    4. Cerrar Dia");

        int opcion = sc.nextInt();

        switch (opcion) {
            case 1:
            Contiinuo.administrarInventario();
            break;
            case 2:
                
            Contiinuo.regisitrarVenta();
            break;
            case 3:
            
            Contiinuo.buscarProducto();;
            break;
            case 4:
            Contiinuo.cerrarDia();
            break;
        
            default:
            break;
        }

    
    }
    //Clase que contiene unicamente el menu en consola
    }