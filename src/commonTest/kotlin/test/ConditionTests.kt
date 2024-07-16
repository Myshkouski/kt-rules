package test

import io.github.myshkouski.kotlin.Storage
import io.github.myshkouski.kotlin.condition.MatchAllRules
import io.github.myshkouski.kotlin.condition.MatchAnyRules
import io.github.myshkouski.kotlin.criterion.DefaultTypedCriterion
import io.github.myshkouski.kotlin.fact.ValueProvider
import io.github.myshkouski.kotlin.operator.GreaterOrEqualOperator
import io.github.myshkouski.kotlin.operator.LessOperator
import io.github.myshkouski.kotlin.rule.Rule
import io.github.myshkouski.kotlin.rule.TypedRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class ConditionTests {
    @Test
    fun `all conditions`() = runTest {
        val rule: Rule = MatchAllRules(
            TypedRule(
                fact = "birthdate.year",
                criterion = DefaultTypedCriterion(
                    operator = LessOperator<Number, Number>(),
                    value = 2000,
                ),
            ),
            TypedRule(
                fact = "birthdate.year",
                criterion = DefaultTypedCriterion(
                    operator = GreaterOrEqualOperator<Number, Number>(),
                    value = 1900,
                ),
            ),
        )

        val testCases = arrayOf(
            Storage("birthdate.year" to ValueProvider(1996)) to true,
            Storage("birthdate.year" to ValueProvider(2000)) to false,
            Storage("birthdate.year" to ValueProvider(1888)) to false,
        )

        testCases.forEach { (facts, expected) ->
            val actual = rule.evaluate(facts, null)
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `any conditions`() = runTest {
        val rule = MatchAnyRules(
            MatchAllRules(
                TypedRule(
                    fact = "birthdate.year",
                    criterion = DefaultTypedCriterion(
                        operator = LessOperator<Number, Number>(),
                        value = 2000,
                    )
                ),
                TypedRule(
                    fact = "birthdate.year",
                    criterion = DefaultTypedCriterion(
                        operator = GreaterOrEqualOperator<Number, Number>(),
                        value = 1900,
                    )
                ),
            ),
            MatchAllRules(
                TypedRule(
                    fact = "birthdate.year",
                    criterion = DefaultTypedCriterion(
                        operator = LessOperator<Number, Number>(),
                        value = 1600,
                    )
                ),
                TypedRule(
                    fact = "birthdate.year",
                    criterion = DefaultTypedCriterion(
                        operator = GreaterOrEqualOperator<Number, Number>(),
                        value = 1500,
                    ),
                ),
            ),
        )

        val testCases = arrayOf(
            Storage("birthdate.year" to ValueProvider(1996)) to true,
            Storage("birthdate.year" to ValueProvider(2000)) to false,
            Storage("birthdate.year" to ValueProvider(1888)) to false,
            Storage("birthdate.year" to ValueProvider(1200)) to false,
            Storage("birthdate.year" to ValueProvider(1550)) to true,
        )

        testCases.forEach { (facts, expected) ->
            val actual = rule.evaluate(facts, null)
            assertEquals(expected, actual)
        }
    }
}