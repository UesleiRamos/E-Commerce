package com.uesleiramos.e_commerce.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.synnapps.carouselview.ImageListener
import com.uesleiramos.e_commerce.R
import com.uesleiramos.e_commerce.databinding.FragmentProductDetailBinding
import com.uesleiramos.e_commerce.infrastructure.event.response.ProductDetailResponse
import com.uesleiramos.e_commerce.ui.adapter.FeatureAdapter
import com.uesleiramos.e_commerce.ui.adapter.QuemViuAdapter
import com.uesleiramos.e_commerce.ui.viewModel.ProductDetailViewModel
import kotlinx.android.synthetic.main.fragment_product_detail.*
import kotlinx.android.synthetic.main.include_quem_viu.*


class ProductDetailFragment : Fragment() {

    private lateinit var viewModel: ProductDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ProductDetailViewModel::class.java)

        val binding: FragmentProductDetailBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_product_detail,
                container,
                false
            )

        binding.productDetailViewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.getProduct()
        setObserver()



        return binding.root
    }

    private fun setObserver() {
        viewModel.productDetail.observe(viewLifecycleOwner, Observer { produto ->
            produto?.let {
                viewModel.fillObject(produto)
                carregarImg(produto)

                with(rv_feature) {
                    layoutManager = LinearLayoutManager(
                        context,
                        RecyclerView.VERTICAL,
                        false
                    )
                    setHasFixedSize(true)
                    adapter = FeatureAdapter(produto.maisInformacoes.get(0).valores)
                }
            }
        })

        viewModel.listQuemViu.observe(viewLifecycleOwner, Observer { listQuemViu ->
            listQuemViu?.let {
                with(rv_quem_viu) {
                    layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                    setHasFixedSize(true)
                    adapter = QuemViuAdapter(listQuemViu)
                }
            }
        })

        viewModel.vfLiveDate.observe(viewLifecycleOwner, Observer {
            it?.let { viewFlipper ->
                view_flipper_detail.displayedChild = viewFlipper.first

                viewFlipper.second?.let { erroMessagerResId ->
                    text_erro_detail.text = getString(erroMessagerResId)
                }
            }
        })
    }

    private fun carregarImg(produtos: ProductDetailResponse) {
        val imageListener = ImageListener { position: Int, imageView: ImageView ->
            Glide.with(this)
                .load(produtos.modelo.padrao.imagens[position].url)
                .into(imageView)
        }
        carousel.setImageListener(imageListener)
        carousel.setPageCount(produtos.modelo.padrao.imagens.size)
    }
}
