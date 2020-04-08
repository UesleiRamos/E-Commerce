package com.uesleiramos.e_commerce.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.uesleiramos.e_commerce.R
import com.uesleiramos.e_commerce.ui.adapter.ProductAdapter
import com.uesleiramos.e_commerce.ui.viewModel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_product.*

class ProductFragment : Fragment() {

    private lateinit var viewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_product, container, false)
        setObserver()
        viewModel.init()
        return root
    }

    fun setObserver() {
        viewModel.listProduct.observe(viewLifecycleOwner, Observer { listProduct ->
            listProduct?.let {
                with(rv_product) {
                    layoutManager = GridLayoutManager(context, 2)
                    setHasFixedSize(true)
                    adapter = ProductAdapter(listProduct) { product ->
                        findNavController().navigate(
                            R.id.action_nav_product_to_productDetailFragment,
                            Bundle().apply {
                                putString(TITLE_PAGE, product.nome)
                            })
                    }
                }
            }
        })

        viewModel.vfLiveDate.observe(viewLifecycleOwner, Observer {
            it?.let { viewFlipper ->
                view_flipper_product.displayedChild = viewFlipper.first

                viewFlipper.second?.let { erroMessagerResId ->
                    text_erro.text = getString(erroMessagerResId)

                }
            }
        })
    }

    companion object {
        const val TITLE_PAGE = "title"
    }
}
