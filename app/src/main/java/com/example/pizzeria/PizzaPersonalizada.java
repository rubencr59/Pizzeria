package com.example.pizzeria;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzeria.Clases.BackgroundManager;
import com.example.pizzeria.Clases.Pizza;
import com.example.pizzeria.Clases.Usuario;
import com.example.pizzeria.Enum.TamañoPizza;

import java.util.ArrayList;
import java.util.List;

public class PizzaPersonalizada extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout fondo;
    private  LinearLayout linearBotones;
    private EditText editNombrePizza;
    private RadioGroup radioGroupTamaño;
    private  RadioButton radioPequeño,radioMediano, radioGrande;
    private CheckBox checkJamon, checkQueso, checkBacon, checkAtun, checkSalchichas, checkChampiñones, checkPeperoni, checkPulledPork, checkBBQ, checkYogurt, checkBrava;

    private Usuario usuarioConectado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_personalizada);

        fondo = findViewById(R.id.fondoPizzaPersonalizada);
        usuarioConectado = Usuario.getUsuarioActual();
        radioGroupTamaño = findViewById(R.id.radioGroupTamaño);
        radioPequeño = findViewById(R.id.radioPequeña);
        radioMediano = findViewById(R.id.radioMediana);
        radioGrande = findViewById(R.id.radioGrande);
        checkJamon = findViewById(R.id.checkJamon);
        checkQueso = findViewById(R.id.checkQueso);
        checkBacon = findViewById(R.id.checkBacon);
        checkAtun = findViewById(R.id.checkAtun);
        checkSalchichas = findViewById(R.id.checkSalchichas);
        checkChampiñones = findViewById(R.id.checkChampiñones);
        checkPeperoni = findViewById(R.id.checkPeperoni);
        checkPulledPork = findViewById(R.id.checkPulledPork);
        checkBBQ = findViewById(R.id.checkBBQ);
        checkYogurt = findViewById(R.id.checkYogurt);
        checkBrava = findViewById(R.id.checkBrava);
        linearBotones = findViewById(R.id.lineraLayoutBotonesPersonalizada);
        editNombrePizza = findViewById(R.id.editNombrePizza);

        int colorFondo = Integer.parseInt(BackgroundManager.getColorFondo(this));
        fondo.setBackgroundColor(colorFondo);
        linearBotones.setBackgroundColor(colorFondo);

        int colorTexto = Integer.parseInt(BackgroundManager.getColorTexto(this));

        editNombrePizza.setTextColor(colorTexto);
        editNombrePizza.setHintTextColor(colorTexto);
        radioPequeño.setTextColor(colorTexto);
        radioMediano.setTextColor(colorTexto);
        radioGrande.setTextColor(colorTexto);

        checkJamon.setTextColor(colorTexto);
        checkQueso.setTextColor(colorTexto);
        checkBacon.setTextColor(colorTexto);
        checkAtun.setTextColor(colorTexto);
        checkSalchichas.setTextColor(colorTexto);
        checkChampiñones.setTextColor(colorTexto);
        checkPeperoni.setTextColor(colorTexto);
        checkPulledPork.setTextColor(colorTexto);
        checkBBQ.setTextColor(colorTexto);
        checkYogurt.setTextColor(colorTexto);
        checkBrava.setTextColor(colorTexto);
    }

    public void onCheckboxClicked(View view) {
        int numChecksSeleccionados = contarChecksSeleccionados();

        // Limito los ingredientes en 3.
        if (numChecksSeleccionados > 3) {
            ((CheckBox) view).setChecked(false);
            Toast.makeText(this, "Solo puedes seleccionar 3 ingredientes", Toast.LENGTH_SHORT).show();
        }
    }

    private int contarChecksSeleccionados() {
        int count = 0;
        if (checkJamon.isChecked()) count++;
        if (checkQueso.isChecked()) count++;
        if (checkBacon.isChecked()) count++;
        if (checkAtun.isChecked()) count++;
        if (checkSalchichas.isChecked()) count++;
        if (checkChampiñones.isChecked()) count++;
        if (checkPeperoni.isChecked()) count++;
        if (checkPulledPork.isChecked()) count++;
        if (checkBBQ.isChecked()) count++;
        if (checkYogurt.isChecked()) count++;
        if (checkBrava.isChecked()) count++;

        return count;
    }


    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnSalirFav){
            Intent intent = new Intent(this, Pedidos.class);
            startActivity(intent);
            finish();
        }else {
            String nombrePizza = String.valueOf(editNombrePizza.getText());

            // Verifica si se ha ingresado un nombre
            if (nombrePizza.isEmpty()) {
                Toast.makeText(this, "Ingrese un nombre para la pizza", Toast.LENGTH_SHORT).show();
                return;
            }

            // Obtiene el tamaño de la pizza
            RadioButton radioButton = findViewById(radioGroupTamaño.getCheckedRadioButtonId());
            TamañoPizza tamañoPizza = obtenerTamañoDesdeRadioButton(radioButton);

            // Verifica si se ha seleccionado un tamaño
            if (tamañoPizza == null) {
                Toast.makeText(this, "Seleccione un tamaño para la pizza", Toast.LENGTH_SHORT).show();
                return;
            }

            // Obtiene los ingredientes seleccionados
            List<String> ingredientes = obtenerIngredientesSeleccionados();

            // Crea el objeto Pizza con los detalles proporcionados
            Pizza pizzaPersonalizada = new Pizza();
            pizzaPersonalizada.setNombre(nombrePizza);
            pizzaPersonalizada.setTamañoPizza(tamañoPizza);
            pizzaPersonalizada.setIngredientesPizza(ingredientes);

            if(usuarioConectado.getActivarPizzaBoolean()){
                usuarioConectado.setPizzaFav(pizzaPersonalizada);
            }

            usuarioConectado.añadirProductoCarrito(pizzaPersonalizada);

            AlertDialog.Builder successDialogBuilder = new AlertDialog.Builder(this);
            successDialogBuilder.setTitle("Éxito");
            successDialogBuilder.setMessage("Pizza agregada correctamente al carrito");
            Intent intent = new Intent(this, Confirmacion.class);
            successDialogBuilder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface successDialog, int which) {
                    successDialog.dismiss();
                    startActivity(intent);
                    finish();
                }
            });
            successDialogBuilder.create().show();
        }
    }

    private TamañoPizza obtenerTamañoDesdeRadioButton(RadioButton radioButton) {

        if (radioButton.getId() == R.id.radioPequeña) {
            return TamañoPizza.PEQUEÑA;
        } else if (radioButton.getId() == R.id.radioMediana) {
            return TamañoPizza.MEDIANA;
        } else if (radioButton.getId() == R.id.radioGrande) {
            return TamañoPizza.GRANDE;
        }else{
            return null;
        }
    }

    private List<String> obtenerIngredientesSeleccionados() {
        List<String> ingredientes = new ArrayList<>();
        if (checkJamon.isChecked()) ingredientes.add("Jamón");
        if (checkQueso.isChecked()) ingredientes.add("Queso");
        if (checkBacon.isChecked()) ingredientes.add("Bacon");
        if (checkAtun.isChecked()) ingredientes.add("Atún");
        if (checkSalchichas.isChecked()) ingredientes.add("Salchichas");
        if (checkChampiñones.isChecked()) ingredientes.add("Champiñones");
        if (checkPeperoni.isChecked()) ingredientes.add("Peperoni");
        if (checkPulledPork.isChecked()) ingredientes.add("PulledPork");
        if (checkBBQ.isChecked()) ingredientes.add("Salsa BBQ");
        if (checkYogurt.isChecked()) ingredientes.add("Salsa yogurt");
        if (checkBrava.isChecked()) ingredientes.add("Salsa brava");
        return ingredientes;
    }
}
