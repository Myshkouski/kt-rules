package io.github.myshkouski.kotlin.operator

class NotEqualOperator<T, U> : TypedOperator<T, U> by ReferentialEqualOperator<T, U>().inverse()
