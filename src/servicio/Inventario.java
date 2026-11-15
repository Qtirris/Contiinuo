package servicio;

import java.util.ArrayList;

import modelo.Producto;

public class Inventario{

    private ArrayList<Producto> Inv = new ArrayList<>();

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

    public void revisarStock(){
        //Revisa que tal vamos de stock (Si necesita o no reposición o si no hay)
        //Se piensa cambiar a que devuelva un array de boolean con cada indice siendo el estado del stock de un producto (de pronto)
        String texto = "";
        for (Producto p: Inv){

            if (p==null){continue;}

            String reposicion = p.hayStockSobra()?" -(No necesita reposición)":" -(Necesita reposición)";

            texto+=p.getNombre()+"-->"+p.getStock()+"/"+p.getStockMinimo()+reposicion+"\n";
        }
        System.out.print( texto);
    }
}