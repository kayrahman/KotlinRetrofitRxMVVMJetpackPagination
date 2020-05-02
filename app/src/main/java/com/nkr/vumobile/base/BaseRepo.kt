package com.nkr.vumobile.base

abstract class BaseRepo {

    /**
     * common
     */
    interface RequestStatusCallBack {
        fun startRequest()
        fun endRequest()
        fun <T> resultSuccess(results: T)
        fun <T> resultError(t: T)
    }

}