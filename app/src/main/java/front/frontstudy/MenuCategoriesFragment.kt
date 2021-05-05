package front.frontstudy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import front.frontstudy.databinding.MenuCategoriesFragmentBinding

class MenuCategoriesFragment: Fragment() {

    var viewBinding: MenuCategoriesFragmentBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val itemList = ArrayList<MenuCategory>()
        MenuCategory("Coffee","Freshly brewed coffee","url", R.mipmap.coffee_background).also { itemList.add(it) }
        MenuCategory("Breakfast","Perfectly baked & served warm","url", R.mipmap.breakfast_background).also { itemList.add(it) }
        MenuCategory("Munchies","Perfectly baked & served warm","url", R.mipmap.munchies_background).also { itemList.add(it) }
        MenuCategory("Sandwiches","Fresh, healthy and tasty","url", R.mipmap.sandwiches_background).also { itemList.add(it) }
        MenuCategory("Speciality Drinks","Special drinks for every taste","url", R.mipmap.drinks_background).also { itemList.add(it) }

        MenuCategoriesFragmentBinding.inflate(inflater, container, false).also {
            it.menuCategoriesRecView.adapter = MenuCategoriesAdapter().also {adapter -> adapter.menuCategories = itemList }
            it.menuCategoriesRecView.layoutManager = LinearLayoutManager(activity)
            return it.root }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }
}

class MenuCategoriesAdapter: RecyclerView.Adapter<MenuCategoriesViewHolder>() {

    var menuCategories = ArrayList<MenuCategory>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuCategoriesViewHolder {
        LayoutInflater.from(parent.context).also {
            return MenuCategoriesViewHolder(it.inflate(R.layout.menu_category_viewholder, parent, false)) }
    }

    override fun onBindViewHolder(holder: MenuCategoriesViewHolder, position: Int) {
        menuCategories[position].also {
            holder.title.text = it.title
            holder.subtitle.text = it.subtitle
            Picasso.get().load(it.imgResource).into(holder.background)
        }
    }

    override fun getItemCount() = menuCategories.size
}

class MenuCategoriesViewHolder(item: View): RecyclerView.ViewHolder(item) {

    var title: TextView = item.findViewById(R.id.category_title)
    var subtitle: TextView = item.findViewById(R.id.category_subtitle)
    var background: ImageView = item.findViewById(R.id.menu_position_background)
}