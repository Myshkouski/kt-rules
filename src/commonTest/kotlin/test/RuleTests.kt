package test

import io.github.myshkouski.kotlin.Storage
import io.github.myshkouski.kotlin.createStorage
import io.github.myshkouski.kotlin.criterion.TypedCriterion
import io.github.myshkouski.kotlin.fact.Fact
import io.github.myshkouski.kotlin.operator.ContainsOperator
import io.github.myshkouski.kotlin.operator.EqualOperator
import io.github.myshkouski.kotlin.operator.TypedOperator
import io.github.myshkouski.kotlin.rule.TypedRule
import io.github.myshkouski.kotlin.set
import io.github.myshkouski.kotlin.trace.LoggerTrace
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class RuleTests {
    private fun userFacts(id: Number, role: String): Storage<Fact> {
        val facts: Storage<Fact> = Storage()
        facts.set("user.id", id)
        facts.set("user.role", arrayOf(role))
        return facts
    }

    @Test
    fun `all rules`() = runTest {
        val operators: Storage<TypedOperator<*, Any?>> = createStorage()
        operators.set("equals", EqualOperator<Any?, Any?>())
        operators.set("contains", ContainsOperator())

        val rules = arrayOf(
            TypedRule(
                fact = "user.id",
                criterion = TypedCriterion(
                    operator = operators.get("equals")!!,
                    value = 1,
                ),
            ),
            TypedRule(
                fact = "user.role",
                criterion = TypedCriterion(
                    operator = operators.get("contains")!!,
                    value = "admin",
                ),
            ),
        )

        val testCases = arrayOf(
            userFacts(1, "admin") to true,
            userFacts(1, "subscriber") to false,
            userFacts(2, "admin") to false,
            userFacts(2, "subscriber") to false,
        )

        testCases.forEach { (facts, expected) ->
            val actual = rules.all {
                it.evaluate(facts, LoggerTrace())
                // val (result, trace) = it.explain()
                // println(trace)
                // result
            }
            assertEquals(expected, actual)
        }
    }
}
