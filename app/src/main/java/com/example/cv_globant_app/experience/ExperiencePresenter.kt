package com.example.cv_globant_app.experience

import android.util.Log
import com.example.cv_globant_app.common.GeneralContract
import com.example.cv_globant_app.data.sources.GeneralDataSource

class ExperiencePresenter(private var mView: GeneralContract.View?, private val mRepo: GeneralDataSource) :
    GeneralContract.Presenter {
    override fun getInfo(isActivityFirstCreated: Boolean) {
        if (isActivityFirstCreated) {
            mRepo.getExperience(object : GeneralDataSource.GeneralLoadCallback {
                override fun <T> onSuccess(info: T) {
                    mView?.paintInfo(info)
                }

                override fun onFailure(t: Throwable) {
                    // ¯\_(ツ)_/¯
                    Log.e("getInfo", "Knowledge call Failed")
                    throw t
                }
            })
        }
    }

    override fun finalize() {
        mView = null
    }
}