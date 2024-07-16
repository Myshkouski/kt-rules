package io.github.myshkouski.kotlin.operator

open class ReferentialEqualOperator<T, U> : TypedOperator<T, U> {
    override fun match(value: T, operatorValue: U): Boolean {
        return value === operatorValue
    }
}