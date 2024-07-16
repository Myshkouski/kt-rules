package io.github.myshkouski.kotlin.trace

fun interface EvaluationTrace {
    fun push(event: Event)
}

fun EvaluationTrace.push(type: String, payload: Any? = null) {
    val event = DefaultEvent(type, payload)
    push(event)
}
