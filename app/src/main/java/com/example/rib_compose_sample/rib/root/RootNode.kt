package com.example.rib_compose_sample.rib.root


import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.view.ViewFactory

class RootNode internal constructor(
    buildParams: BuildParams<*>,
    viewFactory: ViewFactory<Nothing?, RootView>?,
    plugins: List<Plugin> = emptyList()
) : Node<RootView>(
    buildParams = buildParams,
    viewFactory = viewFactory?.invoke(null),
    plugins = plugins
), Root