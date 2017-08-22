package control;

import java.util.ArrayList;

/**
 *
 * @author GarciHard
 */
public class ExArbol {
  
    private ExArbol padre;
    private final ArrayList<ExArbol> hijos;
    private final ArrayList<ExVariable> variables;
    private int nivel;
    
    public ExArbol() {
        variables = new ArrayList();
        padre = null;
        hijos = new ArrayList();
        nivel = 0;
    }
    
    private void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    public int getNivel() {
        return nivel;
    }

    public void insertartHijo(ExArbol hijo) {
        hijo.setPadre(this);
        int aux = nivel + 1;
        hijo.setNivel(aux);
        hijos.add(hijo);
    }
    
    private void setPadre(ExArbol padre) {
        this.padre = padre;
    }

    public void insertarVariables(ExVariable var) {
        variables.add(var);
    }
    
    public ArrayList<ExArbol> getHijos() {
        return hijos;
    }
    
    public ExArbol getPadre() {
        return padre;
    }
    
    public ArrayList<ExVariable> getVariables() {
        return variables;
    } 
}