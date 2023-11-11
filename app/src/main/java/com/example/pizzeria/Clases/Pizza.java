package com.example.pizzeria.Clases;

import com.example.pizzeria.Enum.TamañoPizza;

import java.util.List;

public class Pizza {

    private int imagenPizza;

    private String nombre;

    private TamañoPizza tamañoPizza;
    private List<String> ingredientesPizza;

    private Double precio;


    public Pizza(int imagenSource, String nombrePizza,  List<String> ingredientes){
        imagenPizza = imagenSource;

        nombre = nombrePizza;

        ingredientesPizza = ingredientes;
    }

    public Pizza(){

    }




    public int getImagenSource(){
        return imagenPizza;
    }

    public String getNombrePizza(){
        return nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public String getPrecioString(){
        String precioString = "";
        if (this.precio !=null){
            precioString = this.precio +"€";
        }
        return  precioString ;
    }

    public void setTamañoPizza(TamañoPizza tamañoPizza) {

        this.tamañoPizza = tamañoPizza;
        switch (this.tamañoPizza){
            case PEQUEÑA:
                this.precio = 6.99;
                break;
            case MEDIANA:
                this.precio = 9.99;
                break;
            case GRANDE:
                this.precio = 14.99;
                break;
        }
    }

    public void setTamañoPizzaString(String tamañoPizza) {

        switch (tamañoPizza){
            case "Pequeña":
                this.tamañoPizza = TamañoPizza.PEQUEÑA;
                break;
            case "Mediana":
                this.tamañoPizza = TamañoPizza.MEDIANA;
                break;
            case "Grande":
                this.tamañoPizza = TamañoPizza.GRANDE;
                break;
        }


    }

    public String  getTamañoPizzaString(){
        String tamaño = "";
        if(this.tamañoPizza != null){
            switch (this.tamañoPizza){
                case PEQUEÑA:
                    tamaño = "Pequeña";
                    break;
                case MEDIANA:
                    tamaño = "Mediana";
                    break;
                case GRANDE:
                    tamaño = "Grande";
                    break;
            }
        }

        return tamaño;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public List<String> getIngredientesPizza() {
        return ingredientesPizza;
    }

    public void setIngredientesPizza(List<String> ingredientesPizza) {
        this.ingredientesPizza = ingredientesPizza;
    }
}
