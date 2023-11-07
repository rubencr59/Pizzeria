package com.example.pizzeria.Clases;

public class Pizza {

    private int imagenPizza;

    private String nombre;
    private String tamañoPizza = null;
    private String[] ingredientesPizza;



    public Pizza(int imagenSource, String nombrePizza,  String[] ingredientes){
        imagenPizza = imagenSource;

        nombre = nombrePizza;

        ingredientesPizza = ingredientes;
    }




    public int getImagenSource(){
        return imagenPizza;
    }
    public String getTamañoPizza() {
        return tamañoPizza;
    }

    public String getNombrePizza(){
        return nombre;
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
