package com.getir.patika.getir_android_kotlin_bootcamp_finalcase_aysenur_

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class CartItem(
    val product: Product,
    var quantity: Int
)



class ShoppingCart {
    private val cartItems = mutableListOf<CartItem>()

    fun addItem(product: Product, quantity: Int = 1) {
        val existingItem = cartItems.find { it.product.id == product.id }
        if (existingItem != null) {
            existingItem.quantity += quantity
        } else {
            val newItem = CartItem(product, quantity)
            cartItems.add(newItem)
        }
    }

    fun removeItem(product: Product, quantity: Int = 1) {
        val existingItem = cartItems.find { it.product.id == product.id }
        if (existingItem != null) {
            existingItem.quantity -= quantity
            if (existingItem.quantity <= 0) {
                cartItems.remove(existingItem)
            }
        }
    }

    fun getTotalPrice(): Double {
        var totalPrice = 0.0
        for (item in cartItems) {
            totalPrice += item.product.price * item.quantity
        }
        return totalPrice
    }

    fun getQuantityOfProduct(productId: String): Int {
        val item = cartItems.find { it.product.id == productId }
        return item?.quantity ?: 0
    }

    fun getCartItems(): List<CartItem> {
        return cartItems.toList()
    }

    fun clearCart() {
        cartItems.clear()
    }

    class ShoppingCartViewModel : ViewModel() {

        val _cartItemsLiveData = MutableLiveData<List<CartItem>>()
        val cartItemsLiveData: LiveData<List<CartItem>> = _cartItemsLiveData
        private val shoppingCart = ShoppingCart()

        fun addItem(product: Product) {
            shoppingCart.addItem(product)
        }

        fun removeItem(product: Product) {
            shoppingCart.removeItem(product)
        }

        fun getTotalPrice(): Double {
            return shoppingCart.getTotalPrice()
        }

        fun updateCartItems(cartItems: List<CartItem>) {
            _cartItemsLiveData.value = cartItems
        }

    }
}
