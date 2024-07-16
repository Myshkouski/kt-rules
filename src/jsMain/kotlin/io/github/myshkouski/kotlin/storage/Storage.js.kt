package io.github.myshkouski.kotlin.storage

import js.array.JsTuple2
import js.collections.JsMap

internal actual fun <T> emptyStorage(): Storage<T> {
    return JsMapStorage()
}

private class JsMapStorage<T> : Storage<T> {
    private val map = JsMap<String, T>()

    override fun get(key: String): T? {
        return map[key]
    }

    override fun remove(key: String) {
        map.delete(key)
    }

    override fun contains(key: String): Boolean {
        return map.has(key)
    }

    override fun entries(): Array<out Pair<String, T>> {
        val iterator = map.entries()
        val array: Array<JsTuple2<String, T>> = iterator.asDynamic().toArray().unsafeCast<Array<JsTuple2<String, T>>>()
        // val array: ReadonlyArray<JsTuple2<String, T>> = iterator.toArray()
        return Array(array.size) {
            val (key, value) = array[it]
            key to value
        }
    }

    override fun set(key: String, value: T) {
        map[key] = value
    }
}

private fun <T, U> JsTuple2<T, U>.toPair(): Pair<T, U> {
    return component1() to component2()
}
