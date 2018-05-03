/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial.utils;

/**
 *
 * @author Toshiba
 */
public class Dui {
    private  int[] digitos;

    public Dui(int[] dui) {
        this.digitos = dui;
    }


    public static boolean validarDui(String cadena){
        if(cadena.length()< 9 || cadena.length()>9) return false;
        int [] digitos;
        digitos = Dui.parseDui(cadena);
        if(digitos.length == 0) return false;

        int suma =0, contador = 0, posicion = 9, verificador = digitos[digitos.length -1] ;

        while(posicion!=1){
            suma+= digitos[contador] * posicion;
            contador ++;
            posicion --;
        }

        return (10 - (suma % 10)) == verificador;

    }

    public static int[] parseDui(String stringDui){
        try {
            int[] result = new int[9];
            for (int i = 0; i<stringDui.length();i++){

                result[i] = Integer.parseInt(stringDui.substring(i,i+1));
            }
            return result;
        }
        catch (Exception e){
            System.out.println(e.getCause());
            return new int[]{};
        }
    }
}
