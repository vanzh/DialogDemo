package com.vanzh.dialogdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class CirclePublicChoiceDialog extends DialogFragment implements View.OnClickListener {
    private static final String TAG = "CirclePublicChoiceDialog";
    private TextView escTv;
    private TextView okTv;
    private MyTextView tvTitle;
    private TextView topTitleTv;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View parentView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_circle_public_choice_layout, container, false);
        topTitleTv = parentView.findViewById(R.id.dialog_circle_choice_top_title_tv);
        if(TextUtils.isEmpty(topTitleDesc.toString())) {
            topTitleTv.setVisibility(View.GONE);
        }else {
            topTitleTv.setVisibility(View.VISIBLE);
            topTitleTv.setText(topTitleDesc);
        }
        tvTitle = parentView.findViewById(R.id.dialog_circle_choice_title_tv);
        tvTitle.setText(titleDesc);
        tvTitle.requestLayout();
        escTv = parentView.findViewById(R.id.dialog_circle_choice_esc_tv);
        escTv.setText(escDesc);
        escTv.setOnClickListener(this);
        okTv = parentView.findViewById(R.id.dialog_circle_choice_ok_tv);
        okTv.setText(okDesc);
        okTv.setOnClickListener(this);
        return parentView;
    }

    @Override
    public void onClick(View v) {
        if(v == escTv) {
            dismiss();
            if(mOnDialogFragmentClickListener != null) {
                mOnDialogFragmentClickListener.onCancelClick();
            }
        }else if(v == okTv) {
            dismiss();
            if(mOnDialogFragmentClickListener != null) {
                mOnDialogFragmentClickListener.onOkClick(this, null);
            }
        }
    }

    public static CirclePublicChoiceDialog builder(FragmentManager fm) {
        CirclePublicChoiceDialog dialog =  new CirclePublicChoiceDialog();
        dialog.setFragmentManager(fm);
        return dialog;
    }

    private FragmentManager fragmentManager;
    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    private CharSequence topTitleDesc;
    public CirclePublicChoiceDialog makeTopTitleDesc(CharSequence topTitleDesc) {
        this.topTitleDesc = topTitleDesc;
        return this;
    }

    private CharSequence titleDesc;
    public CirclePublicChoiceDialog makeTitleDesc(CharSequence titleDesc) {
        this.titleDesc = titleDesc;
        return this;
    }

    private CharSequence okDesc = "确定";
    public CirclePublicChoiceDialog makeOkDesc(CharSequence okDesc) {
        this.okDesc = okDesc;
        return this;
    }

    private CharSequence escDesc = "取消";
    public CirclePublicChoiceDialog makeEscDesc(CharSequence escDesc) {
        this.escDesc = escDesc;
        return this;
    }

    private OnDialogFragmentClickListener mOnDialogFragmentClickListener;
    public void show(OnDialogFragmentClickListener mOnDialogFragmentClickListener) {
        this.mOnDialogFragmentClickListener = mOnDialogFragmentClickListener;
        if(null == getDialog() || !getDialog().isShowing()) {
            show(fragmentManager, getTAG());
        }
    }

    public static String getTAG() {
        return TAG;
    }
}
