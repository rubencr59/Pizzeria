package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Pedidos extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnPizzaPred){
            Intent intent = new Intent(this, NuestrasPizzas.class);
            startActivity(intent);
            finish();
        }
    }
}