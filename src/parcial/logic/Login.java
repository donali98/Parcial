package parcial.logic;

import parcial.base.Usuario;
import parcial.utils.Globals;
import parcial.utils.Menu;

import java.util.Scanner;

public class Login {

    private static Login login;
    
    private Login(){}
    
    public static Login getInstance(){
        if(login == null) {
            login = new Login();
            return login;
        }
        else return login;
    }
    
    private static Usuario autenticar(String user, String pass){

        for (Usuario usuario: ListaUsuarios.getLista()){
            if (usuario.getUser().equals(user) && usuario.getPass().equals(pass)){
                return usuario;
            }
        }
        return null;
    }
    
    public static void loguear(){
        String user, pass;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su usuario:");
        user = scanner.next();
        System.out.println("Ingrese su clave:");
        pass = scanner.next();
        Usuario usuario = autenticar(user,pass);
        if(usuario!=null){
            switch (usuario.getTipo()){
                case (Globals.USUARIO_ADMIN):
                    Menu.getInstance().menuPrincipal();
                    break;
            }
        }

    }
    
    
    
}
