package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConfirmarSalir extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_salir);
        Button botonConfirmarSalir = findViewById(R.id.btnSi);
        Button botonCancelarSalir = findViewById(R.id.btnNo);
        botonConfirmarSalir.setOnClickListener(this);
        botonCancelarSalir.setOnClickListener(this);    }



    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnSi){
            finishAffinity();
        }else if (v.getId() == R.id.btnNo){
            Intent intent = new Intent(this, Main.class);
            startActivity(intent);
            finish();
        }

    }
}

