package front.frontstudy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import front.frontstudy.databinding.UserSettingsFragmentBinding

class UserSettingsFragment: Fragment() {

    var viewBinding: UserSettingsFragmentBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        UserSettingsFragmentBinding.inflate(inflater, container, false).also {
            it.settingsToolbar.title = "Settings"
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