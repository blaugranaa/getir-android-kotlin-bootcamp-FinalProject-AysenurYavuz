import androidx.lifecycle.MutableLiveData
import com.getir.patika.getir_android_kotlin_bootcamp_finalcase_aysenur_.CartItem
import com.getir.patika.getir_android_kotlin_bootcamp_finalcase_aysenur_.Product

object GlobalDataHolder {
    val productList = mutableListOf<Product>()
    val cartItemList = MutableLiveData<List<CartItem>>()

    fun getTotalPrice(): Double {
        val cartItems = cartItemList.value ?: return 0.0
        var totalPrice = 0.0
        for (cartItem in cartItems) {
            totalPrice += cartItem.product.price * cartItem.quantity
        }
        return totalPrice
    }

    fun getCartItemCount(): Int {
        val cartItems = cartItemList.value ?: return 0
        return cartItems.size
    }

    fun clearCart() {
        cartItemList.value = emptyList()
    }

    fun getQuantityOfProduct(productId: String): Int {
        val cartItems = cartItemList.value ?: return 0
        val item = cartItems.find { it.product.id == productId }
        return item?.quantity ?: 0
    }

    fun addToCart(product: Product) {
        val currentCartItems = cartItemList.value ?: emptyList()
        val existingItem = currentCartItems.find { it.product.id == product.id }
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            val newCartItem = CartItem(product, 1)
            cartItemList.value = currentCartItems + listOf(newCartItem)
        }
    }




    fun removeFromCart(productId: String) {
        val currentCartItems = cartItemList.value ?: emptyList()
        val existingItem = currentCartItems.find { it.product.id == productId }
        if (existingItem != null) {
            if (existingItem.quantity > 1) {
                existingItem.quantity--
            } else {
                cartItemList.value = currentCartItems - listOf(existingItem)
            }
        }
    }
}
