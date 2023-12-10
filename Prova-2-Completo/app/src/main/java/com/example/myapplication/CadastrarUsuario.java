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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CadastrarUsuario extends AppCompatActivity {

    private EditText editTextNome_, editTextEmailCadastrar_, editTextSenhaCadastrar_;
    private Button buttonCadastrar_;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        mAuth = FirebaseAuth.getInstance();

        editTextNome_ = findViewById(R.id.editTextNome);
        editTextEmailCadastrar_ = findViewById(R.id.editTextEmailCadastrar);
        editTextSenhaCadastrar_ = findViewById(R.id.editTextSenhaCadastrarUsuario);
        buttonCadastrar_ = findViewById(R.id.buttonCadastrarUsuario);

        buttonCadastrar_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criarUsuario(editTextEmailCadastrar_.getText().toString(), editTextSenhaCadastrar_.getText().toString());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    /* private void updateUI(FirebaseUser currentUser) {
    } */


    private void criarUsuario(String email, String senha){
        //validação do campo nome para verificar se os dados estão preenchidos corretamente
        if (editTextNome_.getText().toString().equals("")){
            editTextNome_.setError("Preencha corretamente meu chapa");
            editTextNome_.requestFocus();
            return;
        }
        //validação do email
        if (email.equals("") || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmailCadastrar_.setError("Preencha corretamente meu chapa");
            editTextEmailCadastrar_.requestFocus();
            return;
        }
        //se estiver vazio
        if (senha.equals("")){
            editTextNome_.setError("Preencha corretamente meu chapa");
            editTextNome_.requestFocus();
            return;
        }

        //cria usuario com email e senha
        mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //verificação dos dados, caso os campos estejam ok, realiza o cadastro
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Usuário criado com sucesso!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(CadastrarUsuario.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Erro ao criar uma conta de usuário", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}