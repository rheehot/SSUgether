package org.collapsed.ssuparty_android.utils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.StorageReference;

public class ImageUtil {

    public static void loadImageForUrl(ImageView imageView, String url, Drawable errorDrawable) {
        Glide.with(imageView.getContext()).load(url).error(errorDrawable).into(imageView);
    }

    public static void loadImageForFirebase(ImageView imageView, StorageReference storageReference) {
        Glide.with(imageView.getContext()).using(new FirebaseImageLoader()).load(storageReference).into(imageView);
    }

}
