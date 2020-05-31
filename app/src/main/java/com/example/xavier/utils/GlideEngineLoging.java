package com.example.xavier.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.xavier.R;

import static com.example.xavier.app.api.ConstantPool.DEFAULT_RADIUS;
import static com.example.xavier.app.api.ConstantPool.SIZE_MULTIPLIER;

public class GlideEngineLoging implements ImageEngineLoging {

    @Override
    public void loadImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView) {
        Glide.with(context).asBitmap().load(url).into(imageView);
    }

    @Override
    public void loadAsBitmapImage(@NonNull Context context, @NonNull String url,
                                  @NonNull ImageView imageView, int placeholderId) {
        Glide.with(context)
                .asBitmap()
                .override(180, 180)
                .centerCrop()
                .sizeMultiplier(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeholderId)
                .load(url)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.
                                        create(context.getResources(), resource);
                        circularBitmapDrawable.setCornerRadius(8);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    @Override
    public void loadAsGifImage(@NonNull Context context, @NonNull String url,
                               @NonNull ImageView imageView) {
        Glide.with(context)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .priority(Priority.HIGH)
                .load(url)
                .into(imageView);
    }

    @Override
    public void loadAsBitmapGridImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView, int placeholderId) {
        Glide.with(context)
                .asBitmap()
                .override(200, 200)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeholderId)
                .load(url)
                .into(imageView);
    }


    private GlideEngineLoging() {
    }

    private static GlideEngineLoging instance;

    public static GlideEngineLoging createGlideEngine() {
        if (null == instance) {
            synchronized (GlideEngineLoging.class) {
                if (null == instance) {
                    instance = new GlideEngineLoging();
                }
            }
        }
        return instance;
    }

    ///////////////////////////////////////////////////////////////////////////
    //
    // xin-xavier
    //
    ///////////////////////////////////////////////////////////////////////////
    // Gif 不进行磁盘缓存 优先加载
    public void loadAsGifImage(@NonNull Context context, @DrawableRes int resId,
                               @NonNull ImageView imageView) {
        Glide.with(context)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .priority(Priority.HIGH)
                .load(resId)
                .into(imageView);
    }

    // 上下文判断 Bitmap 中心作物 默认压缩 三级缓存 默认空图片 默认圆角
    public void loadDefaultImage(Context context, @NonNull String url,
                                 @NonNull ImageView imageView) {
        if (context != null) {
            Glide.with(context)
                    .asBitmap()
                    .centerCrop() // 中心作物
                    .sizeMultiplier(SIZE_MULTIPLIER)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.null_map)//图片加载出来前，显示的图片
                    .fallback(R.drawable.null_map) //url为空的时候,显示的图片
                    .error(R.drawable.null_map)//图片加载失败后，显示的图片
                    .load(url)
                    .into(new BitmapImageViewTarget(imageView) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.
                                            create(context.getResources(), resource);
                            circularBitmapDrawable.setCornerRadius(SizeUtils.dp2px(DEFAULT_RADIUS));
                            imageView.setImageDrawable(circularBitmapDrawable);
                        }
                    });
        } else {
            imageView.setImageResource(R.drawable.placeholder_map);
        }
    }

    // 上下文判断 Bitmap 中心作物 默认压缩 三级缓存 默认占位图 默认圆角
    public void loadDefaultMapImage(Context context, @NonNull String url,
                                    @NonNull ImageView imageView) {
        if (context != null) {
            Glide.with(context)
                    .asBitmap()
                    .centerCrop() // 中心作物
                    .sizeMultiplier(SIZE_MULTIPLIER)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.placeholder_map)//图片加载出来前，显示的图片
                    .fallback(R.drawable.placeholder_map) //url为空的时候,显示的图片
                    .error(R.drawable.placeholder_map)//图片加载失败后，显示的图片
                    .load(url)
                    .into(new BitmapImageViewTarget(imageView) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.
                                            create(context.getResources(), resource);
                            circularBitmapDrawable.setCornerRadius(SizeUtils.dp2px(DEFAULT_RADIUS));
                            imageView.setImageDrawable(circularBitmapDrawable);
                        }
                    });
        } else {
            imageView.setImageResource(R.drawable.placeholder_map);
        }
    }

    // 上下文判断 Bitmap 中心作物 默认压缩 三级缓存 指定图片 默认圆角
    public void loadImageAsResId(Context context, @NonNull String url, @DrawableRes int resId,
                                 @NonNull ImageView imageView) {
        if (context != null) {
            Glide.with(context)
                    .asBitmap()
                    .centerCrop() // 中心作物
                    .sizeMultiplier(SIZE_MULTIPLIER)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(resId)//图片加载出来前，显示的图片
                    .fallback(resId) //url为空的时候,显示的图片
                    .error(resId)//图片加载失败后，显示的图片
                    .load(url)
                    .into(new BitmapImageViewTarget(imageView) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.
                                            create(context.getResources(), resource);
                            circularBitmapDrawable.setCornerRadius(SizeUtils.dp2px(DEFAULT_RADIUS));
                            imageView.setImageDrawable(circularBitmapDrawable);
                        }
                    });
        } else {
            imageView.setImageResource(resId);
        }
    }

    // 上下文判断 Bitmap 中心作物 默认压缩 三级缓存 指定图片 指定圆角
    public void loadImageAsResRadiusId(Context context, @NonNull String url, @DrawableRes int resId, int radius,
                                       @NonNull ImageView imageView) {
        if (context != null) {
            Glide.with(context)
                    .asBitmap()
                    .centerCrop() // 中心作物
                    .sizeMultiplier(SIZE_MULTIPLIER)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(resId)//图片加载出来前，显示的图片
                    .fallback(resId) //url为空的时候,显示的图片
                    .error(resId)//图片加载失败后，显示的图片
                    .load(url)
                    .into(new BitmapImageViewTarget(imageView) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.
                                            create(context.getResources(), resource);
                            circularBitmapDrawable.setCornerRadius(radius);
                            imageView.setImageDrawable(circularBitmapDrawable);
                        }
                    });
        } else {
            imageView.setImageResource(resId);
        }
    }

}
