package com.example.rib_compose_sample.rib.child1


import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.view.ViewFactory

class Child1Node internal constructor(
    buildParams: BuildParams<*>,
    viewFactory: ViewFactory<Nothing?, Child1View>?,
    plugins: List<Plugin> = emptyList()
) : Node<Child1View>(
    buildParams = buildParams,
    viewFactory = viewFactory?.invoke(null),
    plugins = plugins
), Child1