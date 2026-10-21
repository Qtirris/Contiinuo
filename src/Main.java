import java.util.Scanner;


public class Main{
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Tienda mitienda = new Tienda(sc);
        
        System.out.println("Bienvenido :)");

        int opcion = -1;
        while (opcion != 0) {
            System.out.println("Seleccione una opción:");

            System.out.println("    1. Administrar Inventario");
            System.out.println("    2. Registrar Venta");
            System.out.println("    3. Buscar Producto");
            System.out.println("    4. Cerrar día");
            System.out.println("    5. Consultar Contabilidad");
            System.out.println("    0. Salir");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    mitienda.administrarInventario();
                    break;
                case 2:

                    mitienda.registrarVenta();
                    break;
                case 3:

                    mitienda.buscarProducto();
                    break;
                case 4:
                    mitienda.cerrarDia();
                    break;
                case 5:
                    mitienda.abrirContabilidad();
                    break;
                case 0:
                    System.out.print("¡Hasta luego!");
                    break;
                default:
                    System.out.print("opción no válida, intenta de nuevo.");
                    break;


            }

        }

    
    }
    //Clase que contiene unicamente el menu en consola
    }