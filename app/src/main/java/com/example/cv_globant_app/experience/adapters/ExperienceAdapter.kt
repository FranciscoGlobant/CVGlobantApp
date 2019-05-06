package com.example.cv_globant_app.experience.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cv_globant_app.R
import com.example.cv_globant_app.data.model.ExperienceItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.experience_element.view.*

class ExperienceAdapter(private val mData: List<ExperienceItem>) :
    RecyclerView.Adapter<ExperienceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.experience_element, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exp = mData[position]
        val datesString = holder.v.context.getString(R.string.from_to_date, exp.fromDate, exp.toDate)
        holder.bindViews(exp, datesString)
    }

    class ViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
        fun bindViews(exp: ExperienceItem, datesString: String) {
            Picasso.get().load(exp.companyLogo).fit().centerCrop().into(v.iv_company_logo)
            v.tv_company_name.text = exp.companyName
            v.tv_company_role.text = exp.role
            v.tv_company_dates.text = datesString
        }
    }
}