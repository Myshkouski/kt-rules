@file:OptIn(ExperimentalJsExport::class)

package io.github.myshkouski.kotlin.operator

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.reflect.KClass

@JsExport
fun interface TypedOperator<in T, in U> {
    fun match(value: T, operatorValue: U): Boolean
}

class TypesafeOperator<T, U>(
    private val actualOperator: TypedOperator<T, U>,
    private val type: KClass<*>,
    private val operatorType: KClass<*>,
): TypedOperator<Any?, Any?> {
    override fun match(value: Any?, operatorValue: Any?): Boolean {
        if (!type.isInstance(value) || !operatorType.isInstance(operatorValue)) {
            return false
        }
        return actualOperator.match(value as T, operatorValue as U)
    }
}

typealias Operator = TypedOperator<Any?, Any?>
