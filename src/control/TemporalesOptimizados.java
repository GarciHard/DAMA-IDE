package control;

import java.util.ArrayList;

/**
 *
 * @author GarciHard
 */
public class TemporalesOptimizados {

    ArrayList<String[]> temporales;

    public TemporalesOptimizados() {
    }

    public String temporalesConArreglo(ArrayList<String> lis) {
        ArrayList<String> lista;
        temporales = new ArrayList();
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
            int centro = 0;
            for (int i = 0; i < lista.size(); i++) {
                String token2 = lista.get(i);
                if (token2.equals("*") || token2.equals("/")) {
                    centro = i;
                    break;
                }
            }
            if (centro > 0) {
                if ((lista.get(centro - 1).equals("0") || lista.get(centro + 1).equals("0"))) {
                    for (int j = 0; j < 3; j++) {
                        lista.remove(centro - 1);
                    }
                    lista.add(centro - 1, "0");
                } else if (lista.get(centro + 1).equals("1")) {
                    String auxi = lista.get(centro - 1);
                    for (int j = 0; j < 3; j++) {
                        lista.remove(centro - 1);
                    }
                    lista.add(centro - 1, auxi);
                } else if (lista.get(centro).equals("/") && lista.get(centro - 1).equals(lista.get(centro + 1))) {
                    for (int j = 0; j < 3; j++) {
                        lista.remove(centro - 1);
                    }
                    lista.add(centro - 1, "1");
                } else {
                    String tes[] = new String[2];
                    boolean banderin = true;
                    for (int i = 0; i < temporales.size(); i++) {
                        if (temporales.get(i)[1].equals(lista.get(centro - 1) + lista.get(centro) + lista.get(centro + 1))) {
                            banderin = false;
                            for (int j = 0; j < 3; j++) {
                                lista.remove(centro - 1);
                            }
                            lista.add(centro - 1, temporales.get(i)[0]);
                            break;
                        }
                    }
                    if (banderin) {
                        tes[0] = "T" + tempo;
                        tes[1] = lista.get(centro - 1) + lista.get(centro) + lista.get(centro + 1);
                        float res = 0;
                        if (Character.isDigit(lista.get(centro - 1).charAt(0)) && Character.isDigit(lista.get(centro + 1).charAt(0))) {
                            float var1, var2;
                            var1 = Float.parseFloat(lista.get(centro - 1));
                            var2 = Float.parseFloat(lista.get(centro + 1));
                            if ("*".equals(lis.get(centro))) {
                                res = var1 * var2;
                            }
                            if ("/".equals(lis.get(centro))) {
                                res = var1 / var2;
                            }
                            for (int j = 0; j < 3; j++) {
                                lista.remove(centro - 1);
                            }
                            lista.add(centro - 1, String.valueOf(res));
                        } else if (lista.get(centro - 1).charAt(0) == '-' && Character.isDigit(lista.get(centro - 1).charAt(1)) && Character.isDigit(lista.get(centro + 1).charAt(0))) {
                            float var1, var2;
                            var1 = Float.parseFloat(lista.get(centro - 1));
                            //System.out.println("valor 111111111111111111111111111111111:" + var1);
                            var2 = Float.parseFloat(lista.get(centro + 1));
                            if ("*".equals(lis.get(centro))) {
                                res = var1 * var2;
                            }
                            if ("/".equals(lis.get(centro))) {
                                res = var1 / var2;
                            }
                            for (int j = 0; j < 3; j++) {
                                lista.remove(centro - 1);
                            }
                            lista.add(centro - 1, String.valueOf(res));
                        } else {
                            codigoIntermedio += "T" + tempo + " = " + lista.get(centro - 1) + lista.get(centro) + lista.get(centro + 1) + "\n";
                            for (int j = 0; j < 3; j++) {
                                lista.remove(centro - 1);
                            }
                            temporales.add(tes);
                            lista.add(centro - 1, "T" + tempo);
                            tempo++;
                        }

                    }

                }

            } else {
                for (int i = 0; i < lista.size(); i++) {
                    String token2 = lista.get(i);
                    if (token2.equals("+") || token2.equals("-")) {
                        centro = i;
                        break;
                    }
                }
                if (centro > 0) {
                    if (lista.get(centro - 1).equals("0") && lista.get(centro).equals("+")) {
                        String val = lista.get(centro + 1);
                        for (int j = 0; j < 3; j++) {
                            lista.remove(centro - 1);
                        }
                        lista.add(centro - 1, val);
                    } else if (lista.get(centro).equals("-") && lista.get(centro - 1).equals(lista.get(centro + 1))) {
                        for (int j = 0; j < 3; j++) {
                            lista.remove(centro - 1);
                        }
                        lista.add(centro - 1, "0");
                    } else if (lista.get(centro).equals("-") && lista.get(centro - 1).equals("0")) {
                        String valor = lista.get(centro + 1);
                        for (int j = 0; j < 3; j++) {
                            lista.remove(centro - 1);
                        }
                        if (lista.get(centro - 2).equals("-")) {
                            lista.remove(centro - 2);
                            lista.add(centro - 2, "+");
                        } else if (lista.get(centro - 2).equals("+")) {
                            lista.remove(centro - 2);
                            lista.add(centro - 2, "-");
                        } else {
                            valor = "-" + valor;
                        }
                        lista.add(centro - 1, valor);
                    } else if (lista.get(centro + 1).equals("0")) {
                        String val = lista.get(centro - 1);
                        for (int j = 0; j < 3; j++) {
                            lista.remove(centro - 1);
                        }
                        lista.add(centro - 1, val);
                    } else {
                        String tes[] = new String[2];
                        boolean banderin = true;
                        for (int j = 0; j < temporales.size(); j++) {
                            if (temporales.get(j)[1].equals(lista.get(centro - 1) + lista.get(centro) + lista.get(centro + 1))) {
                                banderin = false;
                                for (int k = 0; k < 3; k++) {
                                    lista.remove(centro - 1);
                                }
                                lista.add(centro - 1, temporales.get(j)[0]);
                                break;
                            }

                        }
                        if (banderin) {
                            tes[0] = "T" + tempo;
                            tes[1] = lista.get(centro - 1) + lista.get(centro) + lista.get(centro + 1);
                            float res = 0;
                            if (Character.isDigit(lista.get(centro - 1).charAt(0)) && Character.isDigit(lista.get(centro + 1).charAt(0))) {
                                float var1, var2;
                                var1 = Float.parseFloat(lista.get(centro - 1));
                                var2 = Float.parseFloat(lista.get(centro + 1));
                                if ("+".equals(lis.get(centro))) {
                                    res = var1 + var2;
                                }
                                if ("-".equals(lis.get(centro))) {
                                    res = var1 - var2;
                                }
                                for (int j = 0; j < 3; j++) {
                                    lista.remove(centro - 1);
                                }
                                lista.add(centro - 1, String.valueOf(res));
                            } else if (lista.get(centro - 1).charAt(0) == '-' && Character.isDigit(lista.get(centro - 1).charAt(1)) && Character.isDigit(lista.get(centro + 1).charAt(0))) {
                                float var1, var2;
                                var1 = Float.parseFloat(lista.get(centro - 1));
//                                System.out.println("valor 22222222222222222222222222222222222222221:" + var1);
                                var2 = Float.parseFloat(lista.get(centro + 1));
                                if ("+".equals(lis.get(centro))) {
                                    res = var1 + var2;
                                }
                                if ("-".equals(lis.get(centro))) {
                                    res = var1 - var2;
                                }
                                for (int j = 0; j < 3; j++) {
                                    lista.remove(centro - 1);
                                }
                                lista.add(centro - 1, String.valueOf(res));
                            } else {
                                codigoIntermedio += "T" + tempo + " = " + lista.get(centro - 1) + lista.get(centro) + lista.get(centro + 1) + "\n";
                                for (int j = 0; j < 3; j++) {
                                    lista.remove(centro - 1);
                                }
                                temporales.add(tes);
                                lista.add(centro - 1, "T" + tempo);
                                tempo++;
                            }

                        }

                    }

                }
            }
            if (empieza != 0) {
                if (lista.size() == 3) {
                    terminar = true;
                    codigoIntermedio += lista.get(0) + " = " + lista.get(lista.size() - 1) + "\n";
                } else if (lista.size() == 4 && lista.get(lista.size() - 1).charAt(0) == '-') {
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
