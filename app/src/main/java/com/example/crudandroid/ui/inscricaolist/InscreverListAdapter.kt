package com.example.crudandroid.ui.inscricaolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crudandroid.R
import com.example.crudandroid.data.db.entity.InscricaoEntity
import kotlinx.android.synthetic.main.inscritos_item.view.*

class InscreverListAdapter(
    private val inscritos: List<InscricaoEntity>
) : RecyclerView.Adapter<InscreverListAdapter.SubscriberListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriberListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.inscritos_item, parent, false)

        return SubscriberListViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubscriberListViewHolder, position: Int) {
        holder.bindView(inscritos[position])
    }

    override fun getItemCount(): Int = inscritos.size

    class SubscriberListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewSubscriberName: TextView = itemView.text_subscriber_name
        private val textViewSubscriberEmail: TextView = itemView.text_subscriber_email

        fun bindView(subscriber: InscricaoEntity) {
            textViewSubscriberName.text = subscriber.name
            textViewSubscriberEmail.text = subscriber.email
        }
    }
}