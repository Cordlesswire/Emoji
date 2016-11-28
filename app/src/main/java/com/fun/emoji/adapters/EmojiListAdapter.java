package com.fun.emoji.adapters;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fun.emoji.viewModels.EmojiViewModel;
import com.fun.emoji.R;
import com.fun.emoji.databinding.EmojiListItemBinding;
import com.fun.emoji.models.EmojiModel;

import java.util.List;


public class EmojiListAdapter extends RecyclerView.Adapter<EmojiListAdapter.EmojiViewHolder> {

    private List<EmojiModel> items;
    private Activity activity;

    public EmojiListAdapter(Activity activity, List<EmojiModel> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public EmojiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        EmojiListItemBinding emojiListItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.emoji_list_item,
                        parent, false);

        return new EmojiViewHolder(emojiListItemBinding);
    }

    @Override
    public void onBindViewHolder(EmojiViewHolder holder, int position) {
        EmojiViewModel emojiViewModel = new EmojiViewModel(activity, items.get(position));
        holder.emojiListItemBinding.setEmojiViewModel(emojiViewModel);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class EmojiViewHolder extends RecyclerView.ViewHolder {

        EmojiListItemBinding emojiListItemBinding;

        EmojiViewHolder(EmojiListItemBinding emojiListItemBinding) {
            super(emojiListItemBinding.getRoot());
            this.emojiListItemBinding = emojiListItemBinding;
        }
    }
}
