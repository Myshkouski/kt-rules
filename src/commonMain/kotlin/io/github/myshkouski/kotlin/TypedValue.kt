package io.github.myshkouski.kotlin

sealed interface TypedValue<T> {
    val value: T
}