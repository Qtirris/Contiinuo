public class Caja{
        //atributos de la caja.
    private int facturasEmitidas;
    private double totalVendido;
    private double gananciaTotal;
    private double totalInvertido;
    private int reposicionesRealizadas;


        // Este constructor inicia la jornada del dia desde 0
     public Caja() {
        this.facturasEmitidas = 0;
        this.totalVendido = 0;
        this.gananciaTotal = 0;
        this.totalInvertido = 0;
        this.reposicionesRealizadas = 0;
    }

        //suma el total y la ganacia de la factura y cuenta una factura más
    public void registrarVenta(Factura factura) {
        totalVendido += factura.getTotal();
        gananciaTotal += factura.getGanancia();
        facturasEmitidas++;
    }

        // para el ticket promedio, esto para saber cuanto dinero deja en promedio, cada venta del dia..
     public double calcularTicketPromedio() {
        if (facturasEmitidas == 0) {
            return 0;
        }
        return totalVendido / facturasEmitidas;
    }


        // para el cierre del dia 
    public String resumenDelDia() {
        return "=== Cierre del día ===\n"
                + "Facturas emitidas: " + facturasEmitidas + "\n"
                + "Vendido: $" + totalVendido + "\n"
                + "Ganancia: $" + gananciaTotal + "\n"
                + "Reposiciones: " + reposicionesRealizadas + "\n"
                + "Invertido: $" + totalInvertido + "\n"
                + "Ticket promedio: $" + calcularTicketPromedio();
    }


        // los getters de caja
     public int getFacturasEmitidas() { return facturasEmitidas; }
    public double getTotalVendido() { return totalVendido; }
    public double getGananciaTotal() { return gananciaTotal; }
    public double getTotalInvertido() { return totalInvertido; }
    public int getReposicionesRealizadas() { return reposicionesRealizadas; }



}