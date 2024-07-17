package io.github.myshkouski.kotlin.condition

import io.github.myshkouski.kotlin.rule.RuleContext
import io.github.myshkouski.kotlin.rule.TypedRule

class MatchAllRules<T>(
    private val rules: Array<out TypedRule<T>>
): TypedRule<T> {
    constructor(condition: TypedRule<T>, vararg other: TypedRule<T>): this(arrayOf(condition) + other)

    override suspend fun evaluate(context: RuleContext): Boolean {
        return rules.all {
            it.evaluate(context)
        }
    }
}
