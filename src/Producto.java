public class Producto{
    /* Pruebas ejemplos
    new Producto("Bafle1","Sonido","Estante 1",10_000, 15_000, 10, 3, 0);
    new Producto("Xbox360","Consolas","Estante 1",500_000, 600_000, 5, 2, 6);
    new Producto("Radio1","Sonido","Repisa",70_000, 100_000, 4, 0, 3);
    new Producto("Bafle2","Sonido","Mostrador",600_000, 900_000, 8, 2, 6);
    new Producto("Power Bank","Otros","Colgado",50_000, 70_000, 3, 0, 1);
    */
    private static int id=1;

    private String nombre;
    private String categoria;
    private String ubicacion;

    private int costoCompra;
    private int gananciaMinima=0;
    private int stock;
    private int stockMinimo=0;
    private int stockVendido=0;
    private int garantia=0;
    private int precioMinimo;
    private int ID;

    private boolean deSobra=false;
    
    //Revisar si a futuro los parametros del contructor se puede pasar como una lista o array.

    public Producto(String nombre, String categoria, String ubicacion, int costoCompra, int gananciaMinima, int stock, int stockMinimo, int garantia){
        this.nombre=nombre;
        this.categoria=categoria;
        this.ubicacion=ubicacion;
        this.costoCompra=costoCompra;
        this.precioMinimo=gananciaMinima+costoCompra;
        this.stock=stock;
        this.stockMinimo=stockMinimo;
        this.garantia=garantia;
        this.ID=id++;
    }

    //Getters y Setters para los atributos

//=====================NOMBRE====================
public String getNombre(){return nombre;}

public void setNombre(String nuevoNombre){nombre=nuevoNombre;}

//=====================CATEGORIA====================
public String getCategoria(){return categoria;}

//=====================UBICACION====================
public String getUbicacion(){return ubicacion;}

public void setUbicacion(String nuevaUbicacion){ ubicacion =nuevaUbicacion;}

//=====================COSTO COMPRA====================
public int getCostoCompra(){ return costoCompra;}

public void setCostoCompra(int nuevoCosto){costoCompra=nuevoCosto>=0?nuevoCosto:costoCompra;}

//=====================GANANCIA MINIMA====================
public int getGananciaMinima(){ return gananciaMinima;}

public void setGananciaMinima(int nuevaGanancia){gananciaMinima = nuevaGanancia>=0?nuevaGanancia:gananciaMinima; precioMinimo=gananciaMinima+costoCompra;}

//=====================STOCK====================
public int getStock(){ return stock;}

public void setStock(int nuevoStock){ stock = nuevoStock>=0?nuevoStock:stock;}

//=====================STOCK MINIMO====================
public int getStockMinimo(){ return stockMinimo;}

public void setStockMinimo(int nuevoStockMinimo){stockMinimo = nuevoStockMinimo>=0?nuevoStockMinimo:stockMinimo;}

//=====================STOCK VENDIDO====================
public int getStockVendido(){ return stockVendido;}

public void registrarVenta(int cantidad) {
    if (cantidad > 0 && cantidad <= stock) {
        stockVendido += cantidad;
        stock -= cantidad;
    }
}

public void setStockVendido(int vendido){ stockVendido = vendido>=0?vendido:stock;}

//=====================GARANTIA====================
public int getGarantia(){ return garantia;}

public void setGarantia(int nuevaGarantia){ garantia = nuevaGarantia>=0?nuevaGarantia:garantia;}

//=====================PRECIO MINIMO====================
public int getPrecioMinimo(){ return precioMinimo;}

public void setPrecioMinimo(int nuevoPrecio){ precioMinimo =nuevoPrecio; gananciaMinima=precioMinimo-costoCompra;}

//=====================ID====================
public int getID(){return ID;}
    

//Metodos principales de la clase

public int calcularGanancia(int precioVenta){
    return precioVenta-costoCompra;
}

public void reponer(int cantidad){
    //Mirar a futuro si necesitamos que retorne la cantidad actualizada, o true o false
    stock+=cantidad>0?cantidad:0;
}

public void descontar(int cantidad){
    stock-=cantidad>0?cantidad:0;
}

public boolean hayStock(){
    return stock>0;
}
public boolean hayStockSobra(){
    //Para mostrar alerta sobre si hay que reponer o no
    deSobra = stock>stockMinimo;
    return deSobra;
}

public String toString(){
    
    String texto= ID+". "+nombre+"("+stock+") -- "+precioMinimo+"$ c/u. En " +ubicacion;
    return texto;
}

}