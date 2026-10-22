import java.util.Scanner;

//es de vital importancia para leer los datos proporcionados por el usuario
//y para asi seguir las reglas de las otras clases.

public class Tienda {
    //atributos de la clase contiinuo, vitales para el funcionamiento del programa.
    private Inventario inventario;
    private Caja caja;
    private Contabilidad contabilidad; 
    private int consecutivofactura;
    private Scanner sc;


    public Tienda(Scanner sc){
        //Constructor y parámetros
        this.inventario= new Inventario();
        this.caja=new Caja();
        this.contabilidad=new Contabilidad(this.caja, this.inventario, sc);
        this.consecutivofactura=1;
        this.sc=sc;
    }

    //getters, para que las otras clases puedan tomar estos atributos.

    public Inventario getInventario(){return inventario;}

    public Caja getCaja(){
        return caja;
    }

    public int getConsecutivoFactura(){
        return consecutivofactura;
    }

    //Métodos, para retornar.

    //listaDeCompras, muestra los productos que se estan acabando y hace una lista en base a ello.

    public void listaDeCompras(){
        System.out.println();
        System.out.println("=== LISTA DE COMPRAS ===");
        System.out.println();

        System.out.println(inventario.porAgotarse().trim().isEmpty()?
        "No hay productos por agotarse":"Está por agotarse:\n"+inventario.porAgotarse());
    }



    //registrar un producto nuevo.

    public void registrarProducto(){
        System.out.println();
        System.out.println("====REGISTRAR PRODUCTO====");
        System.out.println();

        //REGLA, la bodega tiene maximo 10 productos.
        if(inventario.estaLleno()){
            System.out.println("El inventario está lleno (máximo 10 productos");
            return;
        }

        String nombre= "";
        while (nombre.equals("")){
            System.out.print("Nombre: ");
            nombre = sc.nextLine();
        }

        //REGLA, no puede haber dos productos con el mismo nombre
        if(inventario.buscarPorNombre(nombre) != null) {
            System.out.println("Ya existe un producto con ese nombre.");
            return;
        }

        System.out.print("Categoria: ");
        String categoria = sc.nextLine();

        System.out.print("Ubicación: ");
        String ubicacion = sc.nextLine();

        int costo = leerEnteros("Costo de compra: ");
        int gananciaMinima = leerEnteros("Ganancia mínima: ");
        int stock= leerEnteros("Stock inicial: ");
        int stockMinimo= leerEnteros("Stock mínimo: ");
        int garantia = leerEnteros("Meses de garantía (0 si no tiene): ");

        //Mismo orden que el constructor de producto para que no haya problemas.

        Producto prdt= new Producto(nombre,categoria,ubicacion,costo, gananciaMinima, stock, stockMinimo, garantia);

        if(inventario.registrarProducto(prdt)){
            System.out.println();
            System.out.println("Producto registrado: "+prdt);
            System.out.println();

        } else {
            System.out.println("No se pudo registrar el producto.");
        }
    }

    //Creamos el ayudante para leer números enteros.

    private int leerEnteros(String nombre){
        System.out.print(nombre);
        int valor = Integer.parseInt(sc.nextLine());
        while ( valor < 0){
            System.out.print("El valor no puede ser negativo.");
            valor=Integer.parseInt(sc.nextLine());
        }

        return valor;
    }

    //Reponer sotck, se compra mercancia de un producto para reponer el stock.

