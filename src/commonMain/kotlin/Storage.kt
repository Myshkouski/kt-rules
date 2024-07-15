interface Storage<T> {
    fun get(key: String): T?
    fun set(key: String, value: T)
    fun remove(key: String)
    fun contains(key: String): Boolean
    fun entries(): Array<Pair<String, T>>
}

internal expect fun <T> createStorage(): Storage<T>
