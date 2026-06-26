package com.example.dummy.ui.dialog

import android.app.Dialog
import android.content.Context
import android.view.KeyEvent
import com.example.dummy.R

class LoadingDialog(context: Context) : Dialog(context) {
    init {
        setContentView(R.layout.custom_progress_dialog)

        window?.setBackgroundDrawableResource(android.R.color.transparent)

        setCancelable(false)


        setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                dismiss()
                true
            } else {
                false
            }
        }

    }

    override fun show() {
        if (!isShowing)
            super.show()
    }

    override fun dismiss() {
        try {
            if (isShowing)
                super.dismiss()
        } catch (_: Exception) {

        }
    }
}