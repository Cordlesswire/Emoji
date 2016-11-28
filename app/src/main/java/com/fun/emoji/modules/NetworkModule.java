package com.fun.emoji.modules;

import com.fun.emoji.network.EmojiAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private String baseUrl;

    public NetworkModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    GsonConverterFactory loadGsonConvertor() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit loadRetrofit(GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    @Singleton
    EmojiAPI loadEmojiAPIInterface(Retrofit retrofit) {
        return retrofit.create(EmojiAPI.class);
    }

}
