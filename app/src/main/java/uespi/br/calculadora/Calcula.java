package uespi.br.calculadora;

import android.util.Log;

import java.sql.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by RENATO on 09/11/2016.
 */

public abstract class Calcula {
    public static String resultado(String expressao){
            if (expressao.contains("*") || expressao.contains("÷")) {
                for (int i = 0; i < expressao.length(); i++) {
                    if (expressao.charAt(i) == '*' || expressao.charAt(i) == '÷') {
                        int localA = 0;
                        for (int k = i - 1; k >= 0; k--) {
                            if (expressao.charAt(k) == '+' || expressao.charAt(k) == '-' || expressao.charAt(k) == '*' || expressao.charAt(k) == '÷') {
                                localA = k;
                                break;
                            }
                        }

                        int localB = expressao.length();
                        for (int k = i + 1; k < expressao.length(); k++) {
                            if (expressao.charAt(k) == '+' || expressao.charAt(k) == '-' || expressao.charAt(k) == '*' || expressao.charAt(k) == '÷') {
                                localB = k;
                                break;
                            }
                        }

                        Double a = 0.0;
                        if (localA == 0)
                            a = Double.parseDouble(expressao.substring(localA, i));
                        else
                            a = Double.parseDouble(expressao.substring(localA + 1, i));

                        Double b = Double.parseDouble(expressao.substring(i + 1, localB));
                        if (expressao.charAt(i) == '*') {
                            Double resultado = a * b;
                            resultado = Double.parseDouble(new DecimalFormat("#.#######E0").format(resultado).replace(',', '.'));
                            if (localA == 0)
                                expressao = expressao.substring(0, localA) + resultado + expressao.substring(localB, expressao.length());
                            else
                                expressao = expressao.substring(0, localA + 1) + resultado + expressao.substring(localB, expressao.length());
                        } else if (expressao.charAt(i) == '÷') {
                            Double resultado = a / b;
                            resultado = Double.parseDouble(new DecimalFormat("#.#######E0").format(resultado).replace(',', '.'));
                            if (localA == 0)
                                expressao = expressao.substring(0, localA) + resultado + expressao.substring(localB, expressao.length());
                            else
                                expressao = expressao.substring(0, localA + 1) + resultado + expressao.substring(localB, expressao.length());
                        }
                        i = 0;
                    }
                    //Log.d("expressao", expressao);
                }
            }

            if (expressao.contains("+") || expressao.contains("-")) {
                for (int i = 0; i < expressao.length(); i++) {
                    if (expressao.charAt(i) == '+' || (expressao.charAt(i) == '-' && i != 0 && expressao.charAt(i-1)!='E')) {
                        int localA = 0;
                        for (int k = i - 1; k >= 0; k--) {
                            if (expressao.charAt(k) == '+' || (expressao.charAt(k) == '-' && k != 0 && expressao.charAt(k-1)!='E') || expressao.charAt(k) == '*' || expressao.charAt(k) == '÷') {
                                localA = k;
                                break;
                            }
                        }

                        int localB = expressao.length();
                        for (int k = i + 1; k < expressao.length(); k++) {
                            if (expressao.charAt(k) == '+' || expressao.charAt(k) == '-' || expressao.charAt(k) == '*' || expressao.charAt(k) == '÷') {
                                localB = k;
                                break;
                            }
                        }
                        Double a = 0.0;
                        if (localA == 0) {
                            //Log.d("localA", expressao.substring(localA, i));
                            a = Double.parseDouble(expressao.substring(localA, i));
                        }else
                            a = Double.parseDouble(expressao.substring(localA + 1, i));

                        Double b = Double.parseDouble(expressao.substring(i + 1, localB));
                        if (expressao.charAt(i) == '+') {
                            Double resultado = a + b;
                            resultado = Double.parseDouble(new DecimalFormat("#.#######E0").format(resultado).replace(',', '.'));
                            if (localA == 0)
                                expressao = expressao.substring(0, localA) + resultado + expressao.substring(localB, expressao.length());
                            else
                                expressao = expressao.substring(0, localA + 1) + resultado + expressao.substring(localB, expressao.length());
                        } else if (expressao.charAt(i) == '-') {
                            Double resultado = a - b;
                            resultado = Double.parseDouble(new DecimalFormat("#.#######E0").format(resultado).replace(',', '.'));
                            if (localA == 0)
                                expressao = expressao.substring(0, localA) + resultado + expressao.substring(localB, expressao.length());
                            else
                                expressao = expressao.substring(0, localA + 1) + resultado + expressao.substring(localB, expressao.length());
                        }
                        i = 0;
                    }
                    //Log.d("expressao", expressao);
                }
            }

            return expressao;
    }

    public static String maiorNumeroPar(ArrayList<Double> valores){
        String maior = "não existe";
        Log.d("valores", valores.toString());
        Collections.sort(valores, Collections.<Double>reverseOrder());

        for(Double v : valores){
            if(v % 2 == 0){
                maior = String.valueOf(v);
                break;
            }
        }

        return maior;
    }

    public static String menorNumeroImpar(ArrayList<Double> valores){
        String menor = "não existe";
        Collections.sort(valores);
        for(Double v : valores){
            if(v % 2 != 0){
                menor = String.valueOf(v);
                break;
            }
        }

        return menor;
    }

    public static ArrayList<Double> quebraExpressao(String expressao){
        ArrayList<Double> valores = new ArrayList<Double>();
        String valor = "";
        //Log.d("expressao", expressao);
        for(int i = 0; i < expressao.length(); i++){
            if(expressao.charAt(i) == '+' || (expressao.charAt(i) == '-' && i != 0) || expressao.charAt(i) == '*' || expressao.charAt(i) == '÷'){
                valores.add(new Double(Double.parseDouble(valor)));
                valor = "";
            }else{
                valor += expressao.charAt(i);
            }
            //Log.d("valor", valor);
        }
        valores.add(new Double(Double.parseDouble(valor)));

        Collections.sort(valores);
        //Log.d("valores", valores.toString());
        return valores;
    }
}
