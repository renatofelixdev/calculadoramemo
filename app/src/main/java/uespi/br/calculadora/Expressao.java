package uespi.br.calculadora;

/**
 * Created by RENATO on 01/11/2016.
 */

public class Expressao {
    private long id;
    private String expressao;


    public String getExpressao() {
        return expressao;
    }

    public void setExpressao(String expressao) {
        this.expressao = expressao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return id + " " + expressao;
    }
}
