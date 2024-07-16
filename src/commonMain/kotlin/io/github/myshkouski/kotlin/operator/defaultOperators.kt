package io.github.myshkouski.kotlin.operator

import io.github.myshkouski.kotlin.storage.Storage

internal val defaultOperators = Storage<Operator>(
    "equal" to EqualOperator<Any?, Any?>(),
    "notEqual" to NotEqualOperator<Any?, Any?>(),
    // "in" to InOperator<Any?, Any?>(),
    // "notIn" to NotInOperator<Any?, Any?>(),
    // "less" to LessOperator<Any?, Number?>(),
    // "lessOrEqual" to LessOrEqualOperator(),
    // "greater" to GreaterOperator(),
    // "greaterOrEqual" to GreaterOrEqualOperator(),
)
