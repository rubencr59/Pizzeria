package com.example.pizzeria.Clases;

import java.util.ArrayList;

public class Usuario {

    private static int ID = 000;

    int idUsuario;
    String nombre;
    String contraseña;
    private static Usuario usuarioActual;

    boolean activarPizzaBoolean = true;
    Pizza pizzaFav;
    ArrayList<Pizza> carrito = new ArrayList<Pizza>();

    public Usuario(String nombre, String contraseña){
        this.idUsuario = Usuario.ID ++;
        this.nombre = nombre;
        this.contraseña = contraseña;
    }


    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void setUsuarioActual(Usuario usuarioActual) {
        Usuario.usuarioActual = usuarioActual;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public ArrayList<Pizza> getCarrito(){
        return this.carrito;
    }
    public void añadirProductoCarrito(Pizza pizzaAñadida){
        this.carrito.add(pizzaAñadida);
    }

    public void borrarProductoCarrito(Pizza pizzaBorrada){
        carrito.remove(pizzaBorrada);
    }

    public Pizza getPizzaFav(){
        return this.pizzaFav;
    }
    public void vaciarCarrito(){
        this.carrito = new ArrayList<Pizza>();
    }
    public Double obtenerPrecioTotal(){
        Double total = 0.0;
        for (Pizza cadaPizza: this.carrito) {
            total += cadaPizza.getPrecio();
        }
        return total;
    }

    public void setPizzaFav(Pizza newPizzaFav){
        this.pizzaFav = newPizzaFav;
    }

    public void setActivarPizzaBoolean(boolean activado){
        this.activarPizzaBoolean = activado;
    }

    public boolean getActivarPizzaBoolean(){
        return  this.activarPizzaBoolean;
    }

}
