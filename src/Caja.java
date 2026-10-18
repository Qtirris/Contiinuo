import java.util.Locale;

public class Caja{
        //atributos de la caja.
    private int facturasEmitidas;
    private int totalVendido;
    private int gananciaTotal;
    private int totalInvertido;
    private int reposicionesRealizadas;


        // Este constructor inicia la jornada del dia desde 0
     public Caja() {
        this.facturasEmitidas = 0;
        this.totalVendido = 0;
        this.gananciaTotal = 0;
        this.totalInvertido = 0;
        this.reposicionesRealizadas = 0;
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
        return true;
    }


            // Suma lo gastado en la compra y cuenta una reposición más.
            // Devuelve false si no recibió reposición.
    public boolean registrarCompra(Reposicion reposicion) {
        if (reposicion == null) {
            return false;
        }
        totalInvertido += reposicion.calcularTotal();
        reposicionesRealizadas++;
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
                + "Reposiciones: " + reposicionesRealizadas + "\n"
                + "Invertido: " + formatoPesos(totalInvertido) + "\n"
                + "Ticket promedio: " + formatoPesos(calcularTicketPromedio());
    }

            // Los Getters, sin setters. Los totales solo cambian con los métodos de registro
    public int getFacturasEmitidas() { return facturasEmitidas; }
    public int getTotalVendido() { return totalVendido; }
    public int getGananciaTotal() { return gananciaTotal; }
    public int getTotalInvertido() { return totalInvertido; }
    public int getReposicionesRealizadas() { return reposicionesRealizadas; }

}