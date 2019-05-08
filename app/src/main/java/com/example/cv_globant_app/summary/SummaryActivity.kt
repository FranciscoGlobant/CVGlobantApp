package com.example.cv_globant_app.summary

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.cv_globant_app.R
import com.example.cv_globant_app.common.GeneralContract
import com.example.cv_globant_app.data.GeneralRepository
import com.example.cv_globant_app.data.model.SummaryModel
import com.example.cv_globant_app.knowledge.KnowledgeActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.summary_activity.*

class SummaryActivity : AppCompatActivity(), GeneralContract.View {

    private lateinit var mPresenter: SummaryPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.summary_activity)
        initPresenter(savedInstanceState == null)
        setUpView()
    }

    private fun initPresenter(isActivityFirstCreated: Boolean) {
        mPresenter = SummaryPresenter(this, GeneralRepository())
        mPresenter.getInfo(isActivityFirstCreated)
    }

    private fun setUpView() {
        btn_next_summary.setOnClickListener { goToNextPage() }
    }

    private fun goToNextPage() {
        startActivity(Intent(this, KnowledgeActivity::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.finalize()
    }

    override fun <T> paintInfo(info: T) {
        val summary = info as SummaryModel
        tv_name_summary.text = summary.name
        tv_experience_summary.text = summary.experience.toString()
        tv_expertise_summary.text = summary.expertise
        Picasso.get().load(summary.picURL).fit().centerCrop().into(iv_summary)
    }

    override fun notifyError(t: Throwable) {
        Log.e("getInfo", "Summary call Failed")
        throw t
    }
}