public class Inventario{
    /*
    Como aun no se puedne usar listas ni arrays ni cosas por el estilo
    vamos a definir una cantidad maxima de porductos en 10.
    */
    
    public static int productosRestantes=10;
    
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
        System.out.println(Inventario.producto1!=null?Inventario.producto1:"No hay producto");
        System.out.println(Inventario.producto2!=null?Inventario.producto2:"No hay producto");
        System.out.println(Inventario.producto3!=null?Inventario.producto3:"No hay producto");
        System.out.println(Inventario.producto4!=null?Inventario.producto4:"No hay producto");
        System.out.println(Inventario.producto5!=null?Inventario.producto5:"No hay producto");
        System.out.println(Inventario.producto6!=null?Inventario.producto6:"No hay producto");
        System.out.println(Inventario.producto7!=null?Inventario.producto7:"No hay producto");
        System.out.println(Inventario.producto8!=null?Inventario.producto8:"No hay producto");
        System.out.println(Inventario.producto9!=null?Inventario.producto9:"No hay producto");
        System.out.println(Inventario.producto10!=null?Inventario.producto10:"No hay producto");
    }
}