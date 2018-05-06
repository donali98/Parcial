/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial.base;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.LocalDate;

/**
 *
 * @author Toshiba
 */
public class Reservacion {
    private Huesped huesped;
    private Habitacion habitacion;
    private Paquete paquete;
    private LocalDate fechaInicio;
    private  LocalDate fechaFin;    
    private Double totalPagar;

    public Huesped getHuesped() {
        return huesped;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public Double getTotalPagar() {
        return totalPagar;
    }

    public Reservacion(String nombre, String apellido, String dui, Habitacion habitacion, Paquete paquete, LocalDate fechaInicio, LocalDate fechaFin, Double total) {
        this.habitacion = habitacion;
        this.paquete = paquete;
        this.huesped = new Huesped(nombre,apellido,dui);
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.totalPagar = total;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public Reservacion(String nombre, String apellido, String dui, Habitacion habitacion, LocalDate fechaInicio, LocalDate fechaFin, Double total) {
        this.habitacion = habitacion;
        this.huesped = new Huesped(nombre,apellido,dui);
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.totalPagar = total;

    }
}
