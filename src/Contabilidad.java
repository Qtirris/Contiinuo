import java.util.Scanner;

public class Contabilidad{

    private Caja caja;
    private Inventario inventario;
    private Scanner sc; 

}

    public Contabilidad(Caja caja, Inventario inventario, Scanner sc){
        this.caja = caja;
        this.inventario = inventario;
        this.sc = sc; 
    }

    public void mostrarMenu() {

        int opcion = -1;

        while (opcion != 0) {

            System.out.println("\n========== CONTABILIDAD ==========");
            System.out.println("1. Resumen general");
            System.out.println("2. Productos más vendidos");
            System.out.println("3. Productos menos vendidos");
            System.out.println("4. Productos que generan más ganancias");
            System.out.println("5. Ventas por medio de pago");
            System.out.println("6. Consultar total vendido");
            System.out.println("7. Consultar ganancias");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());

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
                        System.out.println("Total vendido: $" + caja.getTotalVendido());
                        break;

                    case 7:
                        System.out.println("Ganancias: $" + caja.getGananciaTotal());
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
    
    public String generarReporteVentasPorMedioPago() {
        return caja.generarReporteVentasPorMedioPago();
    }
        

public String generarResumenGeneral() {

    return "Resumen Contable:\n"
            + "Facturas emitidas: " + caja.getFacturasEmitidas() + "\n"
            + "Total vendido: $" + caja.getTotalVendido() + "\n"
            + "Ganancia total: $" + caja.getGananciaTotal() + "\n"
            + "Ticket promedio: $" + caja.calcularTicketPromedio() + "\n";
}

public String generarReporteProductos() {

    Producto[] productos =Inventario.obtenerProductos();

    Producto masVendido = null;

    for (int i = 0; i < productos.length; i++) {

        if (productos[i] != null) {

            if (masVendido == null ||
                productos[i].getStockVendido() > masVendido.getStockVendido()) {

                masVendido = productos[i];
            }
        }
    }

    if (masVendido == null || masVendido.getStockVendido() == 0) {
        return "Todavía no hay productos vendidos.";
    }

    return "====== PRODUCTO MÁS VENDIDO ======\n"
            + "Producto: " + masVendido.getNombre() + "\n"
            + "Unidades vendidas: " + masVendido.getStockVendido();

}

public String generarReporteProductosMenosVendidos() {

    Producto[] productos =Inventario.obtenerProductos();

    Producto menosVendido = null;

    for (int i = 0; i < productos.length; i++) {

        if (productos[i] != null) {

            if (menosVendido == null ||
                productos[i].getStockVendido() < menosVendido.getStockVendido()) {

                menosVendido = productos[i];
            }
        }
    }

    if (menosVendido == null) {
        return "No hay productos registrados en el inventario.";
    }

    return "====== PRODUCTO MENOS VENDIDO ======\n"
            + "Producto: " + menosVendido.getNombre() + "\n"
            + "Unidades vendidas: " + menosVendido.getStockVendido();
}

public String generarReporteProductosMasRentables() {

    Producto[] productos =Inventario.obtenerProductos();

    Producto masRentable = null;
    int mayorGanancia = 0;

    for (int i = 0; i < productos.length; i++) {

        if (productos[i] != null) {

            int gananciaPorUnidad =
                    productos[i].getPrecioMinimo()
                    - productos[i].getCostoCompra();

            int gananciaTotal =
                    gananciaPorUnidad * productos[i].getStockVendido();

            if (masRentable == null || gananciaTotal > mayorGanancia) {
                masRentable = productos[i];
                mayorGanancia = gananciaTotal;
            }
        }
    }

    if (masRentable == null) {
        return "No hay productos registrados en el inventario.";
    }

    return "====== PRODUCTO QUE GENERA MÁS GANANCIAS ======\n"
            + "Producto: " + masRentable.getNombre() + "\n"
            + "Unidades vendidas: " + masRentable.getStockVendido() + "\n"
            + "Ganancia estimada: $" + mayorGanancia;
}