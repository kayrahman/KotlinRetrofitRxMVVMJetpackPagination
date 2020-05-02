package com.nkr.vumobile.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.nkr.vumobile.base.BaseRepo
import com.nkr.vumobile.model.UserSearchResponse
import com.nkr.vumobile.model.User
import com.nkr.vumobile.repository.ISearchRepository
import com.nkr.vumobile.ui.EventState

class SearchUserDataSource(
    val repo : ISearchRepository,
    val onAction: ( event: EventState) -> Unit

) : PageKeyedDataSource<Int,User>() {

    val FIRST_PAGE : Int = 1

   // val searchRepo = SearchRepo()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, User>
    ) {

        repo.getUserSearchList(FIRST_PAGE,object : BaseRepo.RequestStatusCallBack{
            override fun startRequest() {

                onAction(EventState.onLoadingStart)

            }

            override fun endRequest() {

                onAction(EventState.onLoadingEnd)

            }

            override fun <T> resultSuccess(results: T) {
                Log.d("search_result_initial",results.toString())
                //update search user list here

                var userList = mutableListOf<User>()

                var response = results as UserSearchResponse

                for(item in response.data){
                    userList.add(item)
                }


                if(userList.isEmpty()){
                    onAction(EventState.onEmptyList)

                }else {

                    callback.onResult(userList, null, FIRST_PAGE + 1)

                }
            }

            override fun <T> resultError(t: T) {
                Log.d("search_result_initial",t.toString())
                onAction(EventState.onError(t))}

        })



    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, User>
    ) {


        repo.getUserSearchList(params.key,object : BaseRepo.RequestStatusCallBack{
            override fun startRequest() {

            }

            override fun endRequest() {

            }

            override fun <T> resultSuccess(results: T) {
                Log.d("search_result",results.toString())
                //update search user list here

                var key = params.key + 1

                var userList = mutableListOf<User>()
                var response = results as UserSearchResponse

                for(item in response.data){
                    userList.add(item)
                }
                callback.onResult(userList,key)


                Log.d("page_key_load_after",key.toString())


            }

            override fun <T> resultError(t: T) {
                //Log.d("search_result_after",t.toString())            }
                Log.d("search_result_after",t.toString())

                onAction(EventState.onError(t))
            }

        })


    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, User>
    ) {

        repo.getUserSearchList(params.key,object : BaseRepo.RequestStatusCallBack{
            override fun startRequest() {

            }

            override fun endRequest() {

            }

            override fun <T> resultSuccess(results: T) {
                Log.d("search_result",results.toString())
                //update search user list here

                var key = 0

                if(params.key >1){

                    key = params.key - 1

                }else{

                    key = params.key
                }

                var userList = mutableListOf<User>()
                var response = results as UserSearchResponse

                for(item in response.data){
                    userList.add(item)
                }
                callback.onResult(userList,key)

                Log.d("page_key_load_before",key.toString())


            }

            override fun <T> resultError(t: T) {

                onAction(EventState.onError(t))

                Log.d("search_result_before",t.toString())            }

        })

    }
}