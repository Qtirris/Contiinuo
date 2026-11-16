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

    private Factura[] facturas = new Factura[100];
    private int cantidadFacturas = 0; 

    // Este constructor inicia la jornada del día desde 0
    public Caja() {
        facturasEmitidas = 0;
        totalVendido = 0;
        gananciaTotal = 0;

        totalEfectivo = 0;
        totalTransferencia = 0;
        totalOtros = 0;

        cantidadFacturas = 0; 
        totalNequi = 0; 
    }

    // Suma el total y la ganancia de la factura, la clasifica por medio de pago
    // y cuenta una factura más. Devuelve false si no recibió factura.
    public boolean registrarVenta(Factura factura) {

        if (factura == null) {
            return false;
        }

        if (cantidadFacturas >= facturas.length){
            System.out.println("No se pueden registrar más facturas. "); 
            return false; 
        }

        facturas[cantidadFacturas] = factura; 
        cantidadFacturas++; 

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

    public int getCantidadFacturas() {
        return cantidadFacturas; 
    }

    public Factura getFactura(int posicion){

        if (posicion < 0 || posicion >= cantidadFacturas){
            return null; 
        }
        return facturas[posicion]; 
    }

    // Reporte de ventas por medio de pago (siempre devuelve texto)
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

    public int getTotalNequi() {
        return totalNequi;
    }

    public int getTotalEfectivo() {
        return totalEfectivo; 
    }

    public int getTotalTransferencia() {
        return totalTransferencia;
    }
}

