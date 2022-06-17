package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {

    private EditText primeiroValor_, segundoValor_, terceiroValor_;
    private TextView resultadoGastos_, primeiroResultado_, segundoResultado_, terceiroResultado_, primeiraComparacao_, segundaComparacao_, terceiraComparacao_;
    private Button buttonCalcular_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        primeiraComparacao_ = findViewById(R.id.primeiraComparacao);
        segundaComparacao_ = findViewById(R.id.segundaComparacao);
        terceiraComparacao_ = findViewById(R.id.terceiraComparacao);

        primeiroValor_ = findViewById(R.id.primeiroValor);
        segundoValor_ = findViewById(R.id.segundoValor);
        terceiroValor_ = findViewById(R.id.terceiroValor);
        buttonCalcular_ = findViewById(R.id.buttonCalcular);
        resultadoGastos_ = findViewById(R.id.resultadoGastos);

        primeiroResultado_ = findViewById(R.id.primeiroResultado);
        segundoResultado_ = findViewById(R.id.segundoResultado);
        terceiroResultado_ = findViewById(R.id.terceiroResultado);


        buttonCalcular_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(primeiroValor_.getText().toString().isEmpty()){
                    primeiroValor_.setError("Necessário informar valor");
                    return;
                }

                if(segundoValor_.getText().toString().isEmpty()){
                    segundoValor_.setError("Necessário informar valor");
                    return;
                }

                if(terceiroValor_.getText().toString().isEmpty()){
                    terceiroValor_.setError("Necessário informar valor");
                    return;
                }


                double primeiroCampo, segundoCampo, terceiroCampo, calculFinal, media,
                        resPrimeiroCampo, resSegundoCampo, resTerceiroCampo, resultPrimeiro, resultSegundo, resultTerceiro,
                        menorValor, maiorValor;

                 primeiroCampo = Double.parseDouble(primeiroValor_.getText().toString());
                 segundoCampo = Double.parseDouble(segundoValor_.getText().toString());
                 terceiroCampo = Double.parseDouble(terceiroValor_.getText().toString());

                 calculFinal = primeiroCampo + segundoCampo + terceiroCampo;
                 media = (primeiroCampo + segundoCampo + terceiroCampo) / 3;

                //realizando a soma total dos campos informados
                resultadoGastos_.setText(String.format("%.2f", calculFinal));

                //divisao entre o valor informado pelo gasto total em porcentagem
                 resPrimeiroCampo = (primeiroCampo / calculFinal) * 100;
                 resSegundoCampo = (segundoCampo / calculFinal) * 100;
                 resTerceiroCampo = (terceiroCampo / calculFinal) * 100;

                //exibindo os resultados na tela em porcentagem
                primeiroResultado_.setText(String.format("Naruto %.2f%%", resPrimeiroCampo));
                segundoResultado_.setText(String.format("Madara %.2f%%", resSegundoCampo));
                terceiroResultado_.setText(String.format("Kakashi %.2f%%", resTerceiroCampo));


                //realizando a comparação entre os valores informados
                if (primeiroCampo < media){
                    resultPrimeiro = primeiroCampo - media;
                    primeiraComparacao_.setText(String.format("Naruto -> %.2f Kakashi", resultPrimeiro));
                }
                else{
                    if(primeiroCampo < segundoCampo){
                        resultPrimeiro = primeiroCampo - media;
                        primeiraComparacao_.setText(String.format("Naruto -> %.2f Madara", resultPrimeiro));
                    }
                    else{
                        primeiraComparacao_.setText("tá de boas");
                    }
                    return;
                }

                if(segundoCampo < media){
                    resultSegundo = segundoCampo - media;
                    segundaComparacao_.setText(String.format("Madara -> %.2f Kakashi", resultSegundo));
                }
                else{
                    resultSegundo = segundoCampo - media;
                    segundaComparacao_.setText(String.format("Madara -> %.2f Kakashi", resultSegundo));
                    return;
                }
                /*
                if (terceiroCampo > media && terceiroCampo > segundoCampo){
                    resTerceiroCampo = terceiroCampo - media;
                    terceiraComparacao_.setText(String.format("Kakashi -> %.2f Madara", resTerceiroCampo));
                }
                else{
                    terceiraComparacao_.setText("só vai recebr dos outros");
                    return;
                }*/
            }
        });

    }
}