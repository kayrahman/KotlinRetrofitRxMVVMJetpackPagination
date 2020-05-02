package com.nkr.vumobile.repository

import com.nkr.vumobile.base.BaseRepo
import com.nkr.vumobile.network.NetworkModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SearchRepo : BaseRepo(),ISearchRepository {

    override fun getUserSearchList(page_num:Int,requestStatusCallBack : RequestStatusCallBack) {
        val networkApi = NetworkModule.networkApi

        networkApi.searchUser(page_num)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { requestStatusCallBack.startRequest() }
            .doOnTerminate { requestStatusCallBack.endRequest() }
            .subscribe(
                { result -> requestStatusCallBack.resultSuccess(result) },
                { t -> requestStatusCallBack.resultError(t) }

            )

    }


}