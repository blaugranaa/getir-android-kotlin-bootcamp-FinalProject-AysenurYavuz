package com.getir.patika.getir_android_kotlin_bootcamp_finalcase_aysenur_


import GlobalDataHolder
import ProductPage

import ShoppingCart
import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings.Global
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale


class HomeFragment : Fragment() {
    val shoppingCart = ShoppingCart()



    private lateinit var dataAdapter: DataAdapter
    private lateinit var dataAdapter2: DataAdapter
    private lateinit var viewModel: ShoppingCart.ShoppingCartViewModel

    private val dataRepository = DataRepository()
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        viewModel = ViewModelProvider(this).get(ShoppingCart.ShoppingCartViewModel::class.java)

        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val layoutManager2 = GridLayoutManager(activity,3)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recylerView)
        val recyclerView2 = view.findViewById<RecyclerView>(R.id.recylerView2)
        val close = requireActivity().findViewById<ImageView>(R.id.imageView5)
        close.visibility = View.GONE
        recyclerView.layoutManager = layoutManager

        dataAdapter = DataAdapter(
            onAddToCartClickListener = { position ->
                val product = dataAdapter.getProducts()[position]
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position) as DataAdapter.DataViewHolder

                shoppingCart.addItem(product)


                var x = shoppingCart.getQuantityOfProduct(product.id)
                viewHolder.updateQuantity(x.toString())
                if(x!=0)
                    viewHolder.updateImage(true)
                else
                    viewHolder.updateImage(false)

                update()
            },
            onRemoveFromCartClickListener = { position ->
                val product = dataAdapter.getProducts()[position]
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position) as DataAdapter.DataViewHolder
                shoppingCart.removeItem(product)
                var x = shoppingCart.getQuantityOfProduct(product.id)
                viewModel.addItem(product)
                viewHolder.updateQuantity(x.toString())
                if(x!=0)
                    viewHolder.updateImage(true)
                else
                    viewHolder.updateImage(false)
                update()
            },
            onImageClickListener = { position ->
                val product = dataAdapter.getProducts()[position]
                navigateToProductPage(product)
            }
        )



        recyclerView.adapter = dataAdapter
        dataAdapter2 = DataAdapter(
            onAddToCartClickListener = { position ->
                val product = dataAdapter2.getProducts()[position]

                val viewHolder = recyclerView2.findViewHolderForAdapterPosition(position) as DataAdapter.DataViewHolder


                shoppingCart.addItem(product)


                var x = shoppingCart.getQuantityOfProduct(product.id)
                viewHolder.updateQuantity(x.toString())
                if(x!=0)
                    viewHolder.updateImage(true)
                else
                    viewHolder.updateImage(false)

                update()
            },
            onRemoveFromCartClickListener = { position ->
                val product = dataAdapter2.getProducts()[position]
                val viewHolder = recyclerView2.findViewHolderForAdapterPosition(position) as DataAdapter.DataViewHolder
                shoppingCart.removeItem(product)
                var x = shoppingCart.getQuantityOfProduct(product.id)
                viewModel.addItem(product)
                viewHolder.updateQuantity(x.toString())

                if(x!=0)
                    viewHolder.updateImage(true)
                else
                    viewHolder.updateImage(false)
                update()
            },
            onImageClickListener = { position ->
                val product = dataAdapter2.getProducts()[position]
                navigateToProductPage(product)
            }
        )
        recyclerView2.layoutManager = layoutManager2
        recyclerView2.adapter = dataAdapter2



        fetchData(resources.getString(R.string.url_vertical),true)
        fetchData(resources.getString(R.string.url_horizontal),false)

        return view
    }
    private fun fetchData(url:String,control:Boolean) {
        lifecycleScope.launch {
            val beveragePacks = dataRepository.fetchData(url).singleOrNull()
            beveragePacks?.let { packs ->
                val productList = mutableListOf<Product>()
                for (pack in packs) {
                    if (pack.products != null) {
                        productList.addAll(pack.products)
                    }
                }
                if(control)
                    dataAdapter.setData(productList)
                else
                    dataAdapter2.setData(productList)
            }
        }
    }

    private fun navigateToProductPage(product: Product) {
        val bundle = Bundle().apply {
            putString("productId", product.id)
            putString("productName", product.name)
            putString("productAttribute", product.attribute)
            putString("productThumbnailURL", product.thumbnailURL)
            putString("productImageURL", product.imageURL)
            putDouble("productPrice", product.price)
            putString("productPriceText", product.priceText)
        }

        val productPageFragment = ProductPage().apply {
            arguments = bundle
        }

        parentFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, productPageFragment)
            .addToBackStack(null)
            .commit()
    }


    fun update()
    {
        lateinit var totalPrice: TextView
        GlobalDataHolder.cartItemList.value = shoppingCart.getCartItems()




        totalPrice = requireActivity().findViewById(R.id.cardSum)
        val total = GlobalDataHolder.getTotalPrice()

        val currencyFormat = NumberFormat.getCurrencyInstance(Locale("tr", "TR"))
        val decimalFormatSymbols = (currencyFormat as DecimalFormat).decimalFormatSymbols
        decimalFormatSymbols.currencySymbol = "₺"
        (currencyFormat as DecimalFormat).decimalFormatSymbols = decimalFormatSymbols

        val formattedTotalPrice = currencyFormat.format(total)

        totalPrice.text = formattedTotalPrice
    }
}
