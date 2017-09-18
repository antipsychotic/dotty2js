package com.nitrograph.js.module.html

import com.nitrograph.js.module.expression._

trait HTMLContainer
    extends JSExpression

case object Body
    extends HTMLContainer
{
    override def toString: String = {
        "document.body"
    }
}

case class HTMLDiv(
    id: String,
    parent: Option[JSExpression] = None,
    child: Option[(JSExpression | String)] = None
) extends HTMLContainer { div =>
    def attachTo(container: JSExpression): HTMLDiv = {
        HTMLDiv(
            div.id,
            Some(container),
            child
        )
    }

    def text(text: String): HTMLDiv = {
        HTMLDiv(
            div.id,
            div.parent,
            Some(text)
        )
    }

    def setChild(child: JSExpression): HTMLDiv = {
        HTMLDiv(
            div.id,
            div.parent,
            Some(child)
        )
    }

    override def toString: String = {
        val stringified = s"""
        document.createElement(
            "div"
        )
        """
        div.parent.map(parent =>
            s"""
                ${
                    val attachedDiv = s"${parent.toString}.appendChild(${stringified})"
                    child match {
                        case Some(expression) => {
                            expression match {
                                case text: String => {
                                    val createdTextElement = s"document.createTextNode('${text}')"
                                    s"(function (parent) {return parent.appendChild(${createdTextElement})})(${attachedDiv})"
                                }
                                case expression: JSExpression =>
                                    s"(function (parent) {return parent.appendChild(${expression})})(${attachedDiv})"
                            }
                        }
                        case None => {
                            s"$stringified"
                        }
                    }
                }
            """
        ).getOrElse(
            s"""
                document.createElement(
                    'div'
                )
            """
        )
    }
}