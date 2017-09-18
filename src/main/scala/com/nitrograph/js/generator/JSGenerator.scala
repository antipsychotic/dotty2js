package com.nitrograph.js.generator

import com.nitrograph.js.module._

case class JSGenerator(
    modules: Map[String, JSModule]
) {
    override def toString: String = {
        modules.map(_.toString).mkString("\n")
    }
}
