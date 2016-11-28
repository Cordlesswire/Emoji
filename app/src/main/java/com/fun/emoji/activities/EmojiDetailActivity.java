package com.fun.emoji.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.fun.emoji.R;
import com.fun.emoji.databinding.ActivityEmojiDetailBinding;
import com.fun.emoji.models.EmojiModel;
import com.fun.emoji.utils.TextFormatter;
import com.fun.emoji.viewModels.EmojiViewModel;

public class EmojiDetailActivity extends AppCompatActivity {

    public static final String EMOJI_INTENT_DATA = "emojiData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEmojiDetailBinding emojiDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_emoji_detail);

        Intent intent = getIntent();
        EmojiModel emojiModel = intent.getParcelableExtra(EMOJI_INTENT_DATA);
        emojiDetailBinding.setEmojiDetail(new EmojiViewModel(this, emojiModel));

        setSupportActionBar(emojiDetailBinding.toolbar.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(TextFormatter.capitalizeWords(emojiModel.title()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
