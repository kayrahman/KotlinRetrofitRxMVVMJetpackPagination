package com.nkr.vumobile.model

data class UserSearchResponse(val total : Int,
                              val data : MutableList<User>
                                )