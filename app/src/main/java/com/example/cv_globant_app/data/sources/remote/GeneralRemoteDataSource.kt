package com.example.cv_globant_app.data.sources.remote

import com.example.cv_globant_app.data.model.ExperienceModel
import com.example.cv_globant_app.data.model.KnowledgeModel
import com.example.cv_globant_app.data.model.SummaryModel
import com.example.cv_globant_app.data.sources.GeneralDataSource
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GeneralRemoteDataSource : GeneralDataSource {

    private val httpClient = OkHttpClient()
    private val service: GeneralDataSource.CVServices
    private const val ENDPOINT = "https://gist.githubusercontent.com/FranciscoGlobant/"

    init {
        val retrofit = createAdapter().build()
        service = retrofit
            .create<GeneralDataSource.CVServices>(GeneralDataSource.CVServices::class.java)
    }

    private fun createAdapter(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
    }

    override fun getExperience(callback: GeneralDataSource.GeneralLoadCallback) {
        return service.getExperience().enqueue(object : Callback<ExperienceModel> {
            override fun onFailure(call: Call<ExperienceModel>, t: Throwable) {
                callback.onFailure(t)
            }

            override fun onResponse(call: Call<ExperienceModel>, response: Response<ExperienceModel>) {
                val experience = response.body() ?: ExperienceModel()
                callback.onSuccess(experience)
            }
        })
    }

    override fun getKnowledge(callback: GeneralDataSource.GeneralLoadCallback) {
        return service.getKnowledge().enqueue(object : Callback<KnowledgeModel> {
            override fun onFailure(call: Call<KnowledgeModel>, t: Throwable) {
                callback.onFailure(t)
            }

            override fun onResponse(call: Call<KnowledgeModel>, response: Response<KnowledgeModel>) {
                val knowledge = response.body() ?: KnowledgeModel()
                callback.onSuccess(knowledge)
            }
        })
    }

    override fun getSummary(callback: GeneralDataSource.GeneralLoadCallback) {
        return service.getSummary().enqueue(object :Callback<SummaryModel>{
            override fun onFailure(call: Call<SummaryModel>, t: Throwable) {
                callback.onFailure(t)
            }

            override fun onResponse(call: Call<SummaryModel>, response: Response<SummaryModel>) {
                val summary = response.body() ?: SummaryModel()
                callback.onSuccess(summary)
            }
        })
    }
}