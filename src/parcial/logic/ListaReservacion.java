/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial.logic;
import  parcial.base.Habitacion;
import parcial.base.Reservacion;
import parcial.utils.Dui;
import parcial.utils.Nombre;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
/**
 *
 * @author Guille
 */
public class ListaReservacion {
     private ListaReservacion(){}
    private static ListaReservacion listaReservacion;
    private static ArrayList<Reservacion> reservaciones;
   
    public static ListaReservacion getInstance(){
        if(listaReservacion == null){
            listaReservacion = new ListaReservacion();
            reservaciones = new ArrayList<>();
        }
        return listaReservacion;
    }
    
    public void addReservacion(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Habitaciones disponibles: ");
        System.out.println("------------------------------------------------------------------------");
        for (Habitacion habitacion: this.validarDisponibilidad()){
            System.out.println(habitacion.getCodigo()+"  |   "+habitacion.getPrecio());
            if(habitacion.getCodigo().substring(1,habitacion.getCodigo().length()).contains("10")){
                System.out.println("---------------------------------------------------------------------");
            }

        }
        int op = 10;
        while (op!=0){
            try {
                System.out.println("Elija una habitacion por su codigo");
                String codigoHabitacion = scanner.next();
                Habitacion habitacionAReservar = this.validarCodigo(codigoHabitacion);
                if(habitacionAReservar!=null){
                    op = 0;
                    String nombres[];
                    String dui;
                    LocalDate fechaInicio, fechaFin;
                    System.out.println("Ingrese los datos del huesped: ");
                    nombres = Nombre.pedir();
                    dui = Dui.pedir();
                    System.out.println("Ingrese la fecha de check-in(yyyy-MM-dd): ");
                    fechaInicio = parseDate(scanner.next());
                    System.out.println("Ingrese la fecha de check-out:(yyyy-MM-dd) ");
                    fechaFin = parseDate(scanner.next());



                    String paquete = this.sugerirPaquete();

                    if(!paquete.contains("")){
                        switch (paquete){
                            case "Premium":
                                reservaciones.add(new Reservacion(nombres[0],nombres[1],dui,habitacionAReservar,ListaPaquetes.getInstance().getPaquetes().get(0),fechaInicio,fechaFin));

                                break;
                            case "Basico":
                                reservaciones.add(new Reservacion(nombres[0],nombres[1],dui,habitacionAReservar,ListaPaquetes.getInstance().getPaquetes().get(1),fechaInicio,fechaFin));

                                break;
                        }
                    }
                    else reservaciones.add(new Reservacion(nombres[0],nombres[1],dui,habitacionAReservar,fechaInicio,fechaFin));
                    System.out.println("Reservacion realizada con exito");
                }
                else{
                    System.out.println("Codigo de habitacion no valido");
                }

            }
            catch (Exception e){
                System.out.println(e.getCause());
            }
        }

    }
    private boolean validarDuracionReserva(LocalDate fecha){
        LocalDate fechaLimite = fecha.plusDays(7);
        return fecha.isAfter(fechaLimite);
    }
    private LocalDate parseDate(String fecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(fecha,formatter);
        return  parsedDate;
    }
private String sugerirPaquete(){
        Scanner scanner = new Scanner(System.in);
        String selected;
        int op = 10;
        while (op!=0){
            System.out.println("Asignar paquete? (s/n)");
            selected = scanner.next();
            switch (selected){
                case "s":
                    int opPaquete = 10;

                        while (opPaquete!=0) {
                            try {
                                System.out.println("Seleccione el paquete: ");
                                System.out.println("1-Premium");
                                System.out.println("2-Basico");
                                opPaquete = scanner.nextInt();
                                switch (opPaquete) {
                                    case 1:
                                        return "Premium";

                                    case 2:
                                        return "Basico";
                                    default:
                                        System.out.println("Opcion no valida");
                                        break;
                                }

                            } catch (Exception e) {
                                System.out.println("Valor no valido");
                            }
                        }
                    op = 0;
                break;
                default:
                    return "";

            }
        }
        return "";
    }
private ArrayList<Habitacion> validarDisponibilidad(){
        ArrayList<Habitacion> habitacionesDisponibles = new ArrayList<>();

        for (Habitacion habitacion: ListaHabitacion.getListaHabitaciones()){
            if(!habitacion.getEstado().contains("ocupada") || !habitacion.getEstado().contains("deshabilitada")){
                habitacionesDisponibles.add(habitacion);
            }
        }
        return habitacionesDisponibles;

    }
private Habitacion validarCodigo(String codigo){
        for(Habitacion habitacion: ListaHabitacion.getListaHabitaciones()){
            if(habitacion.getCodigo().contains(codigo)){
               return habitacion;

            }
        }
        return null;

    }
    
}
