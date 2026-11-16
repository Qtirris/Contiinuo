package servicio;

import.java.util.ArrayList;

import modelo.Producto;
import modelo.Factura;
import modelo.Consola;
import modelo.Sonido;
import modelo.Varios;

//Se declara a la clase tienda como parte del paquete servicio y se importa la carpeta modelo y sus clases, para poder
//leerlas.

public class Tienda {
    //atributos de la clase contiinuo, vitales para el funcionamiento del programa.

    // La cuadrícula del inventario tiene 5 columnas (debe coincidir con Inventario.COLUMNAS).
    private static final int COLUMNAS = 5;

    private Inventario inventario;
    private Caja caja;
    private Contabilidad contabilidad;
    private int consecutivofactura;
    private string ultimoComprobante;
    private String ultimaFactura;

    //Tienda ya no imprime, lo hace main, entonces guarda los datos y en ultimoComprobante y ultimaFactura para que
    //main lo muestre.


    public Tienda(){
        //Constructor y parámetros
        this.inventario= new Inventario();
        this.caja=new Caja();
        this.contabilidad=new Contabilidad(this.caja, this.inventario, sc);
        this.consecutivofactura=1;
        this.ultimoComprobante= "";
        this.ultimaFactura= "";
    }

    //getters, para que las otras clases puedan tomar estos atributos.

    public Inventario getInventario(){return inventario;}

    public Caja getCaja(){
        return caja;
    }

    public int getConsecutivoFactura(){
        return consecutivofactura;
    }

    public String getUltimoComprobante(){
        return ultimoComprobante;
    }

    public String getUltimaFactura(){
        return ultimaFactura;
    }

    //Métodos, para retornar.

    //listaDeCompras, muestra los productos que se estan acabando y hace una lista en base a ello.

    public void listaDeCompras(){
        String lista= inventario.porAgotarse;
        if(lista.trim().isEmpty()){
            return "No hay productos por agotarse.";
        }
        return "===LISTA DE COMPRAS===\nEstá por agotarse:\n"+lista;
    }



    //registrar un producto nuevo.

    public void registrarProducto(int tipo, String[] textos, int[] numeros, boolean[] banderas, int subtipo ){
        if (textos[0] == null || texto[0].trim().isEmpty()){
            return "El nombre no puede estar vacío.";
        }
        for (int i=0; i < numeros.length; i++){
            if(numeros[i] < 0){
                return "Datos inválidos: ningún número puede ser negativo.";
            }
        }
        if(inventario.estaLleno()){
            return "El inventario está lleno.";
        }
        Producto p;
        switch(tipo){
            case 1:
                p=new Consola(textos, numeros, banderas);
                break;

            case 2:
                if (subtipo < 1 || subtipo > Sonido.tipoSonido.values().length) {
                    return "Clase de sonido no válida.";
                }
                p = new Sonido(textos, numeros, banderas, Sonido.tipoSonido.values()[subtipo - 1]);
                break;
            case 3:
                if (subtipo < 1 || subtipo > Varios.tipoVarios.values().length) {
                    return "Clase de producto no válida.";
                }
                p = new Varios(textos, numeros, banderas[0], Varios.tipoVarios.values()[subtipo - 1]);
                break;

            default:
                return "Tipo de producto no válido.";
        }

        if (!inventario.registrarProducto(p)){
            return "No se pudo registrar el producto.";
        }
        return null;
    }

    public String reponerProducto( int id, int cantidad, int costoUnitario, String proveedor){
        prodcuto p= inventario.buscarPorId(id);
        if(p==null){
            return "Ese producto no existe.";
        }
        if(cantidad <=0 || costoUnitario <=0){
            return "La cantidad y el costo deben ser mayores que 0.";
        }

        int costoAnterior = p.getCostoCompra(); //Se guarda antes de cambiarlo para comparar.
        p.reponer(cantidad);
        p.setCostoCompra(costoUnitario);

        String texto = "=== COMPROBANTE DE COMPRA ===\n"
                +"Proveedor: "+proveedor+"\n"
                +"Producto: "+p.getNombre()+"\n"
                +"Cantidad: "+cantidad+"\n"
                +"Costo por unidad: $"+costoUnitario+"\n"
                +"Total: $"+(cantidad * costoUnitario)+ "\n"
                +"Stock actual: "+p.getStock();
        if(costoUnitario > costoAnterior){
            texto += "\nOJO: el costo subió. Conviene revisar la ganancia mínima.";
        }
        this.ultimoComprobante = texto;
        return null;
    }

    //Se retiran productor por ID.
    public boolean retirarProducto(int id){
        return inventario.retirarProdcuto(id);

    }

    //Devuelve factura, o null si el medio de pago no es valido.
    public Factura iniciarVenta(String cliente, String telefono, int medio){
        String pago;
        if(medio == 1){
            pago="Efectivo";
        }else if(medio == 2){
            pago="Transferencia";
        } else{
            return null;
        }
        return new Factura(liente, telefono, pago);
    }

