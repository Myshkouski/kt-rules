package io.github.myshkouski.kotlin.condition

import io.github.myshkouski.kotlin.Optional
import io.github.myshkouski.kotlin.criterion.TypedCriterion
import io.github.myshkouski.kotlin.criterion.match
import io.github.myshkouski.kotlin.fact.ValueProvider
import io.github.myshkouski.kotlin.trace.EvaluationTrace
import io.github.myshkouski.kotlin.trace.push

interface Condition<in T> {
    suspend fun evaluate(fact: ValueProvider<T>, trace: EvaluationTrace?): Boolean
}

abstract class AbstractCondition<T>: Condition<T> {
    protected abstract val criterion: TypedCriterion<T>

    override suspend fun evaluate(fact: ValueProvider<T>, trace: EvaluationTrace?): Boolean {
        trace?.push("condition", "Evaluating condition.")

        val optional: Optional<out T> = fact.provide()
        if (!optional.isPresent) {
            trace?.push("condition", "Optional value is not present, so the result will be false.")
            return false
        }
        trace?.push("condition", "Optional value is present.")
        val value: T = optional.value
        trace?.push("condition", "Actual value type ${value?.let { it::class.simpleName }}.")
        trace?.push("condition", "Value equals to $value.")
        val result: Boolean = criterion.match(value, trace)
        trace?.push("condition", "Evaluated result is $result.")
        return result
    }
}

private class DefaultCondition<T>(
    override val criterion: TypedCriterion<T>,
): AbstractCondition<T>()

fun <T> Condition(criterion: TypedCriterion<T>): Condition<T> {
    return DefaultCondition(criterion)
}
