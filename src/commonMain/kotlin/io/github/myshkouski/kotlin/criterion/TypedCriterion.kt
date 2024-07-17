package io.github.myshkouski.kotlin.criterion

import io.github.myshkouski.kotlin.condition.EvaluationContext

class CriterionContext<out T>(
    val value: T,
    private val context: EvaluationContext
) : EvaluationContext by context

sealed interface TypedCriterion<in T> {
    fun match(context: CriterionContext<T>): Boolean
}

// fun <T> TypedCriterion<T>.match(value: T, trace: EvaluationTrace?) : Boolean {
//     return if (null == trace) match(value) else match(value, trace)
// }
