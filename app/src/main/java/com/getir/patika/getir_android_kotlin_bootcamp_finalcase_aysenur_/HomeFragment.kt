package com.getir.patika.getir_android_kotlin_bootcamp_finalcase_aysenur_


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




    private lateinit var viewModel: ShoppingCart.ShoppingCartViewModel

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

}


