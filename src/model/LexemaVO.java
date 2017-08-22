package model;

/**
 * Hecho con <3 Por:
 * @author GarciHard
 */

public class LexemaVO {
    private String lexema = "";//VARIABLE
    private String nombre = "";//TIPO
    private String token = "";//NUMERO
    private String linea = "";//LINEA
    private String lexemaTipo = "";
    
    public LexemaVO() {};
    
    public String getLexema() {return lexema;}
    public String getNombre() {return nombre;}
    public String getToken() {return token;}
    public String getLinea() {return linea;}
    public String getLexemaTipo() {return lexemaTipo;}
    
    public void setLexema(String lexema) {setLexemaTipo(lexema);this.lexema = lexema;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setToken(String token) {this.token = token;}
    public void setLinea(String linea) {this.linea = linea;}
    public void setLexemaTipo(String lexemaTipo) {this.lexemaTipo = lexemaTipo;}
}