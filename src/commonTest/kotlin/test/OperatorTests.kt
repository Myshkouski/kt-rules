package test

import io.github.myshkouski.kotlin.operator.TypedGreaterOperator
import io.github.myshkouski.kotlin.operator.TypedGreaterOrEqualOperator
import io.github.myshkouski.kotlin.operator.TypedLessOperator
import io.github.myshkouski.kotlin.operator.TypedLessOrEqualOperator
import kotlin.test.Test
import kotlin.test.assertEquals

class OperatorTests {
    @Test
    fun less() {
        val operator = TypedLessOperator<Number, Int>()
        val testCases = arrayOf(
            10 to 10 to false,
            10.0 to 10 to false,
            9 to 10 to true,
            11 to 10 to false,
            11.1 to 10 to false,
            9.99999999 to 10 to true,
            10.0000001 to 10 to false,
        )

        testCases.forEach { (values, expected) ->
            val (value, operatorValue) = values
            assertEquals(expected, operator.match(value, operatorValue))
        }
    }

    @Test
    fun lessOrEqual() {
        val operator = TypedLessOrEqualOperator<Number, Int>()
        val testCases = arrayOf(
            10 to 10 to true,
            10.0 to 10 to true,
            9 to 10 to true,
            11 to 10 to false,
            11.1 to 10 to false,
            9.99999999 to 10 to true,
            10.0000001 to 10 to false,
        )

        testCases.forEach { (values, expected) ->
            val (value, operatorValue) = values
            assertEquals(expected, operator.match(value, operatorValue))
        }
    }

    @Test
    fun greater() {
        val operator = TypedGreaterOperator<Number, Int>()
        val testCases = arrayOf(
            10 to 10 to false,
            10.0 to 10 to false,
            9 to 10 to false,
            11 to 10 to true,
            11.1 to 10 to true,
            9.99999999 to 10 to false,
            10.0000001 to 10 to true,
        )

        testCases.forEach { (values, expected) ->
            val (value, operatorValue) = values
            assertEquals(expected, operator.match(value, operatorValue))
        }
    }

    @Test
    fun greaterOrEqual() {
        val operator = TypedGreaterOrEqualOperator<Number, Int>()
        val testCases = arrayOf(
            10 to 10 to true,
            10.0 to 10 to true,
            9 to 10 to false,
            11 to 10 to true,
            11.1 to 10 to true,
            9.99999999 to 10 to false,
            10.0000001 to 10 to true,
        )

        testCases.forEach { (values, expected) ->
            val (value, operatorValue) = values
            assertEquals(expected, operator.match(value, operatorValue))
        }
    }
}