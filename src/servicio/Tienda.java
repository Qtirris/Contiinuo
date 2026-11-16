package servicio;

import java.util.ArrayList;

import modelo.Consola;
import modelo.Factura;
import modelo.Producto;
import modelo.Sonido;
import modelo.Varios;

// Tienda: las REGLAS del negocio y la coordinación.
// REGLA DE ORO: Tienda NUNCA lee (no hay Scanner) y NUNCA imprime (no hay println).
//   - Recibe los datos de cada operación como parámetros.
//   - Devuelve un texto, un boolean o un objeto.
//   - Lo que puede fallar devuelve un String con el motivo, o null si todo salió bien.
// Los productos se identifican por su ID (único), porque puede haber varios con nombres parecidos.
public class Tienda {

    // La cuadrícula del inventario tiene 5 columnas (debe coincidir con Inventario.COLUMNAS).
    private static final int COLUMNAS = 5;

    private Inventario inventario;
    private Caja caja;
    private Contabilidad contabilidad;
    private int consecutivoFactura;
    private String ultimoComprobante;   // texto de la última compra de mercancía
    private String ultimaFactura;       // texto de la última factura cerrada

    public Tienda() {
        this.inventario = new Inventario();
        this.caja = new Caja();
        this.contabilidad = new Contabilidad(this.caja, this.inventario);
        this.consecutivoFactura = 1;
        this.ultimoComprobante = "";
        this.ultimaFactura = "";
    }

    public Inventario getInventario() { return inventario; }
    public Caja getCaja() { return caja; }
    public int getConsecutivoFactura() { return consecutivoFactura; }
    public String ultimoComprobante() { return ultimoComprobante; }
    public String ultimaFactura() { return ultimaFactura; }

    // ======================================================================
    //  ÚNICO LUGAR que habla de lo que devuelve Inventario.buscarPorNombre.
    //  Esa búsqueda es PARCIAL (contiene el texto) e ignora mayúsculas.
    //  Devuelve null si el texto está vacío; aquí lo convertimos en lista vacía.
    // ======================================================================
    private ArrayList<Producto> coincidencias(String nombre) {
        ArrayList<Producto> lista = inventario.buscarPorNombre(nombre);
        if (lista == null) {
            return new ArrayList<>();
        }
        return lista;
    }

    // Devuelve el ID del producto: 0 si no hay ninguno, -1 si hay varios
    // (entonces Main muestra la lista y pide el ID).
    public int idPorNombre(String nombre) {
        ArrayList<Producto> lista = coincidencias(nombre);
        if (lista.size() == 0) {
            return 0;
        }
        if (lista.size() > 1) {
            return -1;
        }
        return lista.get(0).getID();
    }

    // Texto con todos los productos que coinciden (cada uno con su ID). null si no hay.
    public String listarPorNombre(String nombre) {
        ArrayList<Producto> lista = coincidencias(nombre);
        if (lista.size() == 0) {
            return null;
        }
        String texto = "";
        for (int i = 0; i < lista.size(); i++) {
            texto += lista.get(i) + "\n";
        }
        return texto;
    }

