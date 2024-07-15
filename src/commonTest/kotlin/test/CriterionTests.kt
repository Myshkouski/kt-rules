package test

import condition.Condition
import condition.MatchAllCondition
import condition.MatchAnyCondition
import criterion.Criterion
import fact.ValueProvider
import kotlinx.coroutines.test.runTest
import operator.EqualOperator
import operator.GreaterOrEqualOperator
import operator.LessOperator
import kotlin.test.Test
import kotlin.test.assertEquals

class CriterionTests {
    @Test
    fun `boolean criterion`() = runTest {
        val condition = Criterion(
            operator = EqualOperator<Boolean, Boolean>(),
            value = true,
        )

        val actual = condition.evaluate(true)

        assertEquals(true, actual)
    }

    @Test
    fun `number criterion`() = runTest {
        val condition = Criterion(
            operator = LessOperator<Number, Number>(),
            value = 2000,
        )

        val actual = condition.evaluate(1996)

        assertEquals(true, actual)
    }

    @Test
    fun `all conditions`() = runTest {
        fun buildCondition(value: Number): Condition {
            val provider = ValueProvider(value)

            return MatchAllCondition(
                Condition(
                    provider = provider,
                    criterion = Criterion(
                        operator = LessOperator<Number, Number>(),
                        value = 2000,
                    ),
                ),
                Condition(
                    provider = provider,
                    criterion = Criterion(
                        operator = GreaterOrEqualOperator<Number, Number>(),
                        value = 1900,
                    ),
                ),
            )
        }

        val testCases = arrayOf(
            buildCondition(value = 1996) to true,
            buildCondition(value = 2000) to false,
            buildCondition(value = 1888) to false,
        )

        testCases.forEach { (condition, expected) ->
            val actual = condition.evaluate()
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `any conditions`() = runTest {
        fun buildCondition(value: Number): Condition {
            val provider = ValueProvider(value)

            return MatchAnyCondition(
                MatchAllCondition(
                    Condition(
                        provider = provider,
                        criterion = Criterion(
                            operator = LessOperator<Number, Number>(),
                            value = 2000,
                        )
                    ),
                    Condition(
                        provider = provider,
                        criterion = Criterion(
                            operator = GreaterOrEqualOperator<Number, Number>(),
                            value = 1900,
                        )
                    ),
                ),
                MatchAllCondition(
                    Condition(
                        provider = provider,
                        criterion = Criterion(
                            operator = LessOperator<Number, Number>(),
                            value = 1600,
                        )
                    ),
                    Condition(
                        provider = provider,
                        criterion = Criterion(
                            operator = GreaterOrEqualOperator<Number, Number>(),
                            value = 1500,
                        ),
                    ),
                ),
            )
        }

        val testCases = arrayOf(
            buildCondition(value = 1996) to true,
            buildCondition(value = 2000) to false,
            buildCondition(value = 1888) to false,
            buildCondition(value = 1200) to false,
            buildCondition(value = 1550) to true,
        )

        testCases.forEach { (condition, expected) ->
            val actual = condition.evaluate()
            assertEquals(expected, actual)
        }
    }
}
