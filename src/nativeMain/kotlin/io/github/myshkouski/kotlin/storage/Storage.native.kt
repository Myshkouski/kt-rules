package io.github.myshkouski.kotlin.storage

internal actual fun <T> emptyStorage(): Storage<T> {
    return MapStorage()
}
