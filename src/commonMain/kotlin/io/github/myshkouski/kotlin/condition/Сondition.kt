package io.github.myshkouski.kotlin.condition

import io.github.myshkouski.kotlin.Optional
import io.github.myshkouski.kotlin.criterion.CriterionContext
import io.github.myshkouski.kotlin.criterion.TypedCriterion
import io.github.myshkouski.kotlin.fact.ValueProvider
import io.github.myshkouski.kotlin.operator.Operator
import io.github.myshkouski.kotlin.storage.Storage
import io.github.myshkouski.kotlin.trace.EvaluationTrace
import io.github.myshkouski.kotlin.trace.push

interface Condition<in T> {
    suspend fun evaluate(context: ConditionContext<T>): Boolean
}

interface EvaluationContext {
    val operators: Storage<Operator>
    val trace: EvaluationTrace?
}

class ConditionContext<out T>(
    val fact: ValueProvider<T>,
    private val context: EvaluationContext,
): EvaluationContext by context

abstract class AbstractCondition<T>: Condition<T> {
    protected abstract val criterion: TypedCriterion<T>

    override suspend fun evaluate(context: ConditionContext<T>): Boolean {
        context.trace?.push("condition", "Evaluating condition.")

        val optional: Optional<out T> = context.fact.provide()
        if (!optional.isPresent) {
            context.trace?.push("condition", "Optional value is not present, so the result will be false.")
            return false
        }
        context.trace?.push("condition", "Optional value is present.")
        val value: T = optional.value
        // context.trace?.push("condition", "Actual value type ${value?.let { it::class.simpleName }}.")
        // context.trace?.push("condition", "Value equals to $value.")

        val result: Boolean = criterion.match(
            context = CriterionContext(value, context)
        )
        context.trace?.push("condition", "Evaluated result is $result.")
        return result
    }
}

private class DefaultCondition<T>(
    override val criterion: TypedCriterion<T>,
): AbstractCondition<T>()

fun <T> Condition(criterion: TypedCriterion<T>): Condition<T> {
    return DefaultCondition(criterion)
}
