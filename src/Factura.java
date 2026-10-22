public class Factura{
  private String nombreCliente;
  private int numeroProductos=0;
  private String telefono;
  private int total=0;
  private int ganancia=0;
  private String medioDePago;
  private String detalle="";
// atributos de la claseeeeeee
  private static registro registro1;
  private static registro registro2;
  private static registro registro3;
  private static registro registro4;
  private static registro registro5;
  private static registro registro6;
  private static registro registro7;
  private static registro registro8;
  private static registro registro9;
  private static registro registro10;

  //cambiarlas de estaticas tipo string a tipo facturas, mirar a futuro..........o ya
  public class registro{
    private String nom;
    private String tel;
    private String med;
    private String det;
    private int tot;
// creacion de la subclase(atributos publicos, preguntar si se puede dejar asi o toca cambiarlo a privado)
    public registro(String nom, String tel, String med, String det, int tot){
      this.nom = nom;
      this.tel=tel;
      this.med=med;
      this.det=det;
      this.tot=tot;
    }
    //getters
    public String getNom(){return nom;}
    public String getTel(){return tel;}
    public String getMed(){return med;}
    public String getDet(){return det;}
    public int getTot(){return tot;}
  }
// contador para mantener el orden en el que se guardan las facturas
  private static int cr = 0;
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
    cr+=1;
  }
  //se utiliza para poder llamar al atributo de la subclase seleccionado 
  public static registro getRegistro(int posRegistro){
    switch(posRegistro){
      case 1:
        return registro1;
      case 2:
        return registro2;
      case 3:
       return registro3;
      case 4:
       return registro4;
      case 5:
       return registro5;
      case 6:
        return registro6;
      case 7:
        return registro7;
      case 8:
        return registro8;
      case 9:
        return registro9;
      case 10:
        return registro10;
      default:
        return null;
    }
  }
// metodo para guardar las facturas creadas en una subclase llamada registro, se pueden guardar hasta 10 facturas
  public void guardar(){
    switch(cr){
      case 1:
        registro1= new registro(this.nombreCliente, this.telefono, this.medioDePago, this.detalle, this.total);
        break;
      case 2:
        registro2= new registro(this.nombreCliente, this.telefono, this.medioDePago, this.detalle, this.total);
        break;
      case 3:
        registro3= new registro(this.nombreCliente, this.telefono, this.medioDePago, this.detalle, this.total);
        break;
      case 4:
        registro4= new registro(this.nombreCliente, this.telefono, this.medioDePago, this.detalle, this.total);
        break;
      case 5:
        registro5= new registro(this.nombreCliente, this.telefono, this.medioDePago, this.detalle, this.total);
        break;
      case 6:
        registro6= new registro(this.nombreCliente, this.telefono, this.medioDePago, this.detalle, this.total);
        break;
      case 7:
        registro7= new registro(this.nombreCliente, this.telefono, this.medioDePago, this.detalle, this.total);
        break;
      case 8:
        registro8= new registro(this.nombreCliente, this.telefono, this.medioDePago, this.detalle, this.total);
        break;
      case 9:
        registro9= new registro(this.nombreCliente, this.telefono, this.medioDePago, this.detalle, this.total);
        break;
      case 10:
        registro10= new registro(this.nombreCliente, this.telefono, this.medioDePago, this.detalle, this.total);
        break;
      default:
        break;
    }
  }
  public void terminarDia(){
    cr=0;
  }
}
