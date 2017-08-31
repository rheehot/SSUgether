package org.collapsed.ssuparty_android.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.StorageReference;

public class ImageUtil {

    public static void loadUrlImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

}
