package com.nkr.vumobile.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.nkr.vumobile.R
import com.nkr.vumobile.adapter.SearchListAdapter
import com.nkr.vumobile.databinding.ImageGalleryFragmentBinding
import com.nkr.vumobile.ui.Search.SearchEvent
import com.nkr.vumobile.ui.Search.buildlogic.SearchResultInjector
import com.nkr.vumobile.util.FragmentHelper

class ImageGalleryFragment : Fragment() {

    companion object {
        fun newInstance() = ImageGalleryFragment()
    }

    private lateinit var viewModel: ImageGalleryViewModel
    private lateinit var binding: ImageGalleryFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.image_gallery_fragment, container, false)


        initViewModel(savedInstanceState)


        return binding.root

    }

    private fun initViewModel(savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(
            this,
            SearchResultInjector(requireActivity().application).provideSearchViewModelFactory()
        ).get(ImageGalleryViewModel::class.java)


        binding.viewModel= viewModel
        binding.lifecycleOwner = this

        if(savedInstanceState == null) {
            viewModel.handleEvent(SearchEvent.OnStart)
        }

        observeViewModel()

        setupAdapter()

        setupListener()


    }

    private fun setupListener() {
        viewModel.searchAdapter.listener = object : SearchListAdapter.AdapterListener{
            override fun onImageItemClick(img_url: String) {

                val full_sc_image_fm = FullScreenImageFragment.newInstance(img_url)
                listener!!.goToFragment(full_sc_image_fm)


            }

        }
    }


    private fun setupAdapter() {

        viewModel.searchUserResultList.observe(
            viewLifecycleOwner,
            Observer { userList ->
                viewModel.searchAdapter.submitList(userList)

            }
        )


    }


    private fun observeViewModel() {

        viewModel.showEmptyUser.observe(this, Observer { aBoolean ->
            if (aBoolean) {
                binding.tvEmptyUser.visibility = View.VISIBLE
            }else{
                binding.tvEmptyUser.visibility = View.GONE
            }
        })

        viewModel.error.observe(this, Observer { errorMsg ->
            Toast.makeText(requireContext(),errorMsg,Toast.LENGTH_LONG).show()
        })



    }


    override fun onDestroyView() {
        super.onDestroyView()

       binding.rvSavedSearch.adapter = null

    }


    var listener: Listener? = null

    interface Listener {
        fun goToFragment(fragment: Fragment)
    }

}
