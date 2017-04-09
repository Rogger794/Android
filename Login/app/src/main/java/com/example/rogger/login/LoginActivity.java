package com.example.rogger.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText usuario;
    private EditText contra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario=(EditText)findViewById(R.id.usuario);
        contra=(EditText)findViewById(R.id.contraseña);
    }

    public void onClick(View view){
        Intent intent = new Intent(this, Muestra.class);
        intent.putExtra("nombre",usuario.getText().toString());
        intent.putExtra("pass",contra.getText().toString());
        startActivity(intent);
        finish();
    }

}
