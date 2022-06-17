package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity6 extends AppCompatActivity {
    Button buttonLimparDados_, buttonDiminuir_,buttonAumentar_, buttonCalcularValor_;
    TextView acrescimoNum_, totalConta_, tvGorjeta_, totalPessoas_;
    EditText editTextGorjeta_, valorInserido_;
    RadioGroup radioGroup_;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        buttonAumentar_ = findViewById(R.id.buttonAumentarValor);
        buttonDiminuir_ = findViewById(R.id.buttonDiminuirValor);
        acrescimoNum_ = findViewById(R.id.acrescimoNum);
        buttonLimparDados_ = findViewById(R.id.buttonLimparDados1);
        radioGroup_ = findViewById(R.id.radioGroup2);
        valorInserido_ = findViewById(R.id.valorInserido);
        totalConta_ = findViewById(R.id.totalConta);
        totalPessoas_ = findViewById(R.id.totalPessoas);
        editTextGorjeta_ = findViewById(R.id.editTextNumber);
        buttonCalcularValor_ = findViewById(R.id.buttonCalcularValor);
        tvGorjeta_ = findViewById(R.id.gorjeta);

        buttonAumentar_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ajustarMais = acrescimoNum_.getText().toString();
                int valorMais = Integer.parseInt(acrescimoNum_.getText().toString());

                int n = 0;
                if (ajustarMais == null || valorMais >= 3){
                    Toast.makeText(MainActivity6.this, "Valor não pode ser acima de 4", Toast.LENGTH_LONG).show();
                }
                else{
                    n = Integer.parseInt(ajustarMais) + 1;
                }
                acrescimoNum_.setText(String.valueOf(n));
            }
        });

        buttonDiminuir_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ajustarMenos = acrescimoNum_.getText().toString();
                int valorMenos = Integer.parseInt(acrescimoNum_.getText().toString());

                int n = 0;
                if (ajustarMenos == null || valorMenos <=0 ){
                    Toast.makeText(MainActivity6.this, "Valor não pode ser menor que 0", Toast.LENGTH_LONG).show();
                }
                else{
                    n = Integer.parseInt(ajustarMenos) - 1;
                }
                acrescimoNum_.setText(String.valueOf(n));
            }
        });

        buttonLimparDados_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valorInserido_.setText("");
                radioGroup_.clearCheck();
                acrescimoNum_.setText("0");
                valorInserido_.setText("");
                totalConta_.setText("");
                editTextGorjeta_.setText("");
                tvGorjeta_.setText("");
                totalPessoas_.setText("");
            }
        });

        buttonCalcularValor_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String totalPagar = valorInserido_.getText().toString();
                String numeroPessoa = acrescimoNum_.getText().toString();
                String gorjeta = editTextGorjeta_.getText().toString();

                if (!totalPagar.equals("") && !numeroPessoa.equals("")){
                    double numTotalPagar = Double.parseDouble(totalPagar);
                    int numPessoa = Integer.parseInt(numeroPessoa);
                    double totalGorjeta = 0;
                    double total, totalPorPessoa;
                    if (numPessoa!=0){
                        switch (radioGroup_.getCheckedRadioButtonId()){
                            case R.id.radioButton5:
                                totalGorjeta = numTotalPagar * 0.15;
                                break;
                            case R.id.radioButton6:
                                totalGorjeta = numTotalPagar * 0.2;
                                break;
                            case R.id.radioButton7:
                                if (!gorjeta.equals("")){
                                    double outroValor = Double.parseDouble(gorjeta);
                                    totalGorjeta = numTotalPagar * (outroValor / 100);
                                }
                                else{
                                    Toast.makeText(MainActivity6.this, "Necessário informar valor gorjeta", Toast.LENGTH_LONG).show();
                                }
                                break;
                        }
                        total = numTotalPagar + totalGorjeta;
                        totalPorPessoa = total / numPessoa;

                        totalConta_.setText(String.format("%.2f", total));
                        tvGorjeta_.setText(String.format("%.2f", totalGorjeta));
                        totalPessoas_.setText(String.format("%.2f", totalPorPessoa));

                    }
                    else{
                        Toast.makeText(MainActivity6.this, "Selecione valor gorjeta", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity6.this, "Selecione uma opção", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}