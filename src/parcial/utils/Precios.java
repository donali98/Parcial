/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial.utils;

import java.util.Scanner;

/**
 *
 * @author Toshiba
 */
public class Precios {
    
    private static Double precioSencilla;
    private static Double precioDoble;

    private Precios(){}

    public static Double getPrecioSencilla() {
        return precioSencilla;
    }

    public static Double getPrecioDoble() {
        return precioDoble;
    }
    
    
    public static void pedirPrecios(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            try{
                System.out.println("Ingrese el precio de la habitacion sencilla: ");
                precioSencilla = scanner.nextDouble();
                break;

            }
            catch (Exception e){
                scanner.next();
                System.out.println("Valor ingresado no valido\n");
            }
        }
        while (true){
            try{
                System.out.println("Ingrese el precio de la habitacion doble: ");
                precioDoble = scanner.nextDouble();
                if(precioSencilla>=precioDoble){
                    System.out.println("Atencion, el precio de la habitacion sencilla es igual o superior a la doble, desea continuar?(s/n)");
                    if(scanner.next().contains("s")) break;
                }
                else break;

            }
            catch (Exception e){
                scanner.next();
                System.out.println("Valor ingresado no valido\n");
            }
        }
    }    
}
