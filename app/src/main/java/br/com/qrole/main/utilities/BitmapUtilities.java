package br.com.qrole.main.utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

/**
 * Utility Bitmap class.
 */
public class BitmapUtilities {

    public static Bitmap getBitmapFromBase64String(String string) {

        if (StringUtilities.isBlank(string)) {
            throw new IllegalStateException("Sting must not be null!");
        }

        byte[] byteContent = Base64.decode(string, Base64.DEFAULT);

        return BitmapFactory.decodeByteArray(byteContent, 0, byteContent.length);
    }
}
