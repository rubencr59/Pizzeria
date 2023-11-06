package com.example.pizzeria;

import static com.example.pizzeria.Clases.BackgroundManager.getColorFondo;
import static com.example.pizzeria.Clases.BackgroundManager.getColorTexto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


public class Configuracion  extends AppCompatActivity implements View.OnClickListener {

    ConstraintLayout base;

    RadioButton[] listabotones;
    TextView texto;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        base = findViewById(R.id.backgroundconfig);
        listabotones = new RadioButton[]{
                findViewById(R.id.radiobtnRojo),
                findViewById(R.id.radiobtnAzul),
                findViewById(R.id.radiobtnNegro),
                findViewById(R.id.radiobtnBlanco)
        };

        texto = findViewById(R.id.txtCambiarColor);

        GradientDrawable border = new GradientDrawable();
        border.setColor(0xFFFFFFFF);  // Color de fondo del borde
        border.setStroke(4, 0xFF000000);  // Ancho y color del borde
        border.setCornerRadius(3);  // Radio de las esquinas del RadioGroup
        String colorFondo = getColorFondo(this);

        String colorTexto = getColorTexto(this);
        if (colorFondo != "" && colorTexto !=""){
            base.setBackgroundColor(Integer.parseInt(colorFondo));
            texto.setTextColor(Integer.parseInt(colorTexto));
        }

        for (Button btn:listabotones) {
            btn.setBackground(border);
        }

    }


    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.btnRetrocederConfiguracion){
            Intent intent = new Intent(this, Main.class);
            startActivity(intent);
            finish();
        }else{
            int colorFondo = Color.WHITE;
            int colorTexto = Color.BLACK;
            if (v.getId() == R.id.radiobtnRojo){
                colorFondo = Color.RED;
                colorTexto = Color.WHITE;
            }else if (v.getId() == R.id.radiobtnAzul){
                colorFondo = Color.BLUE;
                colorTexto = Color.WHITE;
            }else if (v.getId() == R.id.radiobtnNegro){
                colorFondo = Color.BLACK;
                colorTexto = Color.WHITE;
                base.setBackgroundColor(Color.BLACK);
            }else if (v.getId() == R.id.radiobtnBlanco){
                colorFondo = Color.WHITE;
                colorTexto = Color.BLACK;
            }
            base.setBackgroundColor(colorFondo);
            texto.setTextColor(colorTexto);


            SharedPreferences preferences = getSharedPreferences("Preferencias", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("colorFondo", colorFondo + "");
            editor.putString("colorTexto", colorTexto +"");
            editor.apply();
        }
    }
}
