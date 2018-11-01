package com.steinfluss.sf_fullscreendialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class FullScreenDialog extends Dialog {

    private int resId;
    private Activity activity;
    private View view;

    private OnViewCreatedListener onViewCreatedListener;

    public FullScreenDialog(Activity act) {
        //step 1, required. to stretch the dialog to full screen
        super(act, R.style.full_screen_dialog);
        this.activity = act;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        view = activity.getLayoutInflater().inflate(resId, null);
        setContentView(view);
        keepStatusBar();
    }


    private void keepStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
    }

    @Override
    public void setOnShowListener(@Nullable OnShowListener listener) {
        super.setOnShowListener(listener);
    }

    public FullScreenDialog setContentViewRes(int resId) {
        this.resId = resId;
        return this;

    }

    public FullScreenDialog setOnViewCreatedListener(final OnViewCreatedListener onViewCreatedListener) {
        this.onViewCreatedListener = onViewCreatedListener;
        setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                onViewCreatedListener.onCreated(getView(), dialog);
            }
        });
        return this;
    }

    public View getView() {
        return view;
    }

    public interface OnViewCreatedListener {
        void onCreated(View view, DialogInterface dialogInterface);
    }
}