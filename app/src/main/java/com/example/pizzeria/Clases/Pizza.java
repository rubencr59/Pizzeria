package com.example.pizzeria.Clases;

public class Pizza {

    int imagenPizza;
    String tamañoPizza;
    String[] ingredientesPizza;

    public int getImagenPizza() {
        return imagenPizza;
    }

    public Pizza(int imagenSource, String tamaño, String[] ingredientes){
        imagenPizza = imagenSource;

        tamañoPizza = tamaño;

        ingredientesPizza = ingredientes;
    }

    public String getTamañoPizza() {
        return tamañoPizza;
    }

    public void setTamañoPizza(String tamañoPizza) {
        this.tamañoPizza = tamañoPizza;
    }

    public String[] getIngredientesPizza() {
        return ingredientesPizza;
    }

    public void setIngredientesPizza(String[] ingredientesPizza) {
        this.ingredientesPizza = ingredientesPizza;
    }
}
