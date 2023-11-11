package com.example.pizzeria.Adaptador;

import static com.example.pizzeria.Clases.BackgroundManager.getColorFondo;
import static com.example.pizzeria.Clases.BackgroundManager.getColorTexto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzeria.Clases.Pizza;
import com.example.pizzeria.R;

import java.util.List;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {

    private final Context context;
    private final List<Pizza> pizzas;
    private OnPizzaClickListener onPizzaClickListener;
    private OnBorrarClickListener onBorrarClickListener; // Nueva interfaz para manejar el clic en el botón Borrar

    private boolean verBotonBorrar;
    private static int colorFondo;
    private static int colorTexto;

    public PizzaAdapter(Context context, List<Pizza> pizzas, Boolean verbtnBorrar ) {
        this.context = context;
        this.pizzas = pizzas;
        this.verBotonBorrar = verbtnBorrar;
        this.colorFondo = Integer.parseInt(getColorFondo(context));
        this.colorTexto = Integer.parseInt(getColorTexto(context));
    }

    @NonNull
    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pizza, parent, false);
        return new PizzaViewHolder(view, this.verBotonBorrar, onBorrarClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaViewHolder holder, int position) {
        Pizza pizza = pizzas.get(position);
        holder.bind(pizza);

        // Asigna el OnClickListener al itemView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPizzaClickListener != null) {
                    onPizzaClickListener.onPizzaClick(pizza);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }

    // Interface para manejar clicks en las pizzas
    public interface OnPizzaClickListener {
        void onPizzaClick(Pizza pizza);
    }

    // Método para establecer el listener desde fuera del adaptador
    public void setOnPizzaClickListener(OnPizzaClickListener listener) {
        this.onPizzaClickListener = listener;
    }

    // Nueva interfaz para manejar clics en el botón Borrar
    public interface OnBorrarClickListener {
        void onBorrarClick(int position);
    }

    // Método para establecer el listener del botón Borrar desde fuera del adaptador
    public void setOnBorrarClickListener(OnBorrarClickListener listener) {
        this.onBorrarClickListener = listener;
    }

    public static class PizzaViewHolder extends RecyclerView.ViewHolder {
        private final RelativeLayout cajaContenedora;
        private final ImageView imageView;
        private final TextView nameTextView;
        private final TextView ingredientsTextView;
        private final TextView tamañoTextView;
        private final TextView precioTextView;

        private final LinearLayout linearLayoutDetalles;

        public PizzaViewHolder(@NonNull View itemView, Boolean verBotonBorrar, OnBorrarClickListener onBorrarClickListener) {
            super(itemView);
            cajaContenedora = itemView.findViewById(R.id.relativeLayoutNuestrasPizzas);
            imageView = itemView.findViewById(R.id.pizza_image);
            nameTextView = itemView.findViewById(R.id.txtnombrePizza);
            ingredientsTextView = itemView.findViewById(R.id.txtingredientesPizza);
            tamañoTextView = itemView.findViewById(R.id.txtTamaño);
            precioTextView = itemView.findViewById(R.id.txtPrecio);
            linearLayoutDetalles = itemView.findViewById(R.id.linearLayoutDetalles);

            if(verBotonBorrar){
                Button botonBorrar = itemView.findViewById(R.id.btnBorrarPizza);
                botonBorrar.setVisibility(View.VISIBLE);

                // Configura el OnClickListener del botón Borrar
                botonBorrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onBorrarClickListener != null) {
                            onBorrarClickListener.onBorrarClick(getAdapterPosition());
                        }
                    }
                });
            }

            itemView.setClickable(true);
            itemView.setFocusable(true);
        }

        public void bind(Pizza pizza) {
            // Configuración de color.
            cajaContenedora.setBackgroundColor(colorFondo);
            nameTextView.setTextColor(colorTexto);
            ingredientsTextView.setTextColor(colorTexto);
            tamañoTextView.setTextColor(colorTexto);
            precioTextView.setTextColor(colorTexto);
            linearLayoutDetalles.setBackgroundColor(colorFondo);

            // Configura las vistas con los datos de la pizza
            imageView.setImageResource(pizza.getImagenSource());
            nameTextView.setText(pizza.getNombrePizza());
            ingredientsTextView.setText(String.join(", ", pizza.getIngredientesPizza()));
            tamañoTextView.setText(pizza.getTamañoPizzaString());
            precioTextView.setText(pizza.getPrecioString());
        }
    }
}
