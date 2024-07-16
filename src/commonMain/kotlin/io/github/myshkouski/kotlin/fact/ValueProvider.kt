package io.github.myshkouski.kotlin.fact

import io.github.myshkouski.kotlin.Optional
import io.github.myshkouski.kotlin.of

interface ValueProvider<out T> {
    suspend fun provide(): Optional<out T>
}

typealias Fact = ValueProvider<*>

internal class DefaultValueProvider<out T>(
    val value: T
) : ValueProvider<T> {
    override suspend fun provide(): Optional<out T> {
        return Optional.of(value)
    }
}

fun <T> ValueProvider(value: T): ValueProvider<T> {
    return DefaultValueProvider(value)
}
