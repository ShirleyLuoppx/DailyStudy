package com.gclibrary.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.gclibrary.util.BitmapUtils;
import com.gclibrary.util.ScrUtils;

import java.io.File;

/**
 * Created by 12985 on 2016/10/28.
 */
public class GlideUtils {
    public static void loadImage(Context context, String url, ImageView imageView, int drawNormal) {
        Glide.with(context)
                .load(url)
                .placeholder(drawNormal)
                .error(drawNormal)
//                .thumbnail(0.1f)//先显示缩略图0.1
                .crossFade()
                .into(imageView);
    }

    public static void loadImageOrg(final Context context, String url, final ImageView imageView, int drawNormal) {
        Glide.with(context).load(url)
                .asBitmap()
                .placeholder(drawNormal)
                .thumbnail(0.1f)//先显示缩略图0.1
//                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸又缓存其他尺寸
                .into(new BitmapImageViewTarget(imageView) {
                    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
                    @Override
                    protected void setResource(Bitmap resource) {
                        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        imageView.setImageBitmap(resource);
                    }
                });
    }

    public static void loadImage(Context context, int url, ImageView imageView, int drawNormal) {
        Glide.with(context)
                .load(url)
                .placeholder(drawNormal)
                .error(drawNormal)
                .thumbnail(0.1f)//先显示缩略图0.1
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸又缓存其他尺寸
                .crossFade()
                .into(imageView);
    }

    public static void loadImageRound(Context context, String url, ImageView imageView, int px, int drawNormal) {
        Glide.with(context)
                .load(url)
                .placeholder(drawNormal)
                .error(drawNormal)
                .transform(new GlideRoundTransform(context, (int) ScrUtils.getRealWidth(context, px)))
                .thumbnail(0.1f)//先显示缩略图0.1
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸又缓存其他尺寸
                .crossFade()
                .into(imageView);
    }

    public static void loadImageRoundDP(Context context, String url, ImageView imageView, int dp, int drawNormal) {
        Glide.with(context)
                .load(url)
                .placeholder(drawNormal)
                .error(drawNormal)
                .transform(new GlideRoundTransform(context, ScrUtils.Dp2Px(context, dp)))
                .thumbnail(0.1f)//先显示缩略图0.1
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸又缓存其他尺寸
                .crossFade()
                .into(imageView);
    }

