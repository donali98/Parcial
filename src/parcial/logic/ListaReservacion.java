/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial.logic;
import  parcial.base.Habitacion;
import parcial.base.Huesped;
import parcial.base.Reservacion;
import parcial.utils.Dui;
import parcial.utils.Menu;
import parcial.utils.Nombre;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Guille
 */
public class ListaReservacion {
     private ListaReservacion(){}
    private static ListaReservacion listaReservacion;
    private static ArrayList<Reservacion> reservaciones;
    private static int debg = 0;

    public static ListaReservacion getInstance(){
        if(listaReservacion == null){
            listaReservacion = new ListaReservacion();
            reservaciones = new ArrayList<>();
        }
        return listaReservacion;
    }

    public static ArrayList<Reservacion> getReservaciones(){
        return reservaciones;
    }


    public void performAction(String action){
        Scanner scanner = new Scanner(System.in);
        int op = 10;

        switch (action){
            case "insert":
                Reservacion reservacion;
                Double totalAPagar;

                while (op!=0){
                    try {
                        this.mostrarHabitaciones(true);
                        System.out.println("Elija una habitacion por su codigo");
                        String codigoHabitacion = scanner.next().toUpperCase();
                        Habitacion habitacionAReservar = this.validarCodigo(codigoHabitacion);
                        if(habitacionAReservar!=null){
                            if (habitacionAReservar.getEstado().equals("ocupada") || habitacionAReservar.getEstado().equals("deshabilitada")){
                                switch (habitacionAReservar.getEstado()){
                                    case "ocupada":
                                        System.out.println("La habitacion esta ocupada");
                                        break;
                                    case "deshabilitada":
                                        System.out.println("Habitacion fuera de servicio");
                                        break;
                                }
                                op = 1;
                            }
                            else{
                                op = 0;
                                String nombres[];
                                String dui;
                                LocalDate fechaInicio, fechaFin;
                                LocalDate[]fechas;
                                System.out.println("Ingrese los datos del huesped: ");
                                nombres = Nombre.pedir();
                                dui = Dui.pedir();
                                fechas = this.pedirFechas();
                                fechaInicio = fechas[0];
                                fechaFin = fechas[1];
                                int duracion = Integer.valueOf(String.valueOf(this.getDuration(fechaInicio,fechaFin)));
                                totalAPagar = this.calcularTotal(habitacionAReservar,duracion,"");
                                String paquete = this.sugerirPaquete();


                                switch (paquete){
                                    case "Premium":
                                        totalAPagar = this.calcularTotal(habitacionAReservar,duracion,"Premium");
                                        reservacion = new Reservacion(nombres[0],nombres[1],dui,habitacionAReservar,ListaPaquetes.getInstance().getPaquetes().get(0),fechaInicio,fechaFin,totalAPagar);
                                        reservaciones.add(reservacion);
                                        this.cambiarEstadoHabitacion(false,habitacionAReservar);
                                    break;
                                    case "Basico":
                                        totalAPagar = this.calcularTotal(habitacionAReservar,duracion,"Basico");
                                        reservacion  = new Reservacion(nombres[0],nombres[1],dui,habitacionAReservar,ListaPaquetes.getInstance().getPaquetes().get(1),fechaInicio,fechaFin,totalAPagar);
                                        reservaciones.add(reservacion);
                                        this.cambiarEstadoHabitacion(false,habitacionAReservar);
                                    break;
                                    case "Ninguno":
                                        reservacion  = new Reservacion(nombres[0],nombres[1],dui,habitacionAReservar,ListaPaquetes.getInstance().getPaquetes().get(1),fechaInicio,fechaFin,totalAPagar);
                                        reservaciones.add(reservacion);
                                        this.cambiarEstadoHabitacion(false,habitacionAReservar);
                                    break;
                                }
                                System.out.println("Reservacion realizada con exito");

                            }

                        }
                        else{
                            System.out.println("Codigo de habitacion no valido");
                        }

                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                break;
            case "delete":
                while (op!=0){
                    if(!this.mostrarHabitaciones(false)){
                        System.out.println("------------------------------------------------");
                        System.out.println("No hay habitaciones reservadas actualmente");
                        System.out.println("-----------------------------------------------");
                        break;
                    }
                    System.out.println("Ingrese una habitacion por el codigo(0 para volver): ");
                    String codigo = scanner.next();
                    if(codigo.equals("0")){
                        break;
                    }
                    else{
                        codigo = codigo.toUpperCase();
                        //Valida existencia de la habitacion
                        Habitacion habitacion =  this.validarCodigo(codigo);
                        if(habitacion!=null){
                            if(!habitacion.getEstado().equals("ocupada")){
                                op = 1;
                                System.out.println("La habitacion que ha ingresado no esta asociada a una reservacion");
                            }
                            else {
                                op = 0;
                                //Si quiere eliminar la reservacion
                                if(Menu.getInstance().mostrarMenuConfirmacion("Esta seguro que desea eliminar la reservacion asociada con la habitacion "+codigo)){
                                    //busca la reservacion
                                    Reservacion reservacionAEliminar = buscarReservacion(habitacion);
                                    //si la encuentra
                                    if(reservacionAEliminar!=null){
                                        reservaciones.remove(reservacionAEliminar);
                                        System.out.println("Reservacion eliminada con exito");
                                        this.cambiarEstadoHabitacion(true,habitacion);
                                    }
                                    else System.out.println("Reservacion con la habitacion especificada no ha sido encotrada");
                                }
                            }
                        }
                        else{
                            System.out.println("Habitacion no encontrada, asegurese que ha ingresado el codigo de habitacion correctamente");
                        }
                    }

                }
                break;
            case "update":
                while (op!=0){
                    if(!this.mostrarHabitaciones(false)){
                        System.out.println("------------------------------------------------");
                        System.out.println("No hay habitaciones reservadas actualmente");
                        System.out.println("-----------------------------------------------");
                        break;
                    }
                    System.out.println("Ingrese una habitacion por el codigo (0 para volver): ");
                    String codigo = scanner.next();
                    if(codigo.equals("0")){
                        break;
                    }
                    else {
                        codigo = codigo.toUpperCase();
                        //Valida existencia de la habitacion
                        Habitacion habitacion =  this.validarCodigo(codigo);
                        if(habitacion!=null){
                            if(!habitacion.getEstado().equals("ocupada")){
                                op = 1;
                                System.out.println("La habitacion que ha ingresado no esta asociada a una reservacion");
                            }
                            else {
                                int opModificar = 1;
                                //Si quiere eliminar la reservacion
                                //busca la reservacion
                                Reservacion reservacionAModificar = buscarReservacion(habitacion);
                                //si la encuentra
                                if(reservacionAModificar!=null){
                                    String [] opcionesMenuModificar =new String[]{"1-Nombres Huesped","2-DUI Huesped","3-Habitacion","4-Fechas","0-Regresar"};
                                    opModificar = Menu.getInstance().subMenu(opcionesMenuModificar);
                                    Huesped huesped = this.buscarReservacion(habitacion).getHuesped();
                                    Menu menu = Menu.getInstance();
                                    switch (opModificar){
                                        case 1:
                                            String[] nombres = Nombre.pedir();

                                            if(menu.mostrarMenuConfirmacion("Esta seguro que desea modificar los nombres del huesped?")){
                                                huesped.getNombres().setNombres(nombres[0]);
                                                huesped.getNombres().setApellidos(nombres[1]);
                                                System.out.println("Nombres del huesped modificados con exito");
                                                op = 0;
                                            }
                                            break;
                                        case 2:
                                            String dui = Dui.pedir();
                                            if(menu.mostrarMenuConfirmacion("Esta seguro que desea modificar los nombres del huesped?")) {
                                                huesped.setDui(Dui.getDuiFromString(dui));
                                                System.out.println("Dui del huesped modificado con exito");
                                            }
                                            op = 0;
                                            break;
                                        case 3:

                                            if(menu.mostrarMenuConfirmacion("Esta seguro que desea reasignar reservacion?")){
                                                reservaciones.remove(habitacion);
                                                this.performAction("insert");
                                            }
                                            op = 0;
                                            break;
                                    }
                                }
                                else System.out.println("Reservacion con la habitacion especificada no ha sido encotrada");
                            }
                        }
                    }

                }
                break;
        }
    }



    private Double calcularTotal(Habitacion habitacion,int dias, String tipoPaquete){
        Double total = habitacion.getPrecio()*dias;

        switch (tipoPaquete){
            case "Premium":
                for(int i=0; i<dias;i++){
                    total+=150.0;
                }
            return total;

            case "Basico":
                for(int i=0; i<dias;i++){
                    total+=10.0;
                }
            return total;

            default:
                return total;

        }
    }

    private Reservacion buscarReservacion(Habitacion habitacion){
        for (Reservacion reservacion:reservaciones){
            if(reservacion.getHabitacion().equals(habitacion)){
                return reservacion;
            }
        }
        return null;
    }

    private void cambiarEstadoHabitacion(boolean estado,Habitacion habitacion){
       if(estado){
            ListaHabitacion.getListaHabitaciones().get(ListaHabitacion.getListaHabitaciones().indexOf(habitacion)).setEstado("disponible");
       }
       else ListaHabitacion.getListaHabitaciones().get(ListaHabitacion.getListaHabitaciones().indexOf(habitacion)).setEstado("ocupada");

    }
    private long getDuration(LocalDate fechaInicio, LocalDate fechaFin){
        Duration duration = Duration.between(fechaFin.atStartOfDay(),fechaInicio.atStartOfDay());
        return Math.abs(duration.toDays());
    }
    private String[] validarDuracionReserva(LocalDate fechaInicio, LocalDate fechaLimite){
        String[] output = new String[2];

        output[0] = "ok";

        output[1] =  String.valueOf( this.getDuration(fechaInicio,fechaLimite));

        try{
            if(fechaInicio.isBefore(LocalDate.now())){
             output[0] = "error";
             output[1] = "El dia que desea reservar la habitacion ya paso";
             return output;
            }
            if(Long.valueOf(output[1])>7){
                output[0] = "error";
                output[1] = "No se puede realizar una reservacion con duracion mayor a 7 dias";
                return output;
            }
        }
        catch (Exception e){
            output[0] = "error";
            System.out.println(e.getMessage());
        }

        return output;

    }
    private LocalDate parseDate(String fecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(fecha,formatter);
        return  parsedDate;
    }


    private String sugerirPaquete(){

        Scanner scanner = new Scanner(System.in);
        int selected;
        if(Menu.getInstance().mostrarMenuConfirmacion("Asignar paquete?")){
           selected = Menu.getInstance().subMenu(new String []{"1-Premium","2-Basico"});
           switch (selected){
               case 1:
                   return "Premium";
               case 2:
                   return "Basico";
           }
        }
        return "Ninguno";
    }
    private ArrayList<Habitacion> devolverHabitacionesConEstado(boolean sentido){

        ArrayList<Habitacion> habitacionesDisponibles = new ArrayList<>();
        if(sentido){
            for (Habitacion habitacion: ListaHabitacion.getListaHabitaciones()){
                if(!habitacion.getEstado().equals("ocupada") && !habitacion.getEstado().equals("deshabilitada")){
                    habitacionesDisponibles.add(habitacion);
                }
            }
        }
        else
        {
            for (Habitacion habitacion: ListaHabitacion.getListaHabitaciones()){
                if(habitacion.getEstado().equals("ocupada") && !habitacion.getEstado().equals("deshabilitada")){
                    habitacionesDisponibles.add(habitacion);
                }
            }
        }
        return habitacionesDisponibles;

    }
    public ArrayList<Reservacion> mostrarReservacionesSemana(){
        ArrayList<Reservacion> output = new ArrayList<>();
        LocalDate limiteInferior =LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
        LocalDate limiteSuperiot = limiteInferior.plusDays(7);
        Reservacion reservacion;

        for (Habitacion habitacion: this.devolverHabitacionesConEstado(false)){
             reservacion = this.buscarReservacion(habitacion);
             if(reservacion.getFechaInicio().equals(limiteInferior)){
                 output.add(reservacion);
             }
             else if(reservacion.getFechaInicio().isAfter(limiteInferior) && reservacion.getFechaInicio().isBefore(limiteSuperiot)){
                 output.add(reservacion);
             }
        }

       return output;
    }

    private boolean mostrarHabitaciones(boolean estado){
        boolean existe = false;
        if(estado){
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Habitaciones disponibles: ");
            System.out.println("------------------------------------------------------------------------");

            for (Habitacion habitacion: this.devolverHabitacionesConEstado(true)){
                System.out.println("-------------------------------------------------------");
                existe =  true;
                System.out.println("Codigo: "+habitacion.getCodigo());
                System.out.println("Tipo: "+habitacion.getTipo());
                System.out.println("Precio: "+habitacion.getPrecio());
                System.out.println("-------------------------------------------------------");
            }
        }
        else   {
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Habitaciones reservadas: ");
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Desea ver todas las reservaciones o solo las de esta semana?");
            int filtro = Menu.getInstance().subMenu(new String[]{
                    "1-Todas",
                    "2-Programadas para esta Semana"
            });
            switch (filtro){
                case 1:
                    for (Habitacion habitacion: this.devolverHabitacionesConEstado(false)){
                        String paqueteAdquirido = this.buscarReservacion(habitacion).getPaquete().getNombre();

                        System.out.println("------------------------------------------------------------------------");
                        Reservacion reservacion = listaReservacion.buscarReservacion(habitacion);
                        existe = true;
                        System.out.println("Codigo Habitacion: "+habitacion.getCodigo());
                        System.out.println("DUI Cliente: "+reservacion.getHuesped().getDui().getDigitos());
                        System.out.println("Cliente: "+reservacion.getHuesped().getNombres().getApellidos()+", "+reservacion.getHuesped().getNombres().getNombres());
                        System.out.println("Paquete: "+paqueteAdquirido);
                        System.out.println("Fecha Inicio : "+reservacion.getFechaInicio());
                        System.out.println("Fecha Fin : "+reservacion.getFechaFin());
                        System.out.println("Monto Cancelado : "+"$"+reservacion.getTotalPagar());
                        System.out.println("------------------------------------------------------------------------");
                    }

                break;
                case 2:
                    for (Reservacion reservacion: this.mostrarReservacionesSemana()){
                        String paqueteAdquirido = reservacion.getPaquete().getNombre();
                        System.out.println("------------------------------------------------------------------------");
                        existe = true;
                        System.out.println("Codigo Habitacion: "+reservacion.getHabitacion().getCodigo());
                        System.out.println("Cliente: "+reservacion.getHuesped().getNombres().getApellidos()+", "+reservacion.getHuesped().getNombres().getNombres());
                        System.out.println("Paquete: "+paqueteAdquirido);
                        System.out.println("Fecha Inicio : "+reservacion.getFechaInicio());
                        System.out.println("Fecha Fin : "+reservacion.getFechaFin());
                        System.out.println("Monto Cancelado : "+"$"+reservacion.getTotalPagar());
                        System.out.println("------------------------------------------------------------------------");
                    }
                break;
            }
        }
        return  existe;
    }

    private Habitacion validarCodigo(String codigo){

        for(Habitacion habitacion: ListaHabitacion.getListaHabitaciones()){

            if(habitacion.getCodigo().equals(codigo)){
               return habitacion;

            }
        }
        return null;

    }
    private LocalDate[] pedirFechas(){
        Scanner scanner = new Scanner(System.in);
        String []resultadoValidacion;
        LocalDate[] output = new LocalDate[2];
        int op = 1;
        while (op!=0){
            try{
                System.out.println("Ingrese la fecha de check-in(yyyy-MM-dd): ");
                output[0] = parseDate(scanner.next());
                if(output[0].isBefore(LocalDate.now())) System.out.println("La fecha no puede ser antes de ahora");
                else op =0;
            }
            catch (Exception e){
                System.out.println("Valores no validos");
            }
        }
        op = 1;
        while (op!=0){
            try{
                System.out.println("Ingrese la fecha de check-out(yyyy-MM-dd): ");
                output[1] = parseDate(scanner.next());
                if(output[1].isBefore(output[0])){
                    System.out.println("La fecha de checkout no puede ser una fecha antes de la del check in");
                }
                else if(output[1].equals(output[0])){
                    System.out.println("Una reservacion debe ser de un dia minimo");

                }
                else {
                    resultadoValidacion=this.validarDuracionReserva(output[0],output[1]);

                    if (!resultadoValidacion[0].equals("ok")){
                        System.out.println("-----------------------------------------------------------");
                        System.out.println(resultadoValidacion[1]);
                        System.out.println("-----------------------------------------------------------");
                    }
                    else op =0;
                }
            }
            catch (Exception e){
                System.out.println("Valores no validos");
            }
        }
        return output;
    }

}
