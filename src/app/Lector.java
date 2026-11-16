package app;

import java.util.Scanner;

// Lector: la ÚNICA clase que usa Scanner en todo el programa.
// Todos los menús la reutilizan (Lector.leerEntero(...), etc.).
public class Lector {

    // static = es de la clase, no de un objeto: se llama sin hacer "new Lector()".
    private static final Scanner sc = new Scanner(System.in);

    // Pide un número entero. Si escriben letras NO se cae: avisa y vuelve a preguntar.
    public static int leerEntero(String pregunta) {
        while (true) {
            System.out.print(pregunta);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Escriba un número entero.");
            }
        }
    }

    // Pide un texto (le quita los espacios de los lados con trim).
    public static String leerTexto(String pregunta) {
        System.out.print(pregunta);
        return sc.nextLine().trim();
    }

    // Pide s/n y devuelve true si escribió "s" o "S".
    public static boolean leerSiNo(String pregunta) {
        return leerTexto(pregunta + " (s/n): ").equalsIgnoreCase("s");
    }
}