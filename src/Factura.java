public class Factura{
  private String nombreCliente;
  private int numeroProductos=0;
  private int telefono;
  private int total=0;
  private int ganancia=0;
  private String medioDePago;
  private String detalle="";
  /*
   private registro registro1;
   private registro registro2;
   private registro registro3;
   private registro registro4;
   private registro registro5;
   private registro registro6;
   private registro registro7;
   private registro registro8;
   private registro registro9;
   private registro registro10;
  */
  //cambiarlas de estaticas tipo string a tipo facturas, mirar a futuro..........o ya
  public class registro{
    public String nom;
    public int tel;
    public String med;
    public String det;
    public int tot;

    public registro(String nom, int tel, String med, String det, int tot){
      this.nom = nom;
      this.tel=tel;
      this.med=med;
      this.det=det;
      this.tot=tot;
        
    }
  }
  private int cr = 0;
  // el atributo detalle es para almacenar los productos en el metodo de agregaritem para que luego se impriman en la factura
  public Factura(String nombreCliente,int telefono,String medioDePago){
    this.nombreCliente = nombreCliente;
    this.medioDePago=medioDePago;
    this.telefono=telefono;

  }
  //getters y setters
  public String getNombreCliente(){return this.nombreCliente;}
  public int getTelefono(){return this.telefono;}
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
      case 1:
        registro registro1= new registro(this.nombreCliente, this.telefono, this.medioDePago, this.detalle, this.total);
        break;
      case 2:
        registro registro2= new registro(this.nombreCliente, this.telefono, this.medioDePago, this.detalle, this.total);
        break;
      case 3:
        registro registro3= new registro(this.nombreCliente, this.telefono, this.medioDePago, this.detalle, this.total);
        break;
      case 4:
        registro registro4= new registro(this.nombreCliente, this.telefono, this.medioDePago, this.detalle, this.total);
        break;
      case 5:
        registro registro5= new registro(this.nombreCliente, this.telefono, this.medioDePago, this.detalle, this.total);
        break;
      case 6:
        registro registro6= new registro(this.nombreCliente, this.telefono, this.medioDePago, this.detalle, this.total);
        break;
      case 7:
        registro registro7= new registro(this.nombreCliente, this.telefono, this.medioDePago, this.detalle, this.total);
        break;
      case 8:
        registro registro8= new registro(this.nombreCliente, this.telefono, this.medioDePago, this.detalle, this.total);
        break;
      case 9:
        registro registro9= new registro(this.nombreCliente, this.telefono, this.medioDePago, this.detalle, this.total);
        break;
      case 10:
        registro registro10= new registro(this.nombreCliente, this.telefono, this.medioDePago, this.detalle, this.total);
        break;
      default:
        break;
    }

  }
}

