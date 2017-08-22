package control;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author GarciHard
 */
public class ControladorIntermedio {

    DefaultTableModel dtm;
    int contadorEtiquetas;
    public String codigoCompleto;
    public String codigoEtiquetas;
    public String temporales;

    public ControladorIntermedio(DefaultTableModel dtmLexemas) {
        this.dtm = dtmLexemas;
        contadorEtiquetas = 10;
        codigoCompleto = "";
        codigoEtiquetas = "";
        temporales = "";
    }

    public String leerSentencias(ArrayList<String> lista) {
        int tamaño;
        ArrayList<String> linea;
        ArrayList<String> s1;
        String codig = "";
        if (lista == null) {
            tamaño = dtm.getRowCount();
        } else {
            tamaño = lista.size();
        }
        for (int i = 0; i < tamaño; i++) {
            linea = new ArrayList();
            s1 = new ArrayList();
            String lexema;
            if (lista == null) {
                lexema = dtm.getValueAt(i, 0).toString();
            } else {
                lexema = lista.get(i);
            }

            if (lexema.equals("si")) {
                linea.add(lexema);
                int contadorLlaves = 0;
                boolean band = true;
                boolean conta = false;
                boolean se1 = false;
                do {
                    i++;
                    String lexemaSiguiente;
                    if (lista == null) {
                        lexemaSiguiente = dtm.getValueAt(i, 0).toString();
                    } else {
                        lexemaSiguiente = lista.get(i);
                    }
                    if (lexemaSiguiente.equals("{")) {
                        if (contadorLlaves > 0) {
                            se1 = true;
                            s1.add(lexemaSiguiente);
                        } else {
                            linea.add(lexemaSiguiente);
                        }
                        contadorLlaves++;
                    } else if (lexemaSiguiente.equals("}")) {
                        if (contadorLlaves > 1) {
                            s1.add(lexemaSiguiente);
                            contadorLlaves--;
                        } else {
                            contadorLlaves--;
                            linea.add(lexemaSiguiente);
                            if (contadorLlaves == 0) {
                                if (lista == null) {
                                    lexemaSiguiente = dtm.getValueAt(i, 0).toString();
                                } else {
                                    lexemaSiguiente = lista.get(i);
                                }
                                conta = true;
                                break;
                            }
                        }
                    } else if (conta == true) {
                        band = false;
                    } else if (contadorLlaves > 0) {
                        s1.add(lexemaSiguiente);
                    } else {
                        linea.add(lexemaSiguiente);
                    }
                } while (band);
                if (lista == null) {
                    codigoCompleto += obtenerIntermedio(linea, s1);
                } else {
                    codig += obtenerIntermedio(linea, s1);
                }
            } else if (lexema.equals("hacerM")) {
                linea.add(lexema);
                int contadorLlaves = 0;
                boolean band = true;
                boolean conta = false;
                boolean se1 = false;
                boolean mientra = false;
                do {
                    i++;
                    String lexemaSiguiente;
                    if (lista == null) {
                        lexemaSiguiente = dtm.getValueAt(i, 0).toString();
                    } else {
                        lexemaSiguiente = lista.get(i);
                    }
                    if (lexemaSiguiente.equals("{")) {
                        if (contadorLlaves > 0) {
                            se1 = true;
                            s1.add(lexemaSiguiente);
                        } else {
                            linea.add(lexemaSiguiente);
                        }
                        contadorLlaves++;
                    } else if (lexemaSiguiente.equals("}")) {
                        if (contadorLlaves > 1) {
                            s1.add(lexemaSiguiente);
                            contadorLlaves--;
                        } else {
                            contadorLlaves--;
                            linea.add(lexemaSiguiente);
                            if (contadorLlaves == 0) {
                                if (lista == null) {
                                    lexemaSiguiente = dtm.getValueAt(i, 0).toString();
                                } else {
                                    lexemaSiguiente = lista.get(i);
                                }
                                mientra = true;
                            }
                        }
                    } else if (mientra) {
                        if (lexemaSiguiente.equals(";")) {
                            conta = true;
                            mientra = false;
                            break;
                        }
                        linea.add(lexemaSiguiente);
                    } else if (conta && !mientra) {
                        band = false;
                    } else if (contadorLlaves > 0) {
                        s1.add(lexemaSiguiente);
                    } else {
                        linea.add(lexemaSiguiente);
                    }
                } while (band);
                if (lista == null) {
                    codigoCompleto += obtenerIntermedio(linea, s1);
                } else {
                    codig += obtenerIntermedio(linea, s1);
                    System.out.println("CODIG: \n" + codig);
                }
            } else if (lexema.equals("mientras")) {
                linea.add(lexema);
                int contadorLlaves = 0;
                boolean band = true;
                boolean conta = false;
                boolean se1 = false;
                do {
                    i++;
                    String lexemaSiguiente;
                    if (lista == null) {
                        lexemaSiguiente = dtm.getValueAt(i, 0).toString();
                    } else {
                        lexemaSiguiente = lista.get(i);
                    }
                    if (lexemaSiguiente.equals("{")) {
                        if (contadorLlaves > 0) {
                            se1 = true;
                            s1.add(lexemaSiguiente);
                        } else {
                            linea.add(lexemaSiguiente);
                        }
                        contadorLlaves++;
                    } else if (lexemaSiguiente.equals("}")) {
                        if (contadorLlaves > 1) {
                            s1.add(lexemaSiguiente);
                            contadorLlaves--;
                        } else {
                            contadorLlaves--;
                            linea.add(lexemaSiguiente);
                            if (contadorLlaves == 0) {
                                if (lista == null) {
                                    lexemaSiguiente = dtm.getValueAt(i, 0).toString();
                                } else {
                                    lexemaSiguiente = lista.get(i);
                                }
                                conta = true;
                                break;
                            }
                        }
                    } else if (conta == true) {
                        band = false;
                    } else if (contadorLlaves > 0) {
                        s1.add(lexemaSiguiente);
                    } else {
                        linea.add(lexemaSiguiente);
                    }
                } while (band);
                if (lista == null) {
                    codigoCompleto += obtenerIntermedio(linea, s1);
                } else {
                    codig += obtenerIntermedio(linea, s1);
                }
            } else if (lexema.equals("para")) {
                linea.add(lexema);
                int contadorLlaves = 0;
                boolean band = true;
                boolean conta = false;
                boolean se1 = false;
                do {
                    i++;
                    String lexemaSiguiente;
                    if (lista == null) {
                        lexemaSiguiente = dtm.getValueAt(i, 0).toString();
                    } else {
                        lexemaSiguiente = lista.get(i);
                    }
                    if (lexemaSiguiente.equals("{")) {
                        if (contadorLlaves > 0) {
                            se1 = true;
                            s1.add(lexemaSiguiente);
                        } else {
                            linea.add(lexemaSiguiente);
                        }
                        contadorLlaves++;
                    } else if (lexemaSiguiente.equals("}")) {
                        if (contadorLlaves > 1) {
                            s1.add(lexemaSiguiente);
                            contadorLlaves--;
                        } else {
                            contadorLlaves--;
                            linea.add(lexemaSiguiente);
                            if (contadorLlaves == 0) {
                                if (lista == null) {
                                    lexemaSiguiente = dtm.getValueAt(i, 0).toString();
                                } else {
                                    lexemaSiguiente = lista.get(i);
                                }
                                conta = true;
                                break;
                            }
                        }
                    } else if (conta == true) {
                        band = false;
                    } else if (contadorLlaves > 0) {
                        s1.add(lexemaSiguiente);
                    } else {
                        linea.add(lexemaSiguiente);
                    }
                } while (band);
                if (lista == null) {
                    codigoCompleto += obtenerIntermedio(linea, s1);
                } else {
                    codig += obtenerIntermedio(linea, s1);
                }
            } else if (lexema.startsWith("$") && ((lista == null && dtm.getValueAt(i + 1, 0).toString().equals("=")) || (lista != null && lista.get(i + 1).equals("=")))) {
                boolean band = true;
                ArrayList asignacion = new ArrayList();
                ArrayList asignacion2 = new ArrayList();
                asignacion.add(lexema);
                asignacion2.add(lexema);
                do {
                    i++;
                    String lexe;
                    if (lista == null) {
                        lexe = dtm.getValueAt(i, 0).toString();
                    } else {
                        lexe = lista.get(i);
                    }
                    if (lexe.equals(";")) {
                        band = false;
                    } else {
                        asignacion.add(lexe);
                        asignacion2.add(lexe);
                    }
                } while (band);
                if (asignacion.size() > 4 /*|| asignacion2.size() > 4*/) {
                    if (lista == null) {
                        TemporalesOptimizados tmpOp = new TemporalesOptimizados();
                        codigoCompleto += tmpOp.temporalesConArreglo(asignacion);

                        temporales += tmpOp.temporalesConArreglo(asignacion2);
                        //System.out.println("OPTIMIZACION DE TEMPORALES if: \n" + temporales);
                    } else {
                        TemporalesOptimizados tmpOp = new TemporalesOptimizados();
                        codig += tmpOp.temporalesConArreglo(asignacion);

                        temporales += tmpOp.temporalesConArreglo(asignacion);
                        //System.out.println("OPTIMIZACION DE TEMPORALES else : \n" + temporales);
                    }
                } else if (lista == null) {
                    if (dtm.getValueAt(i - asignacion.size() - 1, 0).equals("int") || dtm.getValueAt(i - asignacion.size() - 1, 0).equals("doble") || dtm.getValueAt(i - asignacion.size() - 1, 0).equals("cad")) {
                        codigoCompleto += dtm.getValueAt(i - asignacion.size() - 1, 0) + " ";
                    }
                    for (int j = 0; j < asignacion.size(); j++) {
                        codigoCompleto += asignacion.get(j);
                    }
                    codigoCompleto += "\n\r";
                } else {
                    if (lista.get(i - asignacion.size() - 1).equals("int") || lista.get(i - asignacion.size() - 1).equals("doble") || lista.get(i - asignacion.size() - 1).equals("cad")) {
                        codig += lista.get(i - asignacion.size() - 1) + " ";
                    }
                    for (int j = 0; j < asignacion.size(); j++) {
                        codig += asignacion.get(j);
                    }
                    codig += "\n\r";
                }
            } else if (lista == null) {
                if (lexema.equals("escribir")) {
                    boolean ban = true;
                    codigoCompleto += lexema;
                    do {
                        i++;
                        String lexi = dtm.getValueAt(i, 0).toString();
                        codigoCompleto += lexi;
                        if (lexi.equals(";")) {
                            codigoCompleto += "\n";
                            break;
                        }
                    } while (ban);

                }
            } else if (lista != null) {
                if (lexema.equals("escribir")) {
                    boolean ban = true;
                    codig += lexema;
                    do {
                        i++;
                        String lexi = lista.get(i);
                        codig += lexi;
                        if (lexi.equals(";")) {
                            codig += "\n";
                            break;
                        }
                    } while (ban);
                }
            }
        }
        if (lista == null) {
            return codigoCompleto;
        } else {
            return codig;
        }
    }

