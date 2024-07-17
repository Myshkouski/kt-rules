package test

import io.github.myshkouski.kotlin.condition.MatchAllRules
import io.github.myshkouski.kotlin.condition.MatchAnyRules
import io.github.myshkouski.kotlin.criterion.Criterion
import io.github.myshkouski.kotlin.fact.Fact
import io.github.myshkouski.kotlin.operator.ContainsOperator
import io.github.myshkouski.kotlin.operator.EqualsOperator
import io.github.myshkouski.kotlin.operator.Operator
import io.github.myshkouski.kotlin.rule.Rule
import io.github.myshkouski.kotlin.rule.RuleContext
import io.github.myshkouski.kotlin.storage.Storage
import io.github.myshkouski.kotlin.storage.set
import io.github.myshkouski.kotlin.trace.LoggerTrace
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class RuleTests {
    private fun userFacts(id: Number, role: String): Storage<Fact> {
        val facts: Storage<Fact> = Storage()
        facts.set("user.id", id)
        facts.set("user.role", arrayOf(role))
        return facts
    }

    @Test
    fun matchAnyRules() = runTest {
        val operators: Storage<Operator> = Storage()
        operators.set("equals", EqualsOperator())
        operators.set("contains", ContainsOperator())

        val rule = MatchAnyRules(
            Rule(
                fact = "user.id",
                criterion = Criterion(
                    operator = "equals",
                    value = 1,
                ),
            ),
            Rule(
                fact = "user.role",
                criterion = Criterion(
                    operator = "contains",
                    value = "admin",
                ),
            ),
        )

        val testCases = arrayOf(
            userFacts(1, "admin") to true,
            userFacts(1, "subscriber") to true,
            userFacts(2, "admin") to true,
            userFacts(2, "subscriber") to false,
        )

        val trace = LoggerTrace()

        testCases.forEach { (facts, expected) ->
            val actual = rule.evaluate(RuleContext(facts, operators, trace))
            assertEquals(expected, actual)
        }
    }

    @Test
    fun matchAllRules() = runTest {
        val operators: Storage<Operator> = Storage()
        operators.set("equals", EqualsOperator())
        operators.set("contains", ContainsOperator())

        val rule = MatchAllRules(
            Rule(
                fact = "user.id",
                criterion = Criterion(
                    operator = "equals",
                    value = 1,
                ),
            ),
            Rule(
                fact = "user.role",
                criterion = Criterion(
                    operator = "contains",
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

        val trace = LoggerTrace()

        testCases.forEach { (facts, expected) ->
            val actual = rule.evaluate(RuleContext(facts, operators, trace))
            assertEquals(expected, actual)
        }
    }
}
