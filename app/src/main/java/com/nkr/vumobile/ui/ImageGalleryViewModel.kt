package com.nkr.vumobile.ui


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.nkr.vumobile.adapter.SearchListAdapter
import com.nkr.vumobile.base.BaseViewModel
import com.nkr.vumobile.datasource.SearchUserDataSourceFactory
import com.nkr.vumobile.model.User
import com.nkr.vumobile.repository.ISearchRepository
import com.nkr.vumobile.ui.Search.SearchEvent

class ImageGalleryViewModel(val searchRepo: ISearchRepository) : BaseViewModel<SearchEvent>() {

    var searchAdapter = SearchListAdapter()

    lateinit var searchUserResultList : LiveData<PagedList<User>>
    lateinit var searchUserResultLiveDataSource: MutableLiveData<PageKeyedDataSource<Int, User>>

    var showEmptyUser = MutableLiveData<Boolean>()
    var userQuery = MutableLiveData<String>().default("")


    override fun handleEvent(event: SearchEvent) {

        when(event){
            is SearchEvent.OnStart ->{
                showEmptyUser.value = false
                populateSearchListAdapter()

            }
        }


    }

    private fun populateSearchListAdapter() {


        var factory = SearchUserDataSourceFactory(searchRepo){state ->

            when(state){

                is EventState.onEmptyList -> showEmptyUser.value = true
                is EventState.onError<*> -> {
                    errorMsg.value = getErrorMsg(state.error)

                }

            }
        }

        searchUserResultLiveDataSource = factory.searchUserResultLiveDatasource

        searchUserResultList = LivePagedListBuilder(factory,
            PagedList.Config.Builder()
                .setPageSize(50)
                .setEnablePlaceholders(true)
                .build()
        ).build() as LiveData<PagedList<User>>



        Log.d("userlist_size",searchUserResultList.value?.size.toString())


    }



}
