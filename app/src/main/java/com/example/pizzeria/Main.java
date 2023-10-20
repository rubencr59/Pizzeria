package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main extends AppCompatActivity implements View.OnClickListener {

    TextView mensajeInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button botonSalir = findViewById(R.id.btnSalir);
        botonSalir.setOnClickListener(this);
        mensajeInicio = findViewById(R.id.txtMensajeInicioSesion);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSalir){
            Intent intent = new Intent(this, ConfirmarSalir.class);
            startActivity(intent);
        }
    }
}