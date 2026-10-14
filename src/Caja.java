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



}