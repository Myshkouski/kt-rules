package io.github.myshkouski.kotlin.criterion

import io.github.myshkouski.kotlin.trace.EvaluationTrace
import io.github.myshkouski.kotlin.trace.push

sealed interface TypedCriterion<in T> {
    fun match(value: T): Boolean
    fun match(value: T, trace: EvaluationTrace): Boolean {
        trace.push("criterion", "Match against $value.")
        val result = match(value)
        trace.push("criterion", "Match result is $result.")
        return result
    }
}

fun <T> TypedCriterion<T>.match(value: T, trace: EvaluationTrace?) : Boolean {
    return if (null == trace) match(value) else match(value, trace)
}
