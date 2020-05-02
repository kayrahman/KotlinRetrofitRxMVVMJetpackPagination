package com.nkr.vumobile.ui

sealed class EventState {

    object onLoadingStart : EventState()
    object onLoadingEnd : EventState()
    object onEmptyList : EventState()
    data class onError<E>(val error : E) : EventState()
}