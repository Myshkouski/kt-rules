package io.github.myshkouski.kotlin

internal class MapStorage<T> : Storage<T> {
    private val map = mutableMapOf<String, T>()

    override fun get(key: String): T? {
        return map.get(key)
    }

    override fun remove(key: String) {
        map.remove(key)
    }

    override fun contains(key: String): Boolean {
        return map.containsKey(key)
    }

    override fun set(key: String, value: T) {
        return map.set(key, value)
    }

    override fun entries(): Array<Pair<String, T>> {
        return map.entries.map(Map.Entry<String, T>::toPair).toTypedArray()
    }
}
