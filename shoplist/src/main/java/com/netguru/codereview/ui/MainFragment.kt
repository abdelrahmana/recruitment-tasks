package com.netguru.codereview.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.netguru.codereview.network.model.ShopListItemResponse
import com.netguru.codereview.network.model.ShopListResponse
import com.netguru.codereview.shoplist.R
import com.netguru.codereview.shoplist.databinding.MainFragmentBinding
import com.netguru.codereview.ui.model.ShopList
import com.netguru.codereview.util.Util
import androidx.fragment.app.viewModels
import com.netguru.codereview.util.gone
import com.netguru.codereview.util.show
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class MainFragment : Fragment() {

    // suggest using  hilt dagger to declare lazy viewmodel
    private val viewModel: MainViewModel by viewModels()
     lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater, container, false)
        return  binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         // or even use if u dont want to use dagger 2
       // viewModel = Util.Companion.declarViewModel(this) // on fragment
        binding.message?.show()
        viewModel.getShoppingList()
        getViewModelObserver()
    }
private fun getViewModelObserver() {
    viewModel.shopLists.observe(this, { lists ->
        //  val progressBar = view.findViewById<ProgressBar>(R.id.message)
        //  val latestIcon = view.findViewById<ImageView>(R.id.latest_list_icon)

        val shopLists = lists.map { mapShopList(it.first, it.second) }.also {
            binding.latestListIcon?.load(it.first().iconUrl)
        }

        binding.message?.gone()

        Log.i("LOGTAG", "LOLOLOL Is it done already?")


        // Display the list in recyclerview
        // adapter.submitList(shopLists)
    })
    viewModel.events().observe(this, {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    })
}


    private fun mapShopList(list: ShopListResponse, items: List<ShopListItemResponse>) =
        ShopList(
            list.list_id,
            list.userId,
            list.listName,
            list.iconImageLink,
            items
        )
    override fun onDestroyView() {
        // you have to keep an eye on observers and when to remove listeners to avoid issues
        viewModel.events().removeObservers(this)
        viewModel.shopLists.removeObservers(this) // remove listener from here
        super.onDestroyView()
    }
}
