package com.example.pizzeria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pizzeria.Clases.BackgroundManager;
import com.example.pizzeria.Clases.Pizza;
import com.example.pizzeria.Clases.Usuario;

public class PizzaFav extends AppCompatActivity implements View.OnClickListener {

    private ImageView pizzaImageView;
    private TextView nombreTextView;
    private TextView ingredientesTextView;
    private TextView tamañoTextView;
    private TextView precioTextView;
    private Usuario usuarioConectado;

    private RelativeLayout relativeLayoutPizzaFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_fav);

        // Obtén la pizza favorita del usuario
        usuarioConectado = Usuario.getUsuarioActual();
        Pizza pizzaFavorita = usuarioConectado.getPizzaFav();

        // Inicializa las vistas
        relativeLayoutPizzaFav = findViewById(R.id.relativeLayoutPizzaFav);
        pizzaImageView = findViewById(R.id.pizza_image_fav);
        nombreTextView = findViewById(R.id.txtnombrePizzaFav);
        ingredientesTextView = findViewById(R.id.txtingredientesPizzaFav);
        tamañoTextView = findViewById(R.id.txtTamañoFav);
        precioTextView = findViewById(R.id.txtPrecioFav);

        // Actualiza las vistas con la información de la pizza favorita
        pizzaImageView.setImageResource(pizzaFavorita.getImagenSource());
        nombreTextView.setText(pizzaFavorita.getNombrePizza());
        ingredientesTextView.setText(String.join(", ", pizzaFavorita.getIngredientesPizza()));
        tamañoTextView.setText(pizzaFavorita.getTamañoPizzaString());
        precioTextView.setText(pizzaFavorita.getPrecioString());

        int colorFondo = Integer.parseInt(BackgroundManager.getColorFondo(this));
        relativeLayoutPizzaFav.setBackgroundColor(colorFondo);

        int colorTexto = Integer.parseInt(BackgroundManager.getColorTexto(this));
        nombreTextView.setTextColor(colorTexto);
        ingredientesTextView.setTextColor(colorTexto);
        tamañoTextView.setTextColor(colorTexto);
        precioTextView.setTextColor(colorTexto);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSalirFav){
            Intent intent = new Intent(this, Pedidos.class);
            startActivity(intent);
            finish();
        }else if (v.getId() == R.id.btnAgregarAlCarrito){
            usuarioConectado.añadirProductoCarrito(usuarioConectado.getPizzaFav());
            AlertDialog.Builder successDialogBuilder = new AlertDialog.Builder(this);
            successDialogBuilder.setTitle("Éxito");
            successDialogBuilder.setMessage("Pizza agregada correctamente al carrito");
            successDialogBuilder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface successDialog, int which) {
                    successDialog.dismiss();
                    Intent intent = new Intent(PizzaFav.this, Confirmacion.class);
                    startActivity(intent);
                    finish();
                }
            });
            successDialogBuilder.create().show();
        }
    }
}
