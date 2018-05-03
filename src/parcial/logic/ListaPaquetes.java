/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parcial.logic;

import parcial.base.Paquete;
import parcial.base.Servicio;
import parcial.utils.menu;
import java.util.ArrayList;
import java.util.Scanner;

public class ListaPaquetes {
    
    private ListaPaquetes(){}
    private static ListaPaquetes listaPaquetes;
    private static ArrayList<Paquete> paquetes = new ArrayList<>();
    
    //metodo get instance de la clase 
    public static ListaPaquetes getInstance(){
        if(listaPaquetes == null){
            listaPaquetes = new ListaPaquetes();
        }
        return listaPaquetes;
    }
    
    public  ArrayList<Paquete> getPaquetes(){
        return paquetes;
    }

    public  void addPaquete(String nombrePaquete, int cantiServicios){
        paquetes.add(new Paquete(nombrePaquete,cantiServicios));
    }
    
    public void addServicioPaquete(Paquete paquete,String descripcion ){
       paquete.addNewServicio(descripcion);
    }
    
     public  void updatePaquete(String nombrePaquete){
         
     }

}
