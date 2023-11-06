package com.example.pizzeria;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pizzeria.Clases.Pizza;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NuestrasPizzas extends AppCompatActivity {


    ListView listaPizzas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuestras_pizzas);

        List<Pizza> listaDePizzas = new ArrayList<>();

        //listaDePizzas.add(new Pizza("Margarita", new String[]{"Tomate", "Mozzarella", "Albahaca"}));
        //listaDePizzas.add(new Pizza("Pepperoni", new String[]{"Tomate", "Mozzarella", "Pepperoni"}));
        listaDePizzas.add(new Pizza(R.drawable.carbonara,"Carbonara", new String[]{"Bacon", "Nata", "Queso"}));
        //listaDePizzas.add(new Pizza("Barbacoa", new String[]{"Salsa de barbacoa", "Ternera", "Cebolla", "Queso"}));
        //listaDePizzas.add(new Pizza("4 quesos", new String[]{"Mozzarella", "Gorgonzola", "Parmesano", "Emmental"}));

        listaPizzas = findViewById(R.id.listadoPizzas);

        ArrayAdapter<Pizza> adaptador = new ArrayAdapter<Pizza>(this, R.layout.item_pizza){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.item_pizza, parent, false);
                }

                Pizza pizza = listaDePizzas.get(position);
                ImageView imagenPizza = convertView.findViewById(R.id.imagenPizza);
                TextView nombrePizzaTextView = convertView.findViewById(R.id.nombrePizzaTextView);
                TextView ingredientesPizzaTextView = convertView.findViewById(R.id.ingredientesPizzaTextView);

                imagenPizza.setImageResource(pizza.getImagenPizza());
                nombrePizzaTextView.setText(pizza.getTamañoPizza());
                ingredientesPizzaTextView.setText(Arrays.toString(pizza.getIngredientesPizza()));

                return convertView;
            }
        };
        listaPizzas.setAdapter(adaptador); // ¡Agrega esta línea!

    }
}