    public void reponerProducto(){
        System.out.println();
        System.out.println("===REPONER STOCK===");
        System.out.println();

        //1. Se busca producto en la bodega.

        System.out.print("Nombre del producto: ");
        String nombre = sc.nextLine();
        Producto prdt = inventario.buscarPorNombre(nombre);

        if(prdt == null) {
            System.out.print("Ese producto no existe. ¿Quieres registrarlo ahora? (s/n): ");
            if (sc.nextLine().equals("s")) {
                registrarProducto();
            }
            return;//se sale de la reposicion y se hace de nuevo con el producto ya creado.
        }

            //2.Pedir los datos de la compra
            System.out.println();
            int cantidad = leerEnteros("Cantidad comprada: ");
            System.out.println();

            while(cantidad == 0){
                System.out.println("Debe ser mayor que 0.");

                System.out.println();
                cantidad = leerEnteros("Cantidad comprada: ");
                System.out.println();

            }
            
            int costoUnitario =leerEnteros("Costo por unidad: ");
            System.out.println();

            while (costoUnitario == 0){
                System.out.println("Debe ser mayor que 0.");
                System.out.println();
                costoUnitario = leerEnteros("Costo por unidad: ");
                System.out.println();
            }

            System.out.print("Proveedor: ");
            String proveedor = sc.nextLine();

            //3. Guardar el costo de antes, para compararlo después.
            int costoAnterior = prdt.getCostoCompra();

            //4. El producto suma al stock y actualiza su costo
            prdt.reponer(cantidad);
            prdt.setCostoCompra(costoUnitario);

            //REGLA, si el costo subió se alerta al usuario.
            if(costoUnitario > costoAnterior){
                System.out.println("OJO: el costo subió. Conviene revisar la ganancia mínima.");
            }

            //5. Comprobante de compra.
            System.out.println();
            System.out.println("=== COMPROBANTE DE COMPRA ===");
            System.out.println();
            System.out.println("Proveedor: "+ proveedor);
            System.out.println("Producto: "+prdt.getNombre());
            System.out.println("Cantidad: "+cantidad);
            System.out.println("Costo por unidad: $"+costoUnitario);
            System.out.println("Tota: $"+(cantidad * costoUnitario));
            System.out.println("Stock actual: "+prdt.getStock());

    }


    //Metodo retirar producto del inventario.

    public void retirarProducto(){
        System.out.println();
        System.out.println("===RETIRAR PRODUCTO===");
        System.out.println();

        System.out.print("Nombre del producto: ");
        String nombre = sc.nextLine();
        Producto prdt = inventario.buscarPorNombre(nombre);

        if(prdt == null){
            System.out.println("No se encontró ese producto.");
            return;
        }

        System.out.println(prdt);
        System.out.print("¿Seguro que quieres retirarlo del inventario? (s/n): ");

        if(sc.nextLine().equals("s")){
            //el inventario retira el producto por su ID.
            if(inventario.retirarProducto(prdt.getID())){
                System.out.println("Producto retirado: "+prdt.getNombre());

            } else {
                System.out.println("No se pudo retirar el producto.");
            }

        } else {
            System.out.println("Operación cancelada.");
        }
    }

    public void administrarInventario(){
        int opcion= -1;
        while (opcion != 0){
            System.out.println();
            System.out.println("===ADMINISTRAR INVENTARIO===");
            System.out.println();

            System.out.println("1.  Registrar producto.");
            System.out.println("2.  Reponer stock.");
            System.out.println("3.  Retirar producto.");
            System.out.println("4.  Ver inventario.");
            System.out.println("5.  Lista de compras.");
            System.out.println("0.  Volver.");

            System.out.println();
            opcion= leerEnteros("Opción: ");
            System.out.println();

            switch (opcion){
                case 1: registrarProducto(); break;
                case 2: reponerProducto(); break;// Reponer Stock
                case 3: retirarProducto(); break;
                case 4: inventario.mostrar(); break;
                case 5: listaDeCompras(); break;
                case 0: break;
                default: System.out.println("opción no válida. Intenta de nuevo."); break;
            }
        }
    }

    // Se crea la regla para buscar productos, en donde se llama a inventario para ver si está.
    public void buscarProducto() {
        System.out.println();
        System.out.println("===BUSCAR PRODUCTO===");
        System.out.println();

        System.out.print("Nombre del producto: ");
        String nombreproducto = sc.nextLine();
        Producto prdt = inventario.buscarPorNombre(nombreproducto);

        if (prdt == null) {
            System.out.println("No se encontró ese producto.");
            return;

        }

        System.out.println(prdt); //nombre, stock, ubicacion, garantia

        System.out.print("¿Quiere cambiar su ubicación(s/n): ");
        if (sc.nextLine().equals("s")) {
            System.out.print("Nueva ubicación: ");
            String nueva = sc.nextLine();
            prdt.setUbicacion(nueva);
            System.out.println("Ubicación actualizada "+prdt);

        }
        else if(!sc.nextLine().equals("n")){System.out.println("Opción inválida.");}
        System.out.println();
    }

