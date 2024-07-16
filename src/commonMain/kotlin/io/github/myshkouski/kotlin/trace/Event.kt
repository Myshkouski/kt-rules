package io.github.myshkouski.kotlin.trace

interface TypedEvent<T> {
    val type: String
    val payload: T?
}

typealias Event = TypedEvent<*>

internal data class DefaultEvent(
    override val type: String,
    override val payload: Any? = null,
): TypedEvent<Any?>
