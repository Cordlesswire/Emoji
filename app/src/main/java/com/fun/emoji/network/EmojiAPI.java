package com.fun.emoji.network;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmojiAPI {

    @GET("/emojis")
    Call<HashMap<String,String>> loadEmojiList();
}
