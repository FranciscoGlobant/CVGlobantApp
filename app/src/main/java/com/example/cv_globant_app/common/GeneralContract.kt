package com.example.cv_globant_app.common

import android.os.Bundle

interface GeneralContract {
    interface View {
        fun <T> paintInfo(info: T)
    }

    interface Presenter {
        fun getInfo(isActivityFirstCreated: Boolean)
        fun finalize()
    }
}