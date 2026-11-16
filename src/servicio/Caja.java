package servicio;

import java.util.Locale;
import modelo.Factura;

public class Caja {

    // Atributos de la caja
    private int facturasEmitidas;
    private int totalVendido;
    private int gananciaTotal;

    // Totales por medio de pago
    private int totalEfectivo;
    private int totalTransferencia;
    private int totalOtros;
    private int totalNequi;

    // Este constructor inicia la jornada del día desde 0
    public Caja() {
        facturasEmitidas = 0;
        totalVendido = 0;
        gananciaTotal = 0;

        totalEfectivo = 0;
        totalTransferencia = 0;
        totalOtros = 0;
        totalNequi = 0;
    }

    // Registra los valores de una venta
    public boolean registrarVenta(Factura factura) {

        if (factura == null) {
            return false;
        }

        facturasEmitidas++;
        totalVendido += factura.getTotal();
        gananciaTotal += factura.getGanancia();

        String medio = factura.getMedioDePago();

        if (medio == null) {
            medio = "otros";
        } else {
            medio = medio.trim().toLowerCase();
        }

        switch (medio) {
            case "efectivo":
                totalEfectivo += factura.getTotal();
                break;

            case "nequi":
                totalNequi += factura.getTotal();
                break;

            case "transferencia":
            case "transferencias":
                totalTransferencia += factura.getTotal();
                break;

            default:
                totalOtros += factura.getTotal();
                break;
        }

        return true;
    }

    // Reporte de ventas por medio de pago
    public String generarReporteVentasPorMedioPago() {

        if (facturasEmitidas == 0) {
            return "Todavía no hay ventas registradas.";
        }

        return "====== VENTAS POR MEDIO DE PAGO ======\n"
                + "Efectivo: " + formatoPesos(totalEfectivo) + "\n"
                + "Nequi: " + formatoPesos(totalNequi) + "\n"
                + "Transferencias: " + formatoPesos(totalTransferencia) + "\n"
                + "Otros: " + formatoPesos(totalOtros) + "\n"
                + "=====================================";
    }

    // Calcula el ticket promedio
    public int calcularTicketPromedio() {

        if (facturasEmitidas == 0) {
            return 0;
        }

        return totalVendido / facturasEmitidas;
    }

    // Convierte los valores a formato de pesos
    private String formatoPesos(int valor) {
        return "$" + String.format(
                Locale.forLanguageTag("es-CO"), "%,d", valor);
    }

    // Resumen del cierre del día
    public String resumenDelDia() {

        return "=============== Cierre Del Día ===============\n"
                + "Facturas emitidas: " + facturasEmitidas + "\n"
                + "Vendido: " + formatoPesos(totalVendido) + "\n"
                + "Ganancia: " + formatoPesos(gananciaTotal) + "\n"
                + "Ticket promedio: " + formatoPesos(calcularTicketPromedio());
    }

    // Getters
    public int getFacturasEmitidas() {
        return facturasEmitidas;
    }

    public int getTotalVendido() {
        return totalVendido;
    }

    public int getGananciaTotal() {
        return gananciaTotal;
    }

    public int getTotalNequi() {
        return totalNequi;
    }

    public int getTotalEfectivo() {
        return totalEfectivo;
    }

    public int getTotalTransferencia() {
        return totalTransferencia;
    }

    public int getTotalOtros() {
        return totalOtros;
    }
}