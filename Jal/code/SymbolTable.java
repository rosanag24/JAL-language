package code;

import java.util.ArrayList;
import java.util.LinkedList;
import java.io.*;

/**
 * SymbolTable
 */
public class SymbolTable {
    private ArrayList<Estados> tabla = new ArrayList<Estados>();
    public void agregar(String valor,String variable,String tipo,String estado) {
        this.tabla.add(new Estados(variable, valor, tipo, estado));
    }
    public void change(String valor,String vari) {
        for (Estados estado : this.tabla) {
            if(estado.getVariable()==vari){
                estado.setValor(valor);
                break;
            }
        }   
    }
    @Override
    public String toString(){
        String s = "";
        for (Estados estado : tabla) {
            s = s.concat(estado.getVariable()+" ");
            s =s.concat(estado.getValor()+" ");
            s =s.concat(estado.getTipo()+" ");
            s =s.concat(estado.getEstado()+"\n");
        }    
        return s;   
    }

    public static void main(String[] args) {
        SymbolTable t = new SymbolTable();
        System.out.println("si entra");
        t.agregar("{int,string,float}","persona","ebublio","usando");
        t.agregar("{string,string}","ropa","ebublio","sinuso");
        t.agregar("4","x","int","asignado");
        t.agregar("","y","int","declarado");
        t.change("x","8");
        System.out.println(t.toString());

        File miDir = new File (".");
        try {
          System.out.println ("Directorio actual: " + miDir.getCanonicalPath());
        }
        catch(Exception e) {
          e.printStackTrace();
        }
    }

}

/**
 * Clase Estados: almacena el valor, nombre de variable, tipo actual de la variable y su estado
 * (declarada, asignada, funcion_definida)
 */
class Estados {
    private String valor;
    private String variable;
    private String tipo;
    private String estado;
    /**
    * Constructor de la clase Estados: almacena el valor, nombre de variable, tipo actual de la variable y su estado
    * (declarada, asignada, funcion_definida) que posteriormente sera agregado a la tabla.
    */
    public Estados(String variable,String valor,String tipo,String estado){
        this.tipo = tipo;
        this.variable = variable;
        this.valor = valor;
        this.estado = estado;
    } 
    public String getVariable() {
        return this.variable;
    }
    public String getValor() {
        return this.valor;
    }
    public String getTipo() {
        return this.tipo;
    }
    public String getEstado() {
        return this.estado;
    }
    public String setVariable(String e) {
        return this.variable=e;
    }
    public String setValor(String e) {
        return this.valor=e;
    }
    public String setTipo(String e) {
        return this.tipo=e;
    }
    public String setEstado(String e) {
        return this.estado=e;
    }
}
