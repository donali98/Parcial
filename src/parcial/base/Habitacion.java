/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial.base;

/**
 *
 * @author Toshiba
 */
public class Habitacion {
    private String codigo;
    private Double precio;
    private String tipo;
    private String estado;



    public Habitacion(String codigo, Double precio, String tipo, String estado) {
        this.codigo = codigo;
        this.precio = precio;
        this.tipo = tipo;
        this.estado = estado;

    }

    public Double getPrecio() {
        return precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEstado() { return estado; }
}
