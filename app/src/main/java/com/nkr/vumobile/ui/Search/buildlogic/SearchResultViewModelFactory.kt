package com.nkr.vumobile.ui.Search.buildlogic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nkr.vumobile.repository.ISearchRepository
import com.nkr.vumobile.ui.ImageGalleryViewModel

class SearchResultViewModelFactory(val searchRepo : ISearchRepository): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return ImageGalleryViewModel(
            searchRepo
        ) as T
    }


}