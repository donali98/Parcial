package parcial.logic;

import parcial.base.Habitacion;
import parcial.utils.Menu;
import parcial.utils.Precios;
import java.util.ArrayList;
import java.util.Scanner;

public class ListaHabitacion {
    
    private static ArrayList<Habitacion> listaHabitaciones;
    private static ListaHabitacion listaHabitacion;
    private static int[] valoresUnicode;
    private static int cantiPisos;
    private ListaHabitacion(){}
    
    
    public static ArrayList<Habitacion> getListaHabitaciones(){
        return listaHabitaciones;
    }
    //Cambiar
    private static void pedirCantiPisos(){
        /*Scanner scanner = new Scanner(System.in);
        while (true){
           try {
               System.out.println("Ingrese la cantidad de pisos del hotel: ");
               cantiPisos = scanner.nextInt();
               if (cantiPisos>26 || cantiPisos<3){
                   System.out.println("La cantidad de pisos no puede ser menor que 3 y mayo
        this.menuHoter a 26");
               }
               else break;
           }
           catch (Exception e){
               scanner.next();
               System.out.println("Valor no valido");
           }
        }*/
        cantiPisos =3;
    }
    
    private static void llenarValoresUnicode(){
        int contador = 0;
        valoresUnicode = new int[cantiPisos];
        for (int i = 97; i<=97+(cantiPisos-1)  ;i++){

            valoresUnicode[contador] = i;
            contador++;
        }
    }
    
    private static void crearEdificio(){
        try {
            Precios.pedirPreciosHabitacion();
            Double precio;
            String tipo;

            System.out.println("\n");
            for (int i=1; i<=cantiPisos;i++){
                for (int j=1; j<=10;j++){
                    String codigo = String.valueOf((char) valoresUnicode[i-1]).toUpperCase()+j;

                    //Validando los precios de las habitaciones de los ultimos dos pisos
                    if(i == cantiPisos  || i == cantiPisos - 1){
                        if(j% 2 == 0){
                            precio = Precios.getPrecioDoble()+ (Precios.getPrecioDoble() *0.10);
                            tipo = "doble";
                        }
                        else{
                            precio=Precios.getPrecioDoble()+(Precios.getPrecioSencilla() * 0.10);
                            tipo = "sencilla";
                        }
                    }
                    else{
                        if(j % 2 == 0){
                            precio = Precios.getPrecioDoble();
                            tipo = "doble";
                        }
                        else {
                            precio = Precios.getPrecioSencilla();
                            tipo = "sencilla";
                        }
                    }
                    listaHabitaciones.add(new Habitacion(codigo,precio,tipo,"disponible"));
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void deshabilitarCuarto(Habitacion habitacion){
        if(habitacion == null){
            System.out.println("Operacion cancelada o no se encontro valor");
        }
        else {

            switch (habitacion.getEstado()){
                case "disponible":
                    buscarHabitaion(habitacion).setEstado("deshabilitada");
                    System.out.println("Habitacion deshabilitada con exito");
                break;
                case "deshabilitada":
                    if(Menu.getInstance().mostrarMenuConfirmacion("La habitacion ya esta deshabilitada, desea habilitarla?")){
                        buscarHabitaion(habitacion).setEstado("habilitada");
                        System.out.println("Habitacion restaurada correctamente");
                    }
                    break;
                case "ocupada":
                    System.out.println("No se puede deshabilitar una habitacion que esta reservada");
                break;

            }
        }
    }
    public  static Habitacion buscarHabitaion(Habitacion ha){
        for(Habitacion habitacion:listaHabitaciones){
            if(habitacion.equals(ha)){
                return habitacion;
            }
        }
        return  null;
    }
    public static Habitacion pedir(){
        Habitacion output ;
        String posible ;
        Scanner scanner = new Scanner(System.in);
        try {
            while (true){
                System.out.println("Ingrese el codigo de habitacion(0 para cancelar): ");
                posible = scanner.next();
                if(posible.equals("0")){
                    return null;
                }
                Habitacion habitacion = validarCodigo(posible);
                if(habitacion!=null){
                    return habitacion;
                }
                else System.out.println("Codigo no valido, revisar valor");
            }
        }catch (Exception e){
            scanner.next();
            System.out.println("Habitacion no encontrada");
            return null;
        }
    }
    public static Habitacion validarCodigo(String codigo){

        for(Habitacion habitacion: getListaHabitaciones()){

            if(habitacion.getCodigo().equals(codigo)){
                return habitacion;

            }
        }
        return null;

    }
    public static ListaHabitacion getInstance(){
        if(listaHabitacion == null){
            listaHabitaciones = new ArrayList<>();
            listaHabitacion = new ListaHabitacion();

            pedirCantiPisos();
            llenarValoresUnicode();
            crearEdificio();


        }
        return listaHabitacion;
    }
}
