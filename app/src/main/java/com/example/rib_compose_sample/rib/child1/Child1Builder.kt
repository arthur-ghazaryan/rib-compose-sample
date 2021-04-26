package com.example.rib_compose_sample.rib.child1

import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin

class Child1Builder : SimpleBuilder<Child1>() {

    override fun build(buildParams: BuildParams<Nothing?>): Child1 {
        val customisation = buildParams.getOrDefault(Child1.Customisation())
        return node(
            buildParams = buildParams,
            customisation = customisation,
            plugins = emptyList()
        )
    }

    private fun node(
        buildParams: BuildParams<Nothing?>,
        customisation: Child1.Customisation,
        plugins: List<Plugin>
    ) =
        Child1Node(
            buildParams = buildParams,
            viewFactory = customisation.viewFactory,
            plugins = plugins
        )
}