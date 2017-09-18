package com.nitrograph.js.module.expression

trait JSExpression { self =>
    def :~(that: JSExpression): CompositeJSExpression = {
        CompositeJSExpression(List(self, that))
    }
}

case class CompositeJSExpression(
    sequence: List[JSExpression] = List.empty
) extends JSExpression { compositeJSEXpression =>
    override def :~(that: JSExpression): CompositeJSExpression = {
        CompositeJSExpression(
            compositeJSEXpression.sequence ::: (that :: Nil)
        )
    }

    override def toString: String = {
        sequence.map(_.toString).mkString("\n")
    }
}

object JSExpression {
    case object Nihil extends JSExpression {
        override def toString: String = {
            ""
        }
    }
}
