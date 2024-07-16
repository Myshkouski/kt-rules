package io.github.myshkouski.kotlin.trace

import io.github.myshkouski.kotlin.storage.Storage
import io.github.myshkouski.kotlin.fact.Fact
import io.github.myshkouski.kotlin.rule.TypedRule

private class DefaultExplainResult(
    override val result: Boolean,
    override val trace: EvaluationTrace
) : ExplainResult

suspend fun <T> TypedRule<T>.explain(facts: Storage<Fact>): ExplainResult {
    val trace = ListTrace()
    val result = evaluate(facts, trace)
    return DefaultExplainResult(result, trace)
}
