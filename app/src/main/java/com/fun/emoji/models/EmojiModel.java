package com.fun.emoji.models;


import com.google.auto.value.AutoValue;

import android.os.Parcelable;

@AutoValue
public abstract class EmojiModel implements Parcelable {

    public static EmojiModel create(String title, String imageUrl) {
        return new AutoValue_EmojiModel(title, imageUrl);
    }

    public abstract String title();

    public abstract String imageUrl();
}
