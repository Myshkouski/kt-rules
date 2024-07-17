package test

import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class CriterionTests {
    @Test
    fun booleanCriterion() = runTest {
        // val condition = TypedCriterion(
        //     operator = ReferentialEqualOperator<Boolean, Boolean>(),
        //     value = true,
        // )
        //
        // val actual = condition.match(true)
        //
        // assertEquals(true, actual)
    }

    @Test
    fun numberCriterion() = runTest {
        // val condition = DefaultTypedCriterion(
        //     operator = LessOperator<Number, Number>(),
        //     value = 2000,
        // )
        //
        // val actual = condition.match(1996)
        //
        // assertEquals(true, actual)
    }
}
