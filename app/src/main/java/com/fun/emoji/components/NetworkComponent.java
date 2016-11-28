package com.fun.emoji.components;

import com.fun.emoji.activities.EmojiActivity;
import com.fun.emoji.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class})
public interface NetworkComponent {

    void inject(EmojiActivity emojiActivity);
}
