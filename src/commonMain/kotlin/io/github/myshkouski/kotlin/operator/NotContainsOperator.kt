package io.github.myshkouski.kotlin.operator

class NotContainsOperator<T: U, U>: TypedOperator<Array<out T>, U> by ContainsOperator<T, U>().inverse()
