package io.github.myshkouski.kotlin.trace

class LoggerTrace : EvaluationTrace {
    override fun push(event: Event) {
        println("${event.type}: ${event.payload}")
    }
}