    public String obtenerIntermedio(ArrayList linea, ArrayList s1) {
        ArrayList condicion = new ArrayList();
        String codigin = "";
        String sentencia = (String) linea.get(0);
        switch (sentencia) {
            case "si":
                String condi = "";
                String bTrue = String.valueOf(contadorEtiquetas);
                contadorEtiquetas += 10;
                String bFalse = String.valueOf(contadorEtiquetas);
                contadorEtiquetas += 10;
                codigoEtiquetas += "S-> Si E s1\n\r";
                codigoEtiquetas += "E.true: " + bTrue + "\n\r";
                codigoEtiquetas += "E.false: " + bFalse + "\n\r";
                int par = 0;
                String var = (String) s1.get(0);
                for (int i = 0; i < linea.size(); i++) {
                    String lexema = (String) linea.get(i);
                    if (i == linea.size() - 1) {
                        if (var.equals("si") || var.equals("para") || var.equals("mientras") || var.equals("hacerM")) {
                            codigin += leerSentencias(s1);
                        } else {
                            ArrayList<String> sent = new ArrayList();
                            int tipo = 0;
                            for (int j = 0; j < s1.size(); j++) {
                                String lex = (String) s1.get(j);
                                sent.add(lex);
                                if (lex.equals(";")) {
                                    if (sent.get(0).startsWith("$") && sent.get(1).equals("=") || sent.get(0).equals("int") || sent.get(0).equals("cad") || sent.get(0).equals("doble")) {
                                        boolean band = true;
                                        ArrayList asignacion = new ArrayList();
                                        int h = 0;
                                        do {
                                            String lexe;
                                            lexe = sent.get(h);
                                            if (lexe.equals(";")) {
                                                band = false;
                                            }
                                            asignacion.add(lexe);
                                            h++;
                                        } while (band);
                                        codigin += leerSentencias(asignacion);
                                        sent = new ArrayList();
                                    } else {
                                        String s = "";
                                        for (int k = 0; k < sent.size(); k++) {
                                            s += sent.get(k);
                                        }
                                        codigin += s + "\n\r";
                                        s = "";
                                        sent = new ArrayList();
                                    }
                                } else if (lex.equals("si") || lex.equals("mientras") || lex.equals("para") || lex.equals("hacerM")) {
                                    boolean band = true;
                                    int llaves = 0;
                                    do {
                                        j++;
                                        String l = (String) s1.get(j);
                                        if (l.equals("{")) {
                                            llaves++;
                                            sent.add(l);
                                        } else if (l.equals("}")) {
                                            llaves--;
                                            sent.add(l);
                                            if (llaves == 0) {
                                                band = false;
                                            }
                                        } else {
                                            sent.add(l);
                                        }

                                    } while (band);
                                    codigin += leerSentencias(sent);

                                    sent = new ArrayList();
                                }
                            }
                        }
                    } else if (lexema.equals("(")) {
                        if (par != 0) {
                            condi += " " + lexema;
                        }
                        par++;
                    } else if (lexema.equals(")")) {
                        par--;
                        if (par == 0) {
                            codigin += condi + " goto " + bTrue + "\n\r" + "goto " + bFalse + "\n\r" + bTrue + "\n\r";
                        } else {
                            condi += " " + lexema;
                        }
                    } else {
                        condi += " " + lexema;
                    }
                }
                codigin += bFalse + "\r\n";
                break;
            case "para":
                String condiP = "si";
                String inicioP = String.valueOf(contadorEtiquetas);
                contadorEtiquetas += 10;
                String bTrueP = String.valueOf(contadorEtiquetas);
                contadorEtiquetas += 10;
                String bFalseP = String.valueOf(contadorEtiquetas);
                contadorEtiquetas += 10;
                String sSigP = inicioP;
                codigoEtiquetas += "S->  Para (Ini; E ; Inc;) s1\n\r";
                codigoEtiquetas += "Comienzo: " + inicioP + "\n\r";
                codigoEtiquetas += "E.true: " + bTrueP + "\n\r";
                codigoEtiquetas += "E.false: " + bFalseP + "\n\r";
                codigoEtiquetas += "S.Sig: " + sSigP + "\n\r";
                int parP = 0;
                int contadorComas = 0;
                String incre = "";
                String varP = (String) s1.get(0);
                for (int i = 0; i < linea.size(); i++) {
                    String lexema = (String) linea.get(i);
                    if (i == linea.size() - 1) {
                        if (varP.equals("si") || varP.equals("para") || varP.equals("mientras") || varP.equals("hacerM")) {
                            codigin += leerSentencias(s1);
                        } else {
                            ArrayList<String> sent = new ArrayList();
                            int tipo = 0;
                            for (int j = 0; j < s1.size(); j++) {
                                String lex = (String) s1.get(j);
                                sent.add(lex);
                                if (lex.equals(";")) {
                                    if (sent.get(0).startsWith("$") && sent.get(1).equals("=") || sent.get(0).equals("int") || sent.get(0).equals("cad") || sent.get(0).equals("doble")) {
                                        boolean band = true;
                                        ArrayList asignacion = new ArrayList();
                                        int h = 0;
                                        do {
                                            String lexe;
                                            lexe = sent.get(h);
                                            if (lexe.equals(";")) {
                                                band = false;
                                            }
                                            asignacion.add(lexe);
                                            h++;
                                        } while (band);
                                        codigin += leerSentencias(asignacion);
                                        sent = new ArrayList();
                                    } else {
                                        String s = "";
                                        for (int k = 0; k < sent.size(); k++) {
                                            s += sent.get(k);
                                        }
                                        codigin += s + "\n\r";
                                        s = "";
                                        sent = new ArrayList();
                                    }
                                } else if (lex.equals("si") || lex.equals("mientras") || lex.equals("para") || lex.equals("hacerM")) {
                                    boolean band = true;
                                    int llaves = 0;
                                    do {
                                        j++;
                                        String l = (String) s1.get(j);
                                        if (l.equals("{")) {
                                            llaves++;
                                            sent.add(l);
                                        } else if (l.equals("}")) {
                                            llaves--;
                                            sent.add(l);
                                            if (llaves == 0) {
                                                band = false;
                                            }
                                        } else {
                                            sent.add(l);
                                        }
                                    } while (band);
                                    codigin += leerSentencias(sent);
                                    sent = new ArrayList();
                                }
                            }
                        }
                        codigin += incre + "\n\r";
                        codigin += "goto " + sSigP + "\n\r";
                    } else if (lexema.equals("(")) {
                        if (parP != 0) {
                            if (contadorComas == 1) {
                                condiP += " " + lexema;
                            } else if (contadorComas == 0) {
                                codigin += " " + lexema;
                            } else if (contadorComas == 2) {
                                incre += " " + lexema;
                            }
                        }
                        parP++;
                    } else if (lexema.equals(")")) {
                        parP--;
                        if (parP == 0) {
                            codigin += condiP + " goto " + bTrueP + "\n\r" + "goto " + bFalseP + "\n\r" + bTrueP + "\n\r";
                        } else {
                            condiP += " " + lexema;
                        }
                    } else if (lexema.equals(";")) {
                        contadorComas++;
                        if (contadorComas == 1) {
                            codigin += "\n\r" + inicioP + "\n\r";
                        }
                    } else if (parP > 0) {
                        if (contadorComas == 1) {
                            condiP += " " + lexema;
                        } else if (contadorComas == 0) {
                            codigin += " " + lexema;
                        } else if (contadorComas == 2) {
                            incre += " " + lexema;
                        }
                    }
                }
                codigin += bFalseP + "\r\n";
                break;
            case "mientras":
                String conde = "si";
                String inicioM = String.valueOf(contadorEtiquetas);
                contadorEtiquetas += 10;
                String bTrueM = String.valueOf(contadorEtiquetas);
                contadorEtiquetas += 10;
                String bFalseM = String.valueOf(contadorEtiquetas);
                contadorEtiquetas += 10;
                String sSigM = inicioM;
                codigoEtiquetas += "S-> Mientras E s1\n\r";
                codigoEtiquetas += "Comienzo: " + inicioM + "\n\r";
                codigoEtiquetas += "E.true: " + bTrueM + "\n\r";
                codigoEtiquetas += "E.false: " + bFalseM + "\n\r";
                codigoEtiquetas += "S.Sig: " + sSigM + "\n\r";
                int parM = 0;
                codigin += inicioM + "\n\r";
                String varM = (String) s1.get(0);
                for (int i = 0; i < linea.size(); i++) {
                    String lexema = (String) linea.get(i);
                    if (i == linea.size() - 1) {
                        if (varM.equals("si") || varM.equals("para") || varM.equals("mientras") || varM.equals("hacerM")) {
                            codigin += leerSentencias(s1);
                        } else {
                            ArrayList<String> sent = new ArrayList();
                            int tipo = 0;
                            for (int j = 0; j < s1.size(); j++) {
                                String lex = (String) s1.get(j);
                                sent.add(lex);
                                if (lex.equals(";")) {
                                    if (sent.get(0).startsWith("$") && sent.get(1).equals("=") || sent.get(0).equals("int") || sent.get(0).equals("cad") || sent.get(0).equals("doble")) {
                                        boolean band = true;
                                        ArrayList asignacion = new ArrayList();
                                        int h = 0;
                                        do {
                                            String lexe;
                                            lexe = sent.get(h);
                                            if (lexe.equals(";")) {
                                                band = false;
                                            }
                                            asignacion.add(lexe);
                                            h++;
                                        } while (band);
                                        codigin += leerSentencias(asignacion);
                                        sent = new ArrayList();
                                    } else {
                                        String s = "";
                                        for (int k = 0; k < sent.size(); k++) {
                                            s += sent.get(k);
                                        }
                                        codigin += s + "\n\r";
                                        s = "";
                                        sent = new ArrayList();
                                    }
                                } else if (lex.equals("si") || lex.equals("mientras") || lex.equals("para") || lex.equals("hacerM")) {
                                    boolean band = true;
                                    int llaves = 0;
                                    do {
                                        j++;
                                        String l = (String) s1.get(j);
                                        if (l.equals("{")) {
                                            llaves++;
                                            sent.add(l);
                                        } else if (l.equals("}")) {
                                            llaves--;
                                            sent.add(l);
                                            if (llaves == 0) {
                                                band = false;
                                            }
                                        } else {
                                            sent.add(l);
                                        }
                                    } while (band);
                                    codigin += leerSentencias(sent);
                                    sent = new ArrayList();
                                }
                            }
                        }
                        codigin += "goto " + sSigM + "\n\r";
                    } else if (lexema.equals("(")) {
                        if (parM != 0) {
                            conde += " " + lexema;
                        }
                        parM++;
                    } else if (lexema.equals(")")) {
                        parM--;
                        if (parM == 0) {
                            codigin += conde + " goto " + bTrueM + "\n\r" + "goto " + bFalseM + "\n\r" + bTrueM + "\n\r";
                        } else {
                            conde += " " + lexema;
                        }
                    } else if (parM > 0) {
                        conde += " " + lexema;
                    }
                }
                codigin += bFalseM + "\r\n";
                break;
            case "hacerM":
                String condiH = "si";
                String inicioH = String.valueOf(contadorEtiquetas);
                contadorEtiquetas += 10;
                String bTrueH = inicioH;
                String bFalseH = String.valueOf(contadorEtiquetas);
                contadorEtiquetas += 10;
                codigoEtiquetas += "S-> Hacer s1 Mientras E \n\r";
                codigoEtiquetas += "Comienzo: " + inicioH + "\n\r";
                codigoEtiquetas += "E.true: " + bTrueH + "\n\r";
                codigoEtiquetas += "E.false: " + bFalseH + "\n\r";
                int parH = 0;
                codigin += inicioH + "\n\r";
                String varH = (String) s1.get(0);
                for (int i = 0; i < linea.size(); i++) {
                    String lexema = (String) linea.get(i);
                    if (i == 1) {
                        if (varH.equals("si") || varH.equals("para") || varH.equals("mientras") || varH.equals("hacerM")) {
                            codigin += leerSentencias(s1);
                        } else {
                            ArrayList<String> sent = new ArrayList();
                            int tipo = 0;
                            for (int j = 0; j < s1.size(); j++) {
                                String lex = (String) s1.get(j);
                                sent.add(lex);
                                if (lex.equals(";")) {
                                    if (sent.get(0).startsWith("$") && sent.get(1).equals("=") || sent.get(0).equals("int") || sent.get(0).equals("cad") || sent.get(0).equals("doble")) {
                                        boolean band = true;
                                        ArrayList asignacion = new ArrayList();
                                        int h = 0;
                                        do {
                                            String lexe;
                                            lexe = sent.get(h);
                                            if (lexe.equals(";")) {
                                                band = false;
                                            }
                                            asignacion.add(lexe);
                                            h++;
                                        } while (band);
                                        codigin += leerSentencias(asignacion);
                                        sent = new ArrayList();
                                    } else {
                                        String s = "";
                                        for (int k = 0; k < sent.size(); k++) {
                                            s += sent.get(k);
                                        }
                                        codigin += s + "\n\r";
                                        s = "";
                                        sent = new ArrayList();
                                    }
                                } else if (lex.equals("si") || lex.equals("mientras") || lex.equals("para") || lex.equals("hacerM")) {
                                    boolean band = true;
                                    int llaves = 0;
                                    do {
                                        j++;
                                        String l = (String) s1.get(j);
                                        if (l.equals("{")) {
                                            llaves++;
                                            sent.add(l);
                                        } else if (l.equals("}")) {
                                            llaves--;
                                            sent.add(l);
                                            if (llaves == 0) {
                                                band = false;
                                            }
                                        } else {
                                            sent.add(l);
                                        }
                                    } while (band);
                                    codigin += leerSentencias(sent);
                                    sent = new ArrayList();
                                }
                            }
                        }
                    } else if (lexema.equals("(")) {
                        if (parH != 0) {
                            condiH += " " + lexema;
                        }
                        parH++;
                    } else if (lexema.equals(")")) {
                        parH--;
                        if (parH == 0) {
                            codigin += condiH + " goto " + bTrueH + "\n\r" + "goto " + bFalseH + "\n\r";
                        } else {
                            condiH += " " + lexema;
                        }
                    } else if (parH > 0) {
                        condiH += " " + lexema;
                    }
                }
                codigin += bFalseH + "\r\n";
                break;
        }
        return codigin;
    }

