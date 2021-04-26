package com.example.rib_compose_sample.rib.root

import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation

interface Root : Rib {

    class Customisation(
        val viewFactory: RootView.Factory = RootViewImpl.Factory()
    ) : RibCustomisation

}