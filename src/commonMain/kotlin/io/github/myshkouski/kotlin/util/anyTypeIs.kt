package io.github.myshkouski.kotlin.util

internal inline fun <reified T> anyTypeIs(value: Any?, vararg other: Any?): Boolean {
    return null !== arrayOf(value, other).find {
        it is T
    }
}
