package com.nkr.vumobile.adapter

import androidx.recyclerview.widget.DiffUtil
import com.nkr.vumobile.model.User

class SearchDiffUtilCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.avatar == newItem.avatar
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.avatar == newItem.avatar
    }
}