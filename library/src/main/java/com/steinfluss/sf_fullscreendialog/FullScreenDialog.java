package com.steinfluss.sf_fullscreendialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class FullScreenDialog extends Dialog {

    public static final String TAG = "FullScreenDialog";

    private int resId;

    public FullScreenDialog(Activity act){
        //step 1, required. to stretch the dialog to full screen
        super(act, R.style.full_screen_dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(resId);
        keepStatusBar();
    }

    private void keepStatusBar(){
        try {
            WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(attrs);
        }catch (Exception ae){}
    }

    public FullScreenDialog setContentViewRes(int resId){
        this.resId = resId;
        return this;
    }
}