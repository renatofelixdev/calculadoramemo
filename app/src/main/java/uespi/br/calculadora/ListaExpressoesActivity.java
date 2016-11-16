package uespi.br.calculadora;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENATO on 01/11/2016.
 */

public class ListaExpressoesActivity  extends AppCompatActivity {

    private ExpressaoDAO dao;

    @Override
    protected  void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_expressoes);

        Bundle extras = getIntent().getExtras();
        boolean deletes = extras.getBoolean("delete");
        boolean updates = extras.getBoolean("update");
        if(deletes)
            Toast.makeText(this, "Expressão excluída com sucesso!", Toast.LENGTH_SHORT).show();
        if(updates)
            Toast.makeText(this, "Expressão atualizada com sucesso!", Toast.LENGTH_SHORT).show();

        dao = new  ExpressaoDAO(this);
        dao.open();
        List<Expressao> exprs = dao.getAll();
        ListView lv = (ListView) findViewById(R.id.listView);
        if(lv != null){
            ListScreenAdapter  adapter = new ListScreenAdapter(this, exprs);
            lv.setAdapter(adapter);

        }
        dao.close ();

        Button btCalculadora = (Button) findViewById(R.id.btCalculadora);
        final Intent intent = new Intent(this, MainActivity.class);
        btCalculadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        super.onBackPressed();  // optional depending on your needs
    }
}

class ListScreenAdapter extends ArrayAdapter<Expressao>{
    private List<Expressao> _items;
    private Context _context;

    public ListScreenAdapter(Context context, List<Expressao> items)
    {
        super(context, R.layout.listmodelo, items);

        _context = context;
        _items = items;
    }

    private ListScreenAdapter(Context context, int textViewResourceId)
    {
        super(context, textViewResourceId);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.listmodelo, null);
        }
        final String o = _items.get(position).getExpressao();
        if (o != null) {
            TextView tt = (TextView) v.findViewById(R.id.textViewList);

            if (tt != null){
                tt.setText(o);
            }
        }

        final ImageButton remove = (ImageButton) v.findViewById(R.id.remove);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExpressaoDAO dao = new  ExpressaoDAO(_context);
                dao.open();
                if(dao.delete(_items.get(position)) == -1){
                    Toast.makeText(_context, "Erro ao tentar excluir a expressão:\n" + o, Toast.LENGTH_SHORT).show();
                }else{
                    dao.close();
                    Intent intent = new Intent (_context, ListaExpressoesActivity.class);
                    intent.putExtra("delete", true);
                    intent.putExtra("update", false);
                    _context.startActivity(intent);
                }
                dao.close();
            }
        });

        final ImageButton info = (ImageButton) v.findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(_context);
                dialog.setContentView(R.layout.info_expressao);

                TextView tvExprss = (TextView) dialog.findViewById(R.id.tvExprss);
                TextView resultado = (TextView) dialog.findViewById(R.id.tvResultado);
                TextView maiorPar = (TextView) dialog.findViewById(R.id.tvMaiorPar);
                TextView menorImpar = (TextView) dialog.findViewById(R.id.tvMenorImpar);
                TextView quantidade = (TextView) dialog.findViewById(R.id.tvQtdNum);

                tvExprss.setText(o);

                ArrayList<Double> valores = new ArrayList<Double>();
                valores = Calcula.quebraExpressao(o, true);

                resultado.setText("Resultado:\n"+Calcula.resultado(o));
                maiorPar.setText("Maior número par:\n"+Calcula.maiorNumeroPar(valores));
                menorImpar.setText("Menor número ímpar:\n"+Calcula.menorNumeroImpar(valores));
                quantidade.setText("Quantidade de números:\n"+valores.size());

                dialog.show();


            }
        });

        final ImageButton edit = (ImageButton) v.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(_context);
                dialog.setContentView(R.layout.modal_edita);

                final EditText exp = (EditText)  dialog.findViewById(R.id.editExpres);
                exp.setText(o.subSequence(0, o.length()));

                Button btCancela = (Button) dialog.findViewById(R.id.btCancela);
                Button btEdita = (Button) dialog.findViewById(R.id.btEdita);

                btCancela.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btEdita.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String token = "";
                        int estado = 0;
                        int ultimoEstado = 0;
                        String expressao = exp.getText().toString() + "\n";

                        for(int i = 0; i < expressao.length(); i++){
                            estado = Automato.estado(estado, expressao.charAt(i));
                            //Log.d("char ", expressao.charAt(i)+"");
                            if(estado == -2){
                                Toast.makeText(_context, "Expressão inválida!", Toast.LENGTH_SHORT).show();
                                break;
                            }else if(estado == -1){
                                Toast.makeText(_context, "Expressão inválida!", Toast.LENGTH_SHORT).show();
                                break;
                            }else if(estado == -3){
                                break;
                            }else{
                                token += expressao.charAt(i);
                                ultimoEstado = estado;
                            }
                        }

                        if(estado == -3 && token.equals(expressao.replace("\n", ""))){
                            ExpressaoDAO dao = new  ExpressaoDAO(_context);
                            dao.open();

                            if(dao.update(_items.get(position), token) == -1){
                                Toast.makeText(_context, "Erro ao tentar editar a expressão:\n" + o, Toast.LENGTH_SHORT).show();
                            }else{
                                dao.close();
                                Intent intent = new Intent (_context, ListaExpressoesActivity.class);
                                intent.putExtra("update", true);
                                intent.putExtra("delete", false);
                                _context.startActivity(intent);
                            }
                            dao.close();
                        }else{
                            Toast.makeText(_context, "Expressão inválida!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.show();
            }
        });

        final ImageButton uploadCalc = (ImageButton) v.findViewById(R.id.upload);
        uploadCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(_context, MainActivity.class);
                intent.putExtra("expressao", o);
                _context.startActivity(intent);
            }
        });

        return v;
    }
}
