package com.example.rib_compose_sample.rib.child2

import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin

class Child2Builder : SimpleBuilder<Child2>() {

    override fun build(buildParams: BuildParams<Nothing?>): Child2 {
        val customisation = buildParams.getOrDefault(Child2.Customisation())
        return node(
            buildParams = buildParams,
            customisation = customisation,
            plugins = emptyList()
        )
    }

    private fun node(
        buildParams: BuildParams<Nothing?>,
        customisation: Child2.Customisation,
        plugins: List<Plugin>
    ) =
        Child2Node(
            buildParams = buildParams,
            viewFactory = customisation.viewFactory,
            plugins = plugins
        )
}