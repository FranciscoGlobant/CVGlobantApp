package com.example.cv_globant_app.knowledge

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cv_globant_app.R
import com.example.cv_globant_app.common.GeneralContract
import com.example.cv_globant_app.data.GeneralRepository
import com.example.cv_globant_app.data.model.KnowledgeModel
import com.example.cv_globant_app.experience.ExperienceActivity
import com.example.cv_globant_app.knowledge.adapters.KnowledgeAdapter
import kotlinx.android.synthetic.main.knowledge_activity.*

class KnowledgeActivity : AppCompatActivity(), GeneralContract.View {

    private lateinit var mPresenter: KnowledgePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.knowledge_activity)
        initPresenter(savedInstanceState == null)
        setUpView()
    }

    private fun initPresenter(isActivityFirstCreated: Boolean) {
        mPresenter = KnowledgePresenter(this, GeneralRepository())
        mPresenter.getInfo(isActivityFirstCreated)
    }

    private fun setUpView() {
        btn_next_knowledge.setOnClickListener { goToNextPage() }
    }

    private fun goToNextPage() {
        startActivity(Intent(this, ExperienceActivity::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.finalize()
    }

    override fun <T> paintInfo(info: T) {
        setupRecyclerView(info as KnowledgeModel)
    }

    private fun setupRecyclerView(knowledge: KnowledgeModel) {
        rv_items_knowledge.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = KnowledgeAdapter(knowledge)
        }
    }

    override fun notifyError(t: Throwable) {
        Log.e("getInfo", "Knowledge call Failed")
        throw t
    }
}