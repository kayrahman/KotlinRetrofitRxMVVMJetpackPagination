package com.nkr.vumobile.datasource


import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.nkr.vumobile.model.User
import com.nkr.vumobile.repository.ISearchRepository
import com.nkr.vumobile.ui.EventState


class SearchUserDataSourceFactory(val repo : ISearchRepository , val onAction: (event : EventState) -> Unit) : DataSource.Factory<Int,User>() {

    private var searchUserResultDataSource: MutableLiveData<PageKeyedDataSource<Int, User>> = MutableLiveData()
    val searchUserResultLiveDatasource: MutableLiveData<PageKeyedDataSource<Int, User>> get() = searchUserResultDataSource



    override fun create(): SearchUserDataSource {
        val dataSource = SearchUserDataSource(repo,onAction)
        searchUserResultDataSource.postValue(dataSource)
        return dataSource

    }


}