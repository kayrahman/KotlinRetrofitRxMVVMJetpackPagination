package com.nkr.vumobile.ui.Search.buildlogic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.nkr.vumobile.repository.ISearchRepository
import com.nkr.vumobile.repository.SearchRepo

class SearchResultInjector(application: Application) : AndroidViewModel(application){

    private fun getSearchRepo():ISearchRepository {
        return SearchRepo()

    }

    fun provideSearchViewModelFactory(): SearchResultViewModelFactory =
        SearchResultViewModelFactory(
            getSearchRepo()
        )

}