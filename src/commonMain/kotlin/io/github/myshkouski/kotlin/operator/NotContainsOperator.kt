package io.github.myshkouski.kotlin.operator

class NotContainsOperator<T: U, U>: TypedOperator<Array<out T>, U> by ContainsTypedOperator<T, U>().inverse()
