package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextEmail_, editTextSenha_;
    TextView textViewEsqueciSenha_, textViewCadastrarUser_;
    Button buttonLogin_;

    FirebaseAuth mAuth;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail_ = findViewById(R.id.editTextEmailAddress);
        editTextSenha_ = findViewById(R.id.editTextPassword);

        textViewEsqueciSenha_ = findViewById(R.id.textViewEsqueciSenha);
        textViewCadastrarUser_ = findViewById(R.id.textViewCadastrarUser);

        buttonLogin_ = findViewById(R.id.buttonLogarUsuario);

        mAuth = FirebaseAuth.getInstance();

        textViewCadastrarUser_.setOnClickListener(this);
        textViewEsqueciSenha_.setOnClickListener(this);
        buttonLogin_.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonLogarUsuario:// logando no sistema e chama a função para realizar as validações
                LogarSistema();
                break;
            case R.id.textViewEsqueciSenha: //redireciona o usuário para tela de esqueci senha
                intent = new Intent(MainActivity.this, EsqueciSenha.class);
                startActivity(intent);
                break;
            case R.id.textViewCadastrarUser: //redireciona o usário para para tela para realizar um novo cadastro
                intent = new Intent(MainActivity.this, CadastrarUsuario.class);
                startActivity(intent);
                break;
        }
    }

    public void LogarSistema(){
        String email = editTextEmail_.getText().toString();
        String senha = editTextSenha_.getText().toString();

        if(email.equals("") || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail_.setError("Preencha corretamente!");
            editTextEmail_.requestFocus();
            return;
        }

        if(senha.equals("")){
            editTextSenha_.setError("Preencha corretamente!");
            editTextSenha_.requestFocus();
            return;
        }


        mAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    //realiza a verificação via email
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isEmailVerified()){
                        //se o email foi verificado através do link encaminhado, redireciona para tela login
                        intent = new Intent(MainActivity.this, UsuarioLogado.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Verifica seu email, está incorreto!", Toast.LENGTH_LONG).show();
                        user.sendEmailVerification();
                    }

                }
                else{ //login incorreto
                    Toast.makeText(MainActivity.this, "Erro ao logar", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}