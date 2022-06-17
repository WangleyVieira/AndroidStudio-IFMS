package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    private static final String[] SELECAO = new String[] {"Calculo Gasolina x Etanol", "Conversões de temperaturas", "Divisão de compras entre três pessoas", "Cálculo fuso horário", "Contas de bar"};
    private ArrayAdapter<String> meuAdaptor;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        meuAdaptor = new ArrayAdapter<>(
            this, android.R.layout.simple_list_item_1, SELECAO
        );
        setListAdapter(meuAdaptor);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if(position == 0){ //calculo da gasolina x etanol
            i = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(i);
        }
        if(position == 1){//Conversões de temperaturas
            i = new Intent(MainActivity.this, MainActivity3.class);
            startActivity(i);
        }
        if(position == 2){ //Cálculo de divisao entre três pessoas
            i = new Intent(MainActivity.this, MainActivity4.class);
            startActivity(i);
        }
        if(position == 3){ //Cálculo fuso horário
            i = new Intent(MainActivity.this, MainActivity5.class);
            startActivity(i);
        }
        if(position == 4){ //Cálculo gorjetas
            i = new Intent(MainActivity.this, MainActivity6.class);
            startActivity(i);
        }
    }
}