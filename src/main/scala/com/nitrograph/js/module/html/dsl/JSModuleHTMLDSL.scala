package com.nitrograph.js.module.html

import com.nitrograph.js.module._
import com.nitrograph.js.module.expression._
import html._

package object dsl {
    def div(id: String)(body: HTMLDiv => JSExpression): HTMLDiv = {
        val target = HTMLDiv(
            id = id,
            None
        )
        target.setChild(
            body(
                target
            )
        )
    }

    object html {
        val body = Body
    }
}
