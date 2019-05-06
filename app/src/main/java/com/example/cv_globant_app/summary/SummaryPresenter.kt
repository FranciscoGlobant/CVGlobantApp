package com.example.cv_globant_app.summary

import android.os.Bundle
import android.util.Log
import com.example.cv_globant_app.common.GeneralContract
import com.example.cv_globant_app.data.GeneralRepository
import com.example.cv_globant_app.data.sources.GeneralDataSource

class SummaryPresenter(private var mView: GeneralContract.View?, private val mRepo: GeneralDataSource) :
    GeneralContract.Presenter {
    override fun getInfo(isStateSaved: Bundle?) {
        if (isStateSaved == null) {
            mRepo.getSummary(object : GeneralDataSource.GeneralLoadCallback {
                override fun <T> onSuccess(info: T) {
                    mView?.paintInfo(info)
                }

                override fun onFailure(t: Throwable) {
                    // ¯\_(ツ)_/¯
                    Log.e("getInfo", "Summary call Failed")
                    throw t
                }
            })
        }
    }

    override fun finalize() {
        mView = null
    }
}