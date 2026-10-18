public class Inventario{
    /*
    Como aun no se puedne usar listas ni arrays ni cosas por el estilo
    vamos a definir una cantidad maxima de porductos en 10.
    */
    
    private static final int MAXIMO=10;
    private static int numeroProductos;
    
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
    private Producto getProducto(int casilla) {
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

    public boolean retirarProducto(int id) {
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
        //Poner si hay Stock y Stock de sobra
        System.out.println(producto1!=null?producto1:"");
        System.out.println(producto2!=null?producto2:"");
        System.out.println(producto3!=null?producto3:"");
        System.out.println(producto4!=null?producto4:"");
        System.out.println(producto5!=null?producto5:"");
        System.out.println(producto6!=null?producto6:"");
        System.out.println(producto7!=null?producto7:"");
        System.out.println(producto8!=null?producto8:"");
        System.out.println(producto9!=null?producto9:"");
        System.out.println(producto10!=null?producto10:"");
    }

    public void checkStock(){
        //Revisa que tal vamos de stock (Si necesita o no reposición o si no hay)
        //Se piensa cambiar a que devuelva un array de boolean con cada indice siendo el estado del stock de un producto (de pronto)
        System.out.println("Nombre  -- Stock/Stock Minimo (Necesita reposición?)"+"\n");
        System.out.println(producto1!=null?(producto1.hayStockSobra()?producto1.getNombre()+" -- "+producto1.getStock()+"/"+producto1.getStockMinimo()+" (NO necesita reposoción)":producto1.getNombre()+" -- "+producto1.getStock()+"/"+producto1.getStockMinimo()+" (Necesita reposoción)"):"");
        System.out.println(producto2!=null?(producto2.hayStockSobra()?producto2.getNombre()+" -- "+producto2.getStock()+"/"+producto2.getStockMinimo()+" (NO necesita reposoción)":producto2.getNombre()+" -- "+producto2.getStock()+"/"+producto2.getStockMinimo()+" (Necesita reposoción)"):"");
        System.out.println(producto3!=null?(producto3.hayStockSobra()?producto3.getNombre()+" -- "+producto3.getStock()+"/"+producto3.getStockMinimo()+" (NO necesita reposoción)":producto3.getNombre()+" -- "+producto3.getStock()+"/"+producto3.getStockMinimo()+" (Necesita reposoción)"):"");
        System.out.println(producto4!=null?(producto4.hayStockSobra()?producto4.getNombre()+" -- "+producto4.getStock()+"/"+producto4.getStockMinimo()+" (NO necesita reposoción)":producto4.getNombre()+" -- "+producto4.getStock()+"/"+producto4.getStockMinimo()+" (Necesita reposoción)"):"");
        System.out.println(producto5!=null?(producto5.hayStockSobra()?producto5.getNombre()+" -- "+producto5.getStock()+"/"+producto5.getStockMinimo()+" (NO necesita reposoción)":producto5.getNombre()+" -- "+producto5.getStock()+"/"+producto5.getStockMinimo()+" (Necesita reposoción)"):"");
        System.out.println(producto6!=null?(producto6.hayStockSobra()?producto6.getNombre()+" -- "+producto6.getStock()+"/"+producto6.getStockMinimo()+" (NO necesita reposoción)":producto6.getNombre()+" -- "+producto6.getStock()+"/"+producto6.getStockMinimo()+" (Necesita reposoción)"):"");
        System.out.println(producto7!=null?(producto7.hayStockSobra()?producto7.getNombre()+" -- "+producto7.getStock()+"/"+producto7.getStockMinimo()+" (NO necesita reposoción)":producto7.getNombre()+" -- "+producto7.getStock()+"/"+producto7.getStockMinimo()+" (Necesita reposoción)"):"");
        System.out.println(producto8!=null?(producto8.hayStockSobra()?producto8.getNombre()+" -- "+producto8.getStock()+"/"+producto8.getStockMinimo()+" (NO necesita reposoción)":producto8.getNombre()+" -- "+producto8.getStock()+"/"+producto8.getStockMinimo()+" (Necesita reposoción)"):"");
        System.out.println(producto9!=null?(producto9.hayStockSobra()?producto9.getNombre()+" -- "+producto9.getStock()+"/"+producto9.getStockMinimo()+" (NO necesita reposoción)":producto9.getNombre()+" -- "+producto9.getStock()+"/"+producto9.getStockMinimo()+" (Necesita reposoción)"):"");
        System.out.println(producto10!=null?(producto10.hayStockSobra()?producto10.getNombre()+" -- "+producto10.getStock()+"/"+producto10.getStockMinimo()+" (NO necesita reposoción)":producto10.getNombre()+" -- "+producto10.getStock()+"/"+producto10.getStockMinimo()+" (Necesita reposoción)"):"");
        
    }
}