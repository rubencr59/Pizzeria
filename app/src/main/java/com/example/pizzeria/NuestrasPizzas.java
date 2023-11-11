package com.example.pizzeria;

import static com.example.pizzeria.Clases.BackgroundManager.getColorFondo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.pizzeria.Adaptador.PizzaAdapter;
import com.example.pizzeria.Clases.Alertas;
import com.example.pizzeria.Clases.BackgroundManager;
import com.example.pizzeria.Clases.DAOPizzas;
import com.example.pizzeria.Clases.Pizza;
import com.example.pizzeria.Clases.Usuario;
import com.example.pizzeria.Enum.TamañoPizza;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NuestrasPizzas extends AppCompatActivity implements View.OnClickListener, PizzaAdapter.OnPizzaClickListener {


    RecyclerView listaPizzasRecyclerView;
    ConstraintLayout fondo;
    Usuario usuarioActual;
    LinearLayout linearLayoutBotonesNuestrasPizzas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuestras_pizzas);

        DAOPizzas daoPizzas = new DAOPizzas();

        fondo = findViewById(R.id.fondoNuestrasPizzas);
        String colorFondo = getColorFondo(this);
        fondo.setBackgroundColor(Integer.parseInt(colorFondo));
        listaPizzasRecyclerView = findViewById(R.id.listadoPizzas);
        linearLayoutBotonesNuestrasPizzas = findViewById(R.id.linearLayoutBotonesNuestras);

        linearLayoutBotonesNuestrasPizzas.setBackgroundColor(Integer.parseInt(getColorFondo(this)));
        // Configura el LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listaPizzasRecyclerView.setLayoutManager(layoutManager);
        PizzaAdapter pizzaAdapter = new PizzaAdapter(this, daoPizzas.getListaDePizzas(), false);
        listaPizzasRecyclerView.setAdapter(pizzaAdapter);
        pizzaAdapter.setOnPizzaClickListener(this);

        usuarioActual = Usuario.getUsuarioActual();

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRetrocesoNuestras){
            Intent intent = new Intent(this, Pedidos.class);
            startActivity(intent);
            finish();
        }else if(v.getId() == R.id.btnIrCarrito){
            //Ir a el carrito del usuario.
            Intent intent = new Intent(this, Confirmacion.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onPizzaClick(Pizza pizza) {
        //Cuadro de diálogo para seleccionar el tamaño de la pizza.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar Tamaño");
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_select_size, null);
        builder.setView(dialogView);

        //Grupo de botones para el tamaño.
        RadioGroup radioGroup = dialogView.findViewById(R.id.seleccionarTamaño);
        builder.setPositiveButton("Añadir al carrito", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                if (checkedRadioButtonId == R.id.elegirTamañoPequeño) {
                    pizza.setTamañoPizza(TamañoPizza.PEQUEÑA);
                } else if (checkedRadioButtonId == R.id.elegirTamañoMediano) {
                    pizza.setTamañoPizza(TamañoPizza.MEDIANA);
                } else if (checkedRadioButtonId == R.id.elegirTamañoGrande) {
                    pizza.setTamañoPizza(TamañoPizza.GRANDE);
                }
                usuarioActual.añadirProductoCarrito(pizza);

                // Muestra un segundo cuadro de diálogo para informar que la pizza se agregó correctamente.
                AlertDialog.Builder successDialogBuilder = new AlertDialog.Builder(NuestrasPizzas.this);
                successDialogBuilder.setTitle("Éxito");
                successDialogBuilder.setMessage("Pizza agregada correctamente al carrito");
                successDialogBuilder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface successDialog, int which) {
                        successDialog.dismiss();
                    }
                });
                successDialogBuilder.create().show();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // Mostrar el cuadro de diálogo
        builder.create().show();
    }
}