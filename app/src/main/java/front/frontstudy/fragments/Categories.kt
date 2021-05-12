package front.frontstudy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import front.frontstudy.R
import front.frontstudy.data.MenuCategory
import front.frontstudy.data.Product
import front.frontstudy.databinding.MenuCategoriesFragmentBinding

class Categories: Fragment() {

    var viewBinding: MenuCategoriesFragmentBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        MenuCategoriesFragmentBinding.inflate(inflater, container, false).also {
            it.categoriesToolbar.setNavigationOnClickListener{findNavController().popBackStack()}
            it.menuCategoriesRecView.adapter = MenuCategoriesAdapter().also { adapter -> adapter.menuCategories = initList()
            adapter.navAction = object : MenuCategoriesAdapterCallback {
                override fun action(arg: NavDirections) {
                    findNavController().navigate(arg)
                } } }

            it.menuCategoriesRecView.layoutManager = LinearLayoutManager(activity)
            return it.root }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }

    private fun initList(): ArrayList<MenuCategory>{
        ArrayList<MenuCategory>().also { itemList ->
            MenuCategory("Coffee","Freshly brewed coffee","url", R.drawable.coffee_category_background).also { itemList.add(it) }
            MenuCategory("Breakfast","Perfectly baked & served warm","url", R.drawable.breakfast_category_background).also { itemList.add(it) }
            MenuCategory("Munchies","Perfectly baked & served warm","url", R.drawable.munchies_category_background).also { itemList.add(it) }
            MenuCategory("Sandwiches","Fresh, healthy and tasty","url", R.drawable.sandwiches_category_background).also { itemList.add(it) }
            MenuCategory("Speciality Drinks","Special drinks for every taste","url", R.drawable.coffee_category_background).also { itemList.add(it) }
            return itemList
        }
    }
}

class MenuCategoriesAdapter: RecyclerView.Adapter<MenuCategoriesViewHolder>() {

    var menuCategories = ArrayList<MenuCategory>()
    var navAction: MenuCategoriesAdapterCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuCategoriesViewHolder {
        LayoutInflater.from(parent.context).also {
            return MenuCategoriesViewHolder(it.inflate(R.layout.menu_category_viewholder, parent, false)) }
    }

    override fun onBindViewHolder(holder: MenuCategoriesViewHolder, position: Int) {
        menuCategories[position].also {
            holder.title.text = it.title
            holder.subtitle.text = it.subtitle
            Picasso.get().load(it.imgResource).into(holder.background)
            holder.background.setOnClickListener { navAction?.action(CategoriesDirections.actionMenuCategoriesFragmentToAllProductsMenuFragment()) }
        }
    }

    override fun getItemCount() = menuCategories.size
}

class MenuCategoriesViewHolder(item: View): RecyclerView.ViewHolder(item) {

    var title: TextView = item.findViewById(R.id.category_title)
    var subtitle: TextView = item.findViewById(R.id.category_subtitle)
    var background: ImageView = item.findViewById(R.id.menu_position_background)
}

interface MenuCategoriesAdapterCallback {

    fun action(arg: NavDirections)
}