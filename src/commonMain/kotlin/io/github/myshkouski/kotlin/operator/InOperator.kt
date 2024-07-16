package io.github.myshkouski.kotlin.operator

class InOperator<T, in U: T> : TypedOperator<T, Array<out U>> {
    override fun match(value: T, operatorValue: Array<out U>): Boolean {
        return operatorValue.contains(value)
    }
}
