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

    public static Double[] pedirPrecioPaquetes(){
        Double [] preciosPaquetes = new Double[2];
        Scanner scanner = new Scanner(System.in);
        preciosPaquetes[0] = Globals.pedirDouble("el precio del paquete Premium");
        preciosPaquetes[1] = Globals.pedirDouble("el precio del paquete Basico");
        while (preciosPaquetes[0]>=preciosPaquetes[1]){
            if(!Menu.getInstance().mostrarMenuConfirmacion("Advertencia, el precio del paquete Premium es superior o igual al del Basico, desea continuar?")){
                preciosPaquetes[1] = Globals.pedirDouble("el precio de la habitacion doble");
            }
            else break;
        }
        return preciosPaquetes;
    }

    public static void pedirPreciosHabitacion(){

        precioSencilla = Globals.pedirDouble("el precio de la habitacion sencilla");
        precioDoble = Globals.pedirDouble("el precio de la habitacion doble");

        while (precioSencilla>=precioDoble){
            if(!Menu.getInstance().mostrarMenuConfirmacion("Advertencia, el precio de la habitacion sencilla es superior o igual al de la doble, desea continuar?")){
                    precioDoble = Globals.pedirDouble("el precio de la habitacion doble");
            }
            else break;
        }
    }
}
