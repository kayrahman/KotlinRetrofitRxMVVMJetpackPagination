package com.nkr.vumobile.repository

import com.nkr.vumobile.base.BaseRepo

interface ISearchRepository {
    fun getUserSearchList(page_num:Int,requestStatusCallBack : BaseRepo.RequestStatusCallBack)
}