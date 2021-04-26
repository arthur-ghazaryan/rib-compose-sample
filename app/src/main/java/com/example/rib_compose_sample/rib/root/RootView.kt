package com.example.rib_compose_sample.rib.root

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.example.rib_compose_sample.ComposeRibView
import com.example.rib_compose_sample.ComposeView
import com.example.rib_compose_sample.rib.root.RootView.*
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

interface RootView : RibView, ObservableSource<Event>,
    Consumer<ViewModel> {

    sealed class Event {
        object Next : Event()
    }

    data class ViewModel(
        val text: String = "Initial view model text"
    )

    interface Factory : ViewFactory<Nothing?, RootView>

}

class RootViewImpl(
    context: Context,
    private val events: PublishRelay<Event> = PublishRelay.create()
) : ComposeRibView(context), RootView, ObservableSource<Event> by events {

    class Factory : RootView.Factory {
        override fun invoke(deps: Nothing?): (RibView) -> RootView = {
            RootViewImpl(
                it.context
            )
        }
    }

    private var viewModel: MutableState<ViewModel> = mutableStateOf(ViewModel())

    override fun accept(vm: ViewModel) {
        viewModel.value = vm
    }

    private val content = mutableStateOf<ComposeView?>(null)

    override val composable: @Composable () -> Unit = {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.weight(1f)) {
                content.value?.invoke()
            }
            Button(onClick = { events.accept(Event.Next) }) {
                Text("Next")
            }
        }
    }

    override fun getParentViewForSubtree(subtreeOf: Node<*>): MutableState<ComposeView?> {
        return content
    }

}