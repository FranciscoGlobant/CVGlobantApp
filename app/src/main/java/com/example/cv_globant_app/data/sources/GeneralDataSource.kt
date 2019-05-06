package com.example.cv_globant_app.data.sources

import com.example.cv_globant_app.data.model.ExperienceModel
import com.example.cv_globant_app.data.model.KnowledgeModel
import com.example.cv_globant_app.data.model.SummaryModel
import retrofit2.Call
import retrofit2.http.GET

interface GeneralDataSource {
    fun getExperience(callback: GeneralLoadCallback)

    fun getKnowledge(callback: GeneralLoadCallback)

    fun getSummary(callback: GeneralLoadCallback)

    interface CVServices {
        @GET("e23f0e5f24655212834d1e9e979cd1c8/raw/56f6386253f33c1e9af10c257e8278782ef6e0b1/experience.json")
        fun getExperience(): Call<ExperienceModel>

        @GET("e23f0e5f24655212834d1e9e979cd1c8/raw/3a159bba90331156499a1234d16f46d24df0a3d6/knowledge.json")
        fun getKnowledge(): Call<KnowledgeModel>

        @GET("e23f0e5f24655212834d1e9e979cd1c8/raw/14c2e5d38d7ca6b1fd06474e269f247ba1c1e052/summary.json")
        fun getSummary(): Call<SummaryModel>
    }

    interface GeneralLoadCallback {
        fun <T> onSuccess(info: T)
        fun onFailure(t: Throwable)
    }
}