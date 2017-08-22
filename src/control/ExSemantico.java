package control;
import java.util.ArrayList;
import model.LexemaVO;
import gui.TablaVariables;
import javax.swing.JFrame;

/**
 *
 * @author GarciHard
 */
public class ExSemantico extends ExArbol {
    
    private final ArrayList<LexemaVO> lexemas;
    private final ExArbol arb;
    private String errores;
    private final TablaVariables intVar;
    private final ArrayList<String> lineas = new ArrayList();
    
    public ExSemantico(ArrayList<LexemaVO> lexemas) {
        intVar = new TablaVariables();
        this.lexemas = lexemas;
        arb = new ExArbol();
        errores = "";
    }
    
    public String MensajeSemantico() {
        llenarArbol(arb, 0);
        checarUso(arb);
        checarDuplicados(arb);
        checarVariablesNoDefinidas(arb);
        intVar.setVisible(true);
        return errores;
    }
    
    public ArrayList<String> getLineasdeError(){
        return lineas;
    }
    
    private void checarVariablesNoDefinidas(ExArbol arb) {
        ArrayList<ExVariable> vars = arb.getVariables();
        for (int i = 0; i < vars.size(); i++) {
            if (!vars.get(i).esDeclaracion()) {
                boolean seEncuentra = false;
                for (int j = 0; j < i; j++) {
                    seEncuentra = (
                            !vars.get(i).esDeclaracion() & vars.get(j).esDeclaracion() & 
                            vars.get(i).getNombreVariable().compareTo(vars.get(j).getNombreVariable()) == 0
                    );
                    if (seEncuentra) {
                        vars.get(i).getLexemaObj().setLexemaTipo(vars.get(j).getTipoVariable());
                        break;
                    }
                }
                if (!seEncuentra) checarNivelSuperior(vars.get(i), arb.getPadre());
            }
        }
        for (ExArbol a: arb.getHijos()) checarVariablesNoDefinidas(a);
    }
    
    private void checarNivelSuperior(ExVariable var, ExArbol padre) {
        if (padre == null) {
            errores += "Variable | " + var.getNombreVariable() + " | nunca declarada , Linea: " + var.getLinea() + "\n";
            lineas.add(var.getLinea());
            return;
        }
        for (ExVariable v: padre.getVariables()) {
            boolean aux = var.getNombreVariable().compareTo(v.getNombreVariable()) == 0 & v.esDeclaracion();
            if (aux) {
                var.getLexemaObj().setLexemaTipo(v.getTipoVariable());
                return;
            }
        }
        checarNivelSuperior(var, padre.getPadre());
    }
    
    private void checarDuplicados(ExArbol arb) {
        ArrayList<ExVariable> vars = arb.getVariables();
        for (int i = 0; i < vars.size(); i++) {
            for (int j = i + 1; j < vars.size(); j++) {
                boolean seEncuentra = (
                        vars.get(i).esDeclaracion() &
                        vars.get(j).esDeclaracion() &
                        vars.get(i).getNombreVariable().compareTo(vars.get(j).getNombreVariable()) == 0
                );
                if (seEncuentra) {
                    errores += "Variable | " +
                            vars.get(i).getNombreVariable() +
                            " | (Linea: " +
                            vars.get(i).getLinea() +
                            ") repetida en la linea: " +
                            vars.get(j).getLinea() + "\n";
                    lineas.add(vars.get(j).getLinea());
                    break;
                }
            }
        }
        for (ExArbol a: arb.getHijos()) checarDuplicados(a);
    }
    
    private boolean contiene(String id) {
        for (String[] t: ExArchivo.ExtraerTiposDeDatos()) if (t[1].compareTo(id) == 0) return true;
        return false;
    }
    
