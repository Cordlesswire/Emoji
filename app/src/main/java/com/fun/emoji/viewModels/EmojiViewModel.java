package com.fun.emoji.viewModels;


import android.app.Activity;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.ImageView;

import com.fun.emoji.R;
import com.fun.emoji.activities.EmojiDetailActivity;
import com.fun.emoji.application.EmojiApplication;
import com.fun.emoji.utils.ImageUtil;
import com.fun.emoji.models.EmojiModel;

import javax.inject.Inject;

public class EmojiViewModel {

    @Inject
    EmojiApplication emojiApplication;

    private Activity activity;
    private EmojiModel emojiModel;

    public EmojiViewModel(Activity activity, EmojiModel emojiModel) {
        this.activity = activity;
        this.emojiModel = emojiModel;
    }

    public String getEmojiUrl() {
        return emojiModel.imageUrl();
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadEmojiImage(ImageView view, String imageUrl) {
        ImageUtil.loadImage(view, imageUrl);
    }

    public void loadEmojiDetail(View view) {
        if (emojiModel != null) {
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(activity, view, activity.getString(R.string.emoji_transition_name));

            Intent detailsIntent = new Intent(activity, EmojiDetailActivity.class);
            detailsIntent.putExtra(EmojiDetailActivity.EMOJI_INTENT_DATA, emojiModel);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                activity.startActivity(detailsIntent, optionsCompat.toBundle());
            } else {
                activity.startActivity(detailsIntent);
            }
        }
    }
}
