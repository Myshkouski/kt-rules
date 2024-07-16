package io.github.myshkouski.kotlin

import io.github.myshkouski.kotlin.fact.ValueProvider

interface Storage<T> {
    fun get(key: String): T?
    fun set(key: String, value: T)
    fun remove(key: String)
    fun contains(key: String): Boolean
    fun entries(): Array<Pair<String, T>>
}

internal expect fun <T> createStorage(): Storage<T>

fun <T> Storage(): Storage<T> {
    return createStorage()
}

fun <T> Storage(vararg pairs: Pair<String, T>): Storage<T> {
    val storage = Storage<T>()

    for ((key, value) in pairs) {
        storage.set(key, value)
    }

    return storage
}

fun <T> Storage<ValueProvider<T>>.set(key: String, value: T) {
    val provider = ValueProvider(value)
    return set(key, provider)
}
