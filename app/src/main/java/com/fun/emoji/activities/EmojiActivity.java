package com.fun.emoji.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fun.emoji.R;
import com.fun.emoji.adapters.EmojiListAdapter;
import com.fun.emoji.application.EmojiApplication;
import com.fun.emoji.databinding.ActivityEmojiBinding;
import com.fun.emoji.models.EmojiModel;
import com.fun.emoji.network.EmojiAPI;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmojiActivity extends AppCompatActivity
        implements Callback<HashMap<String, String>> {

    @Inject
    EmojiAPI emojiAPI;

    private ArrayList<EmojiModel> emojiModelList;
    private EmojiListAdapter emojiListAdapter;
    private final String EMOJI_INSTANCE = "emojiList";
    private ActivityEmojiBinding activityEmojiBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEmojiBinding = DataBindingUtil.setContentView(this, R.layout.activity_emoji);
        ((EmojiApplication) getApplication()).getNetworkComponent().inject(this);

        setSupportActionBar(activityEmojiBinding.toolbar.toolbar);

        emojiModelList = new ArrayList<>();
        emojiListAdapter = new EmojiListAdapter(this, emojiModelList);

        activityEmojiBinding.emojiRecyclerView.setHasFixedSize(true);
        activityEmojiBinding.emojiRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        activityEmojiBinding.emojiRecyclerView.setAdapter(emojiListAdapter);
        if (savedInstanceState == null) {
            fetchEmojis();
        } else {
            ArrayList<EmojiModel> tmpList = savedInstanceState.getParcelableArrayList(EMOJI_INSTANCE);
            emojiModelList.addAll(tmpList);
            emojiListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(EMOJI_INSTANCE, emojiModelList);
        super.onSaveInstanceState(outState);
    }

    private void fetchEmojis() {
        Call<HashMap<String, String>> loadEmojis = emojiAPI.loadEmojiList();
        loadEmojis.enqueue(this);
    }

    @Override
    public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
        HashMap<String, String> emojiList = response.body();
        if (emojiList != null
                && !emojiList.isEmpty()) {
            emojiModelList.clear();

            for (String title : emojiList.keySet()) {
                EmojiModel emojiModel = EmojiModel.create(title, emojiList.get(title));
                emojiModelList.add(emojiModel);
            }

            emojiListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(Call<HashMap<String, String>> call, Throwable t) {
        Snackbar errorSnack = Snackbar.make(activityEmojiBinding.activityEmoji, t.getMessage(), Snackbar.LENGTH_SHORT);
        ViewGroup snackView = (ViewGroup) errorSnack.getView();
        snackView.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorAccent, getTheme()));
        TextView textView = (TextView) snackView.getChildAt(0);
        textView.setTextColor(ResourcesCompat.getColor(getResources(), R.color.primaryTextColor, getTheme()));
        errorSnack.show();
    }
}
