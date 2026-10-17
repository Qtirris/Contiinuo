public class Factura{
  private String nombreCliente;
  private int numero=0;
  private String telefono;
  private int total=0;
  private int ganancia=0;
  private String medioDePago;
  private String detalle="";

  // el atributo detalle es para almacenar los productos en el metodo de agregaritem para que luego se impriman en la factura
  public Factura(String nombreCliente,String telefono,String medioDePago){
    this.nombreCliente = nombreCliente;
    this.medioDePago=medioDePago;
    this.telefono=telefono;

  }
  //getters y setters
 public String getNombreCliente(){return this.nombreCliente;}
 public void setNumero(int nuevoNumero){this.numero +=nuevoNumero;}
 public int getNumero(){return this.numero;}
 public void setTotal(int nuevoTotal){this.total +=nuevoTotal;}
 public int getTotal(){return this.total;}
 public void setGanancia(int gananciaProducto,int cantidad){this.ganancia+=gananciaProducto*cantidad;}
 public int getGanancia(){return this.ganancia;}
 public String getMedioDePago(){return this.medioDePago;}
 public String getDetalle(){return this.detalle;}
  public void agregaritem(Producto punto,int canti, int precioVenta){//me acorde de vectores :v
    detalle += "-"+punto.getNombre()+" ---- ("+canti+")"+" $ "+precioVenta+" c/u ---->"+" $"+canti*precioVenta+""+"\n";
    numero += canti;
    punto.stockVendido+=canti;
    total+=precioVenta*canti;
    setGanancia(punto.calcularGanancia(precioVenta),canti);
  }
  public void imprimir(){
    System.out.println("Cliente: " + this.nombreCliente);
    System.out.println("Telefono: " + this.telefono);
    System.out.println("Forma de pago: " + this.medioDePago);
    System.out.println(this.detalle);
    System.out.println("Total a pagar: " + "$"+ this.total);
  }
}
