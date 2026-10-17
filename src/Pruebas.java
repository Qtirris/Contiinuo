/*
Clase para hacer pruebas mientras las clases no estan eonectadas en Contiinuo
*/

public class Pruebas {
    public static void main(String[] args) {
    Inventario.registrarProducto(new Producto("Bafle1","Sonido","Estante 1",10_000, 15_000, 10, 3, 0));
    Inventario.registrarProducto(new Producto("Xbox360","Consolas","Estante 1",500_000, 600_000, 5, 2, 6));
    Inventario.registrarProducto(new Producto("Radio1","Sonido","Repisa",70_000, 100_000, 4, 0, 3));
    Inventario.registrarProducto(new Producto("Bafle2","Sonido","Mostrador",600_000, 900_000, 8, 2, 6));
    Inventario.registrarProducto(new Producto("Power Bank","Otros","Colgado",50_000, 70_000, 3, 0, 1));
    Inventario.registrarProducto(new Producto("Bafle3","Sonido","Estante 1",10_000, 15_000, 10, 3, 0));
    Inventario.registrarProducto(new Producto("Xbox720","Consolas","Estante 1",500_000, 600_000, 5, 2, 6));
    Inventario.registrarProducto(new Producto("Radio2","Sonido","Repisa",70_000, 100_000, 4, 0, 3));
    Inventario.registrarProducto(new Producto("Bafle4","Sonido","Mostrador",600_000, 900_000, 8, 2, 6));
    //Inventario.registrarProducto(new Producto("Power Bank2","Otros","Colgado",50_000, 70_000, 3, 0, 1));

    Inventario.mostrar();
    System.out.println("============================================");
    
    Inventario.producto1.setNombre("Nuevo Bafle1");
    System.out.println("Nombre: "+Inventario.producto1.getNombre());
    
    Inventario.producto1.setUbicacion("Nuevo Ubi");
    System.out.println("Ubi: "+Inventario.producto1.getUbicacion());
    
    Inventario.producto1.setCostoCompra(2000);
    System.out.println("CostoCompra: "+Inventario.producto1.getCostoCompra());
    
    Inventario.producto1.setGananciaMinima(50_000);
    System.out.println("GananciaMinima1: "+Inventario.producto1.getGananciaMinima());
    System.out.println("PrecioMinimo1: "+Inventario.producto1.getPrecioMinimo());
    
    Inventario.producto1.setStock(30);
    System.out.println("Stock: "+Inventario.producto1.getStock());
    
    Inventario.producto1.setStockMinimo(67);
    System.out.println("StockMinimo: "+Inventario.producto1.getStockMinimo());
    
    Inventario.producto1.setGarantia(1_000);
    System.out.println("Garantia: "+Inventario.producto1.getGarantia());
    
    Inventario.producto1.setPrecioMinimo(67_000);
    System.out.println("PrecioMinimo2: "+Inventario.producto1.getPrecioMinimo());
    System.out.println("GananciaMinima2: "+Inventario.producto1.getGananciaMinima());
    
    System.out.println("ID: "+Inventario.producto1.getID());
    System.out.println("Categoria: "+Inventario.producto1.getCategoria());
    
    System.out.println("Hay?: "+Inventario.producto1.hayStock());
    System.out.println("Hay de sobra?(reponer): "+Inventario.producto1.hayStockSobra());
    
    
    System.out.println("============================================");
    
    Inventario.retirarProducto(6);
    Inventario.retirarProducto(7);
    Inventario.retirarProducto(8);
    Inventario.retirarProducto(9);
    Inventario.retirarProducto(10);
    
    Inventario.mostrar();
    
    
    System.out.println("============================================");
    Inventario.checkStock();
    System.out.println("============================================");

    Factura Factura1 = new Factura("Johan","3112557310","Nequi");
    Factura1.agregaritem(Inventario.producto1, 15, 250_000);
    Factura1.agregaritem(Inventario.producto3, 2, 20_000);
    Factura1.agregaritem(Inventario.producto4, 6, 70_000);
    
    Factura1.imprimir();
    
    System.out.println(Factura1.getNombreCliente());
    System.out.println(Factura1.getDetalle());
    System.out.println(Factura1.getGanancia());
    System.out.println(Factura1.getMedioDePago());
    System.out.println(Factura1.getNumero());
    System.out.println(Factura1.getTotal());
    }
    //Aparentemente todo va bien :)
}
