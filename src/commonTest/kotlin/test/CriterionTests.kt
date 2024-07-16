package test

import io.github.myshkouski.kotlin.criterion.DefaultTypedCriterion
import io.github.myshkouski.kotlin.operator.LessOperator
import io.github.myshkouski.kotlin.operator.ReferentialEqualOperator
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class CriterionTests {
    @Test
    fun `boolean criterion`() = runTest {
        val condition = DefaultTypedCriterion(
            operator = ReferentialEqualOperator<Boolean, Boolean>(),
            value = true,
        )

        val actual = condition.match(true)

        assertEquals(true, actual)
    }

    @Test
    fun `number criterion`() = runTest {
        val condition = DefaultTypedCriterion(
            operator = LessOperator<Number, Number>(),
            value = 2000,
        )

        val actual = condition.match(1996)

        assertEquals(true, actual)
    }
}
