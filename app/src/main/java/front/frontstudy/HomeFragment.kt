package front.frontstudy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import front.frontstudy.databinding.HomeFragmentBinding

class HomeFragment: Fragment() {

    var viewBinding: HomeFragmentBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        HomeFragmentBinding.inflate(inflater, container, false).also {
            it.LogIn.setOnClickListener {
                HomeFragmentDirections.goToSingIn().also { destination -> findNavController().navigate(destination) }
            }
            it.Register.setOnClickListener {
                HomeFragmentDirections.goToSignUp().also { destination -> findNavController().navigate(destination) }
            }
            it.Menu.setOnClickListener {
                HomeFragmentDirections.goToMenuCategories().also { destination -> findNavController().navigate(destination) }
            }
            it.Shop.setOnClickListener {
                HomeFragmentDirections.goToAllProducts().also { destination -> findNavController().navigate(destination) }
            }
            it.Favorites.setOnClickListener{
                HomeFragmentDirections.goToFavorites().also { destination -> findNavController().navigate(destination) }
            }
            it.Settings.setOnClickListener{
                HomeFragmentDirections.goToSettings().also { destination -> findNavController().navigate(destination) }
            }
            return it.root }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }
}