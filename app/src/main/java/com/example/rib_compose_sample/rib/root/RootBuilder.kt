package com.example.rib_compose_sample.rib.root

import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.routing.source.backstack.BackStack

class RootBuilder(
) : SimpleBuilder<Root>() {

    override fun build(buildParams: BuildParams<Nothing?>): Root {

        val customisation = buildParams.getOrDefault(Root.Customisation())

        val backStack = BackStack<RootRouter.Configuration>(
            initialConfiguration = RootRouter.Configuration.Content.Child1,
            buildParams = buildParams
        )

        val interactor = RootInteractor(
            buildParams = buildParams,
            backStack = backStack
        )

        val router = RootRouter(
            buildParams = buildParams,
            routingSource = backStack,
            builders = RootChildBuilders()
        )

        return node(
            buildParams = buildParams,
            customisation = customisation,
            plugins = listOf(interactor, router)
        )
    }

    private fun node(
        buildParams: BuildParams<Nothing?>,
        customisation: Root.Customisation,
        plugins: List<Plugin>
    ) =
        RootNode(
            buildParams = buildParams,
            viewFactory = customisation.viewFactory,
            plugins = plugins
        )
}