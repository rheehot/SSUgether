package org.collapsed.ssuparty_android.event.profile;

import android.graphics.Bitmap;
import android.net.Uri;

public class ImageEvent {
    Uri mImageUri;

    public ImageEvent(Uri imageUri){
        this.mImageUri = imageUri;
    }

    public Uri getImageUri(){
        return mImageUri;
    }

}
