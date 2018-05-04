/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial.logic;
import parcialpoo.base.Habitacion;
import parcialpoo.base.Reservacion;
import parcialpoo.utils.Dui;
import parcialpoo.utils.Nombre;

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
    
}
