package com.example.rib_compose_sample.rib.child1

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.example.rib_compose_sample.ComposeRibView
import com.example.rib_compose_sample.rib.root.RootView.Event
import com.example.rib_compose_sample.rib.root.RootView.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

interface Child1View : RibView, ObservableSource<Event>,
    Consumer<ViewModel> {

    sealed class Event

    data class ViewModel(
        val text: String = "Initial view model text"
    )


    interface Factory : ViewFactory<Nothing?, Child1View>

}

class Child1ViewImpl(
    context: Context,
    private val events: PublishRelay<Event> = PublishRelay.create()
) : ComposeRibView(context), Child1View, ObservableSource<Event> by events {

    class Factory : Child1View.Factory {
        override fun invoke(deps: Nothing?): (RibView) -> Child1View = {
            Child1ViewImpl(
                it.context
            )
        }
    }

    private var viewModel: MutableState<ViewModel> = mutableStateOf(ViewModel())

    override fun accept(vm: ViewModel) {
        viewModel.value = vm
    }

    override val composable: @Composable () -> Unit = {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)) {

        }
    }

}