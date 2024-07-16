package io.github.myshkouski.kotlin.rule

import io.github.myshkouski.kotlin.storage.Storage
import io.github.myshkouski.kotlin.condition.Condition
import io.github.myshkouski.kotlin.criterion.TypedCriterion
import io.github.myshkouski.kotlin.fact.Fact
import io.github.myshkouski.kotlin.fact.StoredValueProvider
import io.github.myshkouski.kotlin.fact.ValueProvider
import io.github.myshkouski.kotlin.trace.EvaluationTrace
import io.github.myshkouski.kotlin.trace.push

interface TypedRule<T> {
    suspend fun <U> evaluate(facts: Storage<out ValueProvider<U>>, trace: EvaluationTrace?): Boolean
}

typealias Rule = TypedRule<*>

private class DefaultRule<T>(
    private val fact: String,
    private val condition: Condition<T>,
) : TypedRule<T> {
    override suspend fun <U> evaluate(facts: Storage<out ValueProvider<U>>, trace: EvaluationTrace?): Boolean {
        trace?.push("rule", "Evaluating against fact '$fact'")
        val fact = facts.get(fact)
        if (null == fact) {
            trace?.push("rule", "Value provider is null, so the result will be false..")
            return false
        }
        val result = condition.evaluate(fact as ValueProvider<T>, trace)
        trace?.push("rule", "Evaluated result is $result")
        return result
    }
}

fun <T> TypedRule(fact: String, criterion: TypedCriterion<T>): TypedRule<T> {
    return DefaultRule(
        fact = fact,
        condition = Condition(criterion),
    )
}

fun <T> TypedRule(fact: String, facts: Storage<Fact>, condition: (provider: ValueProvider<T>) -> Condition<T>): TypedRule<T> {
    val provider = StoredValueProvider<T>(fact, facts)
    return DefaultRule(fact, condition(provider))
}
