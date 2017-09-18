package com.nitrograph.js.module.variable

import com.nitrograph.js.module.value._
import com.nitrograph.js.module._

case class JSVariableDeclaration(
    name: String,
    initialValue: JSValuable
)(implicit val parent: JSModule) {
    override def toString: String = {
        s"var $name = $initialValue"
    }
}
