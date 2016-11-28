package com.fun.emoji.application;

import android.app.Application;

import com.fun.emoji.components.DaggerNetworkComponent;
import com.fun.emoji.components.NetworkComponent;
import com.fun.emoji.modules.NetworkModule;


public class EmojiApplication extends Application {

    private NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        networkComponent = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule("https://api.github.com"))
                .build();
    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }
}
