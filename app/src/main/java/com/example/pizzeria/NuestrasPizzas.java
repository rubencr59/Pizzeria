package com.example.pizzeria;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pizzeria.Adaptador.PizzaAdapter;
import com.example.pizzeria.Clases.BackgroundManager;
import com.example.pizzeria.Clases.Pizza;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NuestrasPizzas extends AppCompatActivity implements View.OnClickListener {


    ListView listaPizzasListView;

    ConstraintLayout fondo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuestras_pizzas);

        List<Pizza> listaDePizzas = new ArrayList<>();

        listaDePizzas.add(new Pizza(R.drawable.cesar, "Cesar", new String[]{"Queso", "rúcula", "pops de pollo", "bacon crispy", "salsa césar"}));
        listaDePizzas.add(new Pizza(R.drawable.baconc, "Bacon Crispy", new String[]{ "Bacon", "bacon crispy (crujiente)", "queso", "salsa barbacoa"}));
        listaDePizzas.add(new Pizza(R.drawable.carbonara,"Carbonara",  new String[]{"Bacon", "Nata", "Queso"}));
        listaDePizzas.add(new Pizza(R.drawable.barbacoa, "Barbacoa", new String[]{"Salsa de barbacoa", "Ternera", "Cebolla", "Queso"}));
        listaDePizzas.add(new Pizza(R.drawable.cquesos, "4 quesos", new String[]{"Mozzarella", "Gorgonzola", "Parmesano", "Emmental"}));

        listaPizzasListView = findViewById(R.id.listadoPizzas);

        fondo = findViewById(R.id.fondoNuestrasPizzas);
        String colorFondo = BackgroundManager.getColorFondo(this);
        fondo.setBackgroundColor(Integer.parseInt(colorFondo));
        PizzaAdapter adapter = new PizzaAdapter(this, listaDePizzas);

        listaPizzasListView.setAdapter(adapter);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRetrocesoNuestras){
            Intent intent = new Intent(this, Pedidos.class);

            startActivity(intent);
            finish();


        }
    }
}