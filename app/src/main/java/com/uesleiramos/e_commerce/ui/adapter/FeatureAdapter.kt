package com.uesleiramos.e_commerce.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uesleiramos.e_commerce.R
import com.uesleiramos.e_commerce.infrastructure.event.response.Valores
import kotlinx.android.synthetic.main.rc_item_feature.view.*


class FeatureAdapter(private val listValores: List<Valores>) :
    RecyclerView.Adapter<FeatureAdapter.ValoresViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValoresViewHolder =
        ValoresViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rc_item_feature, parent, false)
        )

    override fun getItemCount(): Int = listValores.count()

    override fun onBindViewHolder(holder: ValoresViewHolder, position: Int) =
        holder.bindView(listValores[position])

    inner class ValoresViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.lbl_titulo
        private val descricao = view.lbl_desc

        fun bindView(valores: Valores) {
            title.text = valores.nome
            descricao.text = valores.valor
        }
    }
}