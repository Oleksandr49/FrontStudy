package front.frontstudy.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import front.frontstudy.R
import front.frontstudy.data.Product
import front.frontstudy.databinding.FavoritesFragmentBinding

class Favorites:Fragment() {

    var viewBinding: FavoritesFragmentBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        FavoritesFragmentBinding.inflate(inflater, container, false).also {
            it.favoritesToolbar.inflateMenu(R.menu.shop)
            it.favoritesToolbar.setOnMenuItemClickListener { menuItem ->
                when(menuItem.itemId){
                    R.id.cart -> {
                        Toast.makeText(activity, "Gone to Cart", Toast.LENGTH_SHORT).also { toast -> toast.show() }
                        true}
                    else -> { false }
                }
            }
            it.favoritesToolbar.setNavigationOnClickListener{findNavController().popBackStack()}
           it.FavoritesRecView.adapter = FavoritesAdapter().also {
                   adapter -> adapter.positions = initList()
           adapter.callback = object : FavoritesAdapterCallback {
               override fun action(arg: NavDirections) {
                   findNavController().navigate(arg)
               }
           }}
           it.FavoritesRecView.layoutManager = LinearLayoutManager(activity)
            return it.root }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }

    private fun initList(): ArrayList<Product>{
        ArrayList<Product>().also { itemList ->
            Product("Chocolate Muffin", arrayOf("Breakfast", "Munchies"),4.99, R.drawable.muffin_img).also { itemList.add(it) }
            Product("Classic Bagel", arrayOf("Breakfast", "Sandwiches"),6.99, R.drawable.bagel_img).also { itemList.add(it) }
            Product("Pancakes", arrayOf("Breakfast"),9.99, R.drawable.pancakes_img).also { itemList.add(it) }
            Product("Latte", arrayOf("Breakfast", "Coffee"),5.69, R.drawable.latte_img).also { itemList.add(it) }
            Product("Breakfast", arrayOf("Breakfast"),14.99, R.drawable.breakfast_img).also { itemList.add(it) }
            Product("Pancakes", arrayOf("Breakfast"),9.99, R.drawable.pancakes_img).also { itemList.add(it) }
            Product("Chocolate Muffin", arrayOf("Breakfast", "Munchies"),4.99, R.drawable.muffin_img).also { itemList.add(it) }
            Product("Classic Bagel", arrayOf("Breakfast", "Sandwiches"),6.99, R.drawable.bagel_img).also { itemList.add(it) }
            Product("Pancakes", arrayOf("Breakfast"),9.99, R.drawable.pancakes_img).also { itemList.add(it) }
            Product("Latte", arrayOf("Breakfast", "Coffee"),5.69, R.drawable.latte_img).also { itemList.add(it) }
            Product("Breakfast", arrayOf("Breakfast"),14.99, R.drawable.breakfast_img).also { itemList.add(it) }
            Product("Pancakes", arrayOf("Breakfast"),9.99, R.drawable.pancakes_img).also { itemList.add(it) }
            return itemList
        }
    }
}

class FavoritesAdapter: RecyclerView.Adapter<FavoritesViewHolder>() {

    var positions = ArrayList<Product>()
    var callback: FavoritesAdapterCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        LayoutInflater.from(parent.context).also {
            return FavoritesViewHolder(it.inflate(R.layout.favorites_viewholder, parent, false)) }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        positions[position].also {product ->
            holder.title.text = product.name
            holder.price.text = "$${product.price}"
            holder.addBtn.setOnClickListener{ Log.i("later", "Add to cart")}
            holder.title.setOnClickListener{
                callback?.action(FavoritesDirections.goToDetailsFrag(product.name, "${product.price}", product.img))
            }
        }
    }

    override fun getItemCount() = positions.size
}

class FavoritesViewHolder(item: View): RecyclerView.ViewHolder(item) {

    var title: TextView = item.findViewById(R.id.cart_position_title)
    var price: TextView = item.findViewById(R.id.cart_position_price)
    var addBtn: ImageButton = item.findViewById(R.id.remove_from_cart)
}

interface FavoritesAdapterCallback{

    fun action(arg: NavDirections)
}