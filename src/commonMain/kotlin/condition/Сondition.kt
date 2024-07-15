package condition

import criterion.TypedCriterion
import fact.ValueProvider
import fact.provide

interface Condition {
    suspend fun evaluate(): Boolean
}

private class DefaultCondition<out T>(
    private val provider: ValueProvider<T>,
    private val criterion: TypedCriterion<T>,
): Condition {
    override suspend fun evaluate(): Boolean {
        return criterion.evaluate(provider.provide())
    }
}

fun <T> Condition(provider: ValueProvider<T>, criterion: TypedCriterion<T>): Condition {
    return DefaultCondition(provider, criterion)
}
