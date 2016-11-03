package br.com.qrole.main.utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

import br.com.qrole.main.resources.ViolationException;

/**
 * Default Exception Handler for unexpected or expected Exceptions.
 */
public class DefaultExceptionHandler {

    public static void handleException(Exception ex, Context context) {
        String title;
        String message;

        if (ex instanceof ViolationException) {
            ViolationException vex = (ViolationException) ex;
            title = vex.getMessageResource().getTitle();
            message = vex.getMessageResource().getMessage();
        } else {
            title = "Exceção inesperada";
            message = ex.getMessage();
        }

        AlertDialog.Builder messageBox = new AlertDialog.Builder(context);
        messageBox.setTitle(title);
        messageBox.setMessage(message);
        messageBox.setCancelable(false);
        messageBox.setNeutralButton("OK", null);
        messageBox.show();
    }
}
