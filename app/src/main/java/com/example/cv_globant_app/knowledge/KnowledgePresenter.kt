package com.example.cv_globant_app.knowledge

import com.example.cv_globant_app.common.GeneralContract
import com.example.cv_globant_app.data.sources.GeneralDataSource

class KnowledgePresenter(private var mView: GeneralContract.View?, private val mRepo: GeneralDataSource) :
    GeneralContract.Presenter {
    override fun getInfo(isActivityFirstCreated: Boolean) {
        if (isActivityFirstCreated) {
            mRepo.getKnowledge(object : GeneralDataSource.GeneralLoadCallback {
                override fun <T> onSuccess(info: T) {
                    mView?.paintInfo(info)
                }

                override fun onFailure(t: Throwable) {
                    // ¯\_(ツ)_/¯
                    mView?.notifyError(t)
                }
            })
        }
    }

    override fun finalize() {
        mView = null
    }
}