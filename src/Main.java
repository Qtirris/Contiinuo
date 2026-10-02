import java.util.Scanner;


public class Main{
    //Clase que contiene unicamente el menu en consola
    public static void main(String[] args){
        //Nuestro Scanner
        Scanner sc = new Scanner(System.in);

        enum Rol{
            CAJERO,
            ADMINISTRADOR,
            DISTRIBIUDOR,
            NULL
        }

        System.out.println("Bienvenido a Contiinuo.");
        System.out.println();
        System.out.println();
        
        System.out.println("Por favor ingrese su rol: ");
        System.out.println();
        System.out.println("1. Cajero");
        System.out.println("2. Administrador ");
        System.out.println("3. Distribuidor");

        //Escuchamos al usuario
        int eleccion = sc.nextInt();

        Rol rol; //Será mas legible con un enum

        switch (eleccion) {
            case 1:
                System.out.println("Bienvenido cajero/a.");
                rol= Rol.CAJERO;
                System.out.println(rol);
                break;

            case 2:
                    
                System.out.println("Bienvenido administrador/a.");
                rol= Rol.ADMINISTRADOR;
                System.out.println(rol);
                break;

            case 3:
                
                System.out.println("Bienvenido distribuidor/a.");
                rol = Rol.DISTRIBIUDOR;
                System.out.println(rol);
                break;

            default:
                
                System.out.println("Opción inválida.");
                rol = Rol.NULL; //No se si lo usaremos despues pero por si las moscas
                System.out.println(rol);
                break;
        }
    }
}