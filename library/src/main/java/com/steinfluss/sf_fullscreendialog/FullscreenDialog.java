package com.steinfluss.sf_fullscreendialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class FullscreenDialog extends Dialog {

    public static final String TAG = "FullScreenDialog";

    public int resId;

    public View view;

    private OnViewCreatedListener onViewCreatedListener;

    public FullscreenDialog(Activity act){
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
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
    }

    @Override
    public View onCreatePanelView(int featureId) {

        View contentView = super.onCreatePanelView(featureId);
        view = contentView;
        if (onViewCreatedListener != null){
            onViewCreatedListener.onCreated(view);
        }
        return contentView;
    }

    public FullscreenDialog setContentViewRes(int resId){
        this.resId = resId;
        return this;
    }


    public FullscreenDialog setOnViewCreatedListener(OnViewCreatedListener onViewCreatedListener){
        this.onViewCreatedListener = onViewCreatedListener;
        return this;
    }

    public View getView(){
        return view;
    }

    public interface OnViewCreatedListener {
        void onCreated(View view);
    }
}