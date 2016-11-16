package uespi.br.calculadora;

import android.util.Log;

import java.math.BigDecimal;
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
            ArrayList<String> operadores = new ArrayList<String>();
            ArrayList<Double> numeros = new ArrayList<Double>();

            numeros = quebraExpressao(expressao, false);

            for(int i = 0; i < expressao.length(); i++){
                if(expressao.charAt(i) == '+' || (expressao.charAt(i) == '-' && i != 0 && expressao.charAt(i-1) != 'E') || expressao.charAt(i) == '*' || expressao.charAt(i) == '÷'){
                    operadores.add(new String(String.valueOf(expressao.charAt(i))));
                }
            }

            for(int i = 0; i < operadores.size(); i++){
                if(operadores.get(i).equals("*")){
                    Double resultado = numeros.get(i) * numeros.get(i+1);
                    resultado = Double.parseDouble(new DecimalFormat("#.#######E0").format(resultado).replace(',', '.'));
                    operadores.remove(i);
                    numeros.remove(i);
                    numeros.remove(i);
                    numeros.add(i, resultado);
                    i--;
                }else if(operadores.get(i).equals("÷")) {
                    Double resultado = numeros.get(i) / numeros.get(i + 1);
                    resultado = Double.parseDouble(new DecimalFormat("#.#######E0").format(resultado).replace(',', '.'));
                    operadores.remove(i);
                    numeros.remove(i);
                    numeros.remove(i);
                    numeros.add(i, resultado);
                    i--;
                }
            }

            for(int i = 0; i < operadores.size(); i++){
                if(operadores.get(i).equals("+")){
                    Double resultado = numeros.get(i) + numeros.get(i+1);
                    resultado = Double.parseDouble(new DecimalFormat("#.#######E0").format(resultado).replace(',', '.'));
                    operadores.remove(i);
                    numeros.remove(i);
                    numeros.remove(i);
                    numeros.add(i, resultado);
                    i--;
                }else if(operadores.get(i).equals("-")){
                    Double resultado = numeros.get(i) - numeros.get(i+1);
                    resultado = Double.parseDouble(new DecimalFormat("#.#######E0").format(resultado).replace(',', '.'));
                    operadores.remove(i);
                    numeros.remove(i);
                    numeros.remove(i);
                    numeros.add(i, resultado);
                    i--;
                }
            }

            return numeros.get(0).toString();
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

    public static ArrayList<Double> quebraExpressao(String expressao, boolean ordena){
        ArrayList<Double> valores = new ArrayList<Double>();
        String valor = "";
        //Log.d("expressao", expressao);
        for(int i = 0; i < expressao.length(); i++){
            if(expressao.charAt(i) == '+' || (expressao.charAt(i) == '-' && i != 0 && expressao.charAt(i-1) != 'E') || expressao.charAt(i) == '*' || expressao.charAt(i) == '÷'){
                valores.add(new Double(Double.parseDouble(valor)));
                valor = "";
            }else{
                valor += expressao.charAt(i);
            }
            //Log.d("valor", valor);
        }
        valores.add(new Double(Double.parseDouble(valor)));
        if(ordena)
            Collections.sort(valores);
        //Log.d("valores", valores.toString());
        return valores;
    }
}
