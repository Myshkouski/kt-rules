package io.github.myshkouski.kotlin.operator

fun TypedOperator<*, *>.unsafeCastToGeneric(): TypedOperator<Any?, Any?> {
    return this as TypedOperator<Any?, Any?>
}