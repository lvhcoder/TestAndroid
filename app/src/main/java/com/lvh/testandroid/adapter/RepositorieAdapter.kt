package com.lvh.testandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lvh.testandroid.R
import com.lvh.testandroid.model.RepositoriesEntity
import kotlinx.android.synthetic.main.item_resposi.view.*

class RepositorieAdapter(var list: List<RepositoriesEntity>?, var callback: ((RepositoriesEntity?) -> Unit)? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_resposi, parent, false))
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var commonComponent = list?.get(position)
        var hd = holder as ViewHolder
        hd?.bindData(commonComponent)
        hd.itemView.setOnClickListener {
            callback?.invoke(commonComponent)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(item: RepositoriesEntity?) {
            itemView.tvUrl.text = item?.html_url
        }
    }

}