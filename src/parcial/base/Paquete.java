package parcial.base;

import parcial.logic.ListaPaquetes;
import parcial.utils.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Paquete {

    private String nombre;
    private  ArrayList<Servicio> servicios = new ArrayList<>();

    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public  ArrayList<Servicio> getServicios() {
        return servicios;
    }

    public Paquete(String nombre, int cantiServicios) {
        this.nombre = nombre;
        for (int i=0;i<cantiServicios;i++){
            servicios.add( Servicio.pedir(i));
        }

    }

    public void addNewServicio(String descripcion){
        servicios.add(new Servicio(servicios.size()+1,descripcion));
    }

    public  void listarServicios(){
        for (Servicio servicio: this.getServicios()){
            System.out.println(servicio.getId()+": "+servicio.getDescripcion());
        }
    }

    public static void performAction(String action){
        Scanner scanner = new Scanner(System.in);
        int servSelected;
        switch (action){
            case "insert":
                servSelected = 10;

                while (servSelected!=0){
                    System.out.println("Ingrese el paquete al que desea agregarle un servicio: \n");
                    System.out.println("1-Premium");
                    System.out.println("2-Basico");
                    System.out.println("0- Volver");
                    servSelected = scanner.nextInt();
                    switch (servSelected){
                        case 1:
                            ListaPaquetes.getInstance().addServicioPaquete(ListaPaquetes.getInstance().getPaquetes().get(0),Servicio.pedir());
                            servSelected = 0;
                            break;
                        case 2:
                            ListaPaquetes.getInstance().addServicioPaquete(ListaPaquetes.getInstance().getPaquetes().get(1),Servicio.pedir());
                            servSelected = 0;
                            break;
                        case 0:
                            Menu.getInstance().menuPaquete(1);
                            break;

                        default:
                            System.out.println("No ha ingresado un valor valido");
                            break;
                    }

                }

                break;

            case "update":
                servSelected = 10;

                while (servSelected!=0){
                    System.out.println("Ingrese el paquete que desea modificar: \n");
                    System.out.println("1-Premium");
                    System.out.println("2-Basico");
                    System.out.println("0- Volver");
                    servSelected = scanner.nextInt();
                    switch (servSelected){
                        case 1:
                            ListaPaquetes.getInstance().updatePaquete("Premium");
                            servSelected = 0;
                            break;
                        case 2:
                            ListaPaquetes.getInstance().updatePaquete("Basico");
                            servSelected = 0;
                            break;
                        case 0:
                            Menu.getInstance().menuPaquete(1);
                            break;

                        default:
                            System.out.println("No ha ingresado un valor valido");
                            break;
                    }

                }
                break;
        }
    }
}
