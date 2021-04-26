package com.example.rib_compose_sample.rib.child2

import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation

interface Child2 : Rib {

    class Customisation(
        val viewFactory: Child2View.Factory = Child2ViewImpl.Factory()
    ) : RibCustomisation

}