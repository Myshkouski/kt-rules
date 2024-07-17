package io.github.myshkouski.kotlin.operator

import kotlin.reflect.KClass

class InTypedOperator<T, in U: T> : TypedOperator<T, Array<out U>> {
    override fun match(value: T, operatorValue: Array<out U>): Boolean {
        return operatorValue.contains(value)
    }
}

fun <T: Any> InOperator(type: KClass<T>): TypedOperator<Any?, Any?> {
    val operator: TypedOperator<T, Array<out Any?>> = InTypedOperator()
    return TypesafeOperator(operator, type, Array::class)
}

// inline fun <reified U: Any> InOperator(): TypedOperator<Any?, Any?> {
//     return InOperator(U::class)
// }

inline fun InOperator(): TypedOperator<Any?, Any?> {
    return InOperator(Any::class)
}
