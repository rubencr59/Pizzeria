package com.example.pizzeria.Clases;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Alertas {

    public static AlertDialog.Builder crearDialogo(Context context, String titulo, String mensaje, boolean dosrespuestas, DialogInterface.OnClickListener confirmarClickListener) {

        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(context);
        dialogo1.setTitle(titulo);
        dialogo1.setMessage(mensaje);
        dialogo1.setCancelable(false);


        if (confirmarClickListener != null) {
            dialogo1.setPositiveButton("Confirmar", confirmarClickListener);
        } else {
            // Si confirmarClickListener es null, simplemente cierra el diálogo.
            dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        if (dosrespuestas){
            if (confirmarClickListener != null) {
                dialogo1.setNegativeButton("Cancelar", confirmarClickListener);
            } else {
                // Si confirmarClickListener es null, simplemente cierra el diálogo.
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        }
    }
        dialogo1.show();
        return dialogo1;
    }
}