    //Llama a caja para obtener los datos del cierre del dia e imprimirlos.
    public void cerrarDia(){
        System.out.println();
        System.out.println("===CIERRE DEL DÍA===");
        System.out.println();

        System.out.println(caja.resumenDelDia());}


    public void abrirContabilidad() {
        contabilidad.mostrarMenu();
    }

    //Llama a contabilidad para obtener el resumen contable e imprimirlo.
    public void mostrarResumenContable(){
        System.out.println(contabilidad.generarResumenGeneral());
    }

    public void registrarVenta(){
        System.out.println();
        System.out.println("=== NUEVA VENTA ===");
        System.out.println();

        //1. Datos del cliente.

        System.out.print("Nombre del cliente: ");
        String cliente = sc.nextLine();
        System.out.print("Telefono del cliente: ");
        String telefono = sc.nextLine();

        //Regla, sin fiado. Solo efectivo o transferencia.

        String med = "";
        while(med.equals("")){
            System.out.println();
            System.out.println("Medio de pago: 1. Efectivo 2. Transferencia");
            System.out.println();

            int opcionPago= leerEnteros("Opción: ");
            System.out.println();
            if(opcionPago == 1){
                med = "Efectivo";
            } else if (opcionPago == 2){
                med = "Transferencia";
            }
            else{
                System.out.println("Solo se acepta afectivo o transferencia(no se fía).");
            }
        }

        Factura factura = new Factura(cliente,telefono,med);

        //2. Ciclo de productos, es decir se repiten hasta que escriban 0.
        int items = 0;
        while(true){
            System.out.print("Producto (0 para terminar): ");
            String nombre = sc.nextLine();
            System.out.println();
            if(nombre.equals("0")){
                break;
            }
            Producto prdt= inventario.buscarPorNombre(nombre);
            if(prdt == null){
                System.out.println("Ese producto no existe.");
                continue;
            }
            System.out.println();
            int cantidad = leerEnteros("Cantidad: ");
            System.out.println();
            while(cantidad == 0){
                System.out.println("Debe ser mayor que 0.");
                System.out.println();
                cantidad = leerEnteros("Cantidad: ");
                System.out.println();
            }

            //Regla, stock suficiente
            if(prdt.getStock()<cantidad){
                System.out.println();
                System.out.println("Stock insuficiente. Disponibles: "+ prdt.getStock());
                System.out.println();
                continue;
            }

            //REGÑA, ganancia minima (el precio no puede ser menor al precio minimo)
            System.out.println();
            int precio= leerEnteros("Precio final por unidad(presione 0 para omitir este producto: ");
            System.out.println();

            while(precio !=0 && precio < prdt.getPrecioMinimo()){
                System.out.println("Precio muy bajo. El mínimo es "+prdt.getPrecioMinimo());
                System.out.println();
                precio = leerEnteros("Precio final por unidad ( presione 0 para omitir este producto: ");
                System.out.println();

            }
            if(precio == 0){
                System.out.println("Producto omitido.");
                continue;
            }

            //todo valido: la factura anota el producto y el mismo descuenta su stock.

            factura.agregarItem(prdt, cantidad, precio);
            items++;

            //REGLA, alerta de reposicion
            if(!prdt.hayStockSobra()){
                System.out.println("¡Toca comprar!"+prdt.getNombre()+"está en el stock mínimo o por debajo.");
            }
        }

        //3. si no se agrega nada no hay factura.
        if(items == 0){
            System.out.println("No se agregaron productos. Venta cancelada.");
            return;
        }

        //4. Mostrar la factura, guardarla y registrarla en caja
        System.out.println();
        System.out.println("======= FACTURA #"+ consecutivofactura + " =======" );
        System.out.println();
        
        factura.imprimir();
        factura.guardar();
        consecutivofactura++;
        //la proxima factura tendrá el número siguiente.
        caja.registrarVenta(factura);
    }

    public String toString(){
        return "Tienda [ próxima factura #"+ consecutivofactura;
    }







}