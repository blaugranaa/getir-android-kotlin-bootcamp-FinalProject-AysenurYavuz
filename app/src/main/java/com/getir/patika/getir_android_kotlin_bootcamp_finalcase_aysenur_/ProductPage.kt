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
import com.bumptech.glide.Glide
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

class ProductPage : Fragment() {

    private lateinit var productImageView: ImageView
    private lateinit var productNameTextView: TextView
    private lateinit var attributeTextView: TextView
    private lateinit var priceTextView: TextView
    private lateinit var productId: String
    private lateinit var adToCard:ImageView
    private lateinit var removeCard:ImageView
    private lateinit var productQuantity:TextView
    private lateinit var addButton:Button
    private lateinit var view13:View
    lateinit var product:Product



    val shoppingCart = ShoppingCart()
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_page, container, false)
        val close = requireActivity().findViewById<ImageView>(R.id.imageView5)
        close.visibility = View.VISIBLE
        val title = requireActivity().findViewById<TextView>(R.id.textView2)
        title.text = "Ürün Detayı"

        close.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()

            val fragment = HomeFragment()
            transaction.replace(R.id.frameLayout, fragment)
            transaction.commit()
        }
        productImageView = view.findViewById(R.id.productImagee)
        productNameTextView = view.findViewById(R.id.productName)
        attributeTextView = view.findViewById(R.id.productAttirbute)
        priceTextView = view.findViewById(R.id.productPrice)
        adToCard = view.findViewById(R.id.imageView10)
        removeCard = view.findViewById(R.id.imageView11)
        productQuantity = view.findViewById(R.id.textView10)
        addButton = view.findViewById(R.id.addToCart)
        view13 = view.findViewById(R.id.view13)

        butonsVisible(false)


        val bundle = arguments
        if (bundle != null) {
            productId = bundle.getString("productId") ?: ""
            val productName = bundle.getString("productName") ?: ""
            val productAttribute = bundle.getString("productAttribute") ?: ""
            val productPrice = bundle.getString("productPriceText") ?: ""
            val productImageUrl = bundle.getString("productImageURL")
            val productThumbnailURL = bundle.getString("productThumbnailURL")
            val productPricee = bundle.getDouble("productPrice")

            product = Product(
                id = productId,
                name = productName,
                attribute = productAttribute,
                thumbnailURL = productThumbnailURL,
                imageURL = productImageUrl ?: "",
                price = productPricee,
                priceText = productPrice
            )
            productNameTextView.text = productName
            attributeTextView.text = productAttribute
            priceTextView.text = productPrice

            Glide.with(requireContext()).load(productImageUrl).into(productImageView)

        }
        val cartList = GlobalDataHolder.cartItemList.value ?: mutableListOf()
        val productId = product.id
        val cartItem = cartList.find { it.product.id == productId }
        val quantity = cartItem?.quantity ?: 0
        if(quantity==0)
            butonsVisible(false)
        else{
            butonsVisible(true)
            productQuantity.text= quantity.toString()
        }


        adToCard.setOnClickListener {

            GlobalDataHolder.addToCart(product)
            var x = GlobalDataHolder.getQuantityOfProduct(productId)
            productQuantity.text = x.toString()

            update()

        }

        removeCard.setOnClickListener {
            //shoppingCart.removeItem(product)
            GlobalDataHolder.removeFromCart(productId)
            var x = GlobalDataHolder.getQuantityOfProduct(productId)
            productQuantity.text = x.toString()
            update()
        }


        addButton.setOnClickListener {
            butonsVisible(true)
        }

        return view
    }

    fun butonsVisible(visible:Boolean)
    {
        if(visible) {
            adToCard.visibility = View.VISIBLE
            removeCard.visibility = View.VISIBLE
            productQuantity.visibility = View.VISIBLE
            view13.visibility = View.VISIBLE
            addButton.visibility = View.GONE
        }
        else {
            adToCard.visibility = View.GONE
            removeCard.visibility = View.GONE
            productQuantity.visibility = View.GONE
            view13.visibility = View.GONE
            addButton.visibility = View.VISIBLE

        }

    }

    fun update()
    {
        lateinit var totalPrice: TextView
        //GlobalDataHolder.cartItemList.value = shoppingCart.getCartItems()

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
