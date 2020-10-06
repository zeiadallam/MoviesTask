package com.coderbot.movies.domain.utils

import android.content.Context
import com.coderbot.movies.presentation.view.loading.LoadingDialog

class Views {

    class LoadingView(context: Context) {
        private val loading: LoadingDialog = LoadingDialog(context)

        fun show() {
            if (!loading.isShowing) {
                loading.show()
            }
        }

        fun dismiss() {
            if (loading.isShowing) {
                loading.dismiss()
            }
        }
    }

}