    public String temporalesConArreglo(ArrayList<String> lis) {
        ArrayList<String> lista;
        lista = lis;
        int inicio = 0;
        String codigoIntermedio = "";
        int fin = 0;
        int tempo = 1;
        int empieza = 0;
        String temporal = "";
        boolean llaves = true;
        boolean terminar = false;
        for (int i = 0; i < lista.size(); i++) {
            String valor = lista.get(i);
            if (valor.equals("=")) {
                empieza = i;
                break;
            }
        }
        while (!terminar) {
            if (llaves) {
                for (int i = 0; i < lista.size(); i++) {
                    String token = lista.get(i);
                    if (token.equals("(")) {
                        inicio = i;
                    } else if (token.equals(")")) {
                        fin = i;
                        break;
                    }
                }
                if (fin > 0) {
                    if ((fin - inicio) == 4) {
                        codigoIntermedio += "T" + tempo + " = " + lista.get(inicio + 1) + lista.get(inicio + 2) + lista.get(fin - 1) + "\n";
                        for (int i = 0; i <= fin - inicio; i++) {
                            lista.remove(inicio);
                        }
                        lista.add(inicio, "T" + tempo);
                        inicio = 0;
                        fin = 0;
                        tempo++;
                    } else {
                        int centro = 0;
                        for (int i = inicio; i <= fin; i++) {
                            String token2 = lista.get(i);
                            if (token2.equals("*") || token2.equals("/")) {
                                centro = i;
                                break;
                            }
                        }
                        if (centro > 0) {
                            codigoIntermedio += "T" + tempo + " = " + lista.get(centro - 1) + lista.get(centro) + lista.get(centro + 1) + "\n";
                            for (int j = 0; j < 3; j++) {
                                lista.remove(centro - 1);
                            }
                            lista.add(centro - 1, "T" + tempo);
                            tempo++;
                        } else {
                            for (int i = inicio; i <= fin; i++) {
                                String token2 = lista.get(i);
                                if (token2.equals("+") || token2.equals("-")) {
                                    centro = i;
                                    break;
                                }
                            }
                            if (centro > 0) {
                                codigoIntermedio += "T" + tempo + " = " + lista.get(centro - 1) + lista.get(centro) + lista.get(centro + 1) + "\n";
                                for (int j = 0; j < 3; j++) {
                                    lista.remove(centro - 1);
                                }
                                lista.add(centro - 1, "T" + tempo);
                                tempo++;
                            }
                        }
                    }
                } else {
                    llaves = false;
                }
            } else {
                int centro = 0;
                for (int i = lista.size(); i > 0; i--) {
                    String token2 = lista.get(i - 1);
                    if (token2.equals("*") || token2.equals("/")) {
                        centro = (i - 1);
                    }
                }
                if (centro > 0) {
                    codigoIntermedio += "T" + tempo + " = " + lista.get(centro - 1) + lista.get(centro) + lista.get(centro + 1) + "\n";
                    for (int j = 0; j < 3; j++) {
                        lista.remove(centro - 1);
                    }
                    lista.add(centro - 1, "T" + tempo);
                    tempo++;
                } else {
                    for (int i = lista.size(); i > 0; i--) {
                        String token2 = lista.get(i - 1);
                        if (token2.equals("+") || token2.equals("-")) {
                            centro = (i - 1);
                        }
                    }
                    if (centro > 0) {
                        codigoIntermedio += "T" + tempo + " = " + lista.get(centro - 1) + lista.get(centro) + lista.get(centro + 1) + "\n";
                        for (int j = 0; j < 3; j++) {
                            lista.remove(centro - 1);
                        }
                        lista.add(centro - 1, "T" + tempo);
                        tempo++;
                    }

                }
            }
            if (empieza != 0) {
                if (lista.size() == 3) {
                    terminar = true;
                    codigoIntermedio += lista.get(0) + " = " + lista.get(lista.size() - 1) + "\n";
                }
            } else if (lista.size() == 1) {
                terminar = true;
            }
        }
        return codigoIntermedio;
    }
    
}
