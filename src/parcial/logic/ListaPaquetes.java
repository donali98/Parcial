package parcial.logic;

import parcial.base.Paquete;
import parcial.base.Servicio;
import parcial.utils.Menu;
import java.util.ArrayList;
import java.util.Scanner;

public class ListaPaquetes {

    private ListaPaquetes(){}
    private static ListaPaquetes listaPaquetes;
    private static ArrayList<Paquete> paquetes = new ArrayList<>();

    public static ListaPaquetes getInstance(){
        if(listaPaquetes == null){
            listaPaquetes = new ListaPaquetes();
        }
        return listaPaquetes;
    }


}
