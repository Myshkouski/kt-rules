package io.github.myshkouski.kotlin.operator

import io.github.myshkouski.kotlin.storage.Storage

internal val defaultOperators = Storage(
    "equal" to EqualsOperator(),
    "notEqual" to EqualsOperator<Any?, Any?>().inverse(),

    "in" to InOperator(Any::class),
    "notIn" to InOperator(Any::class).inverse(),

    "contains" to ContainsOperator(),
    "notContains" to ContainsOperator().inverse(),

    "less" to TypedLessOperator<Number, Number>().unsafeCastToGeneric(),
    "lessOrEqual" to TypedLessOrEqualOperator<Number, Number>().unsafeCastToGeneric(),
    "greater" to TypedGreaterOperator<Number, Number>().unsafeCastToGeneric(),
    "greaterOrEqual" to TypedGreaterOrEqualOperator<Number, Number>().unsafeCastToGeneric(),
)
