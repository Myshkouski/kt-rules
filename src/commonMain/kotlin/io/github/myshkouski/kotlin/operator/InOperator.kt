package io.github.myshkouski.kotlin.operator

class InOperator<T, U: T> : io.github.myshkouski.kotlin.operator.TypedOperator<T, Array<out U>> {
    override fun match(value: T, operatorValue: Array<out U>): Boolean {
        return operatorValue.contains(value)
    }
}