    // ---------- REGISTRAR PRODUCTO ----------
    // tipo: 1 Consola, 2 Sonido, 3 Varios.
    // Cada subclase pide sus datos en tres arreglos (Main los arma):
    //   textos   = Consola {nombre, ubicación, plataforma}
    //              Sonido  {nombre, ubicación, marca}
    //              Varios  {nombre, ubicación, marca, especificación}
    //   numeros  = {costo, ganancia mínima, stock, stock mínimo, (potencia solo en Sonido)}
    //   banderas = Consola {original, chipeada, portátil}
    //              Sonido  {original, micrófono, batería}
    //              Varios  {original}
    // subtipo: la clase de sonido (1 a 5) o de varios (1 a 6). En Consola no se usa.
    // Este es el ÚNICO lugar donde se decide qué subclase crear (la garantía la pone cada subclase).
    public String registrarProducto(int tipo, String[] textos, int[] numeros, boolean[] banderas, int subtipo) {
        if (textos[0] == null || textos[0].trim().isEmpty()) {
            return "El nombre no puede estar vacío.";
        }
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] < 0) {
                return "Datos inválidos: ningún número puede ser negativo.";
            }
        }
        if (inventario.estaLleno()) {
            return "El inventario está lleno.";
        }

        Producto p;
        switch (tipo) {
            case 1:
                p = new Consola(textos, numeros, banderas);
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

        if (!inventario.registrarProducto(p)) {
            return "No se pudo registrar el producto.";
        }
        return null;
    }

    // ---------- REPONER STOCK (por ID) ----------
    public String reponerProducto(int id, int cantidad, int costoUnitario, String proveedor) {
        Producto p = inventario.buscarPorId(id);
        if (p == null) {
            return "Ese producto no existe.";
        }
        if (cantidad <= 0 || costoUnitario <= 0) {
            return "La cantidad y el costo deben ser mayores que 0.";
        }

        int costoAnterior = p.getCostoCompra();   // se guarda ANTES de cambiarlo, para comparar
        p.reponer(cantidad);
        p.setCostoCompra(costoUnitario);

        String texto = "=== COMPROBANTE DE COMPRA ===\n"
                + "Proveedor: " + proveedor + "\n"
                + "Producto: " + p.getNombre() + "\n"
                + "Cantidad: " + cantidad + "\n"
                + "Costo por unidad: $" + costoUnitario + "\n"
                + "Total: $" + (cantidad * costoUnitario) + "\n"
                + "Stock actual: " + p.getStock();
        if (costoUnitario > costoAnterior) {
            texto += "\nOJO: el costo subió. Conviene revisar la ganancia mínima.";
        }
        this.ultimoComprobante = texto;
        return null;
    }

    // ---------- RETIRAR PRODUCTO (por ID) ----------
    public boolean retirarProducto(int id) {
        return inventario.retirarProducto(id);
    }

    // ---------- VENTA ----------
    // Devuelve la Factura, o null si el medio de pago no es válido (no se fía).
    public Factura iniciarVenta(String cliente, String telefono, int medio) {
        String pago;
        if (medio == 1) {
            pago = "Efectivo";
        } else if (medio == 2) {
            pago = "Transferencia";
        } else {
            return null;
        }
        return new Factura(cliente, telefono, pago);
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

    // true si el producto llegó al stock mínimo o quedó por debajo.
    public boolean alertaReposicion(int id) {
        Producto p = inventario.buscarPorId(id);
        return p != null && !p.hayStockSobra();
    }

    // Cierra la venta: la registra en Caja y avanza el consecutivo. Devuelve error o null.
    public String cerrarVenta(Factura factura) {
        if (factura.getNumero() == 0) {
            return "No se agregaron productos. Venta cancelada.";
        }
        this.ultimaFactura = "======= FACTURA #" + consecutivoFactura + " =======\n" + factura;
        caja.registrarVenta(factura);
        consecutivoFactura++;
        return null;
    }

    // ---------- BUSCAR ----------
    // Búsqueda por parte del nombre, sin importar mayúsculas (la hace Inventario con contains).
    public String buscarPorTexto(String texto) {
        String lista = listarPorNombre(texto);
        if (lista == null) {
            return "Sin coincidencias.";
        }
        return lista;
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

    // ---------- CUADRÍCULA ----------
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

    // ---------- LISTAS Y REPORTES ----------
    public String listaDeCompras() {
        String lista = inventario.porAgotarse();   // "" si no hay nada por agotarse
        if (lista.trim().isEmpty()) {
            return "No hay productos por agotarse.";
        }
        return "=== LISTA DE COMPRAS ===\nEstá por agotarse:\n" + lista;
    }

    public String cierreDelDia() {
        return caja.resumenDelDia();
    }

    // ---------- CONTABILIDAD ----------
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

    public String toString() {
        return "Tienda [próxima factura #" + consecutivoFactura + "]";
    }
}