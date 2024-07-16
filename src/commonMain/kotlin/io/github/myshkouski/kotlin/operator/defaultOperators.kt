package io.github.myshkouski.kotlin.operator

import io.github.myshkouski.kotlin.storage.Storage

internal val defaultOperators = Storage(
    "equal" to EqualOperator(),
    "notEqual" to EqualOperator<Any?, Any?>().inverse(),
    "in" to InOperator(Any::class),
    "notIn" to InOperator(Any::class).inverse(),

    // "contains" to ContainsOperator(),
    // "notContains" to ContainsOperator(),
    // "lessOrEqual" to LessOrEqualOperator(),
    // "greater" to GreaterOperator(),
    // "greaterOrEqual" to GreaterOrEqualOperator(),
)
