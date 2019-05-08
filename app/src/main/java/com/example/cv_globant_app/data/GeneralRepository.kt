package com.example.cv_globant_app.data

import com.example.cv_globant_app.data.sources.GeneralDataSource
import com.example.cv_globant_app.data.sources.remote.GeneralRemoteDataSource

class GeneralRepository: GeneralDataSource {
    override fun getExperience(callback: GeneralDataSource.GeneralLoadCallback) {
        return GeneralRemoteDataSource.getExperience(callback)
    }

    override fun getKnowledge(callback: GeneralDataSource.GeneralLoadCallback) {
        return GeneralRemoteDataSource.getKnowledge(callback)
    }

    override fun getSummary(callback: GeneralDataSource.GeneralLoadCallback) {
        return GeneralRemoteDataSource.getSummary(callback)
    }
}