package front.frontstudy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import front.frontstudy.databinding.SignUpFragmentBinding

class SignUp: Fragment() {

    var viewBinding: SignUpFragmentBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        SignUpFragmentBinding.inflate(inflater, container, false).also {
            it.SignUp.setOnClickListener { SignUpDirections.goToSignIn().also { direction -> findNavController().navigate(direction) } }
            return it.root }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }
}