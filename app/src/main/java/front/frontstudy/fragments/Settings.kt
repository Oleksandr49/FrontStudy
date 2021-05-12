package front.frontstudy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import front.frontstudy.R
import front.frontstudy.databinding.UserSettingsFragmentBinding

class Settings: Fragment() {

    var viewBinding: UserSettingsFragmentBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        UserSettingsFragmentBinding.inflate(inflater, container, false).also {
            it.settingsToolbar.setNavigationOnClickListener{findNavController().popBackStack()}
            it.userEmailString.text = getString(R.string.BatMail)
            it.userNameString.text = getString(R.string.BatName)
            it.userLocationString.text = getString(R.string.BatLocation)
            return it.root }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }
}