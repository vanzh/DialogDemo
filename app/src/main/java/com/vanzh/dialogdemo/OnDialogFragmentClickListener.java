package com.vanzh.dialogdemo;

import android.support.v4.app.DialogFragment;

public interface OnDialogFragmentClickListener {
    void onOkClick(DialogFragment mDialog, Object extra);
    void onCancelClick();
}
