package io.github.myshkouski.kotlin.operator

class NotInOperator<T, U: T>: TypedOperator<T, Array<out U>> by io.github.myshkouski.kotlin.operator.InOperator<T, U>().inverse()
