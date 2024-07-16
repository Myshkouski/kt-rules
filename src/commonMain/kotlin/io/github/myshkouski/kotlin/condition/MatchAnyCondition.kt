package io.github.myshkouski.kotlin.condition

import io.github.myshkouski.kotlin.Storage
import io.github.myshkouski.kotlin.fact.ValueProvider
import io.github.myshkouski.kotlin.rule.TypedRule
import io.github.myshkouski.kotlin.trace.EvaluationTrace

class MatchAnyRules<T>(
    private val rules: Array<out TypedRule<T>>
): TypedRule<T> {
    constructor(condition: TypedRule<T>, vararg other: TypedRule<T>): this(arrayOf(condition) + other)
    override suspend fun <U> evaluate(facts: Storage<out ValueProvider<U>>, trace: EvaluationTrace?): Boolean {
        return null !== rules.find {
            it.evaluate(facts, trace)
        }
    }
}
