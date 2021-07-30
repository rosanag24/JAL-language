/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;
import Analizadores.*;
import java.util.ArrayList;;
/**
 *
 * @author rosana
 */
public class Entorno {
   // private ArrayList<Row> tabla;
/*    
    public Entorno(String variable, String valor, String tipo, String estado) {
        this.tabla = new ArrayList<Row>();
        this.tabla.add(new Row(variable, valor, tipo, estado));
    }
    
    //Agrega una nueva variable al entorno
    public void agregar( String variable,String valor,String tipo,String estado) {
        this.tabla.add(new Row(variable, valor,tipo, estado));
    }
    
    //Cambia el valor de la variable vari en el entorno
    public void cambiarValor(String vari,String valor) {
        for (Row estado : this.tabla) {
            if(estado.getVariable()==vari){
                estado.setValor(valor);
                break;
            }
        }   
    }
    
    //Cambia el valor de la variable vari en el entorno
    public void cambiarEstado(String nuevoEstado,String vari) {
        for (Row estado : this.tabla) {
            if(estado.getVariable()==vari){
                estado.setValor(nuevoEstado);
                break;
            }
        }   
    }
    
    @Override
    public String toString(){
        String s = "";
        for (Row estado : tabla) {
            s = s.concat(estado.getVariable()+" ");
            s =s.concat(estado.getValor()+" ");
            s =s.concat(estado.getTipo()+" ");
            s =s.concat(estado.getEstado()+"\n");
        }    
        return s;   
    }
*/
}
