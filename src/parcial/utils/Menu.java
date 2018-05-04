/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial.utils;

import java.util.Scanner;

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
    
    
}
