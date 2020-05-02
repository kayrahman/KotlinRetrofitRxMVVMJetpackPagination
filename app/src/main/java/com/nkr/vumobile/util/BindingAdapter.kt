package com.nkr.vumobile.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.*
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper


@BindingAdapter("adapterGrid")
fun setAdapterGrid(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
    val mLayoutManager = GridLayoutManager(view.context, 2)
    view.layoutManager = mLayoutManager
    val spanCount = 3 // 3 columns
    val spacing = 10 // 50px
    val includeEdge = false
   // view.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
    // Vertical
    OverScrollDecoratorHelper.setUpOverScroll(view, OverScrollDecoratorHelper.ORIENTATION_VERTICAL)
}