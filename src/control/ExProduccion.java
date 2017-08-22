package control;

import java.util.ArrayList;

/**
 * Hecho con <3 Por:
 * @author GarciHard
 */

public class ExProduccion { 
    
    private String gramatica = "";
    private final ArrayList<String> listas = new ArrayList();
    
    public ExProduccion(String gramatica) {
        this.gramatica = gramatica;
        CrearLista();
    }

    public ArrayList<String> getListas() {
        return listas;
    }
    
    private void CrearLista() {
        String s = "";
        for (int i = 0; i < gramatica.length(); i++) {
            if (gramatica.charAt(i) == '>' | gramatica.charAt(i) == ']') {
                i++;
                listas.add(s);
                s = "";
            }
            if (i == gramatica.length()) break;
            if (gramatica.charAt(i) == '<' | gramatica.charAt(i) == '[') i++;
            s = s + gramatica.charAt(i);
        }
    }
}
