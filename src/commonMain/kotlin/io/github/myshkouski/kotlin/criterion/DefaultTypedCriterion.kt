package io.github.myshkouski.kotlin.criterion

import io.github.myshkouski.kotlin.operator.TypedOperator

class DefaultTypedCriterion<in T, in U>(
    private val operator: TypedOperator<T, U>,
    private val value: U,
): TypedCriterion<T> {
    override fun match(value: T): Boolean {
        return operator.match(value, this.value)
    }
}

fun <T, U> TypedCriterion(operator: TypedOperator<T, U>, value: U): TypedCriterion<T> {
    return DefaultTypedCriterion(operator, value)
}
