package com.example.rib_compose_sample

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.viewinterop.AndroidView
import com.badoo.ribs.android.AndroidRibViewHost
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.view.RibView

abstract class ComposeRibView(
    override val context: Context
) : RibView {

    abstract val composable: ComposeView

    /**
     * Only for compatibility with [com.badoo.ribs.core.view.AndroidRibView] parents.
     *
     * Will not be constructed / used if parent is also [ComposeRibView]. In those cases
     * current [composable] is used directly.
     */
    override val androidView: ViewGroup by lazy {
        androidx.compose.ui.platform.ComposeView(context).apply {
            setContent(composable)
        }
    }

    protected open fun getParentViewForSubtree(subtreeOf: Node<*>): MutableState<ComposeView?> =
        mutableStateOf(null)

    override fun attachChild(child: Node<*>, subtreeOf: Node<*>) {
        val target = getParentViewForSubtree(subtreeOf)

        when (val childView = child.onCreateView(this)) {
            is ComposeRibView -> {
                child.onAttachToView()
                target.value = childView.composable
            }

            else -> {
                val innerContainer = FrameLayout(context)
                AndroidRibViewHost(innerContainer).attachChild(child)
                target.value = { AndroidView(factory = { innerContainer }) }
            }
        }
    }

    override fun detachChild(child: Node<*>, subtreeOf: Node<*>) {
        child.onDetachFromView()
        getParentViewForSubtree(subtreeOf).value = null //TODO removing this fix issue
    }
}