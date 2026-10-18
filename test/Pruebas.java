/*
Clase para hacer pruebas mientras las clases no estan eonectadas en Contiinuo
new Producto("Bafle1","Sonido","Estante 1",10_000, 5_000, 10, 3, 0))
new Producto("Xbox360","Consolas","Estante 1",500_000, 120_000, 5, 2, 6))
new Producto("Radio1","Sonido","Repisa",70_000, 30_000, 4, 0, 3))
new Producto("Bafle2","Sonido","Mostrador",600_000, 300_000, 8, 2, 6))
new Producto("Power Bank","Otros","Colgado",50_000, 20_000, 3, 0, 1))
new Producto("Bafle3","Sonido","Estante 1",10_000, 12_000, 10, 3, 0))
new Producto("Xbox720","Consolas","Estante 1",500_000, 130_000, 5, 2, 6))
new Producto("Radio2","Sonido","Repisa",70_000, 10_000, 4, 0, 3))
new Producto("Bafle4","Sonido","Mostrador",600_000, 100_000, 8, 2, 6))
new Producto("Power Bank2","Otros","Colgado",50_000, 10_000, 3, 0, 1))
*/

public class Pruebas {
    public static void main(String[] args) {
        Inventario local= new Inventario();
        //===REGISTRAR PRODUCTOS===
        local.registrarProducto(new Producto("Bafle1","Sonido","Estante 1",10_000, 5_000, 10, 30, 0));
        local.registrarProducto(new Producto("Xbox360","Consolas","Estante 1",500_000, 120_000, 5, 2, 6));
        local.registrarProducto(new Producto("Radio1","Sonido","Repisa",70_000, 30_000, 4, 0, 3));
        local.registrarProducto(new Producto("Bafle2","Sonido","Mostrador",600_000, 300_000, 8, 2, 6));
        local.registrarProducto(new Producto("Power Bank","Otros","Colgado",50_000, 20_000, 3, 0, 1));
        local.registrarProducto(new Producto("Bafle3","Sonido","Estante 1",10_000, 12_000, 10, 3, 0));
        local.registrarProducto(new Producto("Xbox720","Consolas","Estante 1",500_000, 130_000, 5, 2, 6));
        local.registrarProducto(new Producto("Radio2","Sonido","Repisa",70_000, 10_000, 4, 0, 3));
        local.registrarProducto(new Producto("Bafle4","Sonido","Mostrador",600_000, 100_000, 8, 2, 6));
        local.registrarProducto(new Producto("Power Bank2","Otros","Colgado",50_000, 10_000, 3, 15, 1));
        
        //===MOSTRAR===
        System.out.println("\n========================INICIO PRUEBAS===================================\n");
        
        local.mostrar();
        System.out.println("Lleno? "+local.estaLleno());
        
        System.out.println("\n=========================================================================\n");
        
        local.revisarStock();
        
        System.out.println("\n=========================================================================\n");
        
        local.porAgotarse();
        
        System.out.println("\n=========================================================================\n");
        
        //===ACTUALIZAR PRODUCTOS===
        local.retirarProducto(9);
        local.retirarProducto(6);
        local.retirarProducto(2);

        local.registrarProducto(new Producto("PS2","Consolas","Mostrador",300_000, 60_000, 4, 0, 3));
        local.registrarProducto(new Producto("JBL","Sonido","Mostrador",600_000, 1400_000, 1, 7, 6));
        local.registrarProducto(new Producto("Caragador","Otros","Colgado",5_000, 10_000, 20, 15, 1));
        local.registrarProducto(new Producto("Caragador2","Otros","Colgado",5_000, 10_000, 20, 15, 1));
        
        //===BUSCAR POR NOMBRE Y SETTERS Y GETTERS===
        Producto productoPorNombre=local.buscarPorNombre("xbox720");
        productoPorNombre.setNombre("NuevoXbox720");
        productoPorNombre.setUbicacion("NuevaUbi");
        productoPorNombre.setCostoCompra(10_000);
        productoPorNombre.setGananciaMinima(1_000_000);
        productoPorNombre.setStock(1);
        productoPorNombre.setStockMinimo(10);
        productoPorNombre.setGarantia(24);
        productoPorNombre.calcularGanancia(3_000_000);
        productoPorNombre.reponer(2);
        productoPorNombre.descontar(3); // deberian quedar 5+2-3=4
        System.out.println("Stock Vendido de Xbox720: "+productoPorNombre.getStockVendido());
        
        System.out.println("\n=========================================================================\n");
        
        local.mostrar();
        System.out.println("Lleno? "+local.estaLleno());

        System.out.println("\n=========================================================================\n");
        
        local.revisarStock();
        
        System.out.println("\n=========================================================================\n");
        
        local.porAgotarse();
        
        System.out.println("\n=========================================================================\n");
        
        
        
        
        
        
    }
}