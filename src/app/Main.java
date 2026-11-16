package app;

import servicio.Tienda;
import modelo.Factura;

// Main: SOLO habla con el usuario (menús, preguntas, imprimir).
// Las reglas del negocio viven en Tienda. Los "if" de Main solo miran si Tienda
// devolvió un error (distinto de null) para mostrarlo.
public class Main {

    public static void main(String[] args) {
        Tienda tienda = new Tienda();
        menuPrincipal(tienda);
    }

    // ================= MENÚ PRINCIPAL =================
    private static void menuPrincipal(Tienda tienda) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n=== APPLAB ===");
            System.out.println("1. Administrar inventario");
            System.out.println("2. Registrar venta");
            System.out.println("3. Buscar producto");
            System.out.println("4. Cerrar día");
            System.out.println("5. Consultar contabilidad");
            System.out.println("0. Salir");
            opcion = Lector.leerEntero("Opción: ");

            switch (opcion) {
                case 1: menuInventario(tienda); break;
                case 2: menuVenta(tienda); break;
                case 3: menuBuscar(tienda); break;
                case 4: System.out.println(tienda.cierreDelDia()); break;
                case 5: menuContabilidad(tienda); break;
                case 0: System.out.println("¡Hasta luego!"); break;
                default: System.out.println("Opción no válida. Intenta de nuevo."); break;
            }
        }
    }

    // ================= AYUDANTE: elegir un producto por nombre =================
    // La búsqueda es parcial: "xbox" encuentra "Xbox One" y "Xbox 360".
    // Devuelve el ID del producto, o 0 si no hay ninguno / el usuario cancela.
    // Si hay varios, muestra la lista y pide el ID.
    private static int elegirProducto(Tienda tienda, String nombre) {
        int id = tienda.idPorNombre(nombre);
        if (id == 0) {
            System.out.println("No se encontró ese producto.");
            return 0;
        }
        if (id == -1) {
            System.out.println("Hay varios productos que coinciden:");
            System.out.println(tienda.listarPorNombre(nombre));
            id = Lector.leerEntero("Escribe el ID del que quieres (0 para cancelar): ");
        }
        return id;
    }

    // ================= SUBMENÚ INVENTARIO =================
    private static void menuInventario(Tienda tienda) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n=== ADMINISTRAR INVENTARIO ===");
            System.out.println("1. Registrar producto");
            System.out.println("2. Reponer stock");
            System.out.println("3. Retirar producto");
            System.out.println("4. Ver inventario (cuadrícula)");
            System.out.println("5. Lista de compras");
            System.out.println("0. Volver");
            opcion = Lector.leerEntero("Opción: ");

            switch (opcion) {
                case 1: registrarProducto(tienda); break;
                case 2: reponerProducto(tienda); break;
                case 3: retirarProducto(tienda); break;
                case 4: verCuadricula(tienda); break;
                case 5: System.out.println(tienda.listaDeCompras()); break;
                case 0: break;
                default: System.out.println("Opción no válida. Intenta de nuevo."); break;
            }
        }
    }

    // Aquí Main sí pregunta cosas distintas según el tipo (es solo hablar con el usuario).
    // Tienda es quien decide qué subclase crear.
    private static void registrarProducto(Tienda tienda) {
        System.out.println("=== REGISTRAR PRODUCTO ===");
        int tipo = Lector.leerEntero("Tipo (1 Consola, 2 Sonido, 3 Varios): ");
        if (tipo < 1 || tipo > 3) {
            System.out.println("Tipo de producto no válido.");
            return;
        }
        String nombre = Lector.leerTexto("Nombre: ");
        String ubicacion = Lector.leerTexto("Ubicación: ");
        int costo = Lector.leerEntero("Costo de compra: ");
        int ganancia = Lector.leerEntero("Ganancia mínima: ");
        int stock = Lector.leerEntero("Stock inicial: ");
        int minimo = Lector.leerEntero("Stock mínimo: ");

        String[] textos = {nombre, ubicacion};
        int[] numeros = {costo, ganancia, stock, minimo};
        boolean[] banderas = {};
        int subtipo = 0;

        switch (tipo) {
            case 1:   // Consola
                textos = new String[]{nombre, ubicacion, Lector.leerTexto("Plataforma (ej. Xbox One): ")};
                banderas = new boolean[]{
                        Lector.leerSiNo("¿Es original?"),
                        Lector.leerSiNo("¿Está chipeada?"),
                        Lector.leerSiNo("¿Es portátil?")};
                break;
            case 2:   // Sonido
                textos = new String[]{nombre, ubicacion, Lector.leerTexto("Marca: ")};
                subtipo = Lector.leerEntero("Clase de sonido (1 Cabina, 2 Bafle, 3 Parlante, 4 Audífono, 5 Radio): ");
                int potencia = Lector.leerEntero("Potencia (W): ");
                numeros = new int[]{costo, ganancia, stock, minimo, potencia};
                banderas = new boolean[]{
                        Lector.leerSiNo("¿Es original?"),
                        Lector.leerSiNo("¿Tiene micrófono?"),
                        Lector.leerSiNo("¿Tiene batería?")};
                break;
            default:  // Varios (cables, gamepads, power banks, maletas...)
                textos = new String[]{nombre, ubicacion,
                        Lector.leerTexto("Marca (puede ir vacía): "),
                        Lector.leerTexto("Especificación (ej. USB-C, puede ir vacía): ")};
                subtipo = Lector.leerEntero("Clase (1 Cable, 2 Gamepad, 3 Power bank, 4 Maleta, 5 Sombrilla, 6 Otro): ");
                banderas = new boolean[]{Lector.leerSiNo("¿Es original?")};
                break;
        }

        String error = tienda.registrarProducto(tipo, textos, numeros, banderas, subtipo);
        System.out.println(error == null ? "Producto registrado." : error);
    }

    private static void reponerProducto(Tienda tienda) {
        System.out.println("=== REPONER STOCK ===");
        int id = elegirProducto(tienda, Lector.leerTexto("Nombre del producto: "));
        if (id == 0) {
            return;
        }
        int cantidad = Lector.leerEntero("Cantidad comprada: ");
        int costo = Lector.leerEntero("Costo por unidad: ");
        String proveedor = Lector.leerTexto("Proveedor: ");

        String error = tienda.reponerProducto(id, cantidad, costo, proveedor);
        System.out.println(error == null ? tienda.ultimoComprobante() : error);
    }

    private static void retirarProducto(Tienda tienda) {
        System.out.println("=== RETIRAR PRODUCTO ===");
        int id = elegirProducto(tienda, Lector.leerTexto("Nombre del producto: "));
        if (id == 0) {
            return;
        }
        String detalle = tienda.detalleProducto(id);
        if (detalle == null) {
            System.out.println("No existe un producto con ese ID.");
            return;
        }
        System.out.println(detalle);
        if (Lector.leerSiNo("¿Seguro que quieres retirarlo del inventario?")) {
            System.out.println(tienda.retirarProducto(id) ? "Producto retirado." : "No se pudo retirar el producto.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    // Muestra la cuadrícula y deja consultar una casilla (y reponer o retirar desde ahí).
    private static void verCuadricula(Tienda tienda) {
        System.out.println("\n=== INVENTARIO ===");
        System.out.println(tienda.cuadricula());

        if (!Lector.leerSiNo("¿Quieres consultar una casilla?")) {
            return;
        }
        int fila = Lector.leerEntero("Fila: ");
        int columna = Lector.leerEntero("Columna: ");
        String detalle = tienda.consultarCasilla(fila, columna);
        if (detalle == null) {
            System.out.println("Esa casilla está libre o no existe.");
            return;
        }
        System.out.println(detalle);

        int opcion = Lector.leerEntero("¿Qué desea hacer? 1. Reponer   2. Retirar   0. Volver: ");
        if (opcion == 1) {
            int cantidad = Lector.leerEntero("Cantidad comprada: ");
            int costo = Lector.leerEntero("Costo por unidad: ");
            String proveedor = Lector.leerTexto("Proveedor: ");
            String error = tienda.reponerEnCasilla(fila, columna, cantidad, costo, proveedor);
            System.out.println(error == null ? tienda.ultimoComprobante() : error);
        } else if (opcion == 2) {
            if (Lector.leerSiNo("¿Seguro que quieres liberar la casilla?")) {
                System.out.println(tienda.retirarDeCasilla(fila, columna) ? "Producto retirado de la casilla." : "No se pudo retirar.");
            }
        }
    }

    // ================= VENTA =================
    private static void menuVenta(Tienda tienda) {
        System.out.println("\n=== NUEVA VENTA ===");
        String cliente = Lector.leerTexto("Nombre del cliente: ");
        String telefono = Lector.leerTexto("Teléfono del cliente: ");
        int medio = Lector.leerEntero("Medio de pago (1. Efectivo 2. Transferencia): ");

        Factura factura = tienda.iniciarVenta(cliente, telefono, medio);
        if (factura == null) {
            System.out.println("Solo se acepta efectivo o transferencia (no se fía).");
            return;
        }

        String producto = Lector.leerTexto("Producto (0 para terminar): ");
        while (!producto.equals("0")) {
            int id = elegirProducto(tienda, producto);
            if (id != 0) {
                int cantidad = Lector.leerEntero("Cantidad: ");
                int precio = Lector.leerEntero("Precio final por unidad: ");

                String error = tienda.agregarItem(factura, id, cantidad, precio);
                if (error != null) {
                    System.out.println(error);   // Main solo muestra el mensaje
                } else if (tienda.alertaReposicion(id)) {
                    System.out.println("¡Toca comprar! " + producto + " está en el stock mínimo o por debajo.");
                }
            }
            producto = Lector.leerTexto("Producto (0 para terminar): ");
        }

        String error = tienda.cerrarVenta(factura);
        System.out.println(error == null ? tienda.ultimaFactura() : error);
    }

    // ================= BUSCAR =================
    private static void menuBuscar(Tienda tienda) {
        System.out.println("\n=== BUSCAR PRODUCTO ===");
        String texto = Lector.leerTexto("Escribe parte del nombre: ");
        System.out.println(tienda.buscarPorTexto(texto));

        if (Lector.leerSiNo("¿Quieres cambiar la ubicación de algún producto?")) {
            int id = elegirProducto(tienda, Lector.leerTexto("Nombre del producto: "));
            if (id != 0) {
                String nueva = Lector.leerTexto("Nueva ubicación: ");
                System.out.println(tienda.cambiarUbicacion(id, nueva) ? "Ubicación actualizada." : "No se pudo cambiar la ubicación.");
            }
        }
    }

    // ================= CONTABILIDAD =================
    private static void menuContabilidad(Tienda tienda) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n=== CONTABILIDAD ===");
            System.out.println("1. Resumen general");
            System.out.println("2. Producto más vendido");
            System.out.println("3. Producto menos vendido");
            System.out.println("4. Producto que genera más ganancias");
            System.out.println("5. Ventas por medio de pago");
            System.out.println("6. Consultar total vendido");
            System.out.println("7. Consultar ganancias");
            System.out.println("0. Volver");
            opcion = Lector.leerEntero("Opción: ");

            switch (opcion) {
                case 1: System.out.println(tienda.resumenContable()); break;
                case 2: System.out.println(tienda.reporteMasVendido()); break;
                case 3: System.out.println(tienda.reporteMenosVendido()); break;
                case 4: System.out.println(tienda.reporteMasRentable()); break;
                case 5: System.out.println(tienda.reporteMedioDePago()); break;
                case 6: System.out.println(tienda.totalVendido()); break;
                case 7: System.out.println(tienda.gananciaTotal()); break;
                case 0: break;
                default: System.out.println("Opción no válida."); break;
            }
        }
    }
}
