public class Inventario{
    /*
    Como aun no se puedne usar listas ni arrays ni cosas por el estilo
    vamos a definir una cantidad maxima de porductos en 10.
    */
    
    private static int productosRestantes=10;
    
    //Es necesario que sean publicos en orden de poder acceder a dud metodos desde Contiinuo o Main
    public static Producto producto1;
    public static Producto producto2;
    public static Producto producto3;
    public static Producto producto4;
    public static Producto producto5;
    public static Producto producto6;
    public static Producto producto7;
    public static Producto producto8;
    public static Producto producto9;
    public static Producto producto10;

    public static boolean registrarProducto(Producto producto){
        //Asignar cada producto a una variable
        //A futuro se cambiará por una arrayList

        switch (productosRestantes) {
            case 10:
            producto1=producto;
            productosRestantes--;
            return true;
            case 9:
            producto2=producto;
            productosRestantes--;
            return true;
            case 8:
            producto3=producto;
            productosRestantes--;
            return true;
            case 7:
            producto4=producto;
            productosRestantes--;
            return true;
            case 6:
            producto5=producto;
            productosRestantes--;
            return true;
            case 5:
            producto6=producto;
            productosRestantes--;
            return true;
            case 4:
            producto7=producto;
            productosRestantes--;
            return true;
            case 3:
            producto8=producto;
            productosRestantes--;
            return true;
            case 2:
            producto9=producto;
            productosRestantes--;
            return true;
            case 1:
            producto10=producto;
            productosRestantes--;
            return true;
        
            default: //No quedan productos disponibbles
            return false;
        }
    }

    public static boolean retirarProducto(int numeroProducto){
        //Usamos el numero dle producto ya que aun no tenemos una lista o unaforma de acceder directamente al producto
        switch (numeroProducto) {
            case 1:
            producto1=null;
            return true;
            case 2:
            producto2=null;
            return true;
            case 3:
            producto3=null;
            return true;
            case 4:
            producto4=null;
            return true;
            case 5:
            producto5=null;
            return true;
            case 6:
            producto6=null;
            return true;
            case 7:
            producto7=null;
            return true;
            case 8:
            producto8=null;
            return true;
            case 9:
            producto9=null;
            return true;
            case 10:
            producto10=null;
            return true;
        
            default: //nos e removio con exito
            return false;
        }
    }

    public static void mostrar(){
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

public static Producto[] obtenerProductos() {
    return new Producto[] {
        producto1, producto2, producto3, producto4, producto5,
        producto6, producto7, producto8, producto9, producto10
    };
}

public static void checkStock(){
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