/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parcial.logic;

import parcial.base.Paquete;
import parcial.base.Servicio;
import parcial.utils.Globals;
import parcial.utils.Menu;
import parcial.utils.Precios;

import java.util.ArrayList;
import java.util.Scanner;

public class ListaPaquetes {    
    private ListaPaquetes(){}
    private static ListaPaquetes listaPaquetes;

    private static ArrayList<Paquete> paquetes = new ArrayList<>();
    Double precio;

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

    public  void addPaquete(String nombrePaquete, int cantiServicios, Double precio){

        paquetes.add(new Paquete(nombrePaquete,cantiServicios,precio));
    }

    
    public void addServicioPaquete(Paquete paquete,String descripcion ){
       paquete.addNewServicio(descripcion);
    }
    
    
    public  void updatePaquete(String nombrePaquete){
        Scanner scanner = new Scanner(System.in);
        for(Paquete paquete: paquetes){
            if(paquete.getNombre().equals(nombrePaquete)){
                int op = 10;
                while (op!=0){
                    Menu.getInstance().crearMenu(new String[]{"1-Cambiar precio","2-Modificar servicios","0-Volver"});
                    op = scanner.nextInt();
                    switch (op){
                        case 1:
                            Double nuevo = Globals.pedirDouble("el nuevo precio");
                            paquete.setPrecio(nuevo);
                            break;
                        case 2:
                            paquete.listarServicios();
                            this.buscarServicio("update",paquete);

                            break;
                        case 0:
                            Menu.getInstance().menuPaquete(1);
                            break;
                    }
                }
            }
        }
    }

    private static void buscarServicio(String accion,Paquete paquete){
        boolean encontrado = false;
        while (!encontrado){
            int id = Globals.pedirInt("el id del servicio que desea modificar(0 para cancelar)");
            if(id == 0) break;
            for (Servicio servicio: paquete.getServicios()){
                if (servicio.getId() == id){
                    encontrado=true;
                    switch (accion){
                        case "update":
                            servicio.setDescripcion(Servicio.pedir());
                            System.out.println("Servicio modificado con exito");
                        break;
                        case "delete":
                            paquete.getServicios().remove(servicio);
                            System.out.println("Servicio eliminado con exito");
                        break;
                    }
                }
            }
            if (!encontrado) System.out.println("Servicio no encontrado, verificar el id ingresado");
        }
    }
    public static void performAction(String action){
        Scanner scanner = new Scanner(System.in);

        int opcion;
        switch (action){
            case "insert":
                System.out.println("Selecione el paquete: ");
                opcion = Menu.getInstance().subMenu(new String []{"1-Premium","2-Basico","0-Volver"});
                switch (opcion){
                    case 1:
                        ListaPaquetes.getInstance().addServicioPaquete(ListaPaquetes.getInstance().getPaquetes().get(0),Servicio.pedir());
                        break;
                    case 2:
                        ListaPaquetes.getInstance().addServicioPaquete(ListaPaquetes.getInstance().getPaquetes().get(1),Servicio.pedir());
                        break;
                    case 0:
                        Menu.getInstance().menuHotel();
                        break;
                }

                break;

            case "update":
                System.out.println("Selecione el paquete: ");
                opcion = Menu.getInstance().subMenu(new String []{"1-Premium","2-Basico","0-Volver"});

                switch (opcion){

                    case 1:
                        ListaPaquetes.getInstance().updatePaquete("Premium");

                        break;
                    case 2:
                        ListaPaquetes.getInstance().updatePaquete("Basico");

                        break;

                    case 0:
                        Menu.getInstance().menuHotel();
                        break;
                }
            break;
            case "delete":
                System.out.println("Selecione el paquete: ");
                opcion = Menu.getInstance().subMenu(new String []{"1-Premium","2-Basico","0-Volver"});
                switch (opcion){
                    case 1:
                        buscarServicio("delete",paquetes.get(0));
                        Menu.getInstance().menuHotel();
                        break;
                    case 2:
                        buscarServicio("delete",paquetes.get(1));
                        Menu.getInstance().menuHotel();
                        break;
                    case 0:
                        Menu.getInstance().menuHotel();
                        break;
                }
            break;
        }
    }
    public static int[] pedirServicios(){
        int [] cantidad = new int[2];
        cantidad[0] = Globals.pedirInt("la cantidad de servicios para el paquete Premium");
        cantidad[1] = Globals.pedirInt("la cantidad de servicios para el paquete Basico");

        return cantidad;
    }
    public static void cambiarPrecios(){
        Double [] precios = Precios.pedirPrecioPaquetes();
        paquetes.get(0).setPrecio(precios[0]);
        paquetes.get(1).setPrecio(precios[1]);
    }
}
