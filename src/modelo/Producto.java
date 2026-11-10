package modelo;
//ESTA VA A SER LA CLASE ABSTACTA
public abstract class Producto{
    protected static int contadorId=1;

    private final int ID;
    private String nombre;
    private String ubicacion;
    
    private int costoCompra;
    private int gananciaMinima;
    private int stock;
    private int stockMinimo;
    private int stockVendido;
    private int garantia;
    
    
    public Producto(String[] param1, int[] param2){
        
        this.ID=contadorId++;
        
        this.nombre=param1[0];
        this.ubicacion=param1[1];
        this.costoCompra=param2[0];
        this.gananciaMinima=param2[1];
        this.stock=param2[2];
        this.stockMinimo=param2[3];
        this.garantia=param2[4];
    }

//=====================NOMBRE====================
public final String getNombre(){return nombre;}

public final boolean setNombre(String nuevoNombre){
    if (nuevoNombre == null || nuevoNombre.trim().isEmpty()){
        return false;
    }
    nombre=nuevoNombre;
    return true;
}

//=====================CATEGORIA====================
public final String getCategoria(){return this.getClass().getSimpleName();}

//=====================UBICACION====================
public final String getUbicacion(){return ubicacion;}

public final boolean setUbicacion(String nuevaUbicacion){ 
    if (nuevaUbicacion==null || nuevaUbicacion.trim().isEmpty()){
        return false;
    }    
    ubicacion = nuevaUbicacion;
    return true;
}

//=====================COSTO COMPRA====================
public final int getCostoCompra(){ return costoCompra;}

public final boolean setCostoCompra(int nuevoCosto){
    if (nuevoCosto>=0){
        costoCompra=nuevoCosto;
        return true;
    }
    return false;

}

//=====================GANANCIA MINIMA====================
public final int getGananciaMinima(){ return gananciaMinima;}

public final boolean setGananciaMinima(int nuevaGanancia){
    if (nuevaGanancia>=0){
        gananciaMinima=nuevaGanancia;
        return true;
    }
    return false;
}

//=====================STOCK====================
public final int getStock(){ return stock;}

public final boolean setStock(int nuevoStock){ 
    if (nuevoStock>=0){
        stock=nuevoStock;
        return true;
    }
    return false;
}

//=====================STOCK MINIMO====================
public final int getStockMinimo(){ return stockMinimo;}

public final boolean setStockMinimo(int nuevoStockMinimo){
    if (nuevoStockMinimo>=0){
        stockMinimo=nuevoStockMinimo;
        return true;
    }
    return false;
}

//=====================STOCK VENDIDO====================
public final int getStockVendido(){ return stockVendido;}

//Descuenta del stock y suma a lo vendido (la usa Factura.agregaritem)
public final void registrarVenta(int cantidad) {
    if (cantidad > 0 && cantidad <= stock) {
        stockVendido += cantidad;
        stock -= cantidad;
    }
}

public final boolean setStockVendido(int vendido){
    if (vendido>=0){
        stockVendido=vendido;
        return true;
    }
    return false;
}

//=====================GARANTIA====================
public final int getGarantia(){ return garantia;}

public final boolean setGarantia(int nuevaGarantia){ 
    if (nuevaGarantia>=0){
        garantia=nuevaGarantia;
        return true;
    }
    return false;
}

//=====================PRECIO MINIMO====================
public final int getPrecioMinimo(){ return costoCompra+gananciaMinima;}

//=====================ID====================
public final int getID(){return ID;}
    

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


public boolean descontar(int cantidad, boolean dañado){
 if (cantidad>=0 && getStock()>=cantidad && !dañado){
        stock-=cantidad;
        stockVendido+=cantidad;
        return true;
    }
    else if(cantidad>=0 && getStock()>=cantidad && dañado){
        stock-=cantidad;
        return true;
    }

    return false;
}

public boolean hayStock(){
    return stock>0;
}

public boolean hayStockSobra(){
    return stock>=stockMinimo;
}

//=============TO STRING==============
public String toString(){
    
    String texto= ID+". "+nombre+" -- ("+getCategoria()+") ---> Stock: "+stock
    + " -- Precio minimo "+getPrecioMinimo()+"$ c/u. En " +ubicacion;
    return texto;
}

public abstract String detalle();

}