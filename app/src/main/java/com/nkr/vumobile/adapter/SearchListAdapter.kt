package com.nkr.vumobile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nkr.vumobile.R
import com.nkr.vumobile.model.User
import kotlinx.android.synthetic.main.list_item_user_info.view.*

class SearchListAdapter : PagedListAdapter<User, SearchListAdapter.UserViewHolder>(SearchDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return UserViewHolder(
            inflater.inflate(R.layout.list_item_user_info, parent, false)
        )

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position).let { user ->
            holder.username.text = user!!.first_name + user.last_name

            Glide.with(holder.itemView.context)
                .load(user.avatar)
                .apply(
                    RequestOptions()
                    .placeholder(R.drawable.ic_user)
                )

                .into(holder.profImage)

         //   Log.d("username",user.login)


            holder.profImage.setOnClickListener {
                listener!!.onImageItemClick(user.avatar)
            }

        }

    }


    class UserViewHolder(root:View): RecyclerView.ViewHolder(root){
        var username: TextView = root.tv_list_item_user_info
        var profImage: ImageView = root.iv_list_item_user_info

    }



    var listener: AdapterListener? = null

    interface AdapterListener {
        fun onImageItemClick(img_url:String)
    }

}