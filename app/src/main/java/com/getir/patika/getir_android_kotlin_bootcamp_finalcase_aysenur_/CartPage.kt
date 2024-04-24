package com.getir.patika.getir_android_kotlin_bootcamp_finalcase_aysenur_



import GlobalDataHolder
import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings.Global
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


class CardPage : Fragment() {
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
        close.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()

            val fragment = HomeFragment()
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

    fun recy2(){
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recy2.layoutManager = layoutManager
        dataAdapter2= CartAdapter(
            onAddToCartClickListener = { position ->
                val product = dataAdapter2.getProducts()[position]

                GlobalDataHolder.addToCart(product)
                var x = GlobalDataHolder.getQuantityOfProduct(product.id)
                val viewHolder = recy2.findViewHolderForAdapterPosition(position) as CartAdapter.DataViewHolder
                viewHolder.updateQuantity(x.toString())

                update()

            },
            onRemoveFromCartClickListener = { position ->
                val product = dataAdapter2.getProducts()[position]

                GlobalDataHolder.removeFromCart(product.id)
                var x = GlobalDataHolder.getQuantityOfProduct(product.id)
                val viewHolder = recy2.findViewHolderForAdapterPosition(position) as CartAdapter.DataViewHolder
                viewHolder.updateQuantity(x.toString())
                if(x == 0)
                    recy2()

                update()

            },
            onImageClickListener = { position ->
                val product = dataAdapter2.getProducts()[position]

            }

        )
        val cartItemList = GlobalDataHolder.cartItemList.value
        val convertedList = mutableListOf<Product>()

        cartItemList?.forEach { cartItem ->

            val product = cartItem.product
            convertedList.add(product)
        }
        dataAdapter2.setData(convertedList)
        recy2.adapter = dataAdapter2
    }

    fun recy(){
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recy.layoutManager = layoutManager
        dataAdapter = DataAdapter(
            onAddToCartClickListener = { position ->
                val product = dataAdapter.getProducts()[position]


            },
            onRemoveFromCartClickListener = { position ->
                val product = dataAdapter.getProducts()[position]



            },
            onImageClickListener = { position ->
                val product = dataAdapter.getProducts()[position]

            }

        )

        orderComplete.setOnClickListener {
            GlobalDataHolder.clearCart()

            Toast.makeText(requireContext(),"Sipariş Tamamlandı",Toast.LENGTH_SHORT).show()

            val sum = requireActivity().findViewById<TextView>(R.id.cardSum)
            sum.text = "₺0.00"
            val transaction = requireActivity().supportFragmentManager.beginTransaction()

            val fragment = HomeFragment()
            transaction.replace(R.id.frameLayout, fragment)
            transaction.commit()
        }

        fetchData(resources.getString(R.string.url_vertical),false)
        recy.adapter = dataAdapter
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
                    dataAdapter.setData(productList)
            }
        }
    }

    fun moneyFormat(total:Double): String {

        val currencyFormat = NumberFormat.getCurrencyInstance(Locale("tr", "TR"))
        val decimalFormatSymbols = (currencyFormat as DecimalFormat).decimalFormatSymbols
        decimalFormatSymbols.currencySymbol = "₺"
        (currencyFormat as DecimalFormat).decimalFormatSymbols = decimalFormatSymbols

        val formattedTotalPrice = currencyFormat.format(total)

        return formattedTotalPrice
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
        orderSum.text  = formattedTotalPrice
    }
}