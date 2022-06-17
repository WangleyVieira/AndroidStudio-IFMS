package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity5 extends AppCompatActivity {

    EditText campoHoraOrigem_, campoMinOrigem_, acrescimoNum_;
    TextView campoHoraDestino_, campoMinDest_;
    Button buttonLimparDados_, buttonCalcularDest_, buttonDiminuir_,buttonAumentar_ ;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        campoHoraOrigem_ = findViewById(R.id.campoHoraOrigem);
        campoMinOrigem_ = findViewById(R.id.campoMinOrigem);
        acrescimoNum_ = findViewById(R.id.acrescimoNum);
        radioGroup = findViewById(R.id.radioGroup);

        campoHoraDestino_ = findViewById(R.id.campoHoraDestino);
        campoMinDest_ = findViewById(R.id.campoMinDest);
        buttonAumentar_ = findViewById(R.id.buttonAumentarValor);
        buttonDiminuir_ = findViewById(R.id.buttonDiminuirValor);

        buttonLimparDados_ = findViewById(R.id.buttonLimparDados);
        buttonCalcularDest_ = findViewById(R.id.buttonCalcularDest);

        buttonLimparDados_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                campoHoraOrigem_.setText("");
                campoMinOrigem_.setText("");
                radioGroup.clearCheck();
                acrescimoNum_.setText("");
                campoHoraDestino_.setText("");
                campoMinDest_.setText("");
                return;
            }
        });


        buttonAumentar_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fusoHorario = acrescimoNum_.getText().toString();
                int valorFuso = Integer.parseInt(acrescimoNum_.getText().toString());

                int n = 0;
                if (fusoHorario == null || valorFuso >= 12){
                    Toast.makeText(MainActivity5.this, "Valor não pode ser acima de +12", Toast.LENGTH_LONG).show();
                }
                else{
                      n = Integer.parseInt(fusoHorario) + 1;
                }
                acrescimoNum_.setText(String.valueOf(n));
                return;
            }
        });

        buttonDiminuir_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fusoHorario = acrescimoNum_.getText().toString();
                int valorFuso = Integer.parseInt(acrescimoNum_.getText().toString());

                int n = 0;
                if (fusoHorario == null || valorFuso <= -12){
                    Toast.makeText(MainActivity5.this, "Valor não pode ser menor que -12", Toast.LENGTH_LONG).show();
                }
                else{
                    n = Integer.parseInt(fusoHorario) - 1;
                }
                acrescimoNum_.setText(String.valueOf(n));
                return;
            }

        });



        buttonCalcularDest_.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

               switch (radioGroup.getCheckedRadioButtonId()){
                   case R.id.radioButton: //GMT -2 (Horário Padrão de Fernando de Noronha)
                       calcular();
                       break;
                   case R.id.radioButton2: //GMT -3(Horário Padrão de Brasilia)
                       calcular();
                       break;
                   case R.id.radioButton4: // GMT -5(Horário Padrão do Acre)
                       calcular();
                       break;
                   case R.id.radioButton3: //GMT -4(Horário Padrão de Amazonas)
                       calcular();
                       break;
               }

            }
        });

    }

    public void calcular(){

        if (campoHoraOrigem_.getText().toString().isEmpty() || campoMinOrigem_.getText().toString().isEmpty()){
            if (campoHoraOrigem_.getText().toString().isEmpty()){
                Toast.makeText(MainActivity5.this, "Necessario informar horas", Toast.LENGTH_LONG).show();
            }
            if (campoMinOrigem_.getText().toString().isEmpty()){
                Toast.makeText(MainActivity5.this, "Necessario informar minutos", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Integer gethoras = Integer.parseInt(campoHoraOrigem_.getText().toString());
            Integer timezonaDestino = Integer.parseInt(acrescimoNum_.getText().toString());
            Integer timezonaOrigem = Integer.parseInt(campoHoraOrigem_.getText().toString());
            Integer campoHoraOrigem = Integer.parseInt(campoMinOrigem_.getText().toString());
            Integer novaHora = 00;

            if (timezonaOrigem < timezonaDestino){
                //realiza a diferenca entre o destino e a origem ambas com a função Math.obs para então obter valor positivo
                Integer diferenca = Math.abs(timezonaDestino) - Math.abs(timezonaOrigem);

                if (gethoras - diferenca < 0){
                    novaHora = (gethoras - diferenca) + 24;
                }
                else{
                    novaHora = gethoras - diferenca;
                }
            }
            else{
                Integer diferenca = 0;
                for (int i = timezonaOrigem; i < timezonaDestino; i++){
                    diferenca += 1;
                }

                if (gethoras + diferenca > 23){
                    novaHora = (gethoras + diferenca) - 24;
                }
                else{
                    novaHora = gethoras + diferenca;
                }
            }

            campoHoraDestino_.setText(String.valueOf(novaHora));
            campoMinDest_.setText(String.valueOf(campoHoraOrigem));
        }



    }

}