package io.github.myshkouski.kotlin.operator

sealed class NumberOperator<T: Number, U: Number>(
    private val factor: Int,
    private val add: Int,
) : TypedOperator<T, U> {
    final override fun match(value: T, operatorValue: U): Boolean {
        return compare(value, operatorValue) * factor + add > 0
    }
}

internal expect fun compare(value: Number, other: Number): Int
