package com.nitrograph.js.module.variable

case class JSVariable(
    reference: JSVariableDeclaration
) {
    override def toString: String = {
        s"${reference.name}"
    }
}
