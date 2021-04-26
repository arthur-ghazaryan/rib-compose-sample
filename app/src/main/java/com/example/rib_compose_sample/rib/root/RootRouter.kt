package com.example.rib_compose_sample.rib.root

import android.os.Parcelable
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.Routing
import com.badoo.ribs.routing.resolution.ChildResolution.Companion.child
import com.badoo.ribs.routing.resolution.Resolution
import com.badoo.ribs.routing.router.Router
import com.badoo.ribs.routing.source.RoutingSource
import com.badoo.ribs.routing.transition.handler.TransitionHandler
import com.example.rib_compose_sample.rib.root.RootRouter.*
import com.example.rib_compose_sample.rib.root.RootRouter.Configuration.*
import kotlinx.parcelize.Parcelize

class RootRouter internal constructor(
    buildParams: BuildParams<*>,
    routingSource: RoutingSource<Configuration>,
    private val builders: RootChildBuilders,
    transitionHandler: TransitionHandler<Configuration>? = null
) : Router<Configuration>(
    buildParams = buildParams,
    routingSource = routingSource,
    transitionHandler = transitionHandler
) {
    sealed class Configuration : Parcelable {
        sealed class Content : Configuration() {
            @Parcelize
            object Child1 : Content()

            @Parcelize
            object Child2 : Content()
        }
    }

    override fun resolve(routing: Routing<Configuration>): Resolution =
        with(builders) {
            when (routing.configuration) {
                is Content.Child1 -> child { child1Builder.build(it) }
                is Content.Child2 -> child { child2Builder.build(it) }
            }
        }
}