    public String agregarItem(Factura factura, int id, int cantidad, int precioFinal) {
        Producto p = inventario.buscarPorId(id);
        if (p == null) {
            return "Ese producto no existe.";
        }
        if (cantidad <= 0) {
            return "La cantidad debe ser mayor que 0.";
        }
        if (p.getStock() < cantidad) {
            return "Stock insuficiente. Disponibles: " + p.getStock();
        }
        if (precioFinal < p.getPrecioMinimo()) {
            return "Precio muy bajo. El mínimo es " + p.getPrecioMinimo();
        }
        // OJO: Factura.agregarItem YA descuenta el stock (llama a p.registrarVenta).
        // Por eso aquí NO se llama a p.descontar: se restaría dos veces.
        factura.agregarItem(p, cantidad, precioFinal);
        return null;
    }

    //true si el producto llegó al stock mínimo o quedó por debajo.

    public boolean alertaReposicion(int in) {
        Producto p = inventario.buscarPorId(id);
        return p != null && p.getStock() <= p.getStockMinimo();
    }

    // Cierra la venta, registra en caja y avanza en el consecutivo. Devuelve error o null.
    public String cerrarVenta(Factura factura){
        if(factura.getNumero() == 0){
            return "No se agregaron productos. Su venta será cancelada.";
        }

        this.ultimaFactura = "======== FACTURA #"+ consecutivoFactura+"========\n"+factura;
        caja.registrarVenta(factura);
        consecutivofactura++;
        return null;
    }

    // Búsqueda por parte del nombre, sin importar mayúsculas (la hace Inventario con contains).
    public String buscarPorTexto(String texto) {
        String lista = listarPorNombre(texto);
        if (lista == null) {
            return "Sin coincidencias.";
        }
        return lista;
    }

    //ahora cumple la función de buscar por nombre.
    private ArrayList<Producto> coincidencias(String nombre){
        ArrayList<Producto> lista = inventario.buscarPorNombre(nombre);
        if (lista == null) {
            return new ArrayList<>();
        }
        return lista;
    }
}

//Devuelve el ID del producto con ese nombre: 0 si no hay ninguna y -1 si hay varios.
public int idPorNombre(String nombre){
    ArrayList<Producto> lista = coincidencias(nombre);
    if(lista,size() == 0){
        return 0;
    }

    if(lista.size() > 1){
        return -1;
    }

    return lista.get(0).getID();
}

//Texto con todos los productos de ese nombre(cada uno con su id). null si no hay.
public String listarPorNombre(String nombre){
    ArrayList<Producto> lista = coincidencias(nombre);
    if(lista.size() == 0){
        return null;
    }
    String texto = "";
    for(int i = 0; i < lista.size(); i++){
        texto += lista.get(i) + "\n";
    }

    return texto;
}
// Detalle completo de un producto. Aquí está el POLIMORFISMO: p.detalle() lo responde
// cada subclase a su manera y nadie pregunta de qué tipo es.  null si no existe.
public String detalleProducto(int id) {
    Producto p = inventario.buscarPorId(id);
    if (p == null) {
        return null;
    }
    return p + "\n" + p.detalle() + "\nGarantía: " + p.getGarantia() + " meses";
}
public boolean cambiarUbicacion(int id, String nueva) {
    Producto p = inventario.buscarPorId(id);
    if (p == null) {
        return false;
    }
    return p.setUbicacion(nueva);
}

// El usuario escribe fila y columna desde 1. La casilla (fila, columna) es la posición
// (fila - 1) * COLUMNAS + (columna - 1) dentro de la lista del inventario.

public String cuadricula() {
    return inventario.revisarStock();
}
private Producto productoEn(int fila, int columna) {
    if (fila < 1 || columna < 1 || columna > COLUMNAS) {
        return null;
    }
    return inventario.getProducto((fila - 1) * COLUMNAS + (columna - 1));
}
public String consultarCasilla(int fila, int columna) {
    Producto p = productoEn(fila, columna);
    if (p == null) {
        return null;   // casilla libre o posición que no existe
    }
    return p + "\n" + p.detalle() + "\nGarantía: " + p.getGarantia() + " meses";
}
public String reponerEnCasilla(int fila, int columna, int cantidad, int costoUnitario, String proveedor) {
    Producto p = productoEn(fila, columna);
    if (p == null) {
        return "Esa casilla está libre.";
    }
    return reponerProducto(p.getID(), cantidad, costoUnitario, proveedor);
}
public boolean retirarDeCasilla(int fila, int columna) {
    Producto p = productoEn(fila, columna);
    return p != null && inventario.retirarProducto(p.getID());
}

//Llama a caja para obtener los datos del cierre del dia e imprimirlos.
public void cerrarDia() {
    return caja.resumenDelDia();
}
// Cada reporte devuelve un texto. Tienda se lo pide a Contabilidad (o a Caja) y Main lo imprime.
public String resumenContable() {
    return contabilidad.generarResumenGeneral();
}
public String reporteMasVendido() {
    return contabilidad.generarReporteProductos();
}
public String reporteMenosVendido() {
    return contabilidad.generarReporteProductosMenosVendidos();
}
public String reporteMasRentable() {
    return contabilidad.generarReporteProductosMasRentables();
}
public String reporteMedioDePago() {
    return contabilidad.generarReporteVentasPorMedioPago();
}
public String totalVendido() {
    return "Total vendido: $" + caja.getTotalVendido();
}
public String gananciaTotal() {
    return "Ganancias: $" + caja.getGananciaTotal();
}

public String toString(){return "Tienda [ próxima factura #"+ consecutivofactura;}


}