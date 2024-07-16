package io.github.myshkouski.kotlin.operator

internal actual fun compare(value: Number, other: Number): Int {
    return safeCompare(value, other)
}
