package test

import operator.LessOperator
import operator.LessOrEqualOperator
import operator.GreaterOperator
import operator.GreaterOrEqualOperator
import kotlin.test.Test
import kotlin.test.assertEquals

class OperatorTests {
    @Test
    fun less() {
        val operator = LessOperator<Number, Int>()
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
    fun `less or equal`() {
        val operator = LessOrEqualOperator<Number, Int>()
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
    fun more() {
        val operator = GreaterOperator<Number, Int>()
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
    fun `more or equal`() {
        val operator = GreaterOrEqualOperator<Number, Int>()
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