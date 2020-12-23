package com.stigma15.pandu

import android.view.View
import androidx.annotation.NonNull
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationBehavior: CoordinatorLayout.Behavior<BottomNavigationView>() {
    /*private val height:Int = 0
    fun onLayoutChild(parent: CoordinatorLayout, child: BottomNavigationView, layoutDirection:Int):Boolean {
        height = child.getHeight()
        return super.onLayoutChild(parent, child, layoutDirection)
    }
    fun onStartNestedScroll(@NonNull coordinatorLayout: CoordinatorLayout,
                            child: BottomNavigationView, @NonNull
                            directTargetChild: View, @NonNull target: View,
                            axes:Int, type:Int):Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }
    fun onNestedScroll(@NonNull coordinatorLayout: CoordinatorLayout, @NonNull child: BottomNavigationView,
                       @NonNull target: View, dxConsumed:Int, dyConsumed:Int,
                       dxUnconsumed:Int, dyUnconsumed:Int,
                       @ViewCompat.NestedScrollType type:Int) {
        if (dyConsumed > 0)
        {
            slideDown(child)
        }
        else if (dyConsumed < 0)
        {
            slideUp(child)
        }
    }
    private fun slideUp(child:BottomNavigationView) {
        child.clearAnimation()
        child.animate().translationY(0).setDuration(200)
    }
    private fun slideDown(child:BottomNavigationView) {
        child.clearAnimation()
        child.animate().translationY(height).setDuration(200)
    }*/
}