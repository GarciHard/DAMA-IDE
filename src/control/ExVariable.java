package control;

import model.LexemaVO;

/**
 * Hecho con <3 Por:
 * @author GarciHard
 */

public class ExVariable {
    
    private final String nombreVariable;
    private final boolean declaracion;
    private final String tipoVariable;
    private final String linea;
    private final int nivel;
    private boolean usada;
    private final String valor;
    private LexemaVO lexemaObj;

    public ExVariable(String nombreVariable, boolean declaracion, String tipoVariable, String linea, int nivel, String valor) {
        this.nombreVariable = nombreVariable;
        this.declaracion = declaracion;
        this.tipoVariable = tipoVariable;
        this.linea = linea;
        this.nivel = nivel;
        usada = true;
        this.valor = valor;
    }
    
    public void setLexemaObj(LexemaVO lexemaObj) {
        this.lexemaObj = lexemaObj;
    }
    
    public LexemaVO getLexemaObj() {
        return lexemaObj;
    }

    public String getValor() {
        return valor;
    }
    
    public String getNombreVariable() {
        return nombreVariable;
    }

    public boolean esDeclaracion() {
        return declaracion;
    }
    
    public String getTipoVariable() {
        return tipoVariable;
    }

    public String getLinea() {
        return linea;
    }

    public int getNivel() {
        return nivel;
    }

    public boolean esUsada() {
        return usada;
    }

    public void setUsada(boolean usada) {
        this.usada = usada;
    }
}
