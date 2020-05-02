package com.nkr.vumobile.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object FragmentHelper {


    fun loadFragment(fragmentManager: FragmentManager, fragment: Fragment, parentLayout: String, frameLayout: Int) {
        val transaction = fragmentManager.beginTransaction()
        transaction.add(frameLayout, fragment)
        transaction.addToBackStack(parentLayout)
        transaction.commit()
    }


}