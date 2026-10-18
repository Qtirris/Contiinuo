public class Factura{
  private String nombreCliente;
  private int numeroProductos=0;
  private String telefono;
  private int total=0;
  private int ganancia=0;
  private String medioDePago;
  private static String detalle="";
  private static String registro1="";
  private static String registro2="";
  private static String registro3="";
  private static String registro4="";
  private static String registro5="";
  private static String registro6="";
  private static String registro7="";
  private static String registro8="";
  private static string registro9="";
  private static String registro10="";
  private int cr = 0
  // el atributo detalle es para almacenar los productos en el metodo de agregaritem para que luego se impriman en la factura
  public Factura(String nombreCliente,String telefono,String medioDePago){
    this.nombreCliente = nombreCliente;
    this.medioDePago=medioDePago;
    this.telefono=telefono;

  }
  //getters y setters
 public String getNombreCliente(){return this.nombreCliente;}
 public String getTelefono(){return this.telefono;}
 public void setNumero(int nuevoNumero){this.numero=nuevoNumero;}
 public int getNumero(){return this.numero;}
 public void setTotal(int nuevoTotal){this.total +=nuevoTotal;}
 public int getTotal(){return this.total;}
 public void setGanancia(int gananciaProducto,int cantidad){this.ganancia+=gananciaProducto*cantidad;}
 public int getGanancia(){return this.ganancia;}
 public String getMedioDePago(){return this.medioDePago;}
 public String getDetalle(){return this.detalle;}
  public void agregaritem(Producto punto,int canti, int precioVenta){//me acorde de vectores :v
    verificador = false;
    while (!verificador){
      if(precioVenta<0){
        System.out.println("precio invalido");
          }
      else{
    detalle += "-"+punto.getNombre()+" ---- ("+canti+")"+" $ "+precioVenta+" c/u ---->"+" $"+canti*precioVenta+""+"\n";
    numeroProductos += canti;
    //punto.stockVendido+=canti;
    total+=precioVenta*canti;
    setGanancia(punto.calcularGanancia(precioVenta),canti);
        verificador=true
      }
    }
  }
  public void imprimir(){
    System.out.println("Cliente: " + this.nombreCliente);
    System.out.println("Telefono: " + this.telefono);
    System.out.println("Forma de pago: " + this.medioDePago);
    System.out.println(this.detalle);
    System.out.println("Total a pagar: " + "$"+ this.total);
    cr+=1
  }
  private void guardar(){
    switch(cr){
      case 1:
    registro1= "Cliente: " + this.nombreCliente + "\nTelefono: " + this.telefono + "\nForma de pago: " + this.medioDePago + "\n" + this.detalle + "\Total a pagar:.................. $" + this.total;
      case 2:
    registro2= "Cliente: " + this.nombreCliente + "\nTelefono: " + this.telefono + "\nForma de pago: " + this.medioDePago + "\n" + this.detalle + "\Total a pagar:.................. $" + this.total;
      case 3:
    registro3= "Cliente: " + this.nombreCliente + "\nTelefono: " + this.telefono + "\nForma de pago: " + this.medioDePago + "\n" + this.detalle + "\Total a pagar:.................. $" + this.total;
      case 4:
    registro4= "Cliente: " + this.nombreCliente + "\nTelefono: " + this.telefono + "\nForma de pago: " + this.medioDePago + "\n" + this.detalle + "\Total a pagar:.................. $" + this.total;
      case 5:
    registro5= "Cliente: " + this.nombreCliente + "\nTelefono: " + this.telefono + "\nForma de pago: " + this.medioDePago + "\n" + this.detalle + "\Total a pagar:.................. $" + this.total;
      case 6:
    registro6= "Cliente: " + this.nombreCliente + "\nTelefono: " + this.telefono + "\nForma de pago: " + this.medioDePago + "\n" + this.detalle + "\Total a pagar:.................. $" + this.total;
      case 7:
    registro7= "Cliente: " + this.nombreCliente + "\nTelefono: " + this.telefono + "\nForma de pago: " + this.medioDePago + "\n" + this.detalle + "\Total a pagar:.................. $" + this.total;
      case 8:
    registro8= "Cliente: " + this.nombreCliente + "\nTelefono: " + this.telefono + "\nForma de pago: " + this.medioDePago + "\n" + this.detalle + "\Total a pagar:.................. $" + this.total;
      case 9:
    registro9= "Cliente: " + this.nombreCliente + "\nTelefono: " + this.telefono + "\nForma de pago: " + this.medioDePago + "\n" + this.detalle + "\Total a pagar:.................. $" + this.total;
      case 10:
    registro10= "Cliente: " + this.nombreCliente + "\nTelefono: " + this.telefono + "\nForma de pago: " + this.medioDePago + "\n" + this.detalle + "\Total a pagar:.................. $" + this.total;
      default
        
    }

  }
}

