package com.uesleiramos.e_commerce.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uesleiramos.e_commerce.R
import com.uesleiramos.e_commerce.infrastructure.event.response.Product
import com.uesleiramos.e_commerce.util.toMoney
import com.uesleiramos.e_commerce.util.withTypeface
import kotlinx.android.synthetic.main.rc_item_product.view.*


class ProductAdapter(private val listProduct: List<Product>, val onClick: (Product) -> Unit) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rc_item_product, parent, false)
        )

    override fun getItemCount(): Int = listProduct.count()

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bindView(listProduct[position])

    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.txt_name
        private val imagemUrl = view.img_product
        private val oldprice = view.txt_old_price
        private val currentprice = view.txt_current_price

        fun bindView(product: Product) {
            with(itemView) { setOnClickListener { onClick.invoke(product) } }

            title.text = product.nome
            oldprice.text = product.preco.precoAnterior.toMoney(true).withTypeface()
            currentprice.text = product.preco.precoAtual.toMoney(true)

            Glide
                .with(itemView)
                .load(product.imagemUrl)
                .into(imagemUrl)
        }
    }
}