public class Factura{
  private String nombreCliente;
  private int numero=0;
  private int Total=0;
  private int ganancia=0;
  private String medioDePago;
  private String detalle = " ";
  // el atributo detalle es para almacenar los productos en el metodo de agregaritem para que luego se impriman en la factura
public Factura(String nombreCliente, int numero, int total, int ganancia, String medioDePago, String detalle){
  this.nombreCliente = nombreCliente;
  this.numero = numero;
  this.total = total;
  this.ganancia = ganancia;
  this.medioDePago=medioDePago;
  this.detalle=detalle;
}
  //getters y setters
 public String getnombreCliente(){return this.nombreCliente;}
 public void setnumero(int nuevoNumero){this.numero +=nuevoNumero;}
 public int getnumero(){return this.numero;}
 public void setTotal(int nuevoTotal){this.Total +=nuevoTotal;}
 public int gettotal(){return this.Total;}
 public void setganancia(int nuevaGanancia){this.ganancia+=nuevaGanancia;}
 public String getmedioDePago(){return this.medioDePago;}
 public void setDetalle(String addetalle){this.detalle +=addetalle + "\n";}
 public String getDetalle(){return this.detalle;}
//parametros temporales hasta crear la clase producto
public void agregaritem(String producto, int cantidad, int precio){
Factura.setDettalle(producto);
Factura.setnumero(cantidad);
Factura.setTotal(precio);
}
public void imprimir(){
  System.out.prinln("cliente: " + Factura.getnombreCliente());
  System.out.println("Forma de pago: " + Factura.getmedioDePago());
  System.out.println(Factura.getDetalle());
  System.out.println("total a pagar: " + Factura.gettotal());
}

}
