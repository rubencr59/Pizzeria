package com.example.pizzeria.Clases;

import android.content.Context;
import android.content.SharedPreferences;

public class BackgroundManager {
    private static SharedPreferences sharedPreferences;

    public static String getColorFondo(Context context){
        sharedPreferences = context.getSharedPreferences("Preferencias", Context.MODE_PRIVATE);

        return sharedPreferences.getString("colorFondo", "");
    }

    public static String getColorTexto(Context context){
        sharedPreferences = context.getSharedPreferences("Preferencias", Context.MODE_PRIVATE);

        return sharedPreferences.getString("colorTexto", "");
    }
}
