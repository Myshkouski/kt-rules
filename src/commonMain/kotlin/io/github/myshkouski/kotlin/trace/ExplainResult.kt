package io.github.myshkouski.kotlin.trace

interface ExplainResult {
    val result: Boolean
    val trace: EvaluationTrace

    operator fun component1() = result
    operator fun component2() = trace
}