/*
 * This is the source code of Telegram for Android v. 3.x.x.
 * It is licensed under GNU GPL v. 2 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Nikolai Kudashov, 2013-2016.
 */

package org.telegram.ui.Cells;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.R;
import org.telegram.ui.Components.LayoutHelper;

public class TextInfoPrivacyCell extends FrameLayout {

    private TextView textView;

    public TextInfoPrivacyCell(Context context) {
        super(context);

        textView = new TextView(context);
        textView.setTextColor(0xff808080);
        textView.setLinkTextColor(0xff316f9f);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        textView.setGravity(LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT);
        textView.setPadding(0, AndroidUtilities.dp(10), 0, AndroidUtilities.dp(17));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        addView(textView, LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, (LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP, 17, 0, 17, 0));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        setTheme();
    }

    public void setText(CharSequence text) {
        textView.setText(text);
    }

    public void setTextColor(int color) {
        textView.setTextColor(color);
    }

    private void setTheme(){
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences(AndroidUtilities.THEME_PREFS, AndroidUtilities.THEME_PREFS_MODE);

        int summaryColor = preferences.getInt("prefSummaryColor", 0xff808080);
        int shadowColor = preferences.getInt("prefShadowColor", 0xfff0f0f0);
        String tag = getTag() != null ? getTag().toString() : "";
        if(tag.contains("Profile")){
            summaryColor = preferences.getInt("profileSummaryColor", 0xff808080);
        }else{
            if(shadowColor != 0xfff0f0f0) {
                setBackgroundColor(shadowColor);
            } else {
                setBackgroundResource(R.drawable.greydivider);
            }
        }
        textView.setTextColor(summaryColor);
    }
}
