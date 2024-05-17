package me.ashutoshkk.feedbackapp.common

import android.content.Context
import android.util.TypedValue
import androidx.recyclerview.widget.RecyclerView

object Utils {

    fun Context.dpToPx(dp: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, this.resources.displayMetrics).toInt()
    }

    fun RecyclerView.clearItemDecorations() {
        while (itemDecorationCount > 0) {
            removeItemDecorationAt(0)
        }
    }

}