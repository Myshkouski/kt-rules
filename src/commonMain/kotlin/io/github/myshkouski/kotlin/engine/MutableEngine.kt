package io.github.myshkouski.kotlin.engine

import kotlin.js.JsExport

@JsExport
interface MutableEngine: Engine, MutableEngineOperations
