package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    EditText valorCalculado_;
    Button buttonConvCF_, buttonConvFC_, buttonConvGeral_;
    TextView resultadoCF_, resultadoFC_, resultadoCK_, resultadoKC_, resultadoFK_, resultadoKF_;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        valorCalculado_ = findViewById(R.id.valorCalculado);
        buttonConvGeral_ = findViewById(R.id.buttonConvGeral);
        resultadoCF_ = findViewById(R.id.resultadoCF);
        resultadoFC_ = findViewById(R.id.resultadoFC);
        resultadoCK_ = findViewById(R.id.resultadoCK);
        resultadoKC_ = findViewById(R.id.resultadoKC);
        resultadoFK_ = findViewById(R.id.resultadoFK);
        resultadoKF_ = findViewById(R.id.resultadoKF);


        buttonConvGeral_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (valorCalculado_.getText().toString().isEmpty()){
                    valorCalculado_.setError("Preencha o campo");
                }
                else{
                    //calculo celsius para fahreinheit
                    double tempC = Double.parseDouble(valorCalculado_.getText().toString());
                    double resultadoCF = tempC * 1.8 + 32;

                    resultadoCF_.setText(String.format("%.2f", resultadoCF));

                    //calculo de fahrenheit para celsius
                    double resultadoFC = (tempC - 32) / 1.8;

                    resultadoFC_.setText(String.format("%.2f", resultadoFC));

                    //calcula de celsius para kelvin
                    double resultadoCK = (tempC + 273.15);

                    resultadoCK_.setText(String.format("%.2f", resultadoCK));

                    //calcula de kelvin para celsius
                    double resultadoKC = tempC - 273.15;

                    resultadoKC_.setText(String.format("%.2f", resultadoKC));

                    //calcula de fahrenheit para kelvin
                    double resultadoFK = (tempC + 459.67) / 1.8;

                    resultadoFK_.setText(String.format("%.2f", resultadoFK));

                    //calcula de kelvin para fahrenheit
                    double resultadoKF = tempC * 1.8 - 459.67;

                    resultadoKF_.setText(String.format("%.2f", resultadoKF));
                }

            }
        });

    }
}