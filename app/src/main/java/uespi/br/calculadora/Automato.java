package uespi.br.calculadora;

import android.util.Log;

/**
 * Created by RENATO on 09/11/2016.
 */

public abstract class Automato {

    public static int estado(int i, char c){
        if(i == 0){
            if(Character.isDigit(c)) return 1;
            if(c == '-') return 2;
            return -1;
        }

        if(i == 1){
            if(Character.isDigit(c)) return 1;
            if(c == '.') return 3;
            if(c == '+' || c == '-' || c == '*' || c == '÷') return 0;
            return -3;
        }

        if(i == 2){
            if(Character.isDigit(c)) return 1;
        }

        if(i == 3){
            if(Character.isDigit(c)) return 4;
        }

        if(i == 4){
            if(Character.isDigit(c)) return 4;
            if(c == '+' || c == '-' || c == '*' || c == '÷') return 0;
            return -3;
        }
        return -2;
    }

}
