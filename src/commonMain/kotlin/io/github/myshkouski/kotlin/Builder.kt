package io.github.myshkouski.kotlin

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@OptIn(ExperimentalJsExport::class)
@JsExport
fun interface Builder<T> {
    fun build(): T
}
