package com.example.rib_compose_sample.rib.root

import androidx.lifecycle.Lifecycle
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.source.backstack.BackStack
import com.badoo.ribs.routing.source.backstack.operation.push
import io.reactivex.functions.Consumer

class RootInteractor(
    buildParams: BuildParams<Nothing?>,
    private val backStack: BackStack<RootRouter.Configuration>
) : Interactor<Root, RootView>(buildParams) {

    private var childToPush: RootRouter.Configuration.Content =
        RootRouter.Configuration.Content.Child2

    private val eventConsumer = Consumer<RootView.Event> {
        backStack.push(childToPush)
        childToPush =
            if (childToPush == RootRouter.Configuration.Content.Child2) RootRouter.Configuration.Content.Child1 else RootRouter.Configuration.Content.Child2
    }

    override fun onViewCreated(view: RootView, viewLifecycle: Lifecycle) {
        super.onViewCreated(view, viewLifecycle)
        viewLifecycle.startStop {
            bind(view to eventConsumer)
        }
    }

}