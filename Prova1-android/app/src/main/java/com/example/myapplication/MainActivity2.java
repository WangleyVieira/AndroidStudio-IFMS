package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private EditText editTextNum1_;
    private EditText editTextNum2_;
    private TextView textView_;
    private Button buttonCalcular_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editTextNum1_ = findViewById(R.id.editTextNum1);
        editTextNum2_ = findViewById(R.id.editTextNum2);
        textView_ = findViewById(R.id.textViewResult);
        buttonCalcular_ = findViewById(R.id.buttonCalcular);

        buttonCalcular_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextNum1_.getText().toString().isEmpty()){
                    editTextNum1_.setError("Preencha o campo !");
                } else if (editTextNum2_.getText().toString().isEmpty()){
                    editTextNum2_.setError("Preencha o campo !");
                }
                else{
                    double n1 = Double.parseDouble(editTextNum1_.getText().toString());
                    double n2 = Double.parseDouble(editTextNum2_.getText().toString());

                    if (n1 > (n2 * 0.70)){
                        textView_.setText("Compensa abastecer com Gasolina");
                    }
                    else{
                        if (n1  == (n2 *0.70)){
                            textView_.setText("Alcool e Gasolina estão no mesmo preço");
                        }
                        else{
                            textView_.setText("Compensa abastecer com Alcool");
                        }
                    }

                }
            }
        });

    }
}