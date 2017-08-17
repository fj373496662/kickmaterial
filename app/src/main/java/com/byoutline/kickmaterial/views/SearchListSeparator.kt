package com.byoutline.kickmaterial.views

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.byoutline.kickmaterial.R

class SearchListSeparator(context: Context) : RecyclerView.ItemDecoration() {
    private var space = 0

    init {
        space = context.resources.getDimensionPixelSize(R.dimen.recyler_padding)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        val pos = parent.getChildPosition(view)
        outRect.bottom = space
    }
}