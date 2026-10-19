public class Factura{
  private String nombreCliente;
  private int numeroProductos=0;
  private String telefono;
  private int total=0;
  private int ganancia=0;
  private String medioDePago;
  private String detalle="";
  //cambiarlas de estaticas tipo string a tipo facturas.........mirar a futuro o ya
   private Factura registro1;
   private Factura registro2;
   private Factura registro3;
   private Factura registro4;
   private Factura registro5;
   private Factura registro6;
   private Factura registro7;
   private Factura registro8;
   private Factura registro9;
   private Factura registro10;

  private int cr = 0;
  // el atributo detalle es para almacenar los productos en el metodo de agregaritem para que luego se impriman en la factura
  public Factura(String nombreCliente,String telefono,String medioDePago){
    this.nombreCliente = nombreCliente;
    this.medioDePago=medioDePago;
    this.telefono=telefono;

  }
  //getters y setters
  public String getRegistro1(){return registro1;}
  public String getRegistro2(){return registro2;}
  public String getRegistro3(){return registro3;}
  public String getRegistro4(){return registro4;}
  public String getRegistro5(){return registro5;}
  public String getRegistro6(){return registro6;}
  public String getRegistro7(){return registro7;}
  public String getRegistro8(){return registro8;}
  public String getRegistro9(){return registro9;}
  public String getRegistro10(){return registro10;}
  public String getNombreCliente(){return this.nombreCliente;}
  public String getTelefono(){return this.telefono;}
  public void setNumero(int nuevoNumero){this.numeroProductos=nuevoNumero;}
  public int getNumero(){return this.numeroProductos;}
  public void setTotal(int nuevoTotal){this.total +=nuevoTotal;}
  public int getTotal(){return this.total;}
  public void setGanancia(int gananciaProducto,int cantidad){this.ganancia+=gananciaProducto*cantidad;}
  public int getGanancia(){return this.ganancia;}
  public String getMedioDePago(){return this.medioDePago;}
  public String getDetalle(){return this.detalle;}
  public void agregaritem(Producto punto,int canti, int precioVenta){//me acorde de vectores :v
    boolean verificador = false;
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
        boolean verificador=true;
      }
    }
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
    switch(cr){
        // esta mal, tocaria crear una subclase llamada registro  
      case 1:
        registro1= new Factura();
        break;
      case 2:
        registro2= "Cliente: " + this.nombreCliente + "\nTelefono: " + this.telefono + "\nForma de pago: " + this.medioDePago + "\n" + this.detalle + "\nTotal a pagar:.................. $" + this.total;
        break;
      case 3:
        registro3= "Cliente: " + this.nombreCliente + "\nTelefono: " + this.telefono + "\nForma de pago: " + this.medioDePago + "\n" + this.detalle + "\nTotal a pagar:.................. $" + this.total;
        break;
      case 4:
        registro4= "Cliente: " + this.nombreCliente + "\nTelefono: " + this.telefono + "\nForma de pago: " + this.medioDePago + "\n" + this.detalle + "\nTotal a pagar:.................. $" + this.total;
        break;
      case 5:
        registro5= "Cliente: " + this.nombreCliente + "\nTelefono: " + this.telefono + "\nForma de pago: " + this.medioDePago + "\n" + this.detalle + "\nTotal a pagar:.................. $" + this.total;
        break;
      case 6:
        registro6= "Cliente: " + this.nombreCliente + "\nTelefono: " + this.telefono + "\nForma de pago: " + this.medioDePago + "\n" + this.detalle + "\nTotal a pagar:.................. $" + this.total;
        break;
      case 7:
        registro7= "Cliente: " + this.nombreCliente + "\nTelefono: " + this.telefono + "\nForma de pago: " + this.medioDePago + "\n" + this.detalle + "\nTotal a pagar:.................. $" + this.total;
        break;
      case 8:
        registro8= "Cliente: " + this.nombreCliente + "\nTelefono: " + this.telefono + "\nForma de pago: " + this.medioDePago + "\n" + this.detalle + "\nTotal a pagar:.................. $" + this.total;
        break;
      case 9:
        registro9= "Cliente: " + this.nombreCliente + "\nTelefono: " + this.telefono + "\nForma de pago: " + this.medioDePago + "\n" + this.detalle + "\nTotal a pagar:.................. $" + this.total;
        break;
      case 10:
        registro10= "Cliente: " + this.nombreCliente + "\nTelefono: " + this.telefono + "\nForma de pago: " + this.medioDePago + "\n" + this.detalle + "\nTotal a pagar:.................. $" + this.total;
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
    registro8="";
    registro10="";
  }
}

