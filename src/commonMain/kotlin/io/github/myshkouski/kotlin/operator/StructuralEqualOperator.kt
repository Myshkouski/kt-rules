package io.github.myshkouski.kotlin.operator

class StructuralEqualOperator<in T, in U> : TypedOperator<T, U> {
    override fun match(value: T, operatorValue: U): Boolean {
        return value == operatorValue
    }
}

typealias EqualOperator<T, U> = StructuralEqualOperator<T, U>
