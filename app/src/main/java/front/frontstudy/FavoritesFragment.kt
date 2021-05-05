package front.frontstudy

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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import front.frontstudy.databinding.FavoritesFragmentBinding

class FavoritesFragment:Fragment() {

    var viewBinding: FavoritesFragmentBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val itemList = ArrayList<Product>()
        Product("Chocolate Muffin", arrayOf("Breakfast", "Munchies"),4.99, R.mipmap.muffin_img).also { itemList.add(it) }
        Product("Classic Bagel", arrayOf("Breakfast", "Sandwiches"),6.99, R.mipmap.bagel_img).also { itemList.add(it) }
        Product("Pancakes", arrayOf("Breakfast"),9.99, R.mipmap.pancakes_img).also { itemList.add(it) }
        Product("Latte", arrayOf("Breakfast", "Coffee"),5.69, R.mipmap.latte_img).also { itemList.add(it) }
        Product("Breakfast", arrayOf("Breakfast"),14.99, R.mipmap.breakfast_img).also { itemList.add(it) }
        Product("Pancakes", arrayOf("Breakfast"),9.99, R.mipmap.pancakes_img).also { itemList.add(it) }
        Product("Chocolate Muffin", arrayOf("Breakfast", "Munchies"),4.99, R.mipmap.muffin_img).also { itemList.add(it) }
        Product("Classic Bagel", arrayOf("Breakfast", "Sandwiches"),6.99, R.mipmap.bagel_img).also { itemList.add(it) }
        Product("Pancakes", arrayOf("Breakfast"),9.99, R.mipmap.pancakes_img).also { itemList.add(it) }
        Product("Latte", arrayOf("Breakfast", "Coffee"),5.69, R.mipmap.latte_img).also { itemList.add(it) }
        Product("Breakfast", arrayOf("Breakfast"),14.99, R.mipmap.breakfast_img).also { itemList.add(it) }
        Product("Pancakes", arrayOf("Breakfast"),9.99, R.mipmap.pancakes_img).also { itemList.add(it) }

        FavoritesFragmentBinding.inflate(inflater, container, false).also {
            it.favoritesToolbar.inflateMenu(R.menu.shop_menu)
            it.favoritesToolbar.title = "Favorites"
            it.favoritesToolbar.setOnMenuItemClickListener { menuItem ->
                when(menuItem.itemId){
                    R.id.cart -> {
                        Toast.makeText(activity, "Gone to Cart", Toast.LENGTH_SHORT).also { toast -> toast.show() }
                        true}
                    else -> { false }
                }
            }
            it.favoritesToolbar.setNavigationOnClickListener{findNavController().popBackStack()}
           it.FavoritesRecView.adapter = FavoritesAdapter().also { adapter -> adapter.positions = itemList }
           it.FavoritesRecView.layoutManager = LinearLayoutManager(activity)
            return it.root }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }
}

class FavoritesAdapter: RecyclerView.Adapter<FavoritesViewHolder>() {

    var positions = ArrayList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        LayoutInflater.from(parent.context).also {
            return FavoritesViewHolder(it.inflate(R.layout.favorites_viewholder, parent, false)) }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        positions[position].also {
            holder.title.text = it.name
            holder.price.text = "$${it.price}"
            holder.addBtn.setOnClickListener{ Log.i("later", "Add to cart")}
        }
    }

    override fun getItemCount() = positions.size
}

class FavoritesViewHolder(item: View): RecyclerView.ViewHolder(item) {

    var title: TextView = item.findViewById(R.id.position_title)
    var price: TextView = item.findViewById(R.id.position_price)
    var addBtn: ImageButton = item.findViewById(R.id.add_to_cart)
}