package io.github.myshkouski.kotlin.operator

import io.github.myshkouski.kotlin.util.anyTypeIs

sealed class NumberOperator<T: Number, U: Number>(
    private val factor: Int,
    private val add: Int,
) : TypedOperator<T, U> {
    final override fun match(value: T, operatorValue: U): Boolean {
        return compare(value, operatorValue) * factor + add > 0
    }
}

internal expect fun compare(value: Number, other: Number): Int

internal fun defaultCompare(value: Number, other: Number): Int {
    return when {
        anyTypeIs<Double>(value, other) -> safeCompare(value, other)
        anyTypeIs<Float>(value, other) -> value.toFloat() compareTo other.toFloat()
        anyTypeIs<Long>(value, other) -> value.toLong() compareTo other.toLong()
        anyTypeIs<Int>(value, other) -> value.toInt() compareTo other.toInt()
        anyTypeIs<Short>(value, other) -> value.toDouble() compareTo other.toDouble()
        anyTypeIs<Byte>(value, other) -> value.toDouble() compareTo other.toDouble()
        else -> safeCompare(value, other)
    }
}

internal fun safeCompare(value: Number, other: Number): Int {
    return value.toDouble() compareTo other.toDouble()
}
