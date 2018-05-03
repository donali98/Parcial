/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial.base;

import parcial.utils.Dui;
import parcial.utils.Nombre;

public class Persona {
    
    private Nombre nombres;
    private Dui dui;

    public Nombre getNombres() {
        return nombres;
    }

    public void setNombres(Nombre nombres) {
        this.nombres = nombres;
    }

    public Dui getDui() {
        return dui;
    }

    public void setDui(Dui dui) {
        this.dui = dui;
    }
    
    public Persona(String nombre, String apellido, String dui) {
        this.nombres = new Nombre(nombre,apellido);
        this.dui = new Dui(Dui.parseDui(dui));
    }

}
