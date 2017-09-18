package com.nitrograph.js.module

import com.nitrograph.js.module._
import expression._

package object dsl {
    def module(name: String)(body: JSModule => JSExpression): JSModule = {
        val module = JSModule(
            name,
            Map.empty
        )
        module.addExpression(
            body(
                module
            )
        )
    }

    val nihil = JSExpression.Nihil
}
