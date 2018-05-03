/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parcial.utils;

import java.util.Scanner;

public class Nombre {
    
     private  String nombres, apellidos;

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
     
    
    public Nombre(String nombres, String apellidos){
        this.nombres = nombres;
        this.apellidos = apellidos;
    }
    
    public static boolean validarNombre(String nombres, String apellidos){
        return true;
    }
}
