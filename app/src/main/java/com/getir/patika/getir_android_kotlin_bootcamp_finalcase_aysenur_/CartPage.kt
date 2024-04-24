package com.getir.patika.getir_android_kotlin_bootcamp_finalcase_aysenur_


import CartItem
import GlobalDataHolder
import ShoppingCart
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale


class CartPage : Fragment() {
    private val dataRepository = DataRepository()
    private lateinit var viewModel: ShoppingCart.ShoppingCartViewModel
    private lateinit var adapter: ShoppingCartAdapter
    private lateinit var orderSum:TextView
    private lateinit var orderComplete:Button
    private lateinit var dataAdapter: DataAdapter
    private lateinit var dataAdapter2: CartAdapter
    private lateinit var recy:RecyclerView
    private lateinit var recy2:RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.product_listing, container, false)
        orderSum = view.findViewById(R.id.orderSum)
        orderComplete   = view.findViewById(R.id.orderComplete)
        recy = view.findViewById(R.id.recy)
        recy2 = view.findViewById(R.id.listView)
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingCart.ShoppingCartViewModel::class.java)
        val close = requireActivity().findViewById<ImageView>(R.id.imageView5)
        close.visibility = View.VISIBLE
        val title = requireActivity().findViewById<TextView>(R.id.textView2)
        title.text = "Sepetim"
        close.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()

            val fragment = home()
            transaction.replace(R.id.frameLayout, fragment)
            transaction.commit()
        }

        adapter = ShoppingCartAdapter()

        //val listView = view.findViewById<ListView>(R.id.listView)


        // listView.adapter = adapter

        recy()

        recy2()
        viewModel.cartItemsLiveData.observe(viewLifecycleOwner, { cartItems: List<CartItem> ->
            adapter.submitList(cartItems)
        })

        orderSum.text= moneyFormat(GlobalDataHolder.getTotalPrice())
        GlobalDataHolder.cartItemList.observe(viewLifecycleOwner, { cartItems: List<CartItem> ->

            adapter.submitList(cartItems)
        })

        return view
    }

  



}