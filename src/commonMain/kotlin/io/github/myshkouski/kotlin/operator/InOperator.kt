package io.github.myshkouski.kotlin.operator

import kotlin.reflect.KClass

class InOperator<T, in U: T> : TypedOperator<T, Array<out U>> {
    override fun match(value: T, operatorValue: Array<out U>): Boolean {
        return operatorValue.contains(value)
    }
}

fun <T: Any> InOperator(type: KClass<T>): TypedOperator<Any?, Any?> {
    val operator: TypedOperator<T, Array<out Any?>> = InOperator()
    return TypesafeOperator(operator, type, Array::class)
}
