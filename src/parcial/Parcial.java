/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial;

import parcial.logic.ListaUsuarios;
import parcial.logic.Login;

/**
 *
 * @author dona
 */
public class Parcial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("------Ingrese los datos del administrador------");
        String id = ListaUsuarios.getInstance().addUser();
        System.out.println("Su usuario es "+id+"\n");
        System.out.println("------Bienvenido al Hotel------\n");
        Login.loguear();
    }
    
}
