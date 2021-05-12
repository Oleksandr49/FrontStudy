package front.frontstudy.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import front.frontstudy.databinding.HomeFragmentBinding

class Home: Fragment() {

    var viewBinding: HomeFragmentBinding? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        HomeFragmentBinding.inflate(inflater, container, false).also {
            it.LogIn.setOnClickListener {
                HomeDirections.goToSingIn().also { destination -> findNavController().navigate(destination) }
            }
            it.Register.setOnClickListener {
                HomeDirections.goToSignUp().also { destination -> findNavController().navigate(destination) }
            }
            return it.root }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }
}