package control;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author GarciHard
 */
public class Assembler {

    public void evar(ArrayList<String> arr1, int co) {

        //System.out.println("***************");
        ArrayList<String> arr = new ArrayList();
        StringTokenizer tokens = new StringTokenizer(arr1.toString(), " @',][=()+/*-", true);
        //System.out.println("*************");
        while (tokens.hasMoreTokens()) {

            String palabra = tokens.nextToken();
            if (!" ".equals(palabra) && !",".equals(palabra) && !"[".equals(palabra) && !"]".equals(palabra) && !";".equals(palabra) && !"@".equals(palabra)) {
                arr.add(palabra);
                //System.out.println("pali="+palabra);
            }

        }
        if (!"si".equals(arr.get(0))) {
            if (arr.size() == 5 || arr.size() == 3 || arr.size() == 6 || arr.size() == 7) {
                if ("escribir".equals(arr.get(0))) {
                    if (arr.size() == 6) {
                        System.out.println("m" + co + " db 10,13, " + arr.get(2) + arr.get(3) + arr.get(4) + ",'$'");
                    } else {
                        System.out.println("m" + co + " " + arr.get(2) + arr.get(3) + arr.get(4) + " " + arr.get(5) + "");
                    }
                } else if ("\'".equals(arr.get(2))) {
                    System.out.println(arr.get(0) + " " + arr.get(2) + arr.get(3) + arr.get(4));
                } else {
                    System.out.println(arr.get(0) + " DW 0");
                }
            }
        }
    }

    public void ensamblador(ArrayList<String> arr1, int co) {

        ArrayList<String> arr = new ArrayList();
        StringTokenizer tokens = new StringTokenizer(arr1.toString(), " ',][=()+/*-", true);
        //System.out.println("*************");
        while (tokens.hasMoreTokens()) {

            String palabra = tokens.nextToken();
            if (!" ".equals(palabra) && !",".equals(palabra) && !"[".equals(palabra) && !"]".equals(palabra)) {
                arr.add(palabra);
                //System.out.println("pali="+palabra);
            }

        }

//      arr.remove(0);
//      arr.remove(arr.size()-1);
        if ("escribir".equals(arr.get(0))) {
            //SOUT
            if (!"\'".equals(arr.get(2))) {
                System.out.println("MOV AH,09H");
                System.out.println("LEA DX, " + arr.get(2));
                System.out.println("INT 21H");
            } else {
                System.out.println("MOV AH,09H");
                System.out.println("LEA DX, m" + co);
                System.out.println("INT 21H");
            }
        }
        if ("goto".equals(arr.get(0))) {
            //goto
            //System.out.println("goto");
            //System.out.println("JMP E"+arr.get(1)+":");
            System.out.println("JMP E" + arr.get(1));

        }
        if (arr.size() == 6 && !"si".equals(arr.get(3))) {
            //operaciones con resta
            if ("*".equals(arr.get(4))) {
                //MULTIPLICACION
                //System.out.println("mul");
                System.out.println("MOV AX, " + arr.get(2) + arr.get(3));
                System.out.println("MUL " + arr.get(5));
                System.out.println("MOV " + arr.get(0) + ", AX");

            }
            if ("/".equals(arr.get(4))) {
                //DIVISION
                //System.out.println("division");
                System.out.println("MOV AX, " + arr.get(2) + arr.get(3));
                System.out.println("DIV " + arr.get(5));
                System.out.println("MOV " + arr.get(0) + ", AX");
            }
            if ("+".equals(arr.get(4))) {
                //SUMA
                //System.out.println("suma");
                System.out.println("MOV AX, " + arr.get(2) + arr.get(3));
                System.out.println("ADD AX, " + arr.get(5));
                System.out.println("MOV " + arr.get(0) + ", AX");

            }
            if ("-".equals(arr.get(4))) {
                //RESTA
                //System.out.println("resta");
                System.out.println("MOV AX, " + arr.get(2) + arr.get(3));
                System.out.println("SUB AX, " + arr.get(5));
                System.out.println("MOV " + arr.get(0) + ", AX");
            }

        }
        if (arr.size() == 5 && "*".equals(arr.get(3))) {
            //MULTIPLICACION
            //System.out.println("mul");
            System.out.println("MOV AX, " + arr.get(2));
            System.out.println("MUL " + arr.get(4));
            System.out.println("MOV " + arr.get(0) + ", AX");

        }
        if (arr.size() == 5 && "/".equals(arr.get(3))) {
            //DIVISION
            //System.out.println("division");
            System.out.println("MOV AX, " + arr.get(2));
            System.out.println("DIV " + arr.get(4));
            System.out.println("MOV " + arr.get(0) + ", AX");
        }
        if (arr.size() == 5 && "+".equals(arr.get(3))) {
            //SUMA
            //System.out.println("suma");
            System.out.println("MOV AX, " + arr.get(2));
            System.out.println("ADD AX, " + arr.get(4));
            System.out.println("MOV " + arr.get(0) + ", AX");

        }
        if (arr.size() == 5 && "-".equals(arr.get(3))) {
            //RESTA
            //System.out.println("resta");
            System.out.println("MOV AX, " + arr.get(2));
            System.out.println("SUB AX, " + arr.get(4));
            System.out.println("MOV " + arr.get(0) + ", AX");
        }
        if (arr.size() == 3 || arr.size() == 4) {
            //ASIGNACION
            //System.out.println("asignacion");
            if ("-".equals(arr.get(2))) {
                System.out.println("MOV AX," + arr.get(2) + arr.get(3));
                System.out.println("MOV " + arr.get(0) + " ,AX");
            } else {
                System.out.println("MOV AX," + arr.get(2));
                System.out.println("MOV " + arr.get(0) + " ,AX");
            }
        }
        if (arr.size() == 1) {
            System.out.println("E" + arr.get(0) + ":");
        }
        if (arr.size() == 6 && ">".equals(arr.get(2))) {
            //ifmayor
            //System.out.println("MAYOR");
            System.out.println("MOV AX," + arr.get(1));
            System.out.println("CMP AX," + arr.get(3));
            System.out.println("JA E" + arr.get(5));
        }
        if (arr.size() == 6 && "<".equals(arr.get(2))) {
            //ifmenor
            //System.out.println("MENOR");
            System.out.println("MOV AX," + arr.get(1));
            System.out.println("CMP AX," + arr.get(3));
            System.out.println("JB E" + arr.get(5));
        }
        if (arr.size() == 7 && "=".equals(arr.get(2))) {
            //ifIGUAL
            System.out.println("MOV AX," + arr.get(1));
            System.out.println("CMP AX," + arr.get(4));
            System.out.println("JE E" + arr.get(6));
        }
        if (arr.size() == 7 && ">".equals(arr.get(2))) {
            //ifmayorIGUAL
            System.out.println("MOV AX," + arr.get(1));
            System.out.println("CMP AX," + arr.get(4));
            System.out.println("JAE E" + arr.get(6));
        }
        if (arr.size() == 7 && "<".equals(arr.get(2))) {
            //ifmenorIGUAL
            System.out.println("MOV AX," + arr.get(1));
            System.out.println("CMP AX," + arr.get(4));
            System.out.println("JBE E" + arr.get(6));
        }
    }
}
