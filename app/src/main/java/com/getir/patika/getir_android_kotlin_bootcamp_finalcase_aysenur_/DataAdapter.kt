package com.getir.patika.getir_android_kotlin_bootcamp_finalcase_aysenur_


import CartItem
import GlobalDataHolder
import ShoppingCart
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.checkerframework.checker.index.qual.GTENegativeOne

class DataAdapter(
    private val onAddToCartClickListener: (position: Int) -> Unit,
    private val onRemoveFromCartClickListener: (position: Int) -> Unit,
    private val onImageClickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    private var products: MutableList<Product> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = products[position]
        holder.bind(item)


        val shoppingCart = ShoppingCart()
        val quantity = shoppingCart.getQuantityOfProduct(item.id)
        // holder.updateQuantity(quantity.toString())
        holder.updateImage(false)

        holder.itemView.findViewById<ImageView>(R.id.addToCartButton).setOnClickListener {
            onAddToCartClickListener.invoke(position)


        }
        holder.itemView.findViewById<ImageView>(R.id.removeButton).setOnClickListener {
            onRemoveFromCartClickListener.invoke(position)

        }
        holder.itemView.findViewById<ImageView>(R.id.productImageView).setOnClickListener {
            onImageClickListener.invoke(position)
        }
    }

    override fun getItemCount(): Int = products.size

    fun setData(products: MutableList<Product>) {
        this.products = products
        notifyDataSetChanged()
    }

    fun getProducts(): MutableList<Product> {
        return products
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productImageView: ImageView = itemView.findViewById(R.id.productImageView)
        private val removeButton: ImageView = itemView.findViewById(R.id.removeButton)
        private val productNameTextView: TextView = itemView.findViewById(R.id.productNameTextview)
        private val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        private val attributestText: TextView = itemView.findViewById(R.id.attrubidesText)
        private val quantityTextView: TextView = itemView.findViewById(R.id.textVieww)
        private val vieww: View = itemView.findViewById(R.id.view)

        fun bind(product: Product) {
            productNameTextView.text = product.name
            priceTextView.text = product.priceText
            attributestText.text = product.attribute

            var shoppingCart= ShoppingCart()
            val cartList = GlobalDataHolder.cartItemList.value ?: mutableListOf()

            val productId = product.id
            val cartItem = cartList.find { it.product.id == productId }
            val quantity = cartItem?.quantity ?: 0
            quantityTextView.text = quantity.toString()

            Glide.with(itemView.context)
                .load(product.imageURL)
                .centerCrop()
                .into(productImageView)
        }

        fun updateQuantity(text: String) {
            quantityTextView.text = text
        }

        fun updateImage(text: Boolean) {
            if(text)
            {
                removeButton.setImageResource(R.drawable.img_3)


            }
            else
            {
                removeButton.setImageResource(R.drawable.img_1)

            }
        }
    }
}
