package com.example.crimejava.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormat {

    public static boolean isValidDate(String dateStr) {
        SimpleDateFormat[] formats = {
                new SimpleDateFormat("yyyy-MM-dd"),
                new SimpleDateFormat("yyyy/MM/dd")
        };
        for (SimpleDateFormat format : formats) {
            format.setLenient(false);
            try {
                format.parse(dateStr);
                return true;
            } catch (ParseException e) {
                // Continue to the next format
            }
        }
        return false;
    }
}
