package org.collapsed.ssuparty_android.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import org.collapsed.ssuparty_android.R;

public class CustomFontTextView extends AppCompatTextView {

    public CustomFontTextView(Context context) {
        super(context);
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setCustomFont(context, attrs);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray typedArray = ctx.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        String customFont = typedArray.getString(R.styleable.CustomTextView_customFont);
        this.setCustomFont(ctx, customFont);
        typedArray.recycle();
    }

    public boolean setCustomFont(Context ctx, String asset) {
        super.setTypeface(FontCache.getFont(ctx, asset));
        return true;
    }
}
