package io.github.myshkouski.kotlin.criterion

import io.github.myshkouski.kotlin.trace.push

internal sealed class AbstractCriterion<in T, U>: TypedCriterion<T> {
    protected abstract val operatorName: String
    protected abstract val value: U

    override fun match(context: CriterionContext<T>): Boolean {
        val operator = context.operators.get(operatorName)
        if (null == operator) {
            context.trace?.push("condition", "Unknown operator '$operatorName'.")
            throw RuntimeException("Unknown operator '$operatorName'")
        }

        context.trace?.push("criterion", "Match against ${context.value}.")
        val result = operator.match(context.value, value)
        context.trace?.push("criterion", "Match result is $result.")
        return result
    }
}

private class DefaultTypedCriterion<in T, U>(
    override val operatorName: String,
    override val value: U,
): AbstractCriterion<T, U>()

// fun <T, U> TypedCriterion(operator: TypedOperator<T, U>, value: U): TypedCriterion<T> {
//     return DefaultTypedCriterion(operator, value)
// }

fun <U> Criterion(operator: String, value: U): TypedCriterion<Any?> {
    return DefaultTypedCriterion(operator, value)
}
