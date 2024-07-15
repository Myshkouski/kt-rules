package fact

import Parameters

sealed interface ValueProvider<out T>

typealias Fact = ValueProvider<*>

interface EagerValueProvider<out T>: ValueProvider<T> {
    val value: T
}

interface LazyValueProvider<out T>: ValueProvider<T> {
    suspend fun provide(): T
    suspend fun provide(parameters: Parameters): T {
        TODO()
    }
}

suspend fun <T> ValueProvider<T>.provide(): T {
    return when (this) {
        is EagerValueProvider<T> -> value
        is LazyValueProvider<T> -> provide()
    }
}

internal class DefaultEagerValueProvider<out T>(
    override val value: T
) : EagerValueProvider<T>

fun <T> ValueProvider(value: T): ValueProvider<T> {
    return DefaultEagerValueProvider(value)
}
