package com.uesleiramos.e_commerce.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uesleiramos.e_commerce.R
import com.uesleiramos.e_commerce.infrastructure.event.response.QuemViu
import com.uesleiramos.e_commerce.util.toMoney
import com.uesleiramos.e_commerce.util.withTypeface
import kotlinx.android.synthetic.main.rc_item_quem_viu.view.*


class QuemViuAdapter(private val listQuemView: List<QuemViu>) :
    RecyclerView.Adapter<QuemViuAdapter.QuemViuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuemViuViewHolder =
        QuemViuViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rc_item_quem_viu, parent, false)
        )

    override fun getItemCount(): Int = listQuemView.count()

    override fun onBindViewHolder(holder: QuemViuViewHolder, position: Int) =
        holder.bindView(listQuemView[position])

    inner class QuemViuViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.txt_name_quem_viu
        private val imagemUrl = view.img_product
        private val oldprice = view.txt_old_price_quem_viu
        private val currentprice = view.txt_current_price

        fun bindView(quemviu: QuemViu) {

            title.text = quemviu.nome
            oldprice.text = quemviu.precoAnterior.toMoney(true).withTypeface()
            currentprice.text = quemviu.precoAtual.toMoney(true)

            Glide
                .with(itemView)
                .load(quemviu.imagemUrl)
                .into(imagemUrl)
        }
    }
}