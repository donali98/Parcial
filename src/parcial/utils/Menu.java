/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial.utils;

import java.util.Scanner;
import parcialpoo.base.Paquete;
import parcialpoo.logic.ListaHabitacion;
import parcialpoo.logic.ListaPaquetes;
import parcialpoo.logic.ListaReservacion;
import parcialpoo.logic.ListaUsuarios;

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
    //Metodo del menu principal
    public void menuPrincipal(){
        int selected = 10;
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

        }
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
                        menuPaquete();
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
        menuPaquete();
        int selected = 10;
        Scanner scanner = new Scanner(System.in);
        while (selected!=0){
            try {
                this.crearMenu(new String[]{"1-Agregar reservacion"});
                selected = scanner.nextInt();
                switch (selected){
                    case 1:
                        ListaReservacion.getInstance().addReservacion();
                        selected = 0;
                        break;
                }

            }
            catch (Exception e){

            }
        }
    }
    
    public void menuPaquete(){
        Scanner scanner = new Scanner(System.in);

        //Si no existe ningun paquete
        if(ListaPaquetes.getInstance().getPaquetes().size() == 0){


                while (true){
                    try{
                        //Creando los 3 paquete
                        System.out.println("Ingrese la cantidad de servicios para el paquete Premium: ");
                        int canti = scanner.nextInt();
                        ListaPaquetes.getInstance().addPaquete("Premium",canti);
                        System.out.println("Ingrese la cantidad de servicios para el paquete Basico: ");
                        canti = scanner.nextInt();
                        ListaPaquetes.getInstance().addPaquete("Basico",canti);
                        int packSelected = 10;
                        while (packSelected!=0){
                            this.crearMenu(new String[]{"1-Agregar servicios","2-Modificar servicios","0-Salir"});
                            packSelected = scanner.nextInt();
                            switch (packSelected){
                                case 1:
                                    Paquete.performAction("insert");
                                    break;
                                case 2:
                                    Paquete.performAction("update");
                                    packSelected = 0;
                                    break;
                                case 0:
                                    Menu.getInstance().menuPrincipal();
                                    break;
                                    default:
                                        System.out.println("Valor no valido");
                                    break;
                            }
                        }
                        break;
                    }
                    catch (Exception e) {
                        scanner.next();
                        System.out.println("Valor no valido");
                    }

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
    
    
}