    public static void loadImageCircle(final Context context, String url, final ImageView imageView, int drawNormal) {
        Glide.with(context).load(url)
                .asBitmap()
                .placeholder(drawNormal)
                .thumbnail(0.1f)//先显示缩略图0.1
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸又缓存其他尺寸
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
//                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
//                        circularBitmapDrawable.setCircular(true);

//                        RoundedBitmapDrawable circularBitmapDrawable =
//                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
//                        circularBitmapDrawable.setCornerRadius(25); //设置圆角弧度
//                        imageView.setImageDrawable(circularBitmapDrawable);

                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        imageView.setImageBitmap(BitmapUtils.getRoundedCornerBitmap2(resource));
                    }
                });
    }

    public static void loadImageCircleCenter(final Context context, String url, final ImageView imageView, int drawNormal) {
        Glide.with(context).load(url)
                .asBitmap()
                .placeholder(drawNormal)
                .thumbnail(0.1f)//先显示缩略图0.1
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸又缓存其他尺寸
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        imageView.setImageBitmap(BitmapUtils.getRoundedCornerBitmap2(resource));
                    }
                });
    }

    public static void loadImageFile(Context context, String path, ImageView imageView, int drawNormal) {

        Glide.with(context)                             //配置上下文
                .load(Uri.fromFile(new File(path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .error(drawNormal)           //设置错误图片
                .placeholder(drawNormal)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);
    }

    public static void loadImageFileOrg(Context context, String path, final ImageView imageView, int drawNormal) {
        Glide.with(context)
                .load(Uri.fromFile(new File(path)))
                .asBitmap()
                .placeholder(drawNormal)
//                .thumbnail(0.1f)//先显示缩略图0.1
//                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸又缓存其他尺寸
                .into(new BitmapImageViewTarget(imageView) {
                    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
                    @Override
                    protected void setResource(Bitmap resource) {
                        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        imageView.setImageBitmap(resource);
                    }
                });
    }

    public static void loadImage(Context context, String url, final GildeSuccess gildeSuccess) {
        Glide.with(context)
                .load(url)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸又缓存其他尺寸
                .into(new SimpleTarget() {
                    @Override
                    public void onResourceReady(Object o, GlideAnimation glideAnimation) {
                        try {
                            if (gildeSuccess != null) {
                                gildeSuccess.getGildeBitmap((Bitmap) o);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (gildeSuccess != null) {
                                gildeSuccess.getGildeBitmap(((GlideBitmapDrawable) o).getBitmap());
                            }
                        }
                    }
                });
    }

    public static Bitmap loadImageBitmap(Context context, String url, int drawNormal) {
        try {
            return Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeResource(context.getResources(), drawNormal);
    }

    public static void loadImage(Context context, String url, int drawNormal, final GildeSuccess gildeSuccess) {
        Glide.with(context)
                .load(url)
                .placeholder(drawNormal)
                .error(drawNormal)
                .fitCenter()
                .into(new SimpleTarget() {
                    @Override
                    public void onResourceReady(Object o, GlideAnimation glideAnimation) {
                        try {
                            if (gildeSuccess != null) {
                                gildeSuccess.getGildeBitmap((Bitmap) o);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (gildeSuccess != null) {
                                gildeSuccess.getGildeBitmap(((GlideBitmapDrawable) o).getBitmap());
                            }
                        }
                    }
                });
    }

    public static void loadImageGif(Context context, int url, ImageView imageView) {
        Glide.with(context).
                load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    public static void loadImageGif(Context context, int url, ImageView imageView, int count, final GildeSuccessOver gildeSuccessOver) {
        Glide.with(context).
                load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<Integer, GlideDrawable>() {

                    @Override
                    public boolean onException(Exception arg0, Integer arg1,
                                               Target<GlideDrawable> arg2, boolean arg3) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource,
                                                   Integer model, Target<GlideDrawable> target,
                                                   boolean isFromMemoryCache, boolean isFirstResource) {
                        // 计算动画时长
                        GifDrawable drawable = (GifDrawable) resource;
                        GifDecoder decoder = drawable.getDecoder();
                        int duration = 0;
                        for (int i = 0; i < drawable.getFrameCount(); i++) {
                            duration += decoder.getDelay(i);
                        }
                        if (gildeSuccessOver != null) {
                            gildeSuccessOver.gildeGifOver(duration);
                        }
                        return false;
                    }
                })
                .into(new GlideDrawableImageViewTarget(imageView, count));
    }

    public static void loadImageGif(Context context, String url, ImageView imageView, int count, final GildeSuccessOver gildeSuccessOver) {
        Glide.with(context).
                load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<String, GlideDrawable>() {


                    @Override
                    public boolean onException(Exception e, String s, Target<GlideDrawable> target, boolean b) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable glideDrawable, String s, Target<GlideDrawable> target, boolean b, boolean b1) {
                        // 计算动画时长
                        GifDrawable drawable = (GifDrawable) glideDrawable;
                        GifDecoder decoder = drawable.getDecoder();
                        int duration = 0;
                        for (int i = 0; i < drawable.getFrameCount(); i++) {
                            duration += decoder.getDelay(i);
                        }
                        if (gildeSuccessOver != null) {
                            gildeSuccessOver.gildeGifOver(duration);
                        }
                        return false;
                    }
                })
                .into(new GlideDrawableImageViewTarget(imageView, count));
    }


    public interface GildeSuccess {
        void getGildeBitmap(Bitmap bitmap);
    }

    public interface GildeSuccessOver {
        void gildeGifOver(int timeOver);
    }
}
