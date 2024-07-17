package io.github.myshkouski.kotlin.trace

private class DefaultExplainResult(
    override val result: Boolean,
    override val trace: EvaluationTrace
) : ExplainResult

// suspend fun <T> TypedRule<T>.explain(facts: Storage<Fact>): ExplainResult {
//     val trace = ListTrace()
//     val result = evaluate(facts, trace)
//     return DefaultExplainResult(result, trace)
// }
