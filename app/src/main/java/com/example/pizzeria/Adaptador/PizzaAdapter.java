package com.example.pizzeria.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pizzeria.Clases.BackgroundManager;
import com.example.pizzeria.Clases.Pizza;
import com.example.pizzeria.R;

import org.w3c.dom.Text;

import java.util.List;

public class PizzaAdapter extends ArrayAdapter<Pizza> {
    public PizzaAdapter(Context context, List<Pizza> pizzas) {
        super(context, 0, pizzas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtiene la pizza en la posición actual
        Pizza pizza = getItem(position);

        // Reutiliza una vista si está disponible o crea una nueva si no lo está
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_pizza, parent, false);
        }
        LinearLayout cajaContenedora = convertView.findViewById(R.id.linearLayoutNuestrasPizzas);
        String colorFondo = BackgroundManager.getColorFondo(this.getContext());
        String colorTexto = BackgroundManager.getColorTexto(this.getContext());

        ImageView imageView = convertView.findViewById(R.id.pizza_image);
        TextView nameTextView = convertView.findViewById(R.id.nombrePizzaTextView);
        TextView ingredientsTextView = convertView.findViewById(R.id.ingredientesPizzaTextView);

        //Configuracion de color.
        cajaContenedora.setBackgroundColor(Integer.parseInt(colorFondo));
        nameTextView.setTextColor(Integer.parseInt(colorTexto));
        ingredientsTextView.setTextColor(Integer.parseInt(colorTexto));

        // Configura las vistas con los datos de la pizza
        imageView.setImageResource(pizza.getImagenSource());
        nameTextView.setText(pizza.getNombrePizza());
        ingredientsTextView.setText(String.join(", ", pizza.getIngredientesPizza()));

        return convertView;
    }
}
