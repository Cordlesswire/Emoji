package com.fun.emoji.utils;

import android.widget.ImageView;

import com.fun.emoji.R;
import com.squareup.picasso.Picasso;

public class ImageUtil {

    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_placeholder)
                .into(view);
    }
}
