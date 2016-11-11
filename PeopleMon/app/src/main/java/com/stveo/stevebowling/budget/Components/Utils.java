package com.stveo.stevebowling.budget.Components;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

import static com.stveo.stevebowling.budget.Components.Constants.IMAGE;

/**
 * Created by stevebowling on 11/2/16.
 */

public class Utils {
    public static Bitmap resize(Bitmap image) {
        image = Bitmap.createScaledBitmap(image, 100, 100, false);
        return image;
    }

    public static Bitmap decodeImage(String encodedString) {
        byte[] decodedString;
        Bitmap decodedByte;
        if (encodedString != null) {
            if (encodedString.contains(",")) {
                String[] haxor = encodedString.split(",");
                decodedString = Base64.decode(haxor[1], Base64.DEFAULT);
            } else {
                if (encodedString.length() > 200) {
                    decodedString = Base64.decode(encodedString, Base64.DEFAULT);
                } else {
                    decodedString = null;
                }
            }
            if (decodedString != null) {
                decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                decodedByte = resize(decodedByte);
                return decodedByte;
            }
        }
        return null;
    }

    public static String encodeTobase64(Bitmap bitmap) {
        bitmap = resize(bitmap);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        IMAGE = Base64.encodeToString(byteArray, Base64.DEFAULT);
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
}

