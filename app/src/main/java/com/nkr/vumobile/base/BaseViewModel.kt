package com.nkr.vumobile.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONObject
import retrofit2.HttpException

abstract class BaseViewModel <T> : ViewModel() {
    abstract fun handleEvent(event:T)


    val TAG = this::class.java.simpleName

    //var showLoading = MutableLiveData<Boolean>().default(false)
    var showLoading = MutableLiveData<Boolean>().default(false)


    //LiveData references:
    protected val errorMsg = MutableLiveData<String>()
    val error: LiveData<String> get() = errorMsg

    protected val loadingState = MutableLiveData<Unit>()
    val loading: LiveData<Unit> get() = loadingState



    /**
     * show loading flag
     */
    fun apiCallStart() {
      //  apiCallCount++
        showLoading.value = true

    }

    /**
     * dismiss loading function
     */
    fun apiCallFinish() {

        showLoading.value = false

    }

    /**
     * default value of mutable list
     */
    fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply {
        value = initialValue
    }


    /**
     * convert throwable to string
     */
    fun <T> getErrorMsg(t: T): String {
        if (t is String) {
            return t
        } else if (t is HttpException) {
            val body = (t as HttpException).response()
            var errorBody = body.errorBody()
            try {
                val jObjError = JSONObject(errorBody?.string())
                return jObjError.getString("message")
            } catch (e: Exception) {
            }
        } else if (t is Throwable) {
            if (t.localizedMessage != null) {
                return t.localizedMessage
            }
        }
       // return mResourceProvider?.getString(R.string.error_unknown)!!
        return "Unknown error"
    }

}