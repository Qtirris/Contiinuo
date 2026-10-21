public class Factura{
  private String nombreCliente;
  private int numeroProductos=0;
  private String telefono;
  private int total=0;
  private int ganancia=0;
  private String medioDePago;
  // el atributo detalle es para almacenar los productos en el metodo de agregaritem para que luego se impriman en la factura
  private String detalle="";
  private static String registro1="";
  private static String registro2="";
  private static String registro3="";
  private static String registro4="";
  private static String registro5="";
  private static String registro6="";
  private static String registro7="";
  private static String registro8="";
  private static String registro9="";
  private static String registro10="";
  private static int cr = 0;

  public Factura(String nombreCliente,String telefono,String medioDePago){
    this.nombreCliente = nombreCliente;
    this.medioDePago=medioDePago;
    this.telefono=telefono;
  }

  //getters y setters
  public String getProducto1(){return registro1;}
  public String getProducto2(){return registro2;}
  public String getProducto3(){return registro3;}
  public String getProducto4(){return registro4;}
  public String getProducto5(){return registro5;}
  public String getProducto6(){return registro6;}
  public String getProducto7(){return registro7;}
  public String getProducto8(){return registro8;}
  public String getProducto9(){return registro9;}
  public String getProducto10(){return registro10;}
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

  public void agregaritem(Producto punto,int canti, int precioVenta){//me acorde de vectores :v
    if(precioVenta<0){
      System.out.println("precio invalido");
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
    cr+=1;
  }

  public void guardar(){
    String texto = "Cliente: " + this.nombreCliente + "\nTelefono: " + this.telefono + "\nForma de pago: " + this.medioDePago + "\n" + this.detalle + "\nTotal a pagar:.................. $" + this.total;
    switch(cr){
      case 1:
        registro1 = texto;
        break;
      case 2:
        registro2 = texto;
        break;
      case 3:
        registro3 = texto;
        break;
      case 4:
        registro4 = texto;
        break;
      case 5:
        registro5 = texto;
        break;
      case 6:
        registro6 = texto;
        break;
      case 7:
        registro7 = texto;
        break;
      case 8:
        registro8 = texto;
        break;
      case 9:
        registro9 = texto;
        break;
      case 10:
        registro10 = texto;
        break;
      default:
        break;
    }
  }

  public void terminarDia(){
    registro1="";
    registro2="";
    registro3="";
    registro4="";
    registro5="";
    registro6="";
    registro7="";
    registro8="";
    registro9="";
    registro10="";
    cr=0;
  }
}