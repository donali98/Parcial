/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parcial.base;

import java.util.Scanner;

public class Servicio {
    private int id;
    private  String descripcion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Servicio(int id,String descrp) {
        this.id  = id ;
        this.descripcion = descrp ;
    }
    
    public static Servicio pedir(String nombre,int i){
        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese la descripcion del servicio "+(i+1)+" para el paquete "+nombre+": ");
            String des = scanner.nextLine();
            if(des.length()< 5){
                System.out.println("Descripcion demasiado corta");
            }
            else{
                return new Servicio((i+1),des);
            }
        }
    }
    
    public static String pedir(){
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("Ingrese la descripcion del servicio: ");
            String de = scanner.nextLine();
            if(de.length() < 5){
                System.out.println("Descripcion demasiado corta");
            }
            else return de;
        }
    }
}
