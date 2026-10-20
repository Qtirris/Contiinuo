public class Producto{
    /* Pruebas ejemplos
    new Producto("Bafle1","Sonido","Estante 1",10_000, 15_000, 10, 3, 0);
    new Producto("Xbox360","Consolas","Estante 1",500_000, 600_000, 5, 2, 6);
    new Producto("Radio1","Sonido","Repisa",70_000, 100_000, 4, 0, 3);
    new Producto("Bafle2","Sonido","Mostrador",600_000, 900_000, 8, 2, 6);
    new Producto("Power Bank","Otros","Colgado",50_000, 70_000, 3, 0, 1);
    */
    private static int contadorId=1;

    private final int ID;
    private String nombre;
    private String categoria;
    private String ubicacion;

    private int costoCompra;
    private int gananciaMinima;
    private int stock;
    private int stockMinimo;
    private int stockVendido;
    private int garantia;

    private boolean deSobra=false;
    
    //Revisar si a futuro los parametros del contructor se puede pasar como una lista o array.

    public Producto(String nombre, String categoria, String ubicacion, int costoCompra, int gananciaMinima, int stock, int stockMinimo, int garantia){
        
        this.ID=contadorId++;
        
        this.nombre=nombre;
        this.categoria=categoria;
        this.ubicacion=ubicacion;
        this.costoCompra=costoCompra;
        this.gananciaMinima=gananciaMinima;
        this.stock=stock;
        this.stockMinimo=stockMinimo;
        this.garantia=garantia;
    }

    //Getters y Setters para los atributos

//=====================NOMBRE====================
public String getNombre(){return nombre;}

public boolean setNombre(String nuevoNombre){
    if (nuevoNombre == null || nuevoNombre.trim().isEmpty()){ //Revisa que no este vacio
        return false;
    }
    nombre=nuevoNombre;
    return true;
}

//=====================CATEGORIA====================
public String getCategoria(){return categoria;}

//=====================UBICACION====================
public String getUbicacion(){return ubicacion;}

public boolean setUbicacion(String nuevaUbicacion){ 
    if (nuevaUbicacion==null || nuevaUbicacion.trim().isEmpty()){
        return false;
    }    
    ubicacion =nuevaUbicacion;
    return true;
}

//=====================COSTO COMPRA====================
public int getCostoCompra(){ return costoCompra;}

public boolean setCostoCompra(int nuevoCosto){
    if (nuevoCosto>=0){
        costoCompra=nuevoCosto;
        return true;
    }
    return false;

}

//=====================GANANCIA MINIMA====================
public int getGananciaMinima(){ return gananciaMinima;}

public boolean setGananciaMinima(int nuevaGanancia){
    if (nuevaGanancia>=0){
        gananciaMinima=nuevaGanancia;
        return true;
    }
    return false;
}

//=====================STOCK====================
public int getStock(){ return stock;}

public boolean setStock(int nuevoStock){ 
    if (nuevoStock>=0){
        stock=nuevoStock;
        return true;
    }
    return false;
}

//=====================STOCK MINIMO====================
public int getStockMinimo(){ return stockMinimo;}

public boolean setStockMinimo(int nuevoStockMinimo){
    if (nuevoStockMinimo>=0){
        stockMinimo=nuevoStockMinimo;
        return true;
    }
    return false;
}

//=====================STOCK VENDIDO====================
public int getStockVendido(){ return stockVendido;}

//Descuenta del stock y suma a lo vendido (la usa Factura.agregaritem)
public void registrarVenta(int cantidad) {
    if (cantidad > 0 && cantidad <= stock) {
        stockVendido += cantidad;
        stock -= cantidad;
    }
}

public boolean setStockVendido(int vendido){
    if (vendido>=0){
        stockVendido=vendido;
        return true;
    }
    return false;
}

//=====================GARANTIA====================
public int getGarantia(){ return garantia;}

public boolean setGarantia(int nuevaGarantia){ 
    if (nuevaGarantia>=0){
        garantia=nuevaGarantia;
        return true;
    }
    return false;
}

//=====================PRECIO MINIMO====================
public int getPrecioMinimo(){ return costoCompra+gananciaMinima;}

//=====================ID====================
public int getID(){return ID;}
    

//=====================METODOS PRINCIPALES====================

public int calcularGanancia(int precioVenta){
    return precioVenta-costoCompra;
}

public boolean reponer(int cantidad){
    if (cantidad>=0){
        stock+=cantidad;
        return true;
    }
    return false;
}

public boolean descontar(int cantidad){
 if (cantidad>=0 && getStock()>=cantidad){
        stock-=cantidad;
        stockVendido+=cantidad; //Modifica el stockVendido
        return true;
    }
    return false;
}

public boolean hayStock(){
    return stock>0;
}
public boolean hayStockSobra(){
    //Para mostrar alerta sobre si hay que reponer o no
    return stock<=stockMinimo;
}

public String toString(){
    
    String texto= ID+". "+nombre+" ("+categoria+" ) -- Stock: "+stock
    + "-- Precio Minimo "+getPrecioMinimo()+"$ c/u. En " +ubicacion;
    return texto;
}

}