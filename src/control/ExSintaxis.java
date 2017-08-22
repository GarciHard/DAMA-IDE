package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * Hecho con <3 Por:
 * @author GarciHard
 */

public class ExSintaxis {
    
    private final ArrayList<String> ids;
    private static String[][] tabla; static int altoTabla = 0;
    private final ArrayList<ExProduccion> producciones = new ArrayList();
    private final ArrayList<String> lineas;
    
    public ExSintaxis(ArrayList<String> ids, ArrayList<String> lineas) {
        this.ids = ids;
        this.lineas = lineas;
        //System.out.println("VALOR INICIAL DE IDS: "+this.ids);
        //System.out.println("VALOR INICIAL DE LIN: "+this.lineas);
        CrearTablaSintaxis();
        try {
            CrearListaProduccion();
        } catch (IOException e) {}
    }
    
    private void CrearTablaSintaxis() {
        try {
            Workbook libro = Workbook.getWorkbook(new File("src/txt/tablaSintaxis.xls"));
            altoTabla = libro.getSheet(0).getRows();
            //System.out.println(">>>>>ALTO DE LA TABLA DE EXCEL: "+altoTabla);
            //System.out.println(">>>>>ANCH DE LA TABLA DE EXCEL: "+libro.getSheet(0).getColumns());
            tabla = new String[libro.getSheet(0).getColumns()][altoTabla];
            for (int i = 0; i < tabla.length; i++)
                for (int j = 0; j < altoTabla; j++)
                    tabla[i][j] = libro.getSheet(0).getCell(i, j).getContents();
        } catch (BiffException | IOException ex) {}
    }
    
    private void CrearListaProduccion() throws FileNotFoundException, IOException {
        BufferedReader txt = new BufferedReader(new FileReader(new File("src/txt/listaProduccion.txt")));
        String aux = "";
        do {
            if (aux != null & aux.compareTo("") != 0) producciones.add(new ExProduccion(aux));
            aux = txt.readLine();
            //System.out.println(">>>>>LISTA DE PRODUCCIONES: "+aux);
        }
        while (aux != null);
    }
    
    private String AnalizarGramatica() {
        ArrayList<String> pila = new ArrayList();
        //System.out.println(">>>>>IMRPRIMIMOS EL TAMAÑO DE LA PILA: "+pila.size());
        pila.add("$");
        //System.out.println(">>>>>IMRPRIMIMOS EL TAMAÑO DE LA PILA2: "+pila.size());
//        for (int i = 0; i < tabla.length-1; i++) {
//            for (int j = 0; j < i; j++) {
//                System.out.println(">>>>>VALORES DE LA TABLA[6][1]: "+tabla[i][j]);
//            }
//        }
        
        pila.add(tabla[tabla.length - 1][1]);
        int x, y;
//        System.out.println("IDS"+ids.size());
        while (!ids.isEmpty()) {
            //System.out.println(">>>>>IDS: "+ids+">>>>>PILA: "+pila);
            if (comapararTopes(ids, pila)) cotarCabezas(ids, pila);
            else if (checarλ(pila)) pila.remove(pila.size() - 1);
            else {
                x = BuscarX(ids.get(ids.size() - 1));
                y = BuscarY(pila.get(pila.size() - 1));
//                System.out.println("Imprimiendo"+"\nx:"+x+"\ny:"+y);
//                System.out.println("TAMAÑO TABLA: "+tabla.length);
                if (x == tabla.length) 
                    return ids.get(ids.size() - 1) + " en la linea numero: " + lineas.get(lineas.size() - 1) + "\n";
            if (y == altoTabla) 
                    return "\n\nEsperaba: " +
                            ExArchivo.tokenToLexema(pila.get(pila.size() - 1)) +
                            " \n\nen la linea numero: " +
                            lineas.get(lineas.size() - 1) +
                            ", \n\nencontro " + ExArchivo.tokenToLexema(ids.get(ids.size() - 1)) + "\n";
            if (tabla[x][y].compareTo("E") == 0) 
                    return "\nEsperaba: " + Esperando(y) +
                            " \nen la linea numero: " +
                            lineas.get(lineas.size() - 1) +
                            ", \nencontro " + ExArchivo.tokenToLexema(ids.get(ids.size() - 1)) + "\n";
            int numPro = Integer.parseInt(tabla[x][y]);
                pila.remove(pila.size() - 1);
                agregarGramtica(pila, producciones.get(numPro - 1).getListas());
            }
        }
        return /*String.valueOf(x)*/"";
    }
    
    public String MensajeSintaxis() {
        return AnalizarGramatica();
    }

    private int BuscarX(String s) {
        //System.out.print(s + " -> codigo ");
        for (int i = 0; i < tabla.length; i++) if (tabla[i][0].compareTo(s) == 0) return i;
        return tabla.length;
    }

    private int BuscarY(String s) {
        //System.out.println(s + " -> pila");
        for (int i = 0; i < altoTabla; i++) if (tabla[tabla.length - 1][i].compareTo(s) == 0) return i;
        return altoTabla;
    }

    private boolean comapararTopes(ArrayList<String> ids, ArrayList<String> pila) {
        return (ids.get(ids.size() - 1).compareTo(pila.get(pila.size() - 1)) == 0);
    }

    private void cotarCabezas(ArrayList<String> ids, ArrayList<String> pila) {
        //System.out.print(ids.get(ids.size() - 1) + " ");
        //System.out.println(pila.get(pila.size() - 1) + ".   ");
        ids.remove(ids.size() - 1);
        lineas.remove(lineas.size() - 1);
        pila.remove(pila.size() - 1);
    }

    private void agregarGramtica(ArrayList<String> pila, ArrayList<String> l) {
        for (int i = l.size() - 1; i > 0; i --) {
            pila.add(l.get(i));
        }
    }

    private boolean checarλ(ArrayList<String> pila) {
        return (pila.get(pila.size() - 1).compareTo("λ") == 0);
    }

    private String Esperando(int y) {
        String s = "";
        for (int i = 0; i < tabla.length - 1; i++)
            if (tabla[i][y].compareTo("E") != 0) s = s  + ExArchivo.tokenToLexema(tabla[i][0]) + " | ";
        return s;
    }
    
}