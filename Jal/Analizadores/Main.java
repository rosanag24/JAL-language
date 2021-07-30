package Analizadores;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;

import code.Tokens;
import java_cup.runtime.Symbol;
public class Main {
  //Reset
  public static final String ANSI_RESET = "\u001B[0m";
  //Colores de letra
  public static final String ANSI_BLACK = "\u001B[30m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";
  public static final String ANSI_WHITE = "\u001B[37m";

  static JalScanner jlx;
    public static void main(String[] args) throws IOException  {
        parser parser_obj;
        Path p = Path.of(args[0]);
        String c ="";
        if(Files.exists(p)){
            c  = Files.readString(p);
            jlx = new JalScanner(new StringReader(c));
            parser_obj = new parser(jlx);
        /* set the default scanner */
            try{
                analizarLexico(c);
                parser_obj.parse();
                System.out.println(ANSI_GREEN+"Success compiling!"+ANSI_RESET);
            /*    prog theprogram = null;
                try {
                    theprogram = parser_obj.parse();
                    theprogram.run();
                } catch(Exception pe)
                    {
                        System.out.println(pe);
                        System.exit(1);
                    }// error reporting
                    */
            }catch(Exception e){ 
                    Symbol sym = parser_obj.getS();
                    System.out.println(ANSI_RED +"Sintax Error: Line "+(sym.right+1)+" column "+(sym.left+1)+" \""+(sym.value)+"\""+ANSI_RESET);
            }
        }        
        else{
            System.out.println("Input Error: please, provide a valid .m file");
        }
    }
    /**
     * Método que interpreta el contenido del archivo que se encuentra en el path
     * que recibe como parámentro
     * @param path ruta del archivo a interpretar
     */  


    private static void analizarLexico(String s) throws IOException{
        int cont = 1;
        
        Lexer lexer = new Lexer(new StringReader(s));
        String resultado = "";
        while (true) {
            Tokens token = lexer.yylex();
            if (token == null) {
                System.out.print(resultado);
                return;
            }
            switch (token) {
                case InvalidToken:
                    resultado += "Error: Invalid Token "+"<"+lexer.lexeme +"> at line "+cont+"\n";
                    break;
                default:
                    break;
            }
        }
    }
}
