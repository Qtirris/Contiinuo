package servicio;

import java.util.ArrayList;

import modelo.*;

public class Inventario{

    private ArrayList<Producto> Inv = new ArrayList<>();

    private static final int COLUMNAS = 5;
    private static final int ANCHO_CASILLA = 14;

    public boolean registrarProducto(Producto producto) {
        if (producto ==null) {
            return false;
        }
        if (!Inv.contains(producto)){
            Inv.add(producto);
            return true;
        }
        return false;   //EL producto ya esta dentro/
    }

    public boolean retirarProducto(int id) {//Recibe ID
        Producto p = buscarPorId(id);
        if(p!=null){
            Inv.remove(p);
            return true;
        }
        return false;
    }

    public boolean retirarProducto(Producto p) {//Este recibe un Producto como parametro
        if(Inv.contains(p)){
            Inv.remove(p);
            return true;
        }
        return false;
    }

    public ArrayList<Producto> buscarPorNombre(String nombre) {
        /*
        Esto ahora devuelve un ArrayList con todos los productos que contienen al nombre (asi como pide la guia)
        Para seleccionar el buscado Inv.get(retornados.get(i)) siendo i el indice del producto en `retornados`
        */
        ArrayList<Producto> retornados= new ArrayList<>();
        nombre=nombre.trim().toLowerCase();
        
        if (nombre.isEmpty()) {
            return null;
        }
        for(Producto p:Inv) {
            if(p.getNombre().toLowerCase().contains(nombre)){retornados.add(p);}
        }
        return retornados;
    }

    public Producto buscarPorId(int id) {
        for (int i=0; i<Inv.size(); i++) {
            Producto p = Inv.get(i);
            if (p!=null && p.getID()==id) {
                return p;
            }
        }
        return null;
    }
    
    public void mostrar(){
        for (Producto p: Inv){
            if (p==null){continue;}//Por si las moscas lo dejo, no vaya y sea haya un producto en null :(
            System.out.println(p);
        }
    }

    public void porAgotarse(){
        String porAgotarse="";
        for (Producto p: Inv){
            if (p==null|| p.hayStockSobra()){continue;}
            porAgotarse+="\t--"+p.getNombre()+"\n";
        }
        System.out.println(porAgotarse);
    }

    private Producto[][] armarCuadricula(){
        int filas = Math.max(1, (Inv.size() + COLUMNAS - 1) / COLUMNAS); //Esto hace que hayan suficientes filas (quedan alguno en blanco)
        Producto[][] cuadricula = new Producto[filas][COLUMNAS];

        for (int i = 0; i < Inv.size(); i++) {
            cuadricula[i / COLUMNAS][i % COLUMNAS] = Inv.get(i);
        }
        return cuadricula;
    }

    private String ajustar(String texto){
        int maximo = ANCHO_CASILLA - 1;     //el primer caracter siempre es un espacio
        if (texto.length() > maximo) {
            texto = texto.substring(0, maximo - 1) + ".";
        }
        return String.format("%-" + ANCHO_CASILLA + "s", " " + texto);//Formatea a la izq y rellena con espacios
    }

    public void revisarStock(){
        Producto[][] cuadricula = armarCuadricula();
        int porReponer = 0;
        int agotados = 0;

        String separador = "        +";
        for (int c = 0; c < COLUMNAS; c++) {
            separador += "-".repeat(ANCHO_CASILLA) + "+";
        }

        String texto = "         ";
        for (int c = 0; c < COLUMNAS; c++) {
            texto += String.format("%-" + (ANCHO_CASILLA + 1) + "s", " Col " + (c + 1));
        }
        texto += "\n" + separador + "\n";

        for (int f = 0; f < cuadricula.length; f++) {
            String lineaNombre = String.format("%-8s", "Fila " + (f + 1)) + "|";
            String lineaEstado = "        |";

            for (int c = 0; c < COLUMNAS; c++) {
                Producto p = cuadricula[f][c];

                if (p == null) {
                    lineaNombre += ajustar("libre") + "|";
                    lineaEstado += ajustar("") + "|";
                } else {
                    String estado = "OK";
                    if (p.getStock() == 0) {
                        estado = "X";
                        agotados++;
                    } else if (!p.hayStockSobra()) {
                        estado = "!";
                        porReponer++;
                    }
                    lineaNombre += ajustar(p.getNombre()) + "|";
                    lineaEstado += ajustar(String.format("ID %-3d %s", p.getID(), estado)) + "|";
                }
            }
            texto += lineaNombre + "\n" + lineaEstado + "\n" + separador + "\n";
        }

        texto += "\nOK = stock suficiente   ! = necesita reposición   X = agotado\n";
        texto += "Resumen: " + porReponer + " por reponer, " + agotados + " agotado(s).\n";
        System.out.print(texto);
    }
    //================BUSQUEDA ESPECIFICA DE CLASES HEREDADAS=======
    //======CONSOLA=======
    public ArrayList<Consola> buscarPorPlataforma(String plataforma){
        ArrayList<Consola> retornados=new ArrayList<>();
        
        for(Producto p:Inv){
            if (p instanceof Consola c && c.getPlataforma() == plataforma) {
            retornados.add(c);
        }
        }
        return retornados;
    }
    //======SONIDO=======
    public ArrayList<Sonido> buscarPorMarca(String marca){
        ArrayList<Sonido> retornados=new ArrayList<>();
        
        for(Producto p:Inv){
            if (p instanceof Sonido s && s.getMarca() == marca) {
            retornados.add(s);
        }
        }
        return retornados;
    }
    public ArrayList<Sonido> buscarPorTipo(Sonido.tipoSonido tipo){
        ArrayList<Sonido> retornados=new ArrayList<>();
        
        for(Producto p:Inv){
            if (p instanceof Sonido s && s.getTipo() == tipo) {
            retornados.add(s);
        }
        }
        return retornados;
    }
    //======VARIOS=======
    public ArrayList<Varios> buscarPorTipo(Varios.tipoVarios tipo){
    ArrayList<Varios> retornados = new ArrayList<>();
    for (Producto p : Inv) {
        if (p instanceof Varios v && v.getTipo() == tipo) {
            retornados.add(v);
        }
    }
    return retornados;
}
}