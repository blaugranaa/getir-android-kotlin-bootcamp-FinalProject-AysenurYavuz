package com.getir.patika.getir_android_kotlin_bootcamp_finalcase_aysenur_


import CartItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


class ShoppingCartAdapter : BaseAdapter() {

    private var cartItems: List<CartItem> = listOf()

    fun submitList(newList: List<CartItem>) {
        cartItems = newList
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return cartItems.size
    }

    override fun getItem(position: Int): CartItem {
        return cartItems[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(parent?.context).inflate(R.layout.custom_listview, parent, false)

        val productNameTextView = view.findViewById<TextView>(R.id.productNameTextview)
        val productPriceTextView = view.findViewById<TextView>(R.id.priceTextView)
        val image = view.findViewById<ImageView>(R.id.productImageView)
        val textAttribute = view.findViewById<TextView>(R.id.attrubidesText)
        val quantityText = view.findViewById<TextView>(R.id.textVieww)

        val cartItem = getItem(position)

        productNameTextView.text = cartItem.product.name
        productPriceTextView.text = cartItem.product.priceText
        textAttribute.text = cartItem.product.attribute
        quantityText.text = cartItem.quantity.toString()
        parent?.context?.let {
            Glide.with(it)
                .load(cartItem.product.imageURL)
                .into(image)
        }


        return view
    }
}

