package com.example.rogger.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Muestra extends AppCompatActivity{
    private String nombre;
    private String pass;
    private TextView mostrar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        nombre=getIntent().getStringExtra("nombre");
        pass=getIntent().getStringExtra("pass");
        mostrar=(TextView) findViewById(R.id.mostrar);
        mostrarAlerta();
    }

    public void mostrarAlerta() {
        mostrar.setText("Bienvenido " +nombre+" a Codelearn\nRegistrado con el número:\n"+pass);
    }
    public void llamar(View view){
        Intent intent2=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+pass));
        startActivity(intent2);
        finish();
    }
}

