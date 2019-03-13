package com.vanzh.dialogdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun click(v: View) {
        val tips = "你已在一个圈子中，加入新的圈子后，你将从当前圈子退出，并清零当前圈子所有贡献。圈子1天内只能更换1次哟。"
        CirclePublicChoiceDialog.builder(supportFragmentManager)
            .makeTopTitleDesc("")
            .makeTitleDesc(tips)
            .makeEscDesc("取消")
            .makeOkDesc("确定加入")
            .show(object : OnDialogFragmentClickListener{
                override fun onCancelClick() {

                }

                override fun onOkClick(mDialog: DialogFragment?, extra: Any?) {

                }
            })
    }
}
