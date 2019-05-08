package com.example.cv_globant_app.experience

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cv_globant_app.R
import com.example.cv_globant_app.common.GeneralContract
import com.example.cv_globant_app.data.GeneralRepository
import com.example.cv_globant_app.data.model.ExperienceItem
import com.example.cv_globant_app.data.model.ExperienceModel
import com.example.cv_globant_app.experience.adapters.ExperienceAdapter
import kotlinx.android.synthetic.main.experience_activity.*

class ExperienceActivity : AppCompatActivity(), GeneralContract.View {
    private lateinit var mPresenter: ExperiencePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.experience_activity)
        initPresenter(savedInstanceState == null)
    }

    private fun initPresenter(isActivityFirstCreated: Boolean) {
        mPresenter = ExperiencePresenter(this, GeneralRepository())
        mPresenter.getInfo(isActivityFirstCreated)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.finalize()
    }

    override fun <T> paintInfo(info: T) {
        setupRecyclerView((info as ExperienceModel).experienceItems)
    }

    private fun setupRecyclerView(data: List<ExperienceItem>) {
        rv_items_experience.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ExperienceAdapter(data)
        }
    }
}