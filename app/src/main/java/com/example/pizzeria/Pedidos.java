package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pizzeria.Clases.Usuario;

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
        }else if(v.getId() == R.id.btnRetrocesoPedidos){
            Intent intent = new Intent(this, Main.class);
            startActivity(intent);
            finish();
        } else if (v.getId() == R.id.btnPizzaPersonalizada) {
            Intent intent = new Intent(this, PizzaPersonalizada.class);
            startActivity(intent);
            finish();
        } else if(v.getId() == R.id.btnPizzaFavorita){
            if(Usuario.getUsuarioActual().getPizzaFav() == null){
                Toast.makeText(this, "No hay pizza favorita", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(this, PizzaFav.class);
                startActivity(intent);
                finish();
            }

        }
    }
}