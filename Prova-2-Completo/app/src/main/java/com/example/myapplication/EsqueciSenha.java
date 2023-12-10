package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class EsqueciSenha extends AppCompatActivity {

    private EditText editTextEmailRecuperar_;
    private Button buttonRecuperarSenha_;
    FirebaseAuth mAuthRecuperacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_senha);

        editTextEmailRecuperar_ = findViewById(R.id.editTextEmailRecuperar);
        buttonRecuperarSenha_ = findViewById(R.id.buttonRecuperarSenha);

        mAuthRecuperacao = FirebaseAuth.getInstance();

        buttonRecuperarSenha_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmailRecuperar_.getText().toString();

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || email.equals("")){
                    editTextEmailRecuperar_.setError("Email incorreto");
                    return;
                }

                mAuthRecuperacao.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        //realiza uma verificação e então encaminhado um link para o email informado e depois redirecionado para
                        //tela de login
                        if (task.isSuccessful()){
                            Toast.makeText(EsqueciSenha.this, "Email encaminhado, favor verificar!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(EsqueciSenha.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(EsqueciSenha.this, "Erro na validação!!!", Toast.LENGTH_LONG).show();
                        }

                    }
                });


            }
        });
    }
}