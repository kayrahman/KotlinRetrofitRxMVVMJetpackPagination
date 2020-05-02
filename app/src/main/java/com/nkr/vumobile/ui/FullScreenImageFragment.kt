package com.nkr.vumobile.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.nkr.vumobile.R
import kotlinx.android.synthetic.main.fragment_full_screen_image.*


private const val ARG_PARAM1 = "param1"


class FullScreenImageFragment : Fragment() {

    private var img_url: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            img_url = it.getString(ARG_PARAM1)

        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_full_screen_image, container, false)
    }

    override fun onStart() {
        super.onStart()


            Glide.with(requireContext())
                .load(img_url)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_user)
                )

                .into(iv_user_fullsc)


    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            FullScreenImageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}
