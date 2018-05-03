package parcial.base;


import parcial.logic.ListaUsuarios;
import parcial.utils.Globals;

public class Usuario extends Persona {

    private String user,pass;
    private String tipo;


    public Usuario(String nombre, String apellido, String dui, String pass, String tipo){
        super(nombre,apellido,dui);
        //No deja que el usuario ponga su propio user, si no que el sistema lo genera
        this.user = ListaUsuarios.getInstance().getUserId();
        this.pass = pass;

        //Ocupando la clase contenedora Globals para comparar valores
        if(tipo.equals( Globals.USUARIO_ADMIN) || tipo.equals(Globals.USUARIO_RECEP))
            this.tipo = tipo;

    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getUser() { return user; }

    public String getPass() {
        return pass;
    }
}
