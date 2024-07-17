package io.github.myshkouski.kotlin.operator

import kotlin.reflect.KClass

class ContainsTypedOperator<in T: U, U> : TypedOperator<Array<out T>, U> {
    override fun match(value: Array<out T>, operatorValue: U): Boolean {
        return value.contains(operatorValue)
    }
}

fun <U: Any> ContainsOperator(operatorType: KClass<U>): TypedOperator<Any?, Any?> {
    val operator: TypedOperator<Array<out Any?>, U> = ContainsTypedOperator()
    return TypesafeOperator(operator, Array::class, operatorType)
}

// inline fun <reified U: Any> ContainsOperator(): TypedOperator<Any?, Any?> {
//     return ContainsOperator(U::class)
// }

inline fun ContainsOperator(): TypedOperator<Any?, Any?> {
    return ContainsOperator(Any::class)
}
