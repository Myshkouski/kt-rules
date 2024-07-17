package io.github.myshkouski.kotlin.rule

import io.github.myshkouski.kotlin.condition.Condition
import io.github.myshkouski.kotlin.condition.ConditionContext
import io.github.myshkouski.kotlin.condition.EvaluationContext
import io.github.myshkouski.kotlin.criterion.TypedCriterion
import io.github.myshkouski.kotlin.fact.Fact
import io.github.myshkouski.kotlin.fact.ValueProvider
import io.github.myshkouski.kotlin.operator.Operator
import io.github.myshkouski.kotlin.storage.Storage
import io.github.myshkouski.kotlin.trace.EvaluationTrace
import io.github.myshkouski.kotlin.trace.push

interface TypedRule<T> {
    suspend fun evaluate(context: RuleContext): Boolean
}

typealias Rule = TypedRule<*>

class RuleContext(
    val facts: Storage<out Fact>,
    override val operators: Storage<Operator>,
    override val trace: EvaluationTrace?,
): EvaluationContext

private class DefaultRule<T>(
    private val fact: String,
    private val condition: Condition<T>,
) : TypedRule<T> {
    override suspend fun evaluate(context: RuleContext): Boolean {
        context.trace?.push("rule", "Evaluating against fact '$fact'.")
        val fact = context.facts.get(fact)
        if (null == fact) {
            context.trace?.push("rule", "Value provider is null, so the result will be false..")
            return false
        }

        val conditionContext = ConditionContext(
            fact = fact as ValueProvider<T>,
            context = context,
        )
        val result = condition.evaluate(conditionContext)
        context.trace?.push("rule", "Evaluated result is $result.")
        return result
    }
}

fun <T> Rule(fact: String, criterion: TypedCriterion<T>): TypedRule<T> {
    return DefaultRule(
        fact = fact,
        condition = Condition(criterion),
    )
}

// fun <T> TypedRule(fact: String, criterion: CriterionSpec<T>): TypedRule<T> {
//     return DefaultRule(
//         fact = fact,
//         condition = Condition(criterion),
//     )
// }
