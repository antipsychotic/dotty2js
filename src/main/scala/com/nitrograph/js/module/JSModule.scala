package com.nitrograph.js.module

import com.nitrograph.js.module.expression._
import com.nitrograph.js.generator._
import com.nitrograph.js.module.html._

case class JSModule(
    name: String,
    dependencies: Map[String, JSModule] = Map.empty,
    body: JSExpression = JSExpression.Nihil
) extends JSExpression { module =>
    def requestDependencies: String = {
        s"""{
            ${
                dependencies.map(
                    (name, module) => {
                        s"name: dottypack.getModule(${module.name})"
                    }
                ).mkString(",\n")
            }
        }"""
    }

    def defineDependencies: String = {
        s"""{
            ${
                dependencies.map(
                    (dependencyName, dependencyModule) => {
                        s"${dependencyName}: ${dependencyModule}"
                    }
                ).mkString(",\n")
            }
        }"""
    }

    def addExpression(expression: JSExpression): JSModule = {
        expression match {
            case child@JSModule(
                childName,
                dependencies,
                body
            ) => {
                JSModule(
                    module.name,
                    module.dependencies.updated(
                        childName,
                        child
                    ),
                    body
                )
            }
            case JSExpression.Nihil => {
                module
            }
            case compositeJSExpression@CompositeJSExpression(
                sequence
            ) => {
                JSModule(
                    module.name,
                    module.dependencies,
                    module.body :~ compositeJSExpression
                )
            }
            case expression: JSExpression => {
                JSModule(
                    module.name,
                    module.dependencies,
                    expression :~ module.body
                )
            }
        }
    }

    override def toString: String = {
        s"""
        function ${name}() {
            var children = ${module.defineDependencies}
            var dependencies = ${module.requestDependencies}
            ${module.body}
        }
        """
    }
}
