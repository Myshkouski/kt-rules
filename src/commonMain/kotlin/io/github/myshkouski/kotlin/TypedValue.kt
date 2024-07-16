package io.github.myshkouski.kotlin

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@OptIn(ExperimentalJsExport::class)
@JsExport
sealed interface TypedValue<T> {
    val value: T
}
