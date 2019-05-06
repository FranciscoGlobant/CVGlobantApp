package com.example.cv_globant_app.knowledge.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cv_globant_app.R
import com.example.cv_globant_app.data.model.KnowledgeModel

class KnowledgeAdapter(private val mData: KnowledgeModel): RecyclerView.Adapter<KnowledgeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.knowledge_adapter_view, parent, false) as TextView
        return ViewHolder(textView)
    }

    override fun getItemCount(): Int {
        return mData.knowledgeItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv.text = mData.knowledgeItems[position]
    }

    class ViewHolder(val tv: TextView): RecyclerView.ViewHolder(tv)
}