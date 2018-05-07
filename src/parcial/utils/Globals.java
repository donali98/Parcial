package parcial.utils;

import java.util.Scanner;

public class Globals {

    public static final String USUARIO_ADMIN = "admin";
    public static final String USUARIO_RECEP = "recep";
    public static Double pedirDouble(String campo){
        Double doble;
        Scanner scanner = new Scanner(System.in);
        while (true){
            try {

                System.out.println("Ingrese "+campo);
                doble = scanner.nextDouble();
                if(doble<0.0) System.out.println("Valor no valido");
                else return doble;
            }
            catch (Exception e){
                scanner.next();
                System.out.println("Valor no valido");
            }
        }
    }
    public static int pedirInt(String campo){
        int ente;
        Scanner scanner = new Scanner(System.in);
        while (true){
            try {

                System.out.println("Ingrese "+campo);
                ente = scanner.nextInt();
                if(ente<0.0) System.out.println("Valor no valido");
                else return ente;
            }
            catch (Exception e){
                scanner.next();
                System.out.println("Valor no valido");
            }
        }
    }
}
