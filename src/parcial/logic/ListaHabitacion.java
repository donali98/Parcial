package parcial.logic;

import parcial.base.Habitacion;
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
    
    private static void pedirCantiPisos(){
        Scanner scanner = new Scanner(System.in);
        while (true){
           try {
               System.out.println("Ingrese la cantidad de pisos del hotel: ");
               cantiPisos = scanner.nextInt();
               if (cantiPisos>27 || cantiPisos<3){
                   System.out.println("La cantidad de pisos no puede ser menor que 3 y mayor a 27");
               }
               else break;
           }
           catch (Exception e){
               scanner.next();
               System.out.println("Valor no valido");
           }
        }
    }
    
    private static void llenarValoresUnicode(){
        int contador = 0;
        valoresUnicode = new int[cantiPisos];
        for (int i = 97; i<=( 97+(cantiPisos - 1) );i++){
            valoresUnicode[contador] = i;
            contador++;
        }
    }
    
    private static void crearEdificio(){
        Precios.pedirPrecios();
        Double precio;
        String tipo;
        for (int i=0; i<=cantiPisos-1;i++){
            for (int j=0; j<10;j++){
                String codigo = String.valueOf((char) valoresUnicode[i]).toUpperCase()+(j+1);
                if(cantiPisos-1 == (i+1)  || cantiPisos == (i+1)){
                    if((j+1)% 2 == 0){
                        precio = Precios.getPrecioDoble()+ (Precios.getPrecioDoble() *0.10);
                        tipo = "doble";
                    }
                    else{
                        precio=Precios.getPrecioDoble()+(Precios.getPrecioSencilla() * 0.10);
                        tipo = "sencilla";
                    }
                }
                else{
                    if((j+1) % 2 == 0){
                        precio = Precios.getPrecioDoble();
                        tipo = "doble";
                    }
                    else {
                        precio = Precios.getPrecioSencilla();
                        tipo = "sencilla";
                    }
                }
                listaHabitaciones.add(new Habitacion(codigo,precio,tipo,"habilitada"));
            }
        }
    }
}
