import java.util.Scanner;

public class Contabilidad {

    private Caja caja;
    private Inventario inventario;
    private Scanner sc; 

    public Contabilidad(Caja caja, Inventario inventario, Scanner sc){
        this.caja = caja;
        this.inventario = inventario;
        this.sc = sc; 
    }

    public void mostrarMenu() {

        int opcion = -1;

        while (opcion != 0) {

            System.out.println("\n========== CONTABILIDAD ==========");
            System.out.println();
            System.out.println("1. Resumen general");
            System.out.println("2. Productos más vendidos");
            System.out.println("3. Productos menos vendidos");
            System.out.println("4. Productos que generan más ganancias");
            System.out.println("5. Ventas por medio de pago");
            System.out.println("6. Consultar total vendido");
            System.out.println("7. Consultar ganancias");
            System.out.println("0. Volver al menú principal");
            System.out.println();
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(sc.nextLine());
                System.out.println();

                switch (opcion) {

                    case 1:
                        System.out.println(generarResumenGeneral());
                        break;

                    case 2:
                        System.out.println(generarReporteProductos());
                        break;

                    case 3:
                        System.out.println(generarReporteProductosMenosVendidos()); 
                        break;

                    case 4:
                        System.out.println(generarReporteProductosMasRentables());
                        break;

                    case 5:
                        System.out.println(generarReporteVentasPorMedioPago());
                        break;

                    case 6:
                        System.out.println("Total vendido: $"
                        + caja.getTotalVendido());
                        break;

                    case 7:
                        System.out.println("Ganancias: $"
                        + caja.getGananciaTotal());
                        break;

                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un número.");
            }
        }
    }

    //Reporte de venta por medio de pago
    public String generarReporteVentasPorMedioPago() {
        return caja.generarReporteVentasPorMedioPago();
    }
        
//Resumen general
public String generarResumenGeneral() {

    return "Resumen Contable:\n\n"
            + "\tFacturas emitidas: " + caja.getFacturasEmitidas() + "\n"
            + "\tTotal vendido: $" + caja.getTotalVendido() + "\n"
            + "\tGanancia total: $" + caja.getGananciaTotal() + "\n"
            + "\tTicket promedio: $" + caja.calcularTicketPromedio();
}

//Producto mas vendido
public String generarReporteProductos() {

    Producto masVendido = null;

    for (int i = 1; i <= 10; i++) {
        Producto actual = inventario.getProducto(i);

        if (actual != null) {

            if (masVendido == null ||
               actual.getStockVendido() > masVendido.getStockVendido()) {

                masVendido = actual;
            }
        }
    }

    if (masVendido == null || masVendido.getStockVendido() == 0) {
        System.out.println();
        return "Todavía no hay productos vendidos.";
    }

    return "====== PRODUCTO MÁS VENDIDO ======\n"
            + "Producto: " + masVendido.getNombre() + "\n"
            + "Unidades vendidas: " + masVendido.getStockVendido();

}

//producto menos vendido
public String generarReporteProductosMenosVendidos() {

    Producto menosVendido = null;

    for (int i = 1; i<= 10; i++) {

        Producto actual = inventario.getProducto(i);

        if (actual != null && actual.getStockVendido() > 0) {

            if (menosVendido == null ||
                actual.getStockVendido() < menosVendido.getStockVendido()) {

                menosVendido = actual;
            }
        }
    }

    if (menosVendido == null) {
        System.out.println();
        return "Todavía no hay productos vendidos.";
    }

    return "====== PRODUCTO MENOS VENDIDO ======\n"
            + "Producto: " + menosVendido.getNombre() + "\n"
            + "Unidades vendidas: " + menosVendido.getStockVendido();
}

//Producto mas rentable
public String generarReporteProductosMasRentables() {

    Producto masRentable = null;
    int mayorGanancia = 0;

    for (int i = 1; i <= 10; i++) {
        Producto actual = inventario.getProducto(i);

        if (actual != null && actual.getStockVendido() > 0) {

            int gananciaPorUnidad =
                    actual.getPrecioMinimo() - actual.getCostoCompra();

            int gananciaTotal =
                    gananciaPorUnidad * actual.getStockVendido();

            if (masRentable == null || gananciaTotal > mayorGanancia) {
                masRentable = actual;
                mayorGanancia = gananciaTotal;
            }
        }
    }

    if (masRentable == null) {
        System.out.println();
        return "Todavía no hay productos vendidos.";
    }

    return "====== PRODUCTO QUE GENERA MÁS GANANCIAS ======\n"
            + "Producto: " + masRentable.getNombre() + "\n"
            + "Unidades vendidas: " + masRentable.getStockVendido() + "\n"
            + "Ganancia estimada: $" + mayorGanancia;

}

}