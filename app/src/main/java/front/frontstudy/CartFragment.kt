package front.frontstudy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import front.frontstudy.databinding.CartFragmentBinding

class CartFragment: Fragment() {

    var viewBinding: CartFragmentBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val itemList = ArrayList<Product>()
        Product("Chocolate Muffin", arrayOf("Breakfast", "Munchies"),4.99, R.drawable.muffin_img).also { itemList.add(it) }
        Product("Classic Bagel", arrayOf("Breakfast", "Sandwiches"),6.99, R.drawable.bagel_img).also { itemList.add(it) }
        Product("Pancakes", arrayOf("Breakfast"),9.99, R.drawable.pancakes_img).also { itemList.add(it) }
        Product("Latte", arrayOf("Breakfast", "Coffee"),5.69, R.drawable.latte_img).also { itemList.add(it) }
        Product("Breakfast", arrayOf("Breakfast"),14.99, R.drawable.breakfast_img).also { itemList.add(it) }
        Product("Pancakes", arrayOf("Breakfast"),9.99, R.drawable.pancakes_img).also { itemList.add(it) }

        CartFragmentBinding.inflate(inflater, container, false).also {
            it.cartRecView.adapter = CartAdapter().also { adapter -> adapter.cartItems = itemList }
            it.cartRecView.layoutManager = LinearLayoutManager(activity)
            it.cartToolbar.setNavigationOnClickListener{findNavController().popBackStack()}
            return it.root }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }
}

class CartAdapter: RecyclerView.Adapter<CartViewHolder>() {

    var cartItems = ArrayList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        LayoutInflater.from(parent.context).also {
            return CartViewHolder(it.inflate(R.layout.cart_viewholder, parent, false)) }
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        cartItems[position].also {
            holder.title.text = it.name
            holder.price.text = it.price.toString()
            holder.button.setOnClickListener {
                cartItems.removeAt(holder.adapterPosition)
            this.notifyItemRemoved(position)}
        }
    }

    override fun getItemCount() = cartItems.size
}

class CartViewHolder(item: View): RecyclerView.ViewHolder(item) {

    var title: TextView = item.findViewById(R.id.cart_position_title)
    var price: TextView = item.findViewById(R.id.cart_position_price)
    var button: ImageButton = item.findViewById(R.id.remove_from_cart)
}