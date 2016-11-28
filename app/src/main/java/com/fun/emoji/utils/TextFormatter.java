package com.fun.emoji.utils;


import android.support.annotation.NonNull;

public class TextFormatter {

    @NonNull
    public static String capitalizeWords(@NonNull String sentence) {
        sentence = sentence.replace("_", " ");

        return sentence.substring(0, 1).toUpperCase() + sentence.substring(1);
    }
}
