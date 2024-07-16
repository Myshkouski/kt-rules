package io.github.myshkouski.kotlin

internal actual fun <T> createStorage(): Storage<T> {
    return MapStorage()
}
