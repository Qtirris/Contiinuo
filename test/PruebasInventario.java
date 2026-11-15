/*
Clase para hacer pruebas mientras las clases no estan eonectadas en Contiinuo
new Consola(new String[]{"Xbox 360","Arriba","Xbox"},new int[]{300_000,200_000,3,1},new boolean[]{true,true,false}));
new Consola(new String[]{"Xbox One","Arriba","Xbox"},new int[]{500_000,300_000,2,2},new boolean[]{true,false,false}));
new Consola(new String[]{"Nintendo Switch","Abajo","Nintendo"},new int[]{800_000,200_000,5,3},new boolean[]{true,false,true}));
new Sonido(new String[]{"GEDI-G 1016","Derecha","GEDI-G"},new int[]{150_000,60_000,7,3,100},new boolean[]{true,true,true},Sonido.tipoSonido.CABINA));
new Sonido(new String[]{"NIA 2040","IZquierda","NIA"},new int[]{250_000,110_000,2,1,150},new boolean[]{true,true,false},Sonido.tipoSonido.CABINA));
new Sonido(new String[]{"AIIR PODS","Derecha","GEDI-G"},new int[]{20_000,50_000,7,3,10},new boolean[]{false,true,true},Sonido.tipoSonido.AUDIFONO));
new Sonido(new String[]{"JBL BOOMBOX3","Abajo","JBL"},new int[]{60_000,50_000,3,4,30},new boolean[]{false,false,true},Sonido.tipoSonido.BAFLE));*/

import java.util.ArrayList;

import modelo.*;
import servicio.Inventario;

public class PruebasInventario {
    public static void main(String[] args) {
        Inventario local= new Inventario();
        //===REGISTRAR PRODUCTOS===
        local.registrarProducto(new Consola(new String[]{"Xbox 360","Arriba","Xbox"},new int[]{300_000,200_000,3,1},new boolean[]{true,true,false}));
        local.registrarProducto(new Consola(new String[]{"Xbox One","Arriba","Xbox"},new int[]{500_000,300_000,2,2},new boolean[]{true,false,false}));
        local.registrarProducto(new Consola(new String[]{"Nintendo Switch","Abajo","Nintendo"},new int[]{800_000,200_000,5,3},new boolean[]{true,false,true}));
        local.registrarProducto(new Sonido(new String[]{"GEDI-G 1016","Derecha","GEDI-G"},new int[]{150_000,60_000,7,3,100},new boolean[]{true,true,true},Sonido.tipoSonido.CABINA));
        local.registrarProducto(new Sonido(new String[]{"NIA 2040","IZquierda","NIA"},new int[]{250_000,110_000,2,1,150},new boolean[]{true,true,false},Sonido.tipoSonido.CABINA));
        local.registrarProducto(new Sonido(new String[]{"AIIR PODS","Derecha","GEDI-G"},new int[]{20_000,50_000,7,3,10},new boolean[]{false,true,true},Sonido.tipoSonido.AUDIFONO));
        local.registrarProducto(new Sonido(new String[]{"JBL BOOMBOX3","Abajo","JBL"},new int[]{60_000,50_000,3,4,30},new boolean[]{false,false,true},Sonido.tipoSonido.BAFLE));
        
        //===MOSTRAR===
        System.out.println("\n========================INICIO PRUEBAS===================================\n");
        
        local.mostrar();
        
        System.out.println("\n=========================================================================\n");
        
        local.revisarStock();
        
        System.out.println("\n=========================================================================\n");
        
        local.porAgotarse();
        
        System.out.println("\n=========================================================================\n");
        
        //===ACTUALIZAR PRODUCTOS===
        local.retirarProducto(9);
        local.retirarProducto(6);
        local.retirarProducto(2);

        local.registrarProducto(new Consola(new String[]{"Xbox 720","Arriba","Xbox"},new int[]{300_000,200_000,6,7},new boolean[]{true,false,false}));
        local.registrarProducto(new Sonido(new String[]{"JBL BOOMBOX4","Abajo","JBL"},new int[]{60_000,50_000,3,4,30},new boolean[]{false,false,true},Sonido.tipoSonido.BAFLE));
        //===BUSCAR POR NOMBRE Y SETTERS Y GETTERS===
        ArrayList<Producto> productosPorNombre=local.buscarPorNombre("xbox");
        System.out.println(productosPorNombre);
        Producto productoPorNombre=productosPorNombre.get(0);
        productoPorNombre.setNombre("NuevoXbox720");
        productoPorNombre.setUbicacion("NuevaUbi");
        productoPorNombre.setCostoCompra(10_000);
        productoPorNombre.setGananciaMinima(1_000_000);
        productoPorNombre.setStock(1);
        productoPorNombre.setStockMinimo(10);
        productoPorNombre.setGarantia(24);
        productoPorNombre.calcularGanancia(3_000_000);
        productoPorNombre.reponer(2);
        productoPorNombre.descontar(0,false); // deberian quedar 1+2-0=3
        System.out.println("Stock Vendido de Xbox720: "+productoPorNombre.getStockVendido());
        
        System.out.println("\n=========================================================================\n");
        
        local.mostrar();

        System.out.println("\n=========================================================================\n");
        
        local.revisarStock();
        
        System.out.println("\n=========================================================================\n");
        
        local.porAgotarse();
        
        System.out.println("\n=========================================================================\n");
        
        
        
        
        
        
    }
}