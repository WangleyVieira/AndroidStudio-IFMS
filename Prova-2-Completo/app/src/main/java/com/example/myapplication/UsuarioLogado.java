package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class UsuarioLogado extends AppCompatActivity {

    private Button buttonSair_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_logado);

        buttonSair_ = findViewById(R.id.buttonSair);

        buttonSair_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();

                Toast.makeText(UsuarioLogado.this, "Saindo do sistema", Toast.LENGTH_LONG).show();

                startActivity( new Intent(UsuarioLogado.this, MainActivity.class));
            }
        });

        new DownloadJSonAssynTask().execute("https://reqres.in/api/users");

    }


    public class DownloadJSonAssynTask extends AsyncTask<String, Void, ArrayList<Pessoa>>{

        //ProgressDialog progressDialog;
        //apresentando uma mensagem de download
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           // progressDialog = ProgressDialog.show(MainActivity.this, "Aguarde", "Fazendo download");

        }

        @Override
        protected ArrayList<Pessoa> doInBackground(String... strings) {
            String urlString = strings[0];
            URL url;

            try {
                url = new URL(urlString);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(120000);
                httpURLConnection.connect();

                InputStream resposta = httpURLConnection.getInputStream();
                String texto = new Scanner(resposta).useDelimiter("\\A").next();

                if (texto != null){
                    ArrayList<Pessoa> pessoaArrayList = getDados(texto);
                    return  pessoaArrayList;
                }
                else{
                    return null;
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        private ArrayList<Pessoa> getDados(String texto) {
            ArrayList<Pessoa> pessoaArrayList = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(texto);

                for (int i = 0; jsonArray.length() > i; i++){
                    JSONObject pessoaObject = jsonArray.getJSONObject(i);

                    Pessoa pessoa = new Pessoa();
                    //pessoa.setPer_page(pessoaObject.getJSONObject().getString("page"));
                    pessoa.setId(pessoaObject.getString("id"));
                    pessoa.setEmail(pessoaObject.getString("email"));
                    pessoa.setFirst_name(pessoaObject.getString("first_name"));
                    pessoa.setLast_name(pessoaObject.getString("last_name"));
                    pessoa.setAvatar(pessoaObject.getString("avatar"));

                    //adicionar restante dos atributos
                    pessoaArrayList.add(pessoa);

                }
            }
            catch (JSONException exception){
                exception.printStackTrace();
            }
            return pessoaArrayList;
        }

        //retira a mensagem da tela
        @Override
        protected void onPostExecute(ArrayList<Pessoa> pessoas) {
            super.onPostExecute(pessoas);
        }
    }

}