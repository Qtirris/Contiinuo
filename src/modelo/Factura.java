package modelo;
import java.util.ArrayList;


public class Factura{
  private static ArrayList<Factura> registro = new ArrayList<>();
  private String nombreCliente;
  private int numeroProductos=0;
  private String telefono;
  private int total=0;
  private int ganancia=0;
  private String medioDePago;
  private String detalle="";
// atributos de la claseeeeeee
  //cambiarlas de estaticas tipo string a tipo facturas, mirar a futuro..........o ya
  // el atributo detalle es para almacenar los productos en el metodo de agregaritem para que luego se impriman en la factura
  public Factura(String nombreCliente,String telefono,String medioDePago){
    this.nombreCliente = nombreCliente;
    this.medioDePago=medioDePago;
    this.telefono=telefono;
  }

  //getters y setters
  public String getNombreCliente(){return this.nombreCliente;}
  public String getTelefono(){return this.telefono;}
  public void setNumero(int nuevoNumero){this.numeroProductos=nuevoNumero;}
  public int getNumero(){return this.numeroProductos;}
  public void setTotal(int nuevoTotal){this.total+=nuevoTotal;}
  public int getTotal(){return this.total;}
  public void setGanancia(int gananciaProducto,int cantidad){this.ganancia+=gananciaProducto*cantidad;}
  public int getGanancia(){return this.ganancia;}
  public String getMedioDePago(){return this.medioDePago;}
  public String getDetalle(){return this.detalle;}

  public void agregarItem(Producto punto,int canti, int precioVenta){//me acorde de vectores :v
    if(precioVenta<0){
      System.out.println("Precio inválido.");
      return;
    }
    detalle += "-"+punto.getNombre()+" ---- ("+canti+")"+" $ "+precioVenta+" c/u ---->"+" $"+canti*precioVenta+""+"\n";
    numeroProductos += canti;
    //punto.stockVendido+=canti;
    total+=precioVenta*canti;
    setGanancia(punto.calcularGanancia(precioVenta),canti);
    punto.registrarVenta(canti);
  }

  public void imprimir(){
    System.out.println("Cliente: " + this.nombreCliente);
    System.out.println("Telefono: " + this.telefono);
    System.out.println("Forma de pago: " + this.medioDePago);
    System.out.println(this.detalle);
    System.out.println("Total a pagar: " + "$"+ this.total);
  }
  //se utiliza para poder llamar al atributo de la subclase seleccionado 
  public static int cantidadRegistros(int posRegistro){
        return registro.size();
    }
// metodo para guardar las facturas creadas en una subclase llamada registro, se pueden guardar hasta 10 facturas
  public static void guardar(Factura r){
      registro.add(r);
    }
  public static void terminarDia(){
    registro.clear();
  }
  public registro getposition(int eleccion){return registro.get(eleccion - 1);}
}
