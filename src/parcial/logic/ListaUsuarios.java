package parcial.logic;
import parcial.base.Usuario;
import parcial.utils.Dui;
import parcial.utils.Globals;
import parcial.utils.Menu;
import parcial.utils.Nombre;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
public class ListaUsuarios {

    private ListaUsuarios(){}
    private  static  ListaUsuarios listaUsuarios;
    private  static  ArrayList<Usuario> lista;

    public static ListaUsuarios getInstance(){
        if(listaUsuarios == null){
            listaUsuarios = new ListaUsuarios();
            lista = new ArrayList<>();
            lista = new ArrayList<>();

        }
        return listaUsuarios;

    }

    public static ArrayList<Usuario> getLista() {
        return lista;
    }


    public String addUser(){
        String nombres[];
        String dui;
        String pass;
        Scanner reader = new Scanner(System.in);

        //USUARIO LOGIN QUEMADO
        /*nombres = Nombre.pedir();
        dui = Dui.pedir();

        System.out.println("Clave para entrar al sistema: ");
        pass = reader.next();*/

        //corregir
        //Usuario user = new Usuario(nombres[0], nombres[1], dui, pass, Globals.USUARIO_ADMIN);
        Usuario user = new Usuario("d","d", "056741785", "1", Globals.USUARIO_ADMIN);

        ListaUsuarios.getLista().add(user);
        return ListaUsuarios.getLista().get(ListaUsuarios.getLista().indexOf(user)).getUser();

    }
    private void displayUsers(){
        System.out.println("Usuarios disponibles:\n");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Usuario |          Nombres             |               Apellidos                |                DUI           |");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        for(Usuario usuario:lista){
            //Los usuarios disponibles para eliminar menos el usuario administrador
            if(!usuario.getUser().equals("user0")){
                System.out.println(usuario.getUser()+"  |     "+usuario.getNombres().getNombres()+"             |        "+usuario.getNombres().getApellidos()+"          |         "+usuario.getDui().getDigitos()+"           |");
                System.out.println("-----------------------------------------------------------------------------------------------------------------------");
            }
        }
    }
    public String performAction(String action){
        String n_usuario;
        boolean existe = false;
        Iterator<Usuario > myIterator = lista.iterator();
        this.displayUsers();
        Scanner reader = new Scanner(System.in);

        switch (action){
            case "delete":
                System.out.println("\nUsuario a eliminar('0' para cancelar):");
                n_usuario = reader.next();
                if(n_usuario.equals("0")) {
                    return "0";
                }
                while (myIterator.hasNext()){

                    Usuario usuario = myIterator.next();
                    if(usuario.getUser().equals(n_usuario)) {
                        myIterator.remove();
                        existe = true;
                        break;
                    }
                }
                if(existe) {
                    System.out.println("Usuario eliminado con exito\n");
                    return "exito";
                }
                else {
                    System.out.println("Usuario no encontrado\n");
                    return "noEncontrado";
                }

            case "update":
                System.out.println("\nUsuario a actualizar('0' para cancelar):");
                n_usuario = reader.next();
                if(n_usuario.equals("0")) {
                    return "0";
                }
                while (myIterator.hasNext()){

                    Usuario usuario = myIterator.next();
                    if(usuario.getUser().equals(n_usuario)) {
                        int op = 10;
                        while (op!=0){
                            Menu.getInstance().crearMenu(new String[]{
                                    "1-Nombres",
                                    "2-DUI"
                            });
                            System.out.println("0 Para cancelar");
                            op = reader.nextInt();
                            switch (op){
                                case 1:
                                    String [] nombres = Nombre.pedir();
                                    usuario.getNombres().setNombres(nombres[0]);
                                    usuario.getNombres().setApellidos(nombres[1]);
                                    op  = 0;
                                    break;
                                case 2:
                                    String dui = Dui.pedir();
                                    usuario.setDui(new Dui(Dui.parseDui(dui)));
                                    op = 0;
                                    break;
                                case 0:
                                    Menu.getInstance().menuEmpleados();
                                    break;
                                default:
                                    System.out.println("Opcion no valida");
                                    break;
                            }
                        }
                        existe = true;
                        break;
                    }
                }
                if(existe) {
                    System.out.println("Usuario modificado con exito\n");
                    return "exito";
                }
                else {
                    System.out.println("Usuario no encontrado\n");
                    return "noEncontrado";
                }

            default:
                return "err";
        }
    }
    public String getUserId(){
        return "user"+lista.size();
    }



}