import java.util.Locale;
import java.util.ArrayList; 

public class Caja{
        //atributos de la caja.
    private int facturasEmitidas;
    private int totalVendido;
    private int gananciaTotal;
    private ArrayList<Factura> facturas; 

public String generarReporteVentasPorMedioPago() {
    int totalEfectivo = 0;
    int totalNequi = 0;
    int totalTransferencia = 0;
    int totalOtros = 0;

    for (Factura factura : facturas) {
        String medio = factura.getMedioDePago();

        if (medio == null) {
            totalOtros += factura.getTotal();
        } else if (medio.equalsIgnoreCase("efectivo")) {
            totalEfectivo += factura.getTotal();
        } else if (medio.equalsIgnoreCase("nequi")) {
            totalNequi += factura.getTotal();
        } else if (medio.equalsIgnoreCase("transferencia")) {
            totalTransferencia += factura.getTotal();
        } else {
            totalOtros += factura.getTotal();
        }
    }

    return "====== VENTAS POR MEDIO DE PAGO ======\n"
            + "Efectivo: $" + totalEfectivo + "\n"
            + "Nequi: $" + totalNequi + "\n"
            + "Transferencia: $" + totalTransferencia + "\n"
            + "Otros medios de pago: $" + totalOtros + "\n"
            + "Total vendido: $" + totalVendido + "\n"
            + "=====================================";
}

        // Este constructor inicia la jornada del dia desde 0
     public Caja() {
        this.facturasEmitidas = 0;
        this.totalVendido = 0;
        this.gananciaTotal = 0;
        facturas = new ArrayList<>();
    }

              // Suma el total y la ganancia de la factura y cuenta una factura más.
                // Devuelve false si no recibió factura.
    public boolean registrarVenta(Factura factura) {
        if (factura == null) {
            return false;
        }
        totalVendido += factura.getTotal();
        gananciaTotal += factura.getGanancia();
        facturasEmitidas++;
        facturas.add(factura);
        return true;
    }

            // Ticket promedio = lo vendido por factura (NO es la ganancia).
            // Con cuidado de no dividir por cero.
    public int calcularTicketPromedio() {
        if (facturasEmitidas == 0) {
            return 0;
        }
        return totalVendido / facturasEmitidas;
    }

    // Convierte 1200000 en "$1.200.000"
    private String formatoPesos(int valor) {
        return "$" + String.format(Locale.forLanguageTag("es-CO"), "%,d", valor);
    }


            // Devuelve el cierre del día, esto no lo imprime, quien lo imprime es la tienda 
    public String resumenDelDia() {
        return "=============== Cierre Del Día ===============\n"
                + "Facturas emitidas: " + facturasEmitidas + "\n"
                + "Vendido: " + formatoPesos(totalVendido) + "\n"
                + "Ganancia: " + formatoPesos(gananciaTotal) + "\n"
                + "Ticket promedio: " + formatoPesos(calcularTicketPromedio());
    }

            // Los Getters, sin setters. Los totales solo cambian con los métodos de registro
    public int getFacturasEmitidas() { return facturasEmitidas; }
    public int getTotalVendido() { return totalVendido; }
    public int getGananciaTotal() { return gananciaTotal; }
}
