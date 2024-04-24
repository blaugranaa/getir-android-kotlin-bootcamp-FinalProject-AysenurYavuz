package com.getir.patika.getir_android_kotlin_bootcamp_finalcase_aysenur_



import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView



class CartPage : Fragment() {
    private lateinit var viewModel: ShoppingCart.ShoppingCartViewModel
    private lateinit var orderSum:TextView
    private lateinit var orderComplete:Button
    private lateinit var recy:RecyclerView
    private lateinit var recy2:RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.cart_page, container, false)
        orderSum = view.findViewById(R.id.orderSum)
        orderComplete   = view.findViewById(R.id.orderComplete)
        recy = view.findViewById(R.id.recy)
        recy2 = view.findViewById(R.id.listView)
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingCart.ShoppingCartViewModel::class.java)
        val close = requireActivity().findViewById<ImageView>(R.id.imageView5)
        close.visibility = View.VISIBLE
        val title = requireActivity().findViewById<TextView>(R.id.textView2)
        title.text = "Sepetim"


    }





}