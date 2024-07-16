package io.github.myshkouski.kotlin.fact

import io.github.myshkouski.kotlin.Optional
import io.github.myshkouski.kotlin.storage.Storage
import io.github.myshkouski.kotlin.empty

internal class StoredValueProvider<out T>(
    private val name: String,
    private val storage: Storage<ValueProvider<*>>,
    // private val actualType: KClass<T>,
): ValueProvider<T> {
    private val actualProvider: ValueProvider<T>?
        get() {
            val provider = storage.get(name) ?: return null
            // TODO("call 'canProvide' method on ValueProvider")
            return provider as ValueProvider<T>
        }

    override suspend fun provide(): Optional<out T> {
        return actualProvider?.provide() ?: Optional.empty()
    }
}