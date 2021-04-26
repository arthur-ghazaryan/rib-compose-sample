package com.example.rib_compose_sample.rib.child1

import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation

interface Child1 : Rib {

    class Customisation(
        val viewFactory: Child1View.Factory = Child1ViewImpl.Factory()
    ) : RibCustomisation

}