    private void llenarArbol(ExArbol arb, int indice) {
        for (int i = indice; i < lexemas.size(); i ++) {
            //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>ENTRANDO A ESTE SWITCH VA A EVALUAR: "+lexemas.get(i).getLexema());
            switch (lexemas.get(i).getLexema()) {
                case "met":
                    String nombre = lexemas.get(i + 1).getLexema();
                    //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>IMPRIME PARA NOMBRE: "+lexemas.get(i + 1).getLexema());
                    String tipo = lexemas.get(i).getToken();
                    //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>IMPRIME PARA TIPO: "+lexemas.get(i + 1).getToken());
                    String linea = lexemas.get(i + 1).getLinea();
                    //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>IMPRIME PARA LINEA: "+lexemas.get(i + 1).getLinea());
                    ExVariable var = new ExVariable(nombre, true, ExArchivo.tokenToLexema(tipo), linea, arb.getNivel(), null);
                    var.setLexemaObj(lexemas.get(i + 1));
                    arb.insertarVariables(var);
                    lexemas.get(i + 1).setLexemaTipo(ExArchivo.tokenToLexema(tipo));
                    ExArbol arbHijoi = new ExArbol();
                    arb.insertartHijo(arbHijoi);
                    llenarArbol(arbHijoi, i + 2);
                    return;
                case "para":
                case "mientras":
                //case "si":
                case "sino":
                    ExArbol arbHijo = new ExArbol();
                    arb.insertartHijo(arbHijo);
                    llenarArbol(arbHijo, i + 1);
                    return;
                case "\125":
                    llenarArbol(arb.getPadre(), i + 1);
                    return;
                case "hacerM":
                    int j = 0;
                    do {
                        j ++;
                        if (lexemas.get(i + j).getLexema().compareTo("}") == 0) {
                            ExArbol arbHijof = new ExArbol();
                            arb.insertartHijo(arbHijof);
                            llenarArbol(arbHijof, i + 1);
                            return;
                        }
                    } while (lexemas.get(i + j).getLexema().compareTo("=") != 0);
            }
            //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>IMPRIME PARA ESTE IF: "+lexemas.get(i).getNombre());
            if (lexemas.get(i).getNombre().compareTo("Variable") == 0) {//TOKEN
                String nombre = lexemas.get(i).getLexema();
                String tipo = lexemas.get(i - 1).getToken();
                String linea = lexemas.get(i).getLinea();
                String valor = "";
                if (lexemas.get(i + 1).getLexema().compareTo("=") == 0) {
                    int j = i + 2;
                    while (lexemas.get(j).getLexema().compareTo(";") != 0) {
                        valor += lexemas.get(j).getLexema();
                        j ++;
                    }
                }
                if (contiene(tipo)) {
                    //ExVariable var = new ExVariable(ExArchivo.tokenToLexema(tipo), true, nombre, linea, arb.getNivel(), null);
                    ExVariable var = new ExVariable(
                        nombre,
                        true,
                        ExArchivo.tokenToLexema(tipo),
                        linea,
                        arb.getNivel(),
                        valor
                    );
                    var.setLexemaObj(lexemas.get(i));
                    arb.insertarVariables(var);
                    lexemas.get(i).setLexemaTipo(ExArchivo.tokenToLexema(tipo));
                }
                else {
                    ExVariable var = new ExVariable(
                        nombre,
                        false,
                        null,
                        linea,
                        arb.getNivel(),
                        valor
                    );
                    var.setLexemaObj(lexemas.get(i));
                    arb.insertarVariables(var);
                }
            }
            if (lexemas.get(i).getNombre().compareTo("Numero") == 0 |
                    lexemas.get(i).getNombre().compareTo("Doble") == 0 |
                    lexemas.get(i).getNombre().compareTo("Cadena") == 0)
                lexemas.get(i).setLexemaTipo(lexemas.get(i).getToken());
        }
    }
    
    private void checarUso(ExArbol arb) {
        ArrayList<ExVariable> vars = arb.getVariables();
        for (int i = 0; i < vars.size(); i++) {
            if (vars.get(i).esDeclaracion()) {
                boolean seEncuentra = false;
                for (int j = i + 1; j < vars.size(); j++) {
                    seEncuentra = (
                            !vars.get(j).esDeclaracion() &
                            vars.get(i).getNombreVariable().compareTo(vars.get(j).getNombreVariable()) == 0
                    );
                    if (seEncuentra) break;
                }
                if (!seEncuentra) {
                    for (ExArbol a: arb.getHijos()) {
                        seEncuentra = checarUsoSiguienteNivel(a, vars.get(i).getNombreVariable());
                        if (seEncuentra) break;
                    }
                    if (!seEncuentra) {
                        vars.get(i).setUsada(false);
                        errores += "Variable | " +
                                vars.get(i).getNombreVariable() +
                                " | no usada. Linea: " +
                                vars.get(i).getLinea() + "\n";
                        lineas.add(vars.get(i).getLinea());
                    }
                }
                intVar.insertarRegistro(vars.get(i));
            }
        }
        for (ExArbol a: arb.getHijos()) checarUso(a);
    }

    private boolean checarUsoSiguienteNivel(ExArbol arbol, String nombre) {
        for (ExVariable v: arbol.getVariables()) {
            boolean nombresIguales = nombre.compareTo(v.getNombreVariable()) == 0;
            boolean seEncuentra = nombresIguales & !v.esDeclaracion();
            boolean esGlobalDeNivel = nombresIguales & v.esDeclaracion();
            if (esGlobalDeNivel) return false;
            if (!seEncuentra)
                for (ExArbol a: arbol.getHijos()) return checarUsoSiguienteNivel(a, nombre);
            if (seEncuentra) return true;
        }
        return false;
    }
    
}