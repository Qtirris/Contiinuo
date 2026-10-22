import java.util.Locale;

public class Caja {

    // Atributos de la caja
    private int facturasEmitidas;
    private int totalVendido;
    private int gananciaTotal;

    // Totales por medio de pago, se quito nequi y solo nos quedamos con tranferencias 
    private int totalEfectivo;
    private int totalTransferencia;
    private int totalOtros;

    // Este constructor inicia la jornada del día desde 0
    public Caja() {
        this.facturasEmitidas = 0;
        this.totalVendido = 0;
        this.gananciaTotal = 0;
        this.totalEfectivo = 0;
        this.totalTransferencia = 0;
        this.totalOtros = 0;
    }

    // Suma el total y la ganancia de la factura, la clasifica por medio de pago
    // y cuenta una factura más. Devuelve false si no recibió factura.
    public boolean registrarVenta(Factura factura) {

        if (factura == null) {
            return false;
        }

        int total = factura.getTotal();
        totalVendido += total;
        gananciaTotal += factura.getGanancia();
        facturasEmitidas++;

        String medio = factura.getMedioDePago();
        medio = (medio == null) ? "" : medio.trim().toLowerCase();

        switch (medio) {
            case "efectivo":
                totalEfectivo += total;
                break;
            case "transferencia":
                totalTransferencia += total;
                break;
            default:
                totalOtros += total;
        }

        return true;
    }

    // Reporte de ventas por medio de pago (siempre devuelve texto)
    public String generarReporteVentasPorMedioPago() {

        if (facturasEmitidas == 0) {
            return "Todavía no hay ventas registradas.";
        }

        return "====== VENTAS POR MEDIO DE PAGO ======\n"
                + "Efectivo: " + formatoPesos(totalEfectivo) + "\n"
                + "Transferencia: " + formatoPesos(totalTransferencia) + "\n"
                + "Otros medios de pago: " + formatoPesos(totalOtros) + "\n"
                + "Total vendido: " + formatoPesos(totalVendido) + "\n"
                + "=====================================";
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

    // Los getters, sin setters. Los totales solo cambian con los métodos de registro
    public int getFacturasEmitidas() { return facturasEmitidas; }
    public int getTotalVendido() { return totalVendido; }
    public int getGananciaTotal() { return gananciaTotal; }
}

