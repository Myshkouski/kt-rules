package io.github.myshkouski.kotlin.validator

import kotlin.reflect.KClass

interface Validator<T> {
    fun validate(value: Any?): Boolean
    fun validatedOrNull(value: Any?): T?
}

internal class DefaultValidator<T: Any>(
    private val kClass: KClass<T>
) : Validator<T> {
    override fun validate(value: Any?): Boolean {
        return kClass.isInstance(value)
    }

    override fun validatedOrNull(value: Any?): T? {
        if (!validate(value)) return null
        return value as T
    }
}
