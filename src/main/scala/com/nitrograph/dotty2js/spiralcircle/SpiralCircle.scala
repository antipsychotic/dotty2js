package com.nitrograph.dotty2js.spiralcircle

import com.nitrograph.js._
import generator._
import module._

object SpiralCircle {
    val Module = JSModule(
        name = "SpiralCircle",
        dependencies = List(
            "Petal" -> JSModule(
                name = "Petal",
                dependencies = Map.empty
            )
        ).toMap
    )

    object DSL {
        import module.html.dsl._
        import module.dsl._

        val Module = module("SpiralCircle") { spiralcircle =>
            module("Petal") { petal =>
                nihil
            } :~
            (
                div(
                    id = "spiral-circle"
                ) { spiralCircle =>
                    (
                        div(
                            id = "spiral-circle-header"
                        ) { header =>
                            nihil
                        }
                    ).attachTo(
                        spiralCircle
                    ).text(
                        "Hello dotty2js"
                    ) :~ (
                        div(
                            id = "spiral-circle-center"
                        ) { center =>
                            nihil
                        }
                    ).attachTo(
                        spiralCircle
                    ).text(
                        "SpiralCircle"
                    )
                }
            ).attachTo(
                html.body
            )
        }
    }
}
