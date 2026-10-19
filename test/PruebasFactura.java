
public class PruebasFactura{
    static Producto p1 =new Producto("Bafle1","Sonido","Estante 1",10_000, 5_000, 10, 30, 0);
    static Producto p2 =new Producto("Xbox360","Consolas","Estante 1",500_000, 120_000, 5, 2, 6);
    static Producto p3 =new Producto("Radio1","Sonido","Repisa",70_000, 30_000, 4, 0, 3);
    static Producto p4 =new Producto("Bafle2","Sonido","Mostrador",600_000, 300_000, 8, 2, 6);
    static Producto p5 =new Producto("Power Bank","Otros","Colgado",50_000, 20_000, 3, 0, 1);
    
    static Factura f1 = new Factura("Registro1", "3112557310", "CrediQK");
    static Factura f2 = new Factura("Registro2", "3112557310", "CrediQK");

    public static void main(String[] args) {
        f1.agregarItem(p1,2,30_000);
        f1.agregarItem(p3,4,130_000);
        f1.agregarItem(p4,1,910_000);

        f2.agregarItem(p1,2,30_000);
        f2.agregarItem(p2,2,150_000);
        f2.agregarItem(p5,2,1_000_000);

        f1.imprimir(); //SI O SI SE IMPRIMIE PRIMERO O SINO SE JODE TODO
        f1.guardar(); //SI O SI SE GUARDA SEGUNDO O SINO SE JODE TODO
        System.out.println("==========================================");
        
        f2.imprimir();
        f2.guardar();
        System.out.println("==========================================");

        System.out.println(Factura.getRegistro(1).nom);
        System.out.println(Factura.getRegistro(1).det);
        System.out.println(Factura.getRegistro(1).tot);
        System.out.println(Factura.getRegistro(2).nom);
        System.out.println(Factura.getRegistro(2).det);
        System.out.println(Factura.getRegistro(2).tot);
    }
}