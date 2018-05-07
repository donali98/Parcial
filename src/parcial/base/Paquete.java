package parcial.base;

import parcial.logic.ListaPaquetes;
import parcial.utils.Globals;
import parcial.utils.Menu;

import java.util.ArrayList;

public class Paquete {

    private String nombre;
    private  ArrayList<Servicio> servicios = new ArrayList<>();
    private Double precio;

    public String getNombre() {
        return nombre;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public  ArrayList<Servicio> getServicios() {
        return servicios;
    }

    public Paquete(String nombre, int cantiServicios, Double precio) {
        if(cantiServicios!=0){
            this.nombre = nombre;
            for (int i=0;i<cantiServicios;i++){
                servicios.add( Servicio.pedir(nombre,i));
            }
            this.precio = precio;
        }


    }
    public  void listarServicios(){
        for (Servicio servicio: this.getServicios()){
            System.out.println(servicio.getId()+": "+servicio.getDescripcion());
        }
    }
    public void addNewServicio(String descripcion){
        servicios.add(new Servicio(servicios.size()+1,descripcion));
    }


}
