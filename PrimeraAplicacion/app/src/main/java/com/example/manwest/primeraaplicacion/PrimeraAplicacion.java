package com.example.manwest.primeraaplicacion;

import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class PrimeraAplicacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera_aplicacion);
    }
    public void onClick(View view){
        Intent intent = new Intent(this, SegundaActividad.class);
        startActivity(intent);
        finish();
    }

}
