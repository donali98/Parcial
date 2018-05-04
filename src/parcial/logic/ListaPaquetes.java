/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parcial.logic;

import parcial.base.Paquete;
import parcial.base.Servicio;
import parcial.utils.Menu;
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
        Scanner scanner = new Scanner(System.in);
        for(Paquete paquete: paquetes){
            if(paquete.getNombre().contains(nombrePaquete)){
                int op = 10;
                while (op!=0){
                    Menu.getInstance().crearMenu(new String[]{"1-Cambiar nombre","2-Modificar servicios","0-Volver"});
                    op = scanner.nextInt();
                    switch (op){
                        case 1:
                            System.out.println("Ingrese el nuevo nombre: ");
                            paquete.setNombre(scanner.next());
                            break;
                        case 2:
                            boolean encontrado = false;
                            paquete.listarServicios();
                            System.out.println("Seleccione el nombre del servicio que desea modificar (0 para cancelar)");
                            int pa = scanner.nextInt();
                            while (true){
                                for (Servicio servicio: paquete.getServicios()){
                                    if(servicio.getId()== pa){
                                        encontrado = true;
                                        servicio.setDescripcion(Servicio.pedir());
                                        System.out.println("Servicio modificado con exito");
                                        break;
                                    }
                                }
                                break;
                            }
                            if(!encontrado) System.out.println("Servicio no encontrado");
                            break;
                        case 0:
                            Menu.getInstance().menuPaquete(1);
                            break;
                    }
                }
            }
        }
    }
}
