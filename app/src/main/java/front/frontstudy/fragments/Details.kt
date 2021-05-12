package front.frontstudy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import front.frontstudy.R
import front.frontstudy.databinding.ItemDetailsFragmentBinding

class Details: Fragment() {

    var viewBinding: ItemDetailsFragmentBinding? = null
    val args: DetailsArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        ItemDetailsFragmentBinding.inflate(inflater, container, false).also {
            it.detailsToolbar.inflateMenu(R.menu.details)
            it.detailsToolbar.title = args.ProductName
            it.detailsToolbar.setOnMenuItemClickListener { menuItem ->
                when(menuItem.itemId){
                    R.id.add_to_favorites -> {
                        Toast.makeText(activity, "Added to favorites", Toast.LENGTH_SHORT).also { toast -> toast.show() }
                        true}
                    else -> { false }
                }
            }
            it.detailsToolbar.setNavigationOnClickListener{findNavController().popBackStack()}
            Picasso.get().load(args.ProductImgId).into(it.productDetailsImg)
            it.productTotalCostNumber.text = "$20.00"
            it.productDescription.text = getString(R.string.base_description)
            return it.root }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }
}