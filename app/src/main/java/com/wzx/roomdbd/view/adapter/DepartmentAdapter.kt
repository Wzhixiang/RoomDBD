package com.wzx.roomdbd.view.adapter

import android.support.annotation.Nullable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.wzx.roomdbd.R
import com.wzx.roomdbd.db.Department

/**
 * 描述：
 *
 * 创建人： Administrator
 * 创建时间： 2018/9/25
 * 更新时间：
 * 更新内容：
 */
class DepartmentAdapter(@Nullable var data: List<Department>?) : RecyclerView.Adapter<DepartmentAdapter.DepartmentViewHolder>() {

    var seletecPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DepartmentViewHolder =
            DepartmentViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_department, parent, false))

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: DepartmentViewHolder?, position: Int) {
        holder?.mNameTV?.text = data!![position].name

        if (seletecPosition == position) {
            holder?.itemView!!.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.colorAccent))
        } else {
            holder?.itemView!!.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, android.R.color.white))
        }

        holder?.itemView?.setOnClickListener {
            if (seletecPosition == position) {
                seletecPosition = -1
                notifyItemChanged(position)
            } else {
                seletecPosition = position
                notifyDataSetChanged()
            }
        }
    }

    fun newData(data: List<Department>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun selectedDepartment(): Department? {
        if (seletecPosition > -1) {
            return data!![seletecPosition]
        } else {
            return null
        }
    }

    class DepartmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.tv_department)
        lateinit var mNameTV: TextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}