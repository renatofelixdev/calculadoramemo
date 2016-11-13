package uespi.br.calculadora;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ExpressaoDAO dao;
    private final String[] visor = {""};
    private HorizontalScrollView hsvLayout;
    private String position = "right";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        String expressao = "";
        if(extras != null)
            expressao = extras.getString("expressao");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        final boolean[] controlePonto = {false};

        hsvLayout = (HorizontalScrollView) findViewById(R.id.hsvLayout);

        final TextView tvResultado = (TextView) findViewById(R.id.tvResultado);
        final TextView tvVisor = (TextView) findViewById(R.id.tvVisor);

        if(expressao != null) {
            tvVisor.setText(expressao);
            visor[0] = expressao;
        }

        Button bt0 = (Button) findViewById(R.id.bt0);
        Button bt1 = (Button) findViewById(R.id.bt1);
        Button bt2 = (Button) findViewById(R.id.bt2);
        Button bt3 = (Button) findViewById(R.id.bt3);
        Button bt4 = (Button) findViewById(R.id.bt4);
        Button bt5 = (Button) findViewById(R.id.bt5);
        Button bt6 = (Button) findViewById(R.id.bt6);
        Button bt7 = (Button) findViewById(R.id.bt7);
        Button bt8 = (Button) findViewById(R.id.bt8);
        Button bt9 = (Button) findViewById(R.id.bt9);

        Button btPonto = (Button) findViewById(R.id.btPonto);

        Button btMais = (Button) findViewById(R.id.btMais);
        Button btMenos = (Button) findViewById(R.id.btMenos);
        Button btVezes = (Button) findViewById(R.id.btVezes);
        Button btDivide = (Button) findViewById(R.id.btDivide);
        Button btInverte = (Button) findViewById(R.id.btInverte);

        Button btC = (Button) findViewById(R.id.btC);
        Button btBack = (Button) findViewById(R.id.btBack);

        Button btIgual = (Button) findViewById(R.id.btIgual);

        bt0.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            visor[0] += '0';
            tvVisor.setText(visor[0]);
                scrollVisorPosition(position);
            }
        });

        bt1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            visor[0] += '1';
            tvVisor.setText(visor[0]);
                scrollVisorPosition(position);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            visor[0] += '2';
            tvVisor.setText(visor[0]);
                scrollVisorPosition(position);
            }
        });

        bt3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            visor[0] += '3';
            tvVisor.setText(visor[0]);
                scrollVisorPosition(position);
            }
        });

        bt4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            visor[0] += '4';
            tvVisor.setText(visor[0]);
                scrollVisorPosition(position);
            }
        });

        bt5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            visor[0] += '5';
            tvVisor.setText(visor[0]);
                scrollVisorPosition(position);
            }
        });

        bt6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            visor[0] += '6';
            tvVisor.setText(visor[0]);
                scrollVisorPosition(position);
            }
        });

        bt7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            visor[0] += '7';
            tvVisor.setText(visor[0]);
                scrollVisorPosition(position);
            }
        });

        bt8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            visor[0] += '8';
            tvVisor.setText(visor[0]);
                scrollVisorPosition(position);
            }
        });

        bt9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            visor[0] += '9';
            tvVisor.setText(visor[0]);
                scrollVisorPosition(position);
            }
        });

        btMais.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            char ultimo = 0;
            if(!visor[0].equals("")) {
                ultimo = visor[0].charAt(visor[0].length() - 1);

                if (ultimo == '+' || ultimo == '-' || ultimo == '*' || ultimo == '÷'){
                    controlePonto[0] = false;
                    visor[0] = visor[0].substring(0, visor[0].length()-1) + '+';
                    tvVisor.setText(visor[0]);
                    scrollVisorPosition(position);
                }else if(ultimo == '.'){
                    controlePonto[0] = false;
                    visor[0] += "0+";
                    tvVisor.setText(visor[0]);
                    scrollVisorPosition(position);
                }else{
                    controlePonto[0] = false;
                    visor[0] += '+';
                    tvVisor.setText(visor[0]);
                    scrollVisorPosition(position);
                }
            }
            }
        });

        btMenos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            char ultimo = 0;
            if(!visor[0].equals("")) {
                ultimo = visor[0].charAt(visor[0].length() - 1);

                if (ultimo == '+' || ultimo == '-' || ultimo == '*' || ultimo == '÷'){
                    controlePonto[0] = false;
                    visor[0] = visor[0].substring(0, visor[0].length()-1) + '-';
                    tvVisor.setText(visor[0]);
                    scrollVisorPosition(position);
                }else if(ultimo == '.'){
                    controlePonto[0] = false;
                    visor[0] += "0-";
                    tvVisor.setText(visor[0]);
                    scrollVisorPosition(position);
                }else{
                    controlePonto[0] = false;
                    visor[0] += '-';
                    tvVisor.setText(visor[0]);
                    scrollVisorPosition(position);
                }
            }else{
                controlePonto[0] = false;
                visor[0] += '-';
                tvVisor.setText(visor[0]);
                scrollVisorPosition(position);
            }
            }
        });

        btVezes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            char ultimo = 0;
            if(!visor[0].equals("")) {
                ultimo = visor[0].charAt(visor[0].length() - 1);

                if (ultimo == '+' || ultimo == '-' || ultimo == '*' || ultimo == '÷'){
                    controlePonto[0] = false;
                    visor[0] = visor[0].substring(0, visor[0].length()-1) + '*';
                    tvVisor.setText(visor[0]);
                    scrollVisorPosition(position);
                }else if(ultimo == '.'){
                    controlePonto[0] = false;
                    visor[0] += "0*";
                    tvVisor.setText(visor[0]);
                    scrollVisorPosition(position);
                }else{
                    controlePonto[0] = false;
                    visor[0] += '*';
                    tvVisor.setText(visor[0]);
                    scrollVisorPosition(position);
                }
            }
            }
        });

        btDivide.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            char ultimo = 0;
            if(!visor[0].equals("")) {
                ultimo = visor[0].charAt(visor[0].length() - 1);

                if (ultimo == '+' || ultimo == '-' || ultimo == '*' || ultimo == '÷'){
                    controlePonto[0] = false;
                    visor[0] = visor[0].substring(0, visor[0].length()-1) + '÷';
                    tvVisor.setText(visor[0]);
                    scrollVisorPosition(position);
                }else if(ultimo == '.'){
                    controlePonto[0] = false;
                    visor[0] += "0÷";
                    tvVisor.setText(visor[0]);
                    scrollVisorPosition(position);
                }else{
                    controlePonto[0] = false;
                    visor[0] += '÷';
                    tvVisor.setText(visor[0]);
                    scrollVisorPosition(position);
                }
            }
            }
        });

        btPonto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            char ultimo = 0;
            if(visor[0].equals("")) {
                controlePonto[0] = true;
                visor[0] += "0.";
                tvVisor.setText(visor[0]);
                scrollVisorPosition(position);
            }else{
                ultimo = visor[0].charAt(visor[0].length() - 1);

                if (ultimo == '+' || ultimo == '-' || ultimo == '*' || ultimo == '÷') {
                    visor[0] += "0.";
                    tvVisor.setText(visor[0]);
                    scrollVisorPosition(position);
                }else{
                    if(!controlePonto[0]){
                        controlePonto[0] = true;
                        visor[0] += '.';
                        tvVisor.setText(visor[0]);
                        scrollVisorPosition(position);
                    }
                }
            }
            }
        });

        btC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            controlePonto[0] = false;
            visor[0] = "";
            tvVisor.setText(visor[0]);
            tvResultado.setText("");
                scrollVisorPosition(position);
            }
        });

        btBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            if(!visor[0].equals("")){
                if(visor[0].charAt(visor[0].length()-1) == '.') {
                    controlePonto[0] = false;
                }
                visor[0] = visor[0].substring(0, visor[0].length()-1);
                tvVisor.setText(visor[0]);
                scrollVisorPosition(position);
            }
            }
        });

        btInverte.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            if(!tvResultado.getText().toString().equals("")){
                tvResultado.setText(String.valueOf((-1) * Double.parseDouble(tvResultado.getText().toString())));
            }
            }
        });

        btIgual.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                char ultimo = 0;
                if(!visor[0].equals("")) {
                    ultimo = visor[0].charAt(visor[0].length() - 1);
                    boolean alterou = false;
                    if(ultimo == '+' || ultimo == '-') {
                        visor[0] += '0';
                        alterou = true;
                    }else if(ultimo == '*' || ultimo == '÷'){
                        visor[0]+='1';
                        alterou = true;
                    }
                    tvResultado.setText(Calcula.resultado(visor[0]));
                    if(alterou)
                        visor[0] = visor[0].substring(0, visor[0].length()-1);
                    scrollVisorPosition(position);
                }
            }
        });

        tvResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvVisor.setText(tvResultado.getText());
                visor[0] = tvResultado.getText().toString();
                tvResultado.setText("");
            }
        });
    }

    private void scrollVisorPosition(String p){
        if(p.equals("right")){
            hsvLayout.post(new Runnable() {
                public void run() {
                    hsvLayout.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_salva) {
            if(!visor[0].equals("")){
                char ultimo = 0;
                ultimo = visor[0].charAt(visor[0].length() - 1);
                if(ultimo == '+' || ultimo == '-')
                    visor[0]+='0';
                else if(ultimo == '*' || ultimo == '÷')
                    visor[0]+='1';

                dao = new  ExpressaoDAO(this);
                dao.open();
                if(dao.create(visor[0]) == -1){
                    Toast.makeText(getApplicationContext(), "Erro ao salvar expressão!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Expressão salva com sucesso!", Toast.LENGTH_LONG).show();
                }
                dao.close();
            }else{
                Toast.makeText(getApplicationContext(), "Digite uma expressão!", Toast.LENGTH_LONG).show();
            }
        } else if (id == R.id.nav_lista) {
            Intent  intent = new Intent(this, ListaExpressoesActivity.class);
            intent.putExtra("delete", false);
            intent.putExtra("update", false);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            ArrayList<Double> valores = new ArrayList<Double>();
            valores = Calcula.quebraExpressao(visor[0]);
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Calculadora Memo \nExpressão: "+visor[0]+
                                                    "\nResultado: "+Calcula.resultado(visor[0])+
                                                    "\nMaior número par: "+Calcula.maiorNumeroPar(valores)+
                                                    "\nMenor número ímpar: "+Calcula.menorNumeroImpar(valores)+
                                                    "\nQuantidade de números: "+valores.size());
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, "COMPARTILHAR EXPRESSÃO"));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
