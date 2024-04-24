package com.getir.patika.getir_android_kotlin_bootcamp_finalcase_aysenur_


import GlobalDataHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CartAdapter(
    private val onAddToCartClickListener: (position: Int) -> Unit,
    private val onRemoveFromCartClickListener: (position: Int) -> Unit,
    private val onImageClickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.DataViewHolder>() {

    private var products: MutableList<Product> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_listview, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = products[position]
        holder.bind(item)


        val shoppingCart = ShoppingCart()
        val quantity = shoppingCart.getQuantityOfProduct(item.id)
        // holder.updateQuantity(quantity.toString())
        holder.updateImage(true)

        holder.itemView.findViewById<ImageView>(R.id.addToCartButton).setOnClickListener {
            onAddToCartClickListener.invoke(position)


        }
        holder.itemView.findViewById<ImageView>(R.id.imageView2).setOnClickListener {
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
        private val removeButton: ImageView = itemView.findViewById(R.id.imageView2)
        private val productNameTextView: TextView = itemView.findViewById(R.id.productNameTextview)
        private val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        private val attributestText: TextView = itemView.findViewById(R.id.attrubidesText)
        private val quantityTextView: TextView = itemView.findViewById(R.id.textVieww)


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
                removeButton.setImageResource(R.drawable.dcreaseicon)


            }
            else
            {
                removeButton.setImageResource(R.drawable.trash_icon)

            }
        }
    }
}