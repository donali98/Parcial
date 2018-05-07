/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial.utils;

import java.util.Scanner;
import java.util.zip.DeflaterOutputStream;

import parcial.base.Paquete;
import parcial.logic.ListaHabitacion;
import parcial.logic.ListaPaquetes;
import parcial.logic.ListaReservacion;
import parcial.logic.ListaUsuarios;

/**
 *
 * @author Guille
 */
public class Menu {
    
private static Menu menu;
    private Menu(){}

    public static Menu getInstance(){
        if(menu == null){
            menu = new Menu();
        }
        return menu;
    }
    
    // Crear menu
    public void crearMenu(String[] opciones){
        System.out.println("------Seleccione una opcion------\n");
        for (String opcion:opciones){
            System.out.println(opcion);
        }
    }
    //Metodo del menu principal
    public void menuPrincipal(){
        /*int selected = 10;
        Scanner reader = new Scanner(System.in);
        while (selected!=0){

            try {

                this.crearMenu(new String[]{"1-Gestionar personal","2-Gestionar hotel","0-Salir del sistema"});
                selected = reader.nextInt();
                switch (selected){
                    case 1:
                        this.menuEmpleados();
                        selected = 0;
                        break;
                    case 2:
                        this.menuHotel();
                        selected = 0;
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema");
                        break;
                    default:
                        System.out.println("No ha ingresado una opcion valida");
                        break;
                }
            }
            catch (Exception e){
                reader.next();
                System.out.println("Valor no valido");
            }

        }*/
        this.menuHotel();
    }
    public void menuHotel(){
        //Generando al hotel
        ListaHabitacion.getInstance();

        Scanner scanner = new Scanner(System.in);
        int selected = 10;
        while (selected!=0){
            try{
                this.crearMenu(new String[]{"1-Reservaciones","2-Administrar paquetes","0-Salir"});
                selected = scanner.nextInt();
                switch (selected){
                    case 1:
                        menuReservaciones();
                        break;
                    case 2:
                        menuPaquete(1);
                        selected = 0;
                        break;
                    case 0:
                        menuPrincipal();
                        break;
                    default:
                        System.out.println("Opcion no valida");
                        break;
                }
            }
            catch (Exception e){
                scanner.next();
                System.out.println("Valor no valido");

            }
        }
    }
    
    public void menuReservaciones(){
        //modificacion
        menuPaquete(0);
        int selected = 10;
        Scanner scanner = new Scanner(System.in);
        while (selected!=0){
            try {
                this.crearMenu(new String[]{"1-Agregar reservacion","2-Cancelar reservacion","3-Modificar reservacion","0-Volver"});
                selected = scanner.nextInt();
                switch (selected){
                    case 1:
                        ListaReservacion.getInstance().performAction("insert",null);
                    break;
                    case 2:
                        ListaReservacion.getInstance().performAction("delete",null);
                    break;
                    case 3:
                        ListaReservacion.getInstance().performAction("update",null);
                    break;
                }

            }
            catch (Exception e){
                System.out.println(e.toString());
            }
        }
    }
    
    public void menuPaquete(int origen){
        Scanner scanner = new Scanner(System.in);

        //Si no existe ningun paquete
        if(ListaPaquetes.getInstance().getPaquetes().size() == 0) {

            //Creando los 3 paquete
            int canti[] = ListaPaquetes.pedirServicios();
            Double[] preciosPaquetes = Precios.pedirPrecioPaquetes();
            ListaPaquetes.getInstance().addPaquete("Premium",canti[0], preciosPaquetes[0]);
            ListaPaquetes.getInstance().addPaquete("Basico",canti[1],preciosPaquetes[1]);
            ListaPaquetes.getInstance().addPaquete("Ninguno",0,0.0);

        }
        if(origen!=0){
            int opcion = this.subMenu(new String[]{"1-Agregar servicios","2-Modificar servicios","3-Cambiar precio de paquetes","0-Salir"});
            switch (opcion){
                case 1:
                    ListaPaquetes.performAction("insert");
                    break;
                case 2:
                    ListaPaquetes.performAction("update");
                    break;

                case 3:
                    ListaPaquetes.cambiarPrecios();
                    System.out.println("Precios cambiados con exito");
                    break;
                case 0:
                    menuHotel();
                    break;
            }
        }


    }

    public void menuEmpleados(){
        int selected = 10;
        String result;
        Scanner reader = new Scanner(System.in);
        while (selected!=0){
            try{
                this.crearMenu(new String[]{
                        "1-Agregar Recepcionista",
                        "2-Modificar Recepcionista",
                        "3-Eliminar Recepcionista",
                        "4-Cambiar clave",
                        "5-Volver al menu"
                });
                selected = reader.nextInt();
                switch (selected){
                    case 1:
                        ListaUsuarios.getInstance().addUser();
                        break;
                    case 2:
                        result = ListaUsuarios.getInstance().performAction("update");
                        while (result.equals("noEncontrado") && !result.equals("0"))
                        { result = ListaUsuarios.getInstance().performAction("update"); }
                        break;
                    case 3:
                        result = ListaUsuarios.getInstance().performAction("delete");
                        while (result.equals("noEncontrado") && !result.equals("0"))
                        { result = ListaUsuarios.getInstance().performAction("delete"); }
                        break;
                    case 5:
                        this.menuPrincipal();
                        selected =0;
                        break;
                    default:
                        System.out.println("No ha ingresado una opcion valida");
                        break;
                }

            }
            catch (Exception e){
                reader.next();
                System.out.println("Valor no valido");
            }
        }


    }
    public int subMenu(String[] opciones){
        Scanner scanner = new Scanner(System.in);
        int op = 10;
        while ( op !=0){
            try {
                System.out.println("-----Seleccione una opcion------");
                for (String opcion: opciones){
                    System.out.println(opcion);
                }
                op = scanner.nextInt();
                if(op>opciones.length) System.out.println("Opcion no valida");
                else return op;

            }
            catch (Exception e){
                scanner.next();
                System.out.println("Valor no valido");
            }
        }
        return  op;

    }
    public boolean mostrarMenuConfirmacion(String pregunta){
        Scanner scanner = new Scanner(System.in);
        int op = 10;
        while (op!=0){
            System.out.println(pregunta+" (s/n)?");
            String resp = scanner.next();
            switch (resp){
                case "s":
                    op = 0;
                    return true;
                case "n":
                    op = 0;
                    return false;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
        return true;
    }
    
    
}
