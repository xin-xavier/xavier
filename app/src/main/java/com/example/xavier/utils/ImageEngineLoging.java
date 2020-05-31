package com.example.xavier.utils;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;


public interface ImageEngineLoging {
    void loadImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView);

    void loadAsBitmapImage(@NonNull Context context, @NonNull String url,
                           @NonNull ImageView imageView, @DrawableRes int placeholderId);

    void loadAsGifImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView);

    void loadAsBitmapGridImage(@NonNull Context context, @NonNull String url,
                               @NonNull ImageView imageView, @DrawableRes int placeholderId);
}

