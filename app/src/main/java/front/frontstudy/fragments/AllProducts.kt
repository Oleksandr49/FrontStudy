package front.frontstudy.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import front.frontstudy.R
import front.frontstudy.data.Product
import front.frontstudy.databinding.AllProductsMenuFragmentBinding

class AllProducts: Fragment(){

var viewBinding: AllProductsMenuFragmentBinding? = null

override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

    AllProductsMenuFragmentBinding.inflate(inflater, container, false).also {binding ->
        binding.shopToolbar.inflateMenu(R.menu.shop)
        binding.shopToolbar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId){
                R.id.cart -> {findNavController().navigate(AllProductsDirections.goToCartFrag())
                    true}
                else -> { false }
            }
        }

        binding.shopToolbar.setNavigationOnClickListener{findNavController().popBackStack()}
        binding.allProductsRecView.adapter = AllProductsMenuAdapter().also {
                adapter -> adapter.products = initList()
            adapter.navAction = object : AllProductsAdapterCallback {
                override fun action(arg: NavDirections) {
                    findNavController().navigate(arg)
                }
            }
        }

        binding.allProductsRecView.layoutManager = GridLayoutManager(activity, 2)
        return binding.root }
}

override fun onDestroy() {
    super.onDestroy()
    viewBinding = null
}

    private fun initList(): ArrayList<Product>{
       ArrayList<Product>().also { itemList ->
            Product("Chocolate Muffin", arrayOf("Breakfast", "Munchies"), 4.99, R.drawable.muffin_img).also { itemList.add(it) }
            Product("Classic Bagel", arrayOf("Breakfast", "Sandwiches"), 6.99, R.drawable.bagel_img).also { itemList.add(it) }
            Product("Pancakes", arrayOf("Breakfast"), 9.99, R.drawable.pancakes_img).also { itemList.add(it) }
            Product("Latte", arrayOf("Breakfast", "Coffee"), 5.69, R.drawable.latte_img).also { itemList.add(it) }
            Product("Breakfast", arrayOf("Breakfast"), 14.99, R.drawable.breakfast_img).also { itemList.add(it) }
            Product("Pancakes", arrayOf("Breakfast"), 9.99, R.drawable.pancakes_img).also { itemList.add(it) }
            return itemList
        }
    }
}

class AllProductsMenuAdapter: RecyclerView.Adapter<AllProductsMenuViewHolder>() {

    var products = ArrayList<Product>()
    var navAction: AllProductsAdapterCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllProductsMenuViewHolder {
        LayoutInflater.from(parent.context).also {
            return AllProductsMenuViewHolder(it.inflate(R.layout.all_products_view_holder, parent, false)) }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AllProductsMenuViewHolder, position: Int) {
        products[position].also { product ->
            var category = ""
            val iterator = product.categories.iterator()
            while (iterator.hasNext()){
                val lastItem = product.categories[product.categories.lastIndex]
                val currentItem = iterator.next()
                category = if(currentItem != lastItem) "$category$currentItem, " else "$category$currentItem"
            }
            holder.categories.text = category
            holder.title.text = product.name
            holder.price.text = "${product.price}$"
            Picasso.get().load(product.img).into(holder.img)
            holder.img.setOnClickListener{
                AllProductsDirections.goToItemDetails(product.name, "$${product.price}", product.img).also {
                    direction -> navAction?.action(direction)
                }}
        }

    }

    override fun getItemCount() = products.size
}

class AllProductsMenuViewHolder(item: View): RecyclerView.ViewHolder(item) {

    var title: TextView = item.findViewById(R.id.product_name)
    var categories: TextView = item.findViewById(R.id.product_categories)
    var price: TextView = item.findViewById(R.id.product_price)
    var img: ImageView = item.findViewById(R.id.product_img)
}

interface AllProductsAdapterCallback{

    fun action(arg: NavDirections)
}