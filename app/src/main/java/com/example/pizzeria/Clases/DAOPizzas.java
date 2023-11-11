package com.example.pizzeria.Clases;

import com.example.pizzeria.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DAOPizzas {
    List<Pizza> listaDePizzas = new ArrayList<>();

    public DAOPizzas(){
        listaDePizzas.add(new Pizza(R.drawable.cesar, "Cesar", Arrays.asList("Queso", "rúcula", "pops de pollo", "bacon crispy", "salsa césar")));
        listaDePizzas.add(new Pizza(R.drawable.baconc, "Bacon Crispy", Arrays.asList("Bacon", "bacon crispy (crujiente)", "queso", "salsa barbacoa")));
        listaDePizzas.add(new Pizza(R.drawable.carbonara, "Carbonara", Arrays.asList("Bacon", "Nata", "Queso")));
        listaDePizzas.add(new Pizza(R.drawable.barbacoa, "Barbacoa", Arrays.asList("Salsa de barbacoa", "Ternera", "Cebolla", "Queso")));
        listaDePizzas.add(new Pizza(R.drawable.cquesos, "4 quesos", Arrays.asList("Mozzarella", "Gorgonzola", "Parmesano", "Emmental")));
    }

    public List<Pizza> getListaDePizzas() {
        return listaDePizzas;
    }

}
