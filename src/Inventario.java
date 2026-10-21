public class Inventario{
    /*
    Como aun no se puedne usar listas ni arrays ni cosas por el estilo
    vamos a definir una cantidad maxima de porductos en 10.
    */
    
    private static final int MAXIMO=10;
    private int numeroProductos= 0;
    
    //Ya no son staticos ni privados y se agrego getProducto
    private Producto producto1;
    private Producto producto2;
    private Producto producto3;
    private Producto producto4;
    private Producto producto5;
    private Producto producto6;
    private Producto producto7;
    private Producto producto8;
    private Producto producto9;
    private Producto producto10;

    //Devuelve el producto de la casilla correpondiente
    public Producto getProducto(int casilla) {
        switch (casilla) {
            case 1:  
            return producto1;
            case 2:  
            return producto2;
            case 3:  
            return producto3;
            case 4:  
            return producto4;
            case 5:  
            return producto5;
            case 6:  
            return producto6;
            case 7:  
            return producto7;
            case 8:  
            return producto8;
            case 9:  
            return producto9;
            case 10: 
            return producto10;

            default: 
            return null;
        }
    }

    private void asignar(int casilla, Producto producto) {
        switch (casilla) {
            case 1:  
            producto1 = producto;  
            break;
            case 2:  
            producto2 = producto;  
            break;
            case 3:  
            producto3 = producto;  
            break;
            case 4:  
            producto4 = producto;  
            break;
            case 5:  
            producto5 = producto;  
            break;
            case 6:  
            producto6 = producto;  
            break;
            case 7:  
            producto7 = producto;  
            break;
            case 8:  
            producto8 = producto;  
            break;
            case 9:  
            producto9 = producto;  
            break;
            case 10: 
            producto10 = producto; 
            break;

            default: 
            break;
        }
    }
   public boolean registrarProducto(Producto producto) {
        if (producto ==null) {
            return false;
        }
        for (int casilla=1; casilla<= MAXIMO; casilla++) {
            if (getProducto(casilla)==null) {
                asignar(casilla, producto);
                numeroProductos++;
                return true;
            }
        }
        return false;   //El inventario esta lleno llenisimo
    }

    public boolean retirarProducto(int id) {//Mirar si es mejor retirarlo por ID o por casilla
        for (int casilla = 1; casilla <= MAXIMO; casilla++) {
            Producto p = getProducto(casilla);
            if (p!= null && p.getID()==id) {
                asignar(casilla, null);
                numeroProductos--;
                return true;
            }
        }
        return false;
    }

    public Producto buscarPorNombre(String nombre) {
        if (nombre==null) {
            return null;
        }
        String buscado = nombre.trim();
        for(int casilla=1; casilla<= MAXIMO; casilla++) {
            Producto producto = getProducto(casilla);
            if (producto!= null && producto.getNombre().equalsIgnoreCase(buscado)) {
                return producto;
            }
        }
        return null;
    }

    public Producto buscarPorId(int id) {
        for (int casilla=1; casilla<=MAXIMO; casilla++) {
            Producto producto = getProducto(casilla);
            if (producto!=null && producto.getID()==id) {
                return producto;
            }
        }
        return null;
    }
    public boolean estaLleno(){
        return numeroProductos==MAXIMO;
    }
    
    public void mostrar(){
        for (int casilla=1;casilla<=MAXIMO;casilla++){
            Producto producto = getProducto(casilla);

            if (producto==null){continue;}

            System.out.println(producto);
        }
    }

    public void porAgotarse(){
        String porAgotarse="Productos por agotarse:\n";
        for (int casilla=1;casilla<=MAXIMO;casilla++){
            Producto producto = getProducto(casilla);

            if (producto==null|| producto.hayStockSobra()){continue;}

            porAgotarse+="\t--"+producto.getNombre()+"\n";
        }
        System.out.println(porAgotarse);
    }

    public void revisarStock(){
        //Revisa que tal vamos de stock (Si necesita o no reposición o si no hay)
        //Se piensa cambiar a que devuelva un array de boolean con cada indice siendo el estado del stock de un producto (de pronto)
        for (int casilla=1;casilla<=MAXIMO;casilla++){
            Producto producto = getProducto(casilla);

            if (producto==null){continue;}

            String reposicion = producto.hayStockSobra()?" -(No necesita reposición)":" -(Necesita reposición)";

            System.out.println(producto.getNombre()+"--> "
            +producto.getStock()+"/"+producto.getStockMinimo()+reposicion);
        }

    }

        public int getCantidadProductos() {
            return numeroProductos; 
        
    }
}