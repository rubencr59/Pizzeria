package com.example.pizzeria;

import static com.example.pizzeria.Clases.BackgroundManager.getColorFondo;
import static com.example.pizzeria.Clases.BackgroundManager.getColorTexto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pizzeria.Adaptador.PizzaAdapter;
import com.example.pizzeria.Clases.Alertas;
import com.example.pizzeria.Clases.Pizza;
import com.example.pizzeria.Clases.Usuario;


import android.content.DialogInterface;

public class Confirmacion extends AppCompatActivity implements View.OnClickListener {

    RecyclerView listaCarrito;
    LinearLayout linearLayoutPrecioTotal;
    LinearLayout linearLayoutBotonesConfirmar;
    LinearLayout linearLayoutTitulo;
    TextView textCarrito;
    TextView textCarritoVacio;
    TextView precioTotal;
    TextView textPrecioTotal;

    Usuario usuario;
    ConstraintLayout fondo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion);

        listaCarrito = findViewById(R.id.listaCarrito);
        textCarritoVacio = findViewById(R.id.textCarritoVacio);
        precioTotal = findViewById(R.id.numPrecioTotal);
        fondo = findViewById(R.id.fondoConfirmar);
        linearLayoutPrecioTotal = findViewById(R.id.linearLayoutPrecioTotal);
        linearLayoutBotonesConfirmar = findViewById(R.id.linearbotonesConfirmar);
        textCarrito = findViewById(R.id.txtCarrito);
        textPrecioTotal = findViewById(R.id.txtPrecioTotal);
        linearLayoutTitulo = findViewById(R.id.linearLayoutTitulo);
        usuario = Usuario.getUsuarioActual();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listaCarrito.setLayoutManager(layoutManager);

        int colorFondo = Integer.parseInt(getColorFondo(this));
        int colorTexto = Integer.parseInt(getColorTexto(this));
        fondo.setBackgroundColor(colorFondo);
        linearLayoutPrecioTotal.setBackgroundColor(colorFondo);
        linearLayoutBotonesConfirmar.setBackgroundColor(colorFondo);
        linearLayoutTitulo.setBackgroundColor(colorFondo);

        precioTotal.setTextColor(colorTexto);
        textCarrito.setTextColor(colorTexto);
        textPrecioTotal.setTextColor(colorTexto);
        textCarritoVacio.setTextColor(colorTexto);

        if (Usuario.getUsuarioActual().getCarrito().isEmpty()) {
            textCarritoVacio.setVisibility(View.VISIBLE);
            listaCarrito.setVisibility(View.GONE);
        } else {
            textCarritoVacio.setVisibility(View.GONE);
            listaCarrito.setVisibility(View.VISIBLE);
            PizzaAdapter pizzaAdapter = new PizzaAdapter(this, usuario.getCarrito(), true);
            listaCarrito.setAdapter(pizzaAdapter);
            pizzaAdapter.setOnBorrarClickListener(new PizzaAdapter.OnBorrarClickListener() {
                @Override
                public void onBorrarClick(int position) {
                    // Aquí es donde debes implementar la lógica para borrar la pizza del carrito
                    // Puedes obtener la pizza desde la posición y hacer lo necesario
                    Pizza pizzaABorrar = usuario.getCarrito().get(position);
                    usuario.borrarProductoCarrito(pizzaABorrar);

                    // Notifica al adaptador que el conjunto de datos ha cambiado
                    pizzaAdapter.notifyDataSetChanged();

                    // Actualiza el precio total
                    precioTotal.setText(usuario.obtenerPrecioTotal() + "€");

                    // Puedes agregar aquí la lógica adicional según tus necesidades
                }
            });

            listaCarrito.setAdapter(pizzaAdapter);
            //Obtiene el precio total del pedido y lo actualiza.
            precioTotal.setText(usuario.obtenerPrecioTotal() + "€");
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSeguirComprando){
            Intent intent = new Intent(this, Pedidos.class);
            startActivity(intent);
            finish();
        }else if(v.getId() == R.id.btnConfirmar){
            usuario.vaciarCarrito();
            Alertas.crearDialogo(this, "Pedido confirmado", "¡Se ha confirmado tu pedido, en breve llegará a tu casa la pizza bien calentita!", false, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(Confirmacion.this, Main